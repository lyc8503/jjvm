package lab3;

import util.IOUtil;

class A {
    int var = 123;

    public A() {
        IOUtil.writeChar('A');
    }
}

public class InstanceWithFields {

    int a = 1;
    boolean b;
    float f;
    double d;
    Object obj;
    A objA;

    void hello() {
        IOUtil.writeInt(a);
        a = 2;
        IOUtil.writeInt(a);

        if (!b) {
            IOUtil.writeChar('B');
        }

        if (b) {
            IOUtil.writeChar('N');
        }

        IOUtil.writeFloat(f);
        IOUtil.writeDouble(d);

        if (obj == null && objA == null) {
            objA = new A();
            IOUtil.writeInt(objA.var);
        }
    }

    public InstanceWithFields() {
        IOUtil.writeChar('I');
    }

    public static void main(String[] args) {
        InstanceWithFields instance = new InstanceWithFields();
        instance.hello();
    }

}
