package vjvm.interpreter.instruction.constants;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.ProgramCounter;

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
