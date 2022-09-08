package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.class_.constant.MethodRefConstant;

public class INVOKESTATIC extends Instruction {
    private final MethodInfo method;

    public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
        MethodRefConstant methodRef = (MethodRefConstant) method.jClass().constantPool().constant(pc.ushort());
        this.method = methodRef.getMethod();
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var args = stack.popSlots(method.argc());

        method.jClass().init(thread);
        thread.context().interpreter().invoke(method, thread, args);
    }

    @Override
    public String toString() {
        return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
    }
}
