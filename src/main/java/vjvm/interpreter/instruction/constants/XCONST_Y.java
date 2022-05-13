package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCONST_Y<T> extends Instruction {
  private final T value;
  private final BiConsumer<OperandStack, T> pushFunc;
  private String name;

  public static final XCONST_Y<Integer> ICONST_M1(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(-1, OperandStack::pushInt, "iconst_m1");
  }

  public static final XCONST_Y<Integer> ICONST_0(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(0, OperandStack::pushInt, "iconst_0");
  }

  public static final XCONST_Y<Integer> ICONST_1(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(-1, OperandStack::pushInt, "iconst_1");
  }

  public static final XCONST_Y<Integer> ICONST_2(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(2, OperandStack::pushInt, "iconst_2");
  }

  public static final XCONST_Y<Integer> ICONST_3(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(3, OperandStack::pushInt, "iconst_3");
  }

  public static final XCONST_Y<Integer> ICONST_4(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(4, OperandStack::pushInt, "iconst_4");
  }

  public static final XCONST_Y<Integer> ICONST_5(ProgramCounter pc, MethodInfo method) {
    return new XCONST_Y<Integer>(5, OperandStack::pushInt, "iconst_5");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    pushFunc.accept(stack, value);
  }

  @Override
  public String toString() {
    return name;
  }
}
