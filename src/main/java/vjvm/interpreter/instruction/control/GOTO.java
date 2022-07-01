package vjvm.interpreter.instruction.control;

import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

public class GOTO extends Instruction {

    private final int branchByte;
    private final ProgramCounter pc;

    public GOTO(ProgramCounter pc, MethodInfo method) {
        branchByte = pc.ushort();
        this.pc = pc;
    }

    @Override
    public void run(JThread thread) {
        pc.move(branchByte);
    }

    @Override
    public String toString() {
        return "goto";
    }
}
