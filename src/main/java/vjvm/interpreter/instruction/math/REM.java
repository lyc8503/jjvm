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
public class REM<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static REM<Integer> IREM(ProgramCounter pc, MethodInfo method) {
        return new REM<>(OperandStack::popInt, OperandStack::pushInt, "irem");
    }

    public static REM<Long> LREM(ProgramCounter pc, MethodInfo method) {
        return new REM<>(OperandStack::popLong, OperandStack::pushLong, "lrem");
    }

    public static REM<Float> FREM(ProgramCounter pc, MethodInfo method) {
        return new REM<>(OperandStack::popFloat, OperandStack::pushFloat, "frem");
    }

    public static REM<Double> DREM(ProgramCounter pc, MethodInfo method) {
        return new REM<>(OperandStack::popDouble, OperandStack::pushDouble, "drem");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Double) (value1.doubleValue() % value2.doubleValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
