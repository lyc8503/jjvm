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
public class MUL<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static MUL<Integer> IMUL(ProgramCounter pc, MethodInfo method) {
        return new MUL<>(OperandStack::popInt, OperandStack::pushInt, "imul");
    }

    public static MUL<Long> LMUL(ProgramCounter pc, MethodInfo method) {
        return new MUL<>(OperandStack::popLong, OperandStack::pushLong, "lmul");
    }

    public static MUL<Float> FMUL(ProgramCounter pc, MethodInfo method) {
        return new MUL<>(OperandStack::popFloat, OperandStack::pushFloat, "fmul");
    }

    public static MUL<Double> DMUL(ProgramCounter pc, MethodInfo method) {
        return new MUL<>(OperandStack::popDouble, OperandStack::pushDouble, "dmul");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Double) (value1.doubleValue() * value2.doubleValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
