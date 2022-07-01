package vjvm.interpreter.instruction.math;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

public class INC extends Instruction {

    public static INC IINC(ProgramCounter pc, MethodInfo method) {
        return new INC();
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int value1 = stack.popInt();
        stack.pushInt(value1 + 1);
    }

    @Override
    public String toString() {
        return null;
    }
}
