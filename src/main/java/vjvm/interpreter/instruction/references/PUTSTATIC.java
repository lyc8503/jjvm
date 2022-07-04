package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.FieldRefConstant;
import vjvm.runtime.frame.ProgramCounter;

public class PUTSTATIC extends Instruction {

    private final FieldRefConstant fieldRef;

    public PUTSTATIC(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        this.fieldRef = (FieldRefConstant) cp.constant(pc.ushort());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();


        var value = stack.pop(fieldRef.nameAndType().type());
        fieldRef.classInfo().getJClass().init(thread);
        fieldRef.classInfo().getJClass().putField(fieldRef, value);
    }

    @Override
    public String toString() {
        return "putstatic " + fieldRef.toString();
    }
}
