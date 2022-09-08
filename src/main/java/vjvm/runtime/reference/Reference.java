package vjvm.runtime.reference;

import lombok.Getter;
import lombok.var;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.class_.constant.FieldRefConstant;
import vjvm.runtime.heap.JHeap;

public class Reference {


    @Getter
    private final JHeap heap;
    @Getter
    private final JClass jClass;
    @Getter
    private final int innerIndex;


    public Reference(JHeap heap, JClass jClass, int innerIndex) {
        this.heap = heap;
        this.jClass = jClass;
        this.innerIndex = innerIndex;
    }

    public static final Reference NULL = new Reference(null, null, -1);

    public Object getField(FieldRefConstant ref) {
        var fields = heap.fieldsMap.get(innerIndex);
        return fields.getField(ref.nameAndType().name());
    }

    public void putField(FieldRefConstant ref, Object value) {
        var fields = heap.fieldsMap.get(innerIndex);
        fields.putField(ref.nameAndType().name(), value);
    }
}
