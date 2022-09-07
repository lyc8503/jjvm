package vjvm.classfiledefs;

import lombok.var;

import java.util.List;
import java.util.stream.Collectors;

public class MethodDescriptors {
    public static int argc(String descriptor) {
        assert descriptor.startsWith("(");

        List<Character> arr = descriptor.chars().mapToObj(i -> (char) i).collect(Collectors.toList());

        int size = 0;

        while (arr.get(0) != ')') {
            switch (arr.get(0)) {
                case '(':
                    break;
                case Descriptors.DESC_long:
                case Descriptors.DESC_double:
                    size += 2;
                    break;
                case Descriptors.DESC_reference:
                    while (arr.get(0) != ';') {
                        arr.remove(0);
                    }
                    size += 1;
                case Descriptors.DESC_array:  // Array reference
                    while (arr.get(0) == Descriptors.DESC_array) {
                        arr.remove(0);
                    }
                    size += 1;
                case Descriptors.DESC_byte:
                case Descriptors.DESC_char:
                case Descriptors.DESC_float:
                case Descriptors.DESC_int:
                case Descriptors.DESC_short:
                case Descriptors.DESC_boolean:
                    size += 1;
                    break;
                default:
                    assert false;
            }
            arr.remove(0);
        }

        return size;
    }

    public static char returnType(String descriptor) {
        assert descriptor.startsWith("(");

        var i = descriptor.indexOf(')') + 1;
        assert i < descriptor.length();
        return descriptor.charAt(i);
    }
}
