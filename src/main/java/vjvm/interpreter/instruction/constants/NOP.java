package vjvm.interpreter.instruction.constants;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.ProgramCounter;

public class NOP extends Instruction {

    public NOP(ProgramCounter pc, MethodInfo methodInfo) {

    }

    @Override
    public void run(JThread thread) {
        // Do nothing
    }

    @Override
    public String toString() {
        return "nop";
    }
}
