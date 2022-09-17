package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.ProgramCounter;

public class CHECKCAST extends Instruction {

    public CHECKCAST(ProgramCounter pc, MethodInfo method) {
        var discard = pc.ushort();
    }

    @Override
    public void run(JThread thread) {
        // TODO
    }

    @Override
    public String toString() {
        return "checkcast";
    }
}
