package vjvm.runtime.frame;

import lombok.Getter;
import lombok.var;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.class_.ConstantPool;
import vjvm.runtime.class_.MethodInfo;

@Getter
public class JFrame {
    private final Slots vars;
    private final OperandStack stack;
    private final ConstantPool link;
    private final MethodInfo method;
    private final JClass jClass;
    private final ProgramCounter pc;

    public JFrame(MethodInfo method, Slots args) {
        jClass = method.jClass();
        link = jClass.constantPool();
        this.method = method;

        if (method.native_()) {
            stack = null;
            pc = null;
            vars = new Slots(args.size());
        } else {
            var code = method.code();
            stack = new OperandStack(code.maxStack());
            pc = new ProgramCounter(code.code());
            vars = new Slots(code.maxLocals());
        }

        args.copyTo(0, args.size(), vars, 0);
    }

    @Override
    public String toString() {
        return "JFrame: " + jClass.thisClass() + "." + method + ", pc: " + pc.position();
    }
}
