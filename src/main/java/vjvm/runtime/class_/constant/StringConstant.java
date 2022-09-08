package vjvm.runtime.class_.constant;

import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import vjvm.runtime.class_.JClass;

import java.io.DataInput;

public class StringConstant extends Constant {

    private final int stringIndex;

    private final JClass jClass;

    @SneakyThrows
    public StringConstant(DataInput input, JClass jClass) {
        this.jClass = jClass;
        stringIndex = input.readUnsignedShort();
    }

    public String value() {
        return ((UTF8Constant) jClass.constantPool().constant(stringIndex)).value();
    }

    @Override
    public String toString() {
        return String.format("String: \"%s\"", StringEscapeUtils.escapeJava(value()));
    }
}
