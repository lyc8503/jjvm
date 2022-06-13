package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;

public class MethodDescriptors {
    public static int argc(String descriptor) {
        assert descriptor.startsWith("(");

        // TODO: calculate arguments size in slots
        throw new UnimplementedError();
    }

    public static char returnType(String descriptor) {
        assert descriptor.startsWith("(");

        var i = descriptor.indexOf(')') + 1;
        assert i < descriptor.length();
        return descriptor.charAt(i);
    }
}
