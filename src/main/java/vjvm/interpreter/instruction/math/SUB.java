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
public class SUB<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static SUB<Integer> ISUB(ProgramCounter pc, MethodInfo method) {
        return new SUB<>(OperandStack::popInt, OperandStack::pushInt, "isub");
    }

    public static SUB<Long> LSUB(ProgramCounter pc, MethodInfo method) {
        return new SUB<>(OperandStack::popLong, OperandStack::pushLong, "lsub");
    }

    public static SUB<Float> FSUB(ProgramCounter pc, MethodInfo method) {
        return new SUB<>(OperandStack::popFloat, OperandStack::pushFloat, "fsub");
    }

    public static SUB<Double> DSUB(ProgramCounter pc, MethodInfo method) {
        return new SUB<>(OperandStack::popDouble, OperandStack::pushDouble, "dsub");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Double) (value1.doubleValue() - value2.doubleValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
