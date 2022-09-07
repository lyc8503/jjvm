package vjvm.interpreter.instruction.reserved;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

public class BREAKPOINT extends Instruction {
    public BREAKPOINT(ProgramCounter pc, MethodInfo method) {
    }

    @Override
    public void run(JThread thread) {
        var pc = thread.pc();
        pc.position(pc.position() - 1);
    }

    @Override
    public String toString() {
        return "breakpoint";
    }
}
