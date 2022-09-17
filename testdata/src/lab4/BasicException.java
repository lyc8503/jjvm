package lab4;

import util.IOUtil;


class TestException extends Throwable {

}

public class BasicException {
    public static void main(String[] args) {
        try {
            IOUtil.writeInt(1);
            throw new TestException();
        } catch (TestException e) {
            IOUtil.writeInt(2);
        } finally {
            IOUtil.writeInt(3);
        }
    }
}
