package vjvm.runtime.heap;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.reference.ArrayReference;
import vjvm.runtime.reference.Reference;

import java.util.HashMap;

public class Fields {

    private final HashMap<String, Object> map;

    public static Object getDefaultValue(char desc) {
        // default values for the fields
        switch (desc) {
            case Descriptors.DESC_byte:
            case Descriptors.DESC_boolean:
            case Descriptors.DESC_char:
            case Descriptors.DESC_int:
            case Descriptors.DESC_short:
                return 0;
            case Descriptors.DESC_double:
                return 0.0d;
            case Descriptors.DESC_float:
                return 0.0f;
            case Descriptors.DESC_long:
                return 0L;
            case Descriptors.DESC_reference:
            case Descriptors.DESC_array:
                return Reference.NULL;
            default:
                throw new AssertionError();
        }
    }

    public Fields(JClass jClass, boolean static_) {
        map = new HashMap<>();

        while (jClass.superClass() != null) {
            for (int i = 0; i < jClass.fieldsCount(); i++) {
                var field = jClass.field(i);
                if (field.static_() == static_ && !map.containsKey(field.name())) {  // https://stackoverflow.com/questions/43579567/java-inheritance-fields
                    map.put(field.name(), getDefaultValue(field.descriptor().charAt(0)));
                }
            }
            jClass = jClass.classLoader().loadClass("L" + jClass.superClass() + ";");
            if (static_) {  // put all instance fields in one Fields obj, but put static fields separately in each class
                break;
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
        assert map.get(name) == Reference.NULL || map.get(name).getClass() == value.getClass() || value instanceof ArrayReference;

        map.put(name, value);
    }

    public boolean containsField(String name) {
        return map.containsKey(name);
    }
}
