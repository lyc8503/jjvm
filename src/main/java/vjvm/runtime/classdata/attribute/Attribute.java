package vjvm.runtime.classdata.attribute;

import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.classdata.ConstantPool;

import java.io.DataInput;

public abstract class Attribute {

    @SneakyThrows
    public static Attribute constructFromData(DataInput input, ConstantPool constantPool) {
        var nameIndex = input.readUnsignedShort();
        var attrLength = Integer.toUnsignedLong(input.readInt());

        // TODO: detect and construct Code attribute
        return new UnknownAttribute(input, attrLength);
    }
}
