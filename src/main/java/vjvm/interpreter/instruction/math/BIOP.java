package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BIOP<T> extends Instruction {
    /**
     * 二元运算
     */

    private final Function<OperandStack, T> popFunc;
    private final BiFunction<T, T, T> calcFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static BIOP<Integer> IADD(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, Integer::sum, OperandStack::pushInt, "iadd");
    }

    public static BIOP<Long> LADD(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, Long::sum, OperandStack::pushLong, "ladd");
    }

    public static BIOP<Float> FADD(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popFloat, Float::sum, OperandStack::pushFloat, "fadd");
    }

    public static BIOP<Double> DADD(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popDouble, Double::sum, OperandStack::pushDouble, "dadd");
    }

    public static BIOP<Integer> ISUB(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 - s2, OperandStack::pushInt, "isub");
    }

    public static BIOP<Long> LSUB(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 - s2, OperandStack::pushLong, "lsub");
    }

    public static BIOP<Float> FSUB(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popFloat, (s1, s2) -> s1 - s2, OperandStack::pushFloat, "fsub");
    }

    public static BIOP<Double> DSUB(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popDouble, (s1, s2) -> s1 - s2, OperandStack::pushDouble, "dsub");
    }

    public static BIOP<Integer> IMUL(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 * s2, OperandStack::pushInt, "imul");
    }

    public static BIOP<Long> LMUL(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 * s2, OperandStack::pushLong, "lmul");
    }

    public static BIOP<Float> FMUL(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popFloat, (s1, s2) -> s1 * s2, OperandStack::pushFloat, "fmul");
    }

    public static BIOP<Double> DMUL(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popDouble, (s1, s2) -> s1 * s2, OperandStack::pushDouble, "dmul");
    }

    public static BIOP<Integer> IDIV(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 / s2, OperandStack::pushInt, "idiv");
    }

    public static BIOP<Long> LDIV(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 / s2, OperandStack::pushLong, "ldiv");
    }

    public static BIOP<Float> FDIV(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popFloat, (s1, s2) -> s1 / s2, OperandStack::pushFloat, "fdiv");
    }

    public static BIOP<Double> DDIV(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popDouble, (s1, s2) -> s1 / s2, OperandStack::pushDouble, "ddiv");
    }

    public static BIOP<Integer> IAND(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 & s2, OperandStack::pushInt, "iand");
    }

    public static BIOP<Long> LAND(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 & s2, OperandStack::pushLong, "land");
    }

    public static BIOP<Integer> IOR(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 | s2, OperandStack::pushInt, "ior");
    }

    public static BIOP<Long> LOR(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 | s2, OperandStack::pushLong, "lor");
    }

    public static BIOP<Integer> IXOR(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 ^ s2, OperandStack::pushInt, "ixor");
    }

    public static BIOP<Long> LXOR(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 ^ s2, OperandStack::pushLong, "lxor");
    }

    public static BIOP<Integer> IREM(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popInt, (s1, s2) -> s1 % s2, OperandStack::pushInt, "irem");
    }

    public static BIOP<Long> LREM(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popLong, (s1, s2) -> s1 % s2, OperandStack::pushLong, "lrem");
    }

    public static BIOP<Float> FREM(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popFloat, (s1, s2) -> s1 % s2, OperandStack::pushFloat, "frem");
    }

    public static BIOP<Double> DREM(ProgramCounter pc, MethodInfo method) {
        return new BIOP<>(OperandStack::popDouble, (s1, s2) -> s1 % s2, OperandStack::pushDouble, "drem");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value2 = popFunc.apply(stack);
        T value1 = popFunc.apply(stack);

        T result = calcFunc.apply(value1, value2);
        pushFunc.accept(stack, result);
    }

    @Override
    public String toString() {
        return name;
    }
}
