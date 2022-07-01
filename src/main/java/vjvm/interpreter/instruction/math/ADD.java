package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ADD<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiFunction<T, T, T> calcFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static ADD<Integer> IADD(ProgramCounter pc, MethodInfo method) {
        return new ADD<>(OperandStack::popInt, OperandStack::pushInt, "iadd");
    }

    public static ADD<Long> LADD(ProgramCounter pc, MethodInfo method) {
        return new ADD<>(OperandStack::popLong, OperandStack::pushLong, "ladd");
    }

    public static ADD<Float> FADD(ProgramCounter pc, MethodInfo method) {
        return new ADD<>(OperandStack::popFloat, OperandStack::pushFloat, "fadd");
    }

    public static ADD<Double> DADD(ProgramCounter pc, MethodInfo method) {
        return new ADD<>(OperandStack::popDouble, OperandStack::pushDouble, "dadd");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Double) (value1.doubleValue() + value2.doubleValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
