package lab4;

import util.IOUtil;

public class BasicException {
    public static void main(String[] args) {
        try {
            IOUtil.writeInt(1);
            throw new Exception();
        } catch (Exception e) {
            IOUtil.writeInt(2);
        } finally {
            IOUtil.writeInt(3);
        }
    }
}
