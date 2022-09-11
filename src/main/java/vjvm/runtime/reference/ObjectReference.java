package vjvm.runtime.reference;

import lombok.Getter;
import lombok.var;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.class_.constant.FieldRefConstant;
import vjvm.runtime.heap.JHeap;

public class ObjectReference extends Reference{

    @Getter
    private final JHeap heap;
    @Getter
    private final JClass jClass;
    @Getter
    private final int innerIndex;

    public ObjectReference(JHeap heap, JClass jClass, int innerIndex) {
        super();
        this.heap = heap;
        this.jClass = jClass;
        this.innerIndex = innerIndex;
    }

    public Object getField(FieldRefConstant ref) {
        var fields = heap.fieldsMap.get(innerIndex);
        return fields.getField(ref.nameAndType().name());
    }

    public void putField(FieldRefConstant ref, Object value) {
        var fields = heap.fieldsMap.get(innerIndex);
        fields.putField(ref.nameAndType().name(), value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectReference)) {
            return false;
        }

        return ((ObjectReference) obj).innerIndex == this.innerIndex;
    }
}
