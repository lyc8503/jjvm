package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.class_.constant.ClassInfoConstant;
import vjvm.runtime.reference.ObjectReference;
import vjvm.runtime.reference.Reference;

public class INSTANCEOF extends Instruction {

    public final ClassInfoConstant constant;

    public INSTANCEOF(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        this.constant = (ClassInfoConstant) cp.constant(pc.ushort());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var ref = stack.popReference();

        if (ref == Reference.NULL) {
            stack.pushInt(0);
            return;
        }


        // TODO
        stack.pushInt(((ObjectReference) ref).jClass().isSubclassOf(constant.getJClass()) ? 1 : 0);
    }

    @Override
    public String toString() {
        return "instanceof: " + constant;
    }
}
