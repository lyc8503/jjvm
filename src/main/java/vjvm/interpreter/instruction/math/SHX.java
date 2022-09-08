package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class SHX<T> extends Instruction {
    /**
     * 左右移指令
     */
    private final Function<OperandStack, T> popFunc;
    private final BiFunction<T, Integer, T> calcFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static SHX<Integer> ISHL(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popInt, (s1, s2) -> s1 << s2, OperandStack::pushInt, "ishl");
    }

    public static SHX<Long> LSHL(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popLong, (s1, s2) -> s1 << s2, OperandStack::pushLong, "lshl");
    }

    public static SHX<Integer> ISHR(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popInt, (s1, s2) -> s1 >> s2, OperandStack::pushInt, "ishr");
    }

    public static SHX<Long> LSHR(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popLong, (s1, s2) -> s1 >> s2, OperandStack::pushLong, "lshr");
    }

    public static SHX<Integer> IUSHR(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popInt, (s1, s2) -> s1 >>> s2, OperandStack::pushInt, "iushr");
    }

    public static SHX<Long> LUSHR(ProgramCounter pc, MethodInfo method) {
        return new SHX<>(OperandStack::popLong, (s1, s2) -> s1 >>> s2, OperandStack::pushLong, "lushr");
    }


    @Override
    public void run(JThread thread) {

        var stack = thread.top().stack();
        int value2 = stack.popInt();
        T value1 = popFunc.apply(stack);

        T result = calcFunc.apply(value1, value2);
        pushFunc.accept(stack, result);
    }

    @Override
    public String toString() {
        return name;
    }
}
