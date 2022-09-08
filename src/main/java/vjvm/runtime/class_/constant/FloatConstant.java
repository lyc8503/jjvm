package vjvm.runtime.class_.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant extends Constant {

    @Getter
    private final float value;

    @SneakyThrows
    public FloatConstant(DataInput input) {
        this.value = input.readFloat();
    }

    @Override
    public String toString() {
        return String.format("Float: %a", value);
    }
}
