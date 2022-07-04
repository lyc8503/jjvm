package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.JClass;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.ClassInfoConstant;
import vjvm.runtime.classdata.constant.MethodRefConstant;
import vjvm.runtime.frame.ProgramCounter;

public class NEW extends Instruction {
    /**
     * new 一个对象
     */

    private final ClassInfoConstant classInfo;

    public NEW(ProgramCounter pc, MethodInfo method) {
        var index = pc.ushort();
        this.classInfo = (ClassInfoConstant) method.jClass().constantPool().constant(index);
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        classInfo.getJClass().init(thread);
        stack.pushReference(thread.context().heap().alloc(classInfo.getJClass()));
    }

    @Override
    public String toString() {
        return "new " + classInfo.name();
    }
}
