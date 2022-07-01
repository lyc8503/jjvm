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
    private final BiConsumer<OperandStack, Y> pushFunc;
    private final String name;

    public static X2Y<Integer, Long> I2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushLong, "i2l");
    }

    public static X2Y<Integer, Float> I2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushFloat, "i2f");
    }

    public static X2Y<Integer, Double> I2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushDouble, "i2d");
    }

    public static X2Y<Long, Integer> L2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, OperandStack::pushInt, "l2i");
    }

    public static X2Y<Long, Float> L2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, OperandStack::pushFloat, "l2f");
    }

    public static X2Y<Long, Double> L2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popLong, OperandStack::pushDouble, "l2d");
    }

    public static X2Y<Float, Integer> F2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, OperandStack::pushInt, "f2i");
    }

    public static X2Y<Float, Long> F2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, OperandStack::pushLong, "f2l");
    }

    public static X2Y<Float, Double> F2D(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popFloat, OperandStack::pushDouble, "f2d");
    }

    public static X2Y<Double, Integer> D2I(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, OperandStack::pushInt, "d2i");
    }

    public static X2Y<Double, Long> D2L(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, OperandStack::pushLong, "d2l");
    }

    public static X2Y<Double, Float> D2F(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popDouble, OperandStack::pushFloat, "d2f");
    }

    public static X2Y<Integer, Byte> I2B(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushByte, "i2b");
    }

    public static X2Y<Integer, Character> I2C(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushChar, "i2c");
    }

    public static X2Y<Integer, Short> I2S(ProgramCounter pc, MethodInfo method) {
        return new X2Y<>(OperandStack::popInt, OperandStack::pushShort, "i2s");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        X value1 = popFunc.apply(stack);
        pushFunc.accept(stack, (Y) value1);
    }

    @Override
    public String toString() {
        return null;
    }
}
