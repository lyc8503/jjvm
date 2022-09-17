package vjvm.runtime.class_.attribute;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.runtime.class_.ConstantPool;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.class_.constant.ClassInfoConstant;

import java.io.DataInput;


class ExceptionHandler {

    private final ConstantPool constantPool;

    @Getter
    private int start;
    @Getter
    private int end;
    @Getter
    private int handler;
    private int catchType;

    @SneakyThrows
    ExceptionHandler(DataInput input, ConstantPool constantPool) {
        this.constantPool = constantPool;
        start = input.readUnsignedShort();
        end = input.readUnsignedShort();
        handler = input.readUnsignedShort();
        catchType = input.readUnsignedShort();
    }

    JClass catchType() {
        if (catchType == 0) {
            return null;
        }

        var type = ((ClassInfoConstant) constantPool.constant(catchType)).getJClass();
        // TODO: check
        assert type.isSubclassOf(constantPool.jClass().classLoader().loadClass("Ljava/lang/Throwable;"));
        return type;
    }

}

@Getter
public class Code extends Attribute {
    private final int maxStack;
    private final int maxLocals;
    private final byte[] code; // the bytecode represented as raw bytes
    private final Attribute[] attributes;

    private final ExceptionHandler[] exceptionHandlers;

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
        exceptionHandlers = new ExceptionHandler[exceptionLength];
        for (int i = 0; i < exceptionLength; i++) {
            exceptionHandlers[i] = new ExceptionHandler(input, constantPool);
        }

        int attrCount = input.readUnsignedShort();
        attributes = new Attribute[attrCount];
        for (int i = 0; i < attrCount; i++) {
            attributes[i] = Attribute.constructFromData(input, constantPool);
        }
    }

    public int findExceptionHandler(int currentPC, JClass exceptionType) {
        int pc = -1;
        for (ExceptionHandler exceptionHandler : exceptionHandlers) {
            if ((exceptionHandler.catchType() == null ||   // catchType == null means catch all
                exceptionType.isSubclassOf(exceptionHandler.catchType())) &&
                currentPC >= exceptionHandler.start() &&
                currentPC < exceptionHandler.end()) {

                pc = exceptionHandler.handler();
                break;
            }
        }

        return pc;
    }
}
