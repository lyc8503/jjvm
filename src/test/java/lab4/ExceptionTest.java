package lab4;

import org.junit.jupiter.api.Test;
import util.TestUtils;

public class ExceptionTest {
    @Test
    public void runBasicException() {
        TestUtils.runClass("lab4.BasicException");
    }

    @Test
    public void runFuncException() {
        TestUtils.runClass("lab4.FuncException");
    }

    @Test
    public void runDoubleException() {
        TestUtils.runClass("lab4.DoubleException");
    }

    @Test
    public void runRuntimeException() {
        TestUtils.runClass("lab4.RuntimeException");
    }
}
