package vjvm.runtime.class_.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.class_.JClass;

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

    public JClass getJClass() {
        return jClass.classLoader().loadClass("L" + name() + ";");
    }

    @Override
    public String toString() {
        return String.format("Class: %s", name());
    }
}
