package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.class_.constant.FieldRefConstant;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.reference.ObjectReference;

public class PUTFIELD extends Instruction {

    private final FieldRefConstant fieldRef;

    public PUTFIELD(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        this.fieldRef = (FieldRefConstant) cp.constant(pc.ushort());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();

        var value = stack.pop(fieldRef.nameAndType().type());
        var reference = (ObjectReference) stack.popReference();

        reference.putField(fieldRef, value);
    }

    @Override
    public String toString() {
        return "putfield " + fieldRef.toString();
    }
}
