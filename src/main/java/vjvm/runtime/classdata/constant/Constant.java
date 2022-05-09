package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import lombok.var;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.runtime.JClass;

import java.io.DataInput;

import static vjvm.classfiledefs.ConstantTags.*;

public abstract class Constant {
    @SneakyThrows
    public static Pair<Constant, Integer> constructFromData(DataInput input, JClass jClass) {
        var tag = input.readByte();
        var count = 1;

        // TODO: construct Float, Double, Class, Fieldref, Methodref, InterfaceMethodref, String, and Long
        Constant result;
        switch (tag) {
            case CONSTANT_Integer:
                result = new IntegerConstant(input);
                break;
            case CONSTANT_NameAndType:
                result = new NameAndTypeConstant(input, jClass);
                break;
            case CONSTANT_Utf8: {
                result = new UTF8Constant(input);
                break;
            }
            case CONSTANT_Double:
                result = new DoubleConstant(input);
                count = 2;
                break;
            case CONSTANT_Long:
                result = new UnknownConstant(input, 8);
                count = 2;
                break;
            case CONSTANT_MethodHandle:
                result = new UnknownConstant(input, 3);
                break;
            case CONSTANT_String:

            case CONSTANT_Class:
                result = new ClassInfoConstant(input, jClass);
                break;
            case CONSTANT_MethodType:
                result = new UnknownConstant(input, 2);
                break;
            case CONSTANT_Float:
                result = new FloatConstant(input);
                break;
            case CONSTANT_Fieldref:
            case CONSTANT_Methodref:
            case CONSTANT_InterfaceMethodref:
            case CONSTANT_Dynamic:
            case CONSTANT_InvokeDynamic:
                result = new UnknownConstant(input, 4);
                break;
            default:
                throw new ClassFormatError();
        }

        return Pair.of(result, count);
    }
}
