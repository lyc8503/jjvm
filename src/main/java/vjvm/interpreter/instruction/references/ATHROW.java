package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.exception.JThrowable;
import vjvm.runtime.reference.ObjectReference;

public class ATHROW extends Instruction {

    public ATHROW(ProgramCounter pc, MethodInfo method) {

    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var ref = stack.popReference();

        throw new JThrowable((ObjectReference) ref);
    }

    @Override
    public String toString() {
        return "athrow";
    }
}
