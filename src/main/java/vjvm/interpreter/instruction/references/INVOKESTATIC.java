package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.UnimplementedError;

public class INVOKESTATIC extends Instruction {
  private final JClass jClass;
  private final MethodInfo method;

  public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
    throw new UnimplementedError("TODO: decode invokestatic");
  }

  @Override
  public void run(JThread thread) {
    throw new UnimplementedError("TODO: run invokestatic");
    // thread.context().interpreter().invoke(method, thread, args);
  }

  @Override
  public String toString() {
    return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
  }
}
