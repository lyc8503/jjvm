package lab4;

import util.IOUtil;


public class RuntimeException {

    public static void main(String[] args) {

        try {
            Object a = new Object();
            Integer b = (Integer) a;
        } catch (ClassCastException e) {
            IOUtil.writeInt(1);
        }

        try {
            Object a = null;
            a.toString();
        } catch (NullPointerException e) {
            IOUtil.writeInt(2);
        }

        try {
            int i = 1 / 0;
        } catch (ArithmeticException e) {
            IOUtil.writeInt(3);
        }

    }
}
