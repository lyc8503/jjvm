package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.reference.Reference;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RETURN<T> extends Instruction {
    private final Function<OperandStack, T> popFunc;
    private final Function<T, T> convertFunc;
    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static RETURN<Void> RETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(s -> null, Function.identity(), null, "return");
    }

    public static RETURN<Integer> IRETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(OperandStack::popInt, Function.identity(), OperandStack::pushInt, "ireturn");
    }

    public static RETURN<Long> LRETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(OperandStack::popLong, Function.identity(), OperandStack::pushLong, "lreturn");
    }

    public static RETURN<Float> FRETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(OperandStack::popFloat, Function.identity(), OperandStack::pushFloat, "freturn");
    }

    public static RETURN<Double> DRETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(OperandStack::popDouble, Function.identity(), OperandStack::pushDouble, "dreturn");
    }

    public static RETURN<Reference> ARETURN(ProgramCounter pc, MethodInfo method) {
        return new RETURN<>(OperandStack::popReference, Function.identity(), OperandStack::pushReference, "areturn");
    }

    @Override
    public void run(JThread thread) {
        var returnValue = popFunc.apply(thread.top().stack());
        thread.pop();

        if (pushFunc != null) {
            pushFunc.accept(thread.top().stack(), convertFunc.apply(returnValue));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
