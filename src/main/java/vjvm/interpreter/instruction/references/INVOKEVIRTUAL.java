package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.class_.constant.MethodRefConstant;
import vjvm.runtime.ProgramCounter;

public class INVOKEVIRTUAL extends Instruction {
    /**
     * invoke virtual
     */

    private final MethodInfo method;

    public INVOKEVIRTUAL(ProgramCounter pc, MethodInfo method) {
        var cp = method.jClass().constantPool();
        var index = pc.ushort();
        this.method = ((MethodRefConstant) cp.constant(index)).getMethod();
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var args = stack.popSlots(method.argc() + 1);  // The first slot contains objectref (this).

        thread.context().interpreter().invoke(method, thread, args);
    }

    @Override
    public String toString() {
        return String.format("invokevirtual %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
    }
}
