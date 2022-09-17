package lab4;

import util.IOUtil;

public class DoubleException {

    public static void test() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtil.writeInt(1);
        }
    }

    public static void main(String[] args) {
        try {
            try {
                test();
            } catch (Exception e) {
                test();
            }
        } catch (Exception e) {
            IOUtil.writeInt(2);
        }
    }
}
