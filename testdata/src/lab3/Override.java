package lab3;

class Test4 {
    void func() {
        IOUtil.writeInt(4);
    }
}

class Test5 extends Test4{
    void func() {
        IOUtil.writeInt(5);
    }
}


public class Override {
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        test5.func();

        Test4 test4 = new Test5();
        test4.func();
        ((Test5) test4).func();
    }
}
