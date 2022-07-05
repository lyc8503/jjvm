package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.Reference;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XLOAD_Y<T> extends Instruction {

    private final int index;
    private final BiConsumer<OperandStack, T> pushFunc;
    private String name;

    public static XLOAD_Y<Integer> ILOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(pc.ubyte(), OperandStack::pushInt, "iload");
    }

    public static XLOAD_Y<Integer> ILOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(0, OperandStack::pushInt, "iload_0");
    }

    public static XLOAD_Y<Integer> ILOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(1, OperandStack::pushInt, "iload_1");
    }

    public static XLOAD_Y<Integer> ILOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(2, OperandStack::pushInt, "iload_2");
    }

    public static XLOAD_Y<Integer> ILOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(3, OperandStack::pushInt, "iload_3");
    }

    public static XLOAD_Y<Long> LLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(pc.ubyte(), OperandStack::pushLong, "lload");
    }

    public static XLOAD_Y<Long> LLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(0, OperandStack::pushLong, "lload_0");
    }

    public static XLOAD_Y<Long> LLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(1, OperandStack::pushLong, "lload_1");
    }

    public static XLOAD_Y<Long> LLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(2, OperandStack::pushLong, "lload_2");
    }

    public static XLOAD_Y<Long> LLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(3, OperandStack::pushLong, "lload_3");
    }

    public static XLOAD_Y<Float> FLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(pc.ubyte(), OperandStack::pushFloat, "fload");
    }

    public static XLOAD_Y<Float> FLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(0, OperandStack::pushFloat, "fload_0");
    }

    public static XLOAD_Y<Float> FLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(1, OperandStack::pushFloat, "fload_1");
    }

    public static XLOAD_Y<Float> FLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(2, OperandStack::pushFloat, "fload_2");
    }

    public static XLOAD_Y<Float> FLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(3, OperandStack::pushFloat, "fload_3");
    }

    public static XLOAD_Y<Double> DLOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(pc.ubyte(), OperandStack::pushDouble, "dload");
    }

    public static XLOAD_Y<Double> DLOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(0, OperandStack::pushDouble, "dload_0");
    }

    public static XLOAD_Y<Double> DLOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(1, OperandStack::pushDouble, "dload_1");
    }

    public static XLOAD_Y<Double> DLOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(2, OperandStack::pushDouble, "dload_2");
    }

    public static XLOAD_Y<Double> DLOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(3, OperandStack::pushDouble, "dload_3");
    }

    public static XLOAD_Y<Reference> ALOAD(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(pc.ubyte(), OperandStack::pushReference, "aload");
    }

    public static XLOAD_Y<Reference> ALOAD_0(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(0, OperandStack::pushReference, "aload_0");
    }

    public static XLOAD_Y<Reference> ALOAD_1(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(1, OperandStack::pushReference, "aload_1");
    }

    public static XLOAD_Y<Reference> ALOAD_2(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(2, OperandStack::pushReference, "aload_2");
    }

    public static XLOAD_Y<Reference> ALOAD_3(ProgramCounter pc, MethodInfo method) {
        return new XLOAD_Y<>(3, OperandStack::pushReference, "aload_3");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var localVars = thread.top().vars();

        T value = (T) localVars.value(index).get();
        pushFunc.accept(stack, value);
    }

    @Override
    public String toString() {
        return name + " " + index;
    }
}
