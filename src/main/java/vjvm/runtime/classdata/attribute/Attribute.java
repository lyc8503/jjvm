package vjvm.runtime.classdata.attribute;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

public abstract class Attribute {

    @SneakyThrows
    public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
        var nameIndex = input.readUnsignedShort();
        var attrLength = Integer.toUnsignedLong(input.readInt());

        switch (((UTF8Constant) constantPool.constant(nameIndex)).value()) {
            case "Code":
                return new Code(input, constantPool);
            default:
                return new UnknownAttribute(input, attrLength);
        }
    }
}
