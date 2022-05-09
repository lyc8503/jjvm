package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class MethodRefConstant extends Constant{
    private final JClass jClass;
    private final int classIndex;
    private final int nameAndTypeIndex;

    @SneakyThrows
    public MethodRefConstant(DataInput input, JClass jClass) {
        this.jClass = jClass;
        this.classIndex = input.readUnsignedShort();
        this.nameAndTypeIndex = input.readUnsignedShort();
    }

    public ClassInfoConstant classInfo() {
        return (ClassInfoConstant) jClass.constantPool().constant(classIndex);
    }

    public NameAndTypeConstant nameAndType() {
        return (NameAndTypeConstant) jClass.constantPool().constant(nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return String.format("Methodref: %s.%s:%s", classInfo().name(), nameAndType().name(), nameAndType().type());
    }
}
