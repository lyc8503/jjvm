package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class DoubleConstant extends Constant {

    @Getter
    private final double value;

    @SneakyThrows
    public DoubleConstant(DataInput input) {
        this.value = input.readDouble();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
