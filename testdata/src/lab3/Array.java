package lab3;

import util.IOUtil;
public class Array {

    public int[] arr;

    public long[] larr = new long[10];
    public float[] farr = new float[10];
    public double[] darr = new double[10];
    public boolean[] barr = new boolean[10];
    public Array[] aarr = new Array[10];

    public int[] arr3;

    public void func() {
        int[] arr2;

        arr = new int[10];
        arr2 = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            arr2[i] = 3 * i;
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] + arr2[i];
        }

        IOUtil.writeInt(sum);
    }

    public void func2() {
        larr[0] = 1;
        IOUtil.writeLong(larr[0]);
        IOUtil.writeLong(larr[1]);

        farr[0] = 2.2f;
        IOUtil.writeFloat(farr[0]);
        IOUtil.writeFloat(farr[1]);

        darr[0] = 1.1d;
        IOUtil.writeDouble(darr[0]);
        IOUtil.writeDouble(darr[1]);

        barr[0] = true;
        if (barr[0] && !barr[1]) {
            IOUtil.writeChar('B');
        }

        if (aarr[0] == null) {
            IOUtil.writeChar('A');
        }

        if (arr3 == null) {
            IOUtil.writeChar('A');
        }
    }

    public static void main(String[] args) {
        new Array().func();
        new Array().func2();
    }
}
