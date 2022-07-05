package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.ArrayReference;
import vjvm.runtime.heap.Reference;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class XALOAD<T> extends Instruction {

    private final BiConsumer<OperandStack, T> pushFunc;
    private final String name;

    public static XALOAD<Integer> IALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushInt, "iaload");
    }

    public static XALOAD<Long> LALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushLong, "laload");
    }

    public static XALOAD<Float> FALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushFloat, "faload");
    }

    public static XALOAD<Double> DALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushDouble, "daload");
    }

    public static XALOAD<Reference> AALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushReference, "aaload");
    }

    public static XALOAD<Integer> BALOAD(ProgramCounter pc, MethodInfo method) {  // Boolean stored as Integer
        return new XALOAD<>(OperandStack::pushInt, "baload");
    }

    public static XALOAD<Character> CALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushChar, "caload");
    }

    public static XALOAD<Short> SALOAD(ProgramCounter pc, MethodInfo method) {
        return new XALOAD<>(OperandStack::pushShort, "saload");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int index = stack.popInt();
        ArrayReference ref = (ArrayReference) stack.popReference();

        pushFunc.accept(stack, (T) ref.get(index));
    }

    @Override
    public String toString() {
        return name;
    }
}
