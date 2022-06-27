package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;

import java.util.ArrayList;
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
                case 'J':  // Long
                case 'D':  // Double
                    size += 2;
                    break;
                case 'L':  // Class reference
                    while (arr.get(0) != ';') {
                        arr.remove(0);
                    }
                    size += 1;
                case '[':  // Array reference
                    while (arr.get(0) == '[') {
                        arr.remove(0);
                    }
                    size += 1;
                case 'B':  // Byte
                case 'C':  // Char
                case 'F':  // Float
                case 'I':  // Int
                case 'S':  // Short
                case 'Z':  // Boolean
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
