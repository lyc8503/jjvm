package vjvm.runtime.class_.attribute;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.class_.ConstantPool;

import java.io.DataInput;

@Getter
public class Code extends Attribute {
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code; // the bytecode represented as raw bytes
    private final Attribute[] attributes;

    @SneakyThrows
    Code(DataInput input, ConstantPool constantPool) {

//        int nameIndex = input.readUnsignedShort();
//        int attrLength = input.readInt();

        maxStack = input.readUnsignedShort();
        maxLocals = input.readUnsignedShort();

        int codeLength = input.readInt();
        code = new byte[codeLength];
        input.readFully(code);

        int exceptionLength = input.readUnsignedShort();
        for (int i = 0; i < exceptionLength; i++) {
            // Discard
            input.readUnsignedShort();
            input.readUnsignedShort();
            input.readUnsignedShort();
            input.readUnsignedShort();
        }

        int attrCount = input.readUnsignedShort();
        attributes = new Attribute[attrCount];
        for (int i = 0; i < attrCount; i++) {
            attributes[i] = Attribute.constructFromData(input, constantPool);
        }

    }
}
