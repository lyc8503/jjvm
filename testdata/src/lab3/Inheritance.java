package lab3;

import util.IOUtil;
class Test1 {
    public static int svar1 = 1;
    public int var1 = 1;
    public int var4;

    Test1() {
        IOUtil.writeInt(101);
    }

    public void hello() {
        IOUtil.writeChar('H');
        IOUtil.writeChar('e');
        IOUtil.writeChar('l');
        IOUtil.writeChar('l');
        IOUtil.writeChar('o');
    }

}

class Test2 extends Test1{
    public static int svar2 = 2;
    public int var2 = 2;

    Test2() {
        IOUtil.writeInt(102);
    }
}

class Test3 extends Test2 {
    public static int svar3 = 3;
    public int var3 = 3;

    Test3() {
        IOUtil.writeInt(103);
    }

    void printSVars() {
        IOUtil.writeInt(svar1);
        IOUtil.writeInt(svar2);
        IOUtil.writeInt(svar3);
    }

    void printVars() {
        IOUtil.writeInt(var1);
        IOUtil.writeInt(var2);
        IOUtil.writeInt(var3);
        IOUtil.writeInt(var4);
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Test3 t3 = new Test3();
        t3.printSVars();
        t3.printVars();
        t3.hello();
    }
}
