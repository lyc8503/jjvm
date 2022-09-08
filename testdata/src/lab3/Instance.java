package lab3;

import util.IOUtil;

public class Instance {

    public static void main(String[] var0) {
        IOUtil.writeInt((new Instance()).hello());
    }

    public int hello() {
        IOUtil.writeInt(1);
		return 2;
    }
}
