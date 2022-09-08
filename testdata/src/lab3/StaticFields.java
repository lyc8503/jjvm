package lab3;

import util.IOUtil;

class B {
    public static boolean var1 = true;
    public static float var2 = 2.33f;
    public static double var3 = 6.66d;
    public static StaticFields var4 = new StaticFields();
}


public class StaticFields {
    public static int a = 1;
    public static int b;

    public static void main(String[] args) {
        IOUtil.writeInt(a);
        a = 2;
        IOUtil.writeInt(a);
        IOUtil.writeInt(b);

        IOUtil.writeInt(B.var1 ? 1 : 0);
        IOUtil.writeFloat(B.var2);
        IOUtil.writeDouble(B.var3);
        IOUtil.writeInt(B.var4 != null ? 1 : 0);
    }
}
