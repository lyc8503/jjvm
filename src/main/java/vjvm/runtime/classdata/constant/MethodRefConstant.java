package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.classdata.JClass;
import vjvm.runtime.classdata.MethodInfo;

import java.io.DataInput;

public class MethodRefConstant extends Constant {
    private final JClass jClass;
    private final int classIndex;
    private final int nameAndTypeIndex;
    private final boolean interface_;

    @SneakyThrows
    public MethodRefConstant(DataInput input, JClass jClass, boolean interface_) {
        this.jClass = jClass;
        this.classIndex = input.readUnsignedShort();
        this.nameAndTypeIndex = input.readUnsignedShort();
        this.interface_ = interface_;
    }

    public ClassInfoConstant classInfo() {
        return (ClassInfoConstant) jClass.constantPool().constant(classIndex);
    }

    public MethodInfo getMethod() {
        var nameAndType = nameAndType();

        var clazz = classInfo().getJClass();

        var method = clazz.findMethod(nameAndType.name(), nameAndType.type());
        while (method == null) {
            clazz = clazz.classLoader().loadClass("L" + clazz.superClass() + ";");
            method = clazz.findMethod(nameAndType.name(), nameAndType.type());
        }

        return method;
    }

    public NameAndTypeConstant nameAndType() {
        return (NameAndTypeConstant) jClass.constantPool().constant(nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return String.format(interface_ ? "Interface": "" + "Methodref: %s.%s:%s", classInfo().name(), nameAndType().name(), nameAndType().type());
    }
}
