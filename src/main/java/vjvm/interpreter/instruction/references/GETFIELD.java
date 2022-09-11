package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.class_.constant.FieldRefConstant;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.reference.ObjectReference;


public class GETFIELD extends Instruction {

    private final FieldRefConstant fieldRef;

    public GETFIELD(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        this.fieldRef = (FieldRefConstant) cp.constant(pc.ushort());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var reference = (ObjectReference) stack.popReference();

        stack.push(fieldRef.nameAndType().type(), reference.getField(fieldRef));
    }

    @Override
    public String toString() {
        return "getfield " + fieldRef.toString();
    }
}
