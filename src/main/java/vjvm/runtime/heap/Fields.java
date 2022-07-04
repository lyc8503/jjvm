package vjvm.runtime.heap;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.classdata.JClass;
import vjvm.utils.UnimplementedError;

import java.util.HashMap;

public class Fields {

    private final HashMap<String, Object> map;

    public static Object getDefaultValue(char desc) {
        // default values for the fields
        System.err.println(desc);
        switch (desc) {
            case Descriptors.DESC_byte:
            case Descriptors.DESC_boolean:
            case Descriptors.DESC_char:
            case Descriptors.DESC_int:
            case Descriptors.DESC_short:
                return 0;
            case Descriptors.DESC_array:
                throw new UnimplementedError();
            case Descriptors.DESC_double:
                return 0.0d;
            case Descriptors.DESC_float:
                return 0.0f;
            case Descriptors.DESC_long:
                return 0L;
            case Descriptors.DESC_reference:
                return Reference.NULL;
            default:
                throw new AssertionError();
        }
    }

    public Fields(JClass jClass, boolean static_) {
        map = new HashMap<>();
        for (int i = 0; i < jClass.fieldsCount(); i++) {
            var field = jClass.field(i);
            if (field.static_() == static_) {
                map.put(field.name(), getDefaultValue(field.descriptor().charAt(0)));
            }
        }
    }

    public Object getField(String name) {
        // check the field exists
        assert map.containsKey(name);

        return map.get(name);
    }

    public void putField(String name, Object value) {
        // check the type
        assert map.containsKey(name);
        assert map.get(name).getClass() == value.getClass();

        map.put(name, value);
    }
}
