package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;

import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CMP<T extends Number> extends Instruction {

    private final Function<OperandStack, T> popFunc;
    private final boolean true4NaN;
    private final String name;

    public static CMP<Long> LCMP(ProgramCounter pc, MethodInfo method) {
        return new CMP<>(OperandStack::popLong, false, "lcmp");
    }

    public static CMP<Float> FCMPL(ProgramCounter pc, MethodInfo method) {
        return new CMP<>(OperandStack::popFloat, false, "fcmpl");
    }

    public static CMP<Float> FCMPG(ProgramCounter pc, MethodInfo method) {
        return new CMP<>(OperandStack::popFloat, true, "fcmpg");
    }

    public static CMP<Double> DCMPL(ProgramCounter pc, MethodInfo method) {
        return new CMP<>(OperandStack::popDouble, false, "dcmpl");
    }

    public static CMP<Double> DCMPG(ProgramCounter pc, MethodInfo method) {
        return new CMP<>(OperandStack::popDouble, true, "dcmpg");
    }



    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        T value2 = popFunc.apply(stack);
        T value1 = popFunc.apply(stack);

        if (Double.isNaN(value1.doubleValue()) || Double.isNaN(value2.doubleValue())) {
            if (true4NaN) {
                stack.pushInt(1);
            } else {
                stack.pushInt(-1);
            }
        } else {
            if (value1.doubleValue() > value2.doubleValue()) {
                stack.pushInt(1);
            } else if (value1.doubleValue() == value2.doubleValue()) {
                stack.pushInt(0);
            } else if (value1.doubleValue() < value2.doubleValue()){
                stack.pushInt(-1);
            } else {
                assert false;
            }
        }

    }

    @Override
    public String toString() {
        return name;
    }
}
