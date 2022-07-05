package lab3;

public class Polymorphism {

    public void print(int var) {
        IOUtil.writeInt(var);
    }

    public void print(float var) {
        IOUtil.writeFloat(var);
    }

    public void print(double var) {
        IOUtil.writeDouble(var);
    }

    public void print(long var) {
        IOUtil.writeLong(var);
    }

    public static void print2(int var) {
        IOUtil.writeInt(var);
    }

    public static void print2(float var) {
        IOUtil.writeFloat(var);
    }

    public static void print2(double var) {
        IOUtil.writeDouble(var);
    }

    public static void print2(long var) {
        IOUtil.writeLong(var);
    }

    public static void main(String[] args) {
        print2(1);


        print2(2L);
        print2(3.0d);
        print2(4.0f);

        Polymorphism p = new Polymorphism();
        p.print(1);
        p.print(2L);
        p.print(3.0d);
        p.print(4.0f);
    }
}
