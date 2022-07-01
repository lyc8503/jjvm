package vjvm.interpreter.instruction.conversions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y<X, Y> extends Instruction {

    private final Function<OperandStack, X> popFunc;
    private final Function<X, Y> convertFunc;
    private final BiConsumer<OperandStack, Y> pushFunc;
    private final String name;

    public static X2Y<Integer, Long> I2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (long) s, OperandStack::pushLong, "i2l");
    }

    public static X2Y<Integer, Float> I2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (float) s, OperandStack::pushFloat, "i2f");
    }

    public static X2Y<Integer, Double> I2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (double) s, OperandStack::pushDouble, "i2d");
    }

    public static X2Y<Long, Integer> L2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, s -> (int) (long) s, OperandStack::pushInt, "l2i");
    }

    public static X2Y<Long, Float> L2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, s -> (float) s, OperandStack::pushFloat, "l2f");
    }

    public static X2Y<Long, Double> L2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, s -> (double) s, OperandStack::pushDouble, "l2d");
    }

    public static X2Y<Float, Integer> F2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, s -> (int) (float) s, OperandStack::pushInt, "f2i");
    }

    public static X2Y<Float, Long> F2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, s -> (long) (float) s, OperandStack::pushLong, "f2l");
    }

    public static X2Y<Float, Double> F2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, s -> (double) (float) s, OperandStack::pushDouble, "f2d");
    }

    public static X2Y<Double, Integer> D2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, s -> (int) (double) s, OperandStack::pushInt, "d2i");
    }

    public static X2Y<Double, Long> D2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, s -> (long) (double) s, OperandStack::pushLong, "d2l");
    }

    public static X2Y<Double, Float> D2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, s -> (float) (double) s, OperandStack::pushFloat, "d2f");
    }

    public static X2Y<Integer, Byte> I2B(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (byte) (int) s, OperandStack::pushByte, "i2b");
    }

    public static X2Y<Integer, Character> I2C(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (char) (int) s, OperandStack::pushChar, "i2c");
    }

    public static X2Y<Integer, Short> I2S(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, s -> (short) (int) s, OperandStack::pushShort, "i2s");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        X value1 = popFunc.apply(stack);
        pushFunc.accept(stack, convertFunc.apply(value1));
    }

    @Override
    public String toString() {
        return name;
    }
}
