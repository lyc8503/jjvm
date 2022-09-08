package vjvm.interpreter.instruction.stores;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.reference.ArrayReference;
import vjvm.runtime.reference.Reference;

import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XASTORE<T> extends Instruction {

    private final Function<OperandStack, T> popFunc;

    private final String name;

    public static XASTORE<Integer> IASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popInt, "iastore");
    }

    public static XASTORE<Long> LASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popLong, "lastore");
    }

    public static XASTORE<Float> FASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popFloat, "fastore");
    }

    public static XASTORE<Double> DASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popDouble, "dastore");
    }

    public static XASTORE<Reference> AASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popReference, "aastore");
    }

    public static XASTORE<Integer> BASTORE(ProgramCounter pc, MethodInfo method) {  // Boolean stored as Integer
        return new XASTORE<>(OperandStack::popInt, "bastore");
    }

    public static XASTORE<Character> CASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popChar, "castore");
    }

    public static XASTORE<Short> SASTORE(ProgramCounter pc, MethodInfo method) {
        return new XASTORE<>(OperandStack::popShort, "sastore");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();

        T value = popFunc.apply(stack);
        int index = stack.popInt();
        ArrayReference arrayRef = (ArrayReference) stack.popReference();

        arrayRef.put(index, value);
    }

    @Override
    public String toString() {
        return name;
    }
}
