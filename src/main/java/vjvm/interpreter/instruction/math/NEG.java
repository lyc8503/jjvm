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
import java.util.function.Function;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NEG<T extends Number> extends Instruction {

    /**
     * NEG 指令
     */

    private final Function<OperandStack, T> popFunc;

    private final Function<T, T> negFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static NEG<Integer> INEG(ProgramCounter pc, MethodInfo method) {
        return new NEG<>(OperandStack::popInt, s -> -s, OperandStack::pushInt, "ineg");
    }

    public static NEG<Long> LNEG(ProgramCounter pc, MethodInfo method) {
        return new NEG<>(OperandStack::popLong, s -> -s, OperandStack::pushLong, "lneg");
    }

    public static NEG<Float> FNEG(ProgramCounter pc, MethodInfo method) {
        return new NEG<>(OperandStack::popFloat, s -> -s, OperandStack::pushFloat, "fneg");
    }

    public static NEG<Double> DNEG(ProgramCounter pc, MethodInfo method) {
        return new NEG<>(OperandStack::popDouble, s -> -s, OperandStack::pushDouble, "dneg");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        pushFunc.accept(stack, negFunc.apply(value1));
    }

    @Override
    public String toString() {
        return name;
    }
}
