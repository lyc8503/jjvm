package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.FieldRefConstant;
import vjvm.runtime.frame.ProgramCounter;

public class GETSTATIC extends Instruction {

    private final FieldRefConstant fieldRef;

    public GETSTATIC(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        this.fieldRef = (FieldRefConstant) cp.constant(pc.ushort());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();

        fieldRef.classInfo().getJClass().init(thread);
        stack.push(fieldRef.nameAndType().type(), fieldRef.classInfo().getJClass().getField(fieldRef));
    }

    @Override
    public String toString() {
        return "getstatic " + fieldRef.toString();
    }
}
