package vjvm.interpreter;

import lombok.Getter;
import lombok.var;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import vjvm.classfiledefs.Opcodes;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static vjvm.classfiledefs.Descriptors.*;

public class JInterpreter {
  // (ClassName, MethodName, MethodDescriptor) -> HackFunction
  private static final HashMap<Triple<String, String, String>, BiFunction<JThread, Slots, Object>> nativeTable = new HashMap<>();

  @Getter
  private Status status = Status.CONTINUE;
  private long steps;

  private final ArrayList<Triple<MethodInfo, Integer, byte[]>> breakpoints = new ArrayList<>();
  private Triple<MethodInfo, Integer, byte[]> currentBreakpoint;

  /**
   * Invoke a method when there is no frames in a thread.
   *
   * @param method the method to call
   * @param thread the thread to run
   * @param args   the supplied arguments, index begins at 0
   */
  public void invoke(MethodInfo method, JThread thread, Slots args) {
    var frame = new JFrame(method, args);
    thread.push(frame);

    if (method.native_()) {
      runNativeMethod(thread);
    } else {
      run(thread);
    }
  }

  public void step(long steps) {
    assert steps >= 0;

    status = Status.STEP;
    this.steps = steps;
  }

  public void continue_() {
    status = Status.CONTINUE;
  }

  public void break_() {
    status = Status.BREAK;
  }

  public void setBreakpoint(MethodInfo method, int offset) {
    var code = method.code().code();
    var opcode = code[offset];

    if (opcode == Opcodes.OPC_breakpoint) {
      return;
    }

    var pc = new ProgramCounter(code);
    pc.position(offset);
    Instruction.decode(pc, method);
    var end = pc.position();

    breakpoints.add(Triple.of(method, offset, Arrays.copyOfRange(code, offset, end)));
    Arrays.fill(code, offset, end, Opcodes.OPC_breakpoint);
  }

  public void removeBreakpoint(int index) {
    disableBreakpoint(breakpoints.get(index));
    breakpoints.remove(index);
  }

  public List<Pair<MethodInfo, Integer>> breakpoints() {
    return breakpoints.stream().map(t -> Pair.of(t.getLeft(), t.getMiddle())).collect(Collectors.toList());
  }

  private void disableBreakpoint(Triple<MethodInfo, Integer, byte[]> bp) {
    var code = bp.getLeft().code().code();
    var instr = bp.getRight();
    System.arraycopy(instr, 0, code, bp.getMiddle(), instr.length);
  }

  private void findCurrentBreakpoint(JThread thread) {
    var method = thread.top().method();
    var offset = thread.pc().position();

    var bp = breakpoints.stream().filter(t -> t.getLeft().equals(method) && t.getMiddle().equals(offset)).findFirst();

    if (!bp.isPresent()) {
      throw new Error("no breakpoint found");
    }

    currentBreakpoint = bp.get();
  }

  private void run(JThread thread) {
    var frame = thread.top();
    var monitor = thread.context().monitor();

    while (thread.top() == frame) {
      if (status == Status.STEP && steps == 0) {
        monitor.enter(thread);
      }

      var op = Instruction.decode(thread.pc(), frame.method());
      steps--;
      op.run(thread);

      if (currentBreakpoint != null) {
        disableBreakpoint(currentBreakpoint);
        currentBreakpoint = null;
      }

      if (status == Status.BREAK) {
        findCurrentBreakpoint(thread);
        disableBreakpoint(currentBreakpoint);
        monitor.enter(thread);
      }
    }
  }

  private void runNativeMethod(JThread thread) {
    var frame = thread.top();
    var method = frame.method();
    assert method.native_();

    var key = Triple.of(method.jClass().name(), method.name(), method.descriptor());
    var impl = nativeTable.get(key);
    if (impl == null) {
      throw new Error(String.format("Unimplemented native method: %s", key));
    }

    var ret = impl.apply(thread, frame.vars());
    thread.pop();
    var s = thread.top().stack();

    switch (method.descriptor().charAt(method.descriptor().indexOf(')') + 1)) {
    case 'V':
      break;
    case DESC_int:
      s.pushInt((Integer) ret);
      break;
    default:
      throw new Error("Invalid return type");
    }
  }

  public static enum Status {
    CONTINUE, STEP, BREAK,
  }

  static {
  }

}
