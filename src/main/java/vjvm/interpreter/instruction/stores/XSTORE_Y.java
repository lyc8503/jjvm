package vjvm.interpreter.instruction.stores;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XSTORE_Y<T> extends Instruction {

    private final int index;
    private final Function<OperandStack, T> popFunc;
    private String name;

    public static XSTORE_Y<Integer> ISTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(pc.ubyte(), OperandStack::popInt, "istore");
    }

    public static XSTORE_Y<Integer> ISTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(0, OperandStack::popInt, "istore_0");
    }

    public static XSTORE_Y<Integer> ISTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(1, OperandStack::popInt, "istore_1");
    }

    public static XSTORE_Y<Integer> ISTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(2, OperandStack::popInt, "istore_2");
    }

    public static XSTORE_Y<Integer> ISTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(3, OperandStack::popInt, "istore_3");
    }

    public static XSTORE_Y<Long> LSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(pc.ubyte(), OperandStack::popLong, "lstore");
    }

    public static XSTORE_Y<Long> LSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(0, OperandStack::popLong, "lstore_0");
    }

    public static XSTORE_Y<Long> LSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(1, OperandStack::popLong, "lstore_1");
    }

    public static XSTORE_Y<Long> LSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(2, OperandStack::popLong, "lstore_2");
    }

    public static XSTORE_Y<Long> LSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(3, OperandStack::popLong, "lstore_3");
    }

    public static XSTORE_Y<Float> FSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(pc.ubyte(), OperandStack::popFloat, "fstore");
    }

    public static XSTORE_Y<Float> FSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(0, OperandStack::popFloat, "fstore_0");
    }

    public static XSTORE_Y<Float> FSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(1, OperandStack::popFloat, "fstore_1");
    }

    public static XSTORE_Y<Float> FSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(2, OperandStack::popFloat, "fstore_2");
    }

    public static XSTORE_Y<Float> FSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(3, OperandStack::popFloat, "fstore_3");
    }

    public static XSTORE_Y<Double> DSTORE(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(pc.ubyte(), OperandStack::popDouble, "dstore");
    }

    public static XSTORE_Y<Double> DSTORE_0(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(0, OperandStack::popDouble, "dstore_0");
    }

    public static XSTORE_Y<Double> DSTORE_1(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(1, OperandStack::popDouble, "dstore_1");
    }

    public static XSTORE_Y<Double> DSTORE_2(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(2, OperandStack::popDouble, "dstore_2");
    }

    public static XSTORE_Y<Double> DSTORE_3(ProgramCounter pc, MethodInfo method) {
        return new XSTORE_Y<>(3, OperandStack::popDouble, "dstore_3");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var localVars = thread.top().vars();

        T value = popFunc.apply(stack);
        localVars.slots[index] = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
