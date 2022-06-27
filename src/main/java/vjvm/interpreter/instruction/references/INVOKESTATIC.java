package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.MethodRefConstant;
import vjvm.utils.UnimplementedError;

public class INVOKESTATIC extends Instruction {
    private final MethodInfo method;

    public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
        MethodRefConstant methodRef = (MethodRefConstant) method.jClass().constantPool().constant(pc.ushort());

        JClass clazz = method.jClass().classLoader().loadClass("L" + methodRef.classInfo().name() + ";");
        this.method = clazz.findMethod(methodRef.nameAndType().name(), methodRef.nameAndType().type());
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var args = stack.popSlots(method.argc());

        thread.context().interpreter().invoke(method, thread, args);
    }

    @Override
    public String toString() {
        return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
    }
}
