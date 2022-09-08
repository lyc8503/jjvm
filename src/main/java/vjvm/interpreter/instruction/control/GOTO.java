package vjvm.interpreter.instruction.control;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.util.Logger;

public class GOTO extends Instruction {

    private final int branchByte;
    private final ProgramCounter pc;

    public GOTO(ProgramCounter pc, MethodInfo method) {
        branchByte = pc.short_();
        this.pc = pc;
    }

    @Override
    public void run(JThread thread) {
        Logger.debug("GOTO: branchByte " + branchByte);
        pc.move(branchByte - 3);

    }

    @Override
    public String toString() {
        return "goto " + branchByte;
    }
}
