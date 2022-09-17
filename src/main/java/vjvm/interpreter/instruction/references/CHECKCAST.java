package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.ConstantPool;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.constant.ClassInfoConstant;
import vjvm.runtime.exception.JThrowable;
import vjvm.runtime.reference.ObjectReference;

public class CHECKCAST extends Instruction {

    int classInfoIndex;

    ConstantPool cp;

    public CHECKCAST(ProgramCounter pc, MethodInfo method) {
        this.classInfoIndex = pc.ushort();
        this.cp = method.jClass().constantPool();
    }

    @Override
    public void run(JThread thread) {

        var stack = thread.top().stack();
        var ref = (ObjectReference) stack.popReference();

        if (ref != ObjectReference.NULL) {
            var classInfo = (ClassInfoConstant) cp.constant(classInfoIndex);
            if (!ref.jClass().isSubclassOf(classInfo.getJClass())) {
                var exception = thread.context().heap().objAlloc(thread.context().bootstrapLoader().loadClass("Ljava/lang/ClassCastException;"));
                throw new JThrowable(exception);
            }
        }

        // the stack is unchanged
        stack.pushReference(ref);
    }

    @Override
    public String toString() {
        return "checkcast";
    }
}
