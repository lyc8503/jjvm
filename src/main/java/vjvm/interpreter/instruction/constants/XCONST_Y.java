package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCONST_Y<T> extends Instruction {
    private final T value;
    private final BiConsumer<OperandStack, T> pushFunc;
    private String name;

    public static final XCONST_Y<Integer> ICONST_M1(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(-1, OperandStack::pushInt, "iconst_m1");
    }

    public static final XCONST_Y<Integer> ICONST_0(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(0, OperandStack::pushInt, "iconst_0");
    }

    public static final XCONST_Y<Integer> ICONST_1(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(1, OperandStack::pushInt, "iconst_1");
    }

    public static final XCONST_Y<Integer> ICONST_2(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(2, OperandStack::pushInt, "iconst_2");
    }

    public static final XCONST_Y<Integer> ICONST_3(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(3, OperandStack::pushInt, "iconst_3");
    }

    public static final XCONST_Y<Integer> ICONST_4(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(4, OperandStack::pushInt, "iconst_4");
    }

    public static final XCONST_Y<Integer> ICONST_5(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(5, OperandStack::pushInt, "iconst_5");
    }

    public static final XCONST_Y<Long> LCONST_0(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(0L, OperandStack::pushLong, "lconst_0");
    }

    public static final XCONST_Y<Long> LCONST_1(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(1L, OperandStack::pushLong, "lconst_1");
    }
    public static final XCONST_Y<Float> FCONST_0(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(0.0f, OperandStack::pushFloat, "lconst_0");
    }
    public static final XCONST_Y<Float> FCONST_1(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(1.0f, OperandStack::pushFloat, "fconst_1");
    }
    public static final XCONST_Y<Float> FCONST_2(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(2.0f, OperandStack::pushFloat, "fconst_2");
    }
    public static final XCONST_Y<Double> DCONST_0(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(0.0d, OperandStack::pushDouble, "dconst_0");
    }
    public static final XCONST_Y<Double> DCONST_1(ProgramCounter pc, MethodInfo method) {
        return new XCONST_Y<>(1.0d, OperandStack::pushDouble, "dconst_1");
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
