package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassInfoConstant extends Constant {

    @Getter
    private final int nameIndex;
    private final JClass jClass;

    @SneakyThrows
    public ClassInfoConstant(DataInput input, JClass jClass) {
        this.nameIndex = input.readUnsignedShort();
        this.jClass = jClass;
    }

    public String name() {
        return ((UTF8Constant) jClass.constantPool().constant(nameIndex)).value();
    }

    @Override
    public String toString() {
        return String.format("Class: %s", name());
    }
}
