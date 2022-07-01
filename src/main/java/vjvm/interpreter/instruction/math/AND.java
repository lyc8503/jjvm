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
public class AND<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static AND<Integer> IAND(ProgramCounter pc, MethodInfo method) {
        return new AND<>(OperandStack::popInt, OperandStack::pushInt, "iand");
    }

    public static AND<Long> LAND(ProgramCounter pc, MethodInfo method) {
        return new AND<>(OperandStack::popLong, OperandStack::pushLong, "land");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value1 = popFunc.apply(stack);
        T value2 = popFunc.apply(stack);
        pushFunc.accept(stack, (T) (Long) (value1.longValue() & value2.longValue()));
    }

    @Override
    public String toString() {
        return name;
    }
}
