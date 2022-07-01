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
import java.util.function.Function;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DIV<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static DIV<Integer> IDIV(ProgramCounter pc, MethodInfo method) {
        return new DIV<>(OperandStack::popInt, OperandStack::pushInt, "idiv");
    }

    public static DIV<Long> LDIV(ProgramCounter pc, MethodInfo method) {
        return new DIV<>(OperandStack::popLong, OperandStack::pushLong, "ldiv");
    }

    public static DIV<Float> FDIV(ProgramCounter pc, MethodInfo method) {
        return new DIV<>(OperandStack::popFloat, OperandStack::pushFloat, "fdiv");
    }

    public static DIV<Double> DDIV(ProgramCounter pc, MethodInfo method) {
        return new DIV<>(OperandStack::popDouble, OperandStack::pushDouble, "ddiv");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Double) (value1.doubleValue() / value2.doubleValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
