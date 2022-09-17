package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.class_.constant.*;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.error.UnimplementedError;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDC extends Instruction {

    private final int index;
    private final String name;

    private final boolean isLongOrDouble;

    public static LDC LDC(ProgramCounter pc, MethodInfo method) {
        return new LDC(pc.byte_(), "ldc", false);
    }

    public static LDC LDC_W(ProgramCounter pc, MethodInfo method) {
        return new LDC(pc.ushort(), "ldc_w", false);
    }

    public static LDC LDC2_W(ProgramCounter pc, MethodInfo method) {
        return new LDC(pc.ushort(), "ldc2_w", true);
    }

    @Override
    public void run(JThread thread) {
        var opStack = thread.top().stack();
        var runtimeConstantPool = thread.top().link();
        var constant = runtimeConstantPool.constant(index);

        if (isLongOrDouble) {
            assert constant instanceof LongConstant || constant instanceof DoubleConstant;
            if (constant instanceof LongConstant) {
                opStack.pushLong(((LongConstant) constant).value());
            } else {
                opStack.pushDouble(((DoubleConstant) constant).value());
            }
        } else {
            if (constant instanceof FloatConstant) {
                opStack.pushFloat(((FloatConstant) constant).value());
            } else if (constant instanceof IntegerConstant) {
                opStack.pushInt(((IntegerConstant) constant).value());
            } else if (constant instanceof StringConstant) {
                var strClass = thread.top().jClass().classLoader().loadClass("Ljava/lang/String;");
//                strClass.init(thread);
                opStack.pushReference(thread.context().heap().objAlloc(strClass));
            } else if (constant instanceof ClassInfoConstant) {
                // The ref pushed onto the stack is not the reference to the class, but the reference to the `java.lang.Class` class !
//                var clazz = ((ClassInfoConstant) constant).getJClass();
//                clazz.init(thread);
//                var ref = thread.context().heap().objAlloc(clazz);

                var classInfo = ((ClassInfoConstant) constant);
                // TODO: correctly init java.lang.Class

                var classJClass = thread.context().bootstrapLoader().loadClass("Ljava/lang/Class;");
                var ref = thread.context().heap().objAlloc(classJClass);

                opStack.pushReference(ref);
            } else {
                throw new UnimplementedError("ldc unknown type.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, index);
    }
}
