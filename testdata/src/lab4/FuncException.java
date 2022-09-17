package lab4;

import util.IOUtil;

public class FuncException {

    public static void main(String[] args) {
        try {
            IOUtil.writeInt(1);
            dangerousOperation();
        } catch (Exception e) {
            IOUtil.writeInt(2);
        } finally {
            IOUtil.writeInt(3);
        }
    }

    public static void dangerousOperation() throws Exception {
        throw new Exception();
    }
}
