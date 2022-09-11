package vjvm.runtime.heap;

import lombok.var;
import vjvm.runtime.class_.JClass;
import vjvm.runtime.reference.ArrayReference;
import vjvm.runtime.reference.ObjectReference;
import vjvm.runtime.reference.Reference;

import java.util.Arrays;
import java.util.HashMap;

public class JHeap {

    private int innerIndex = 0;  // used to locate Fields inside the heap.

    public HashMap<Integer, Fields> fieldsMap;

    public HashMap<Integer, Object[]> arrayMap;


    public JHeap() {
        fieldsMap = new HashMap<>();
        arrayMap = new HashMap<>();
    }

    public Reference objAlloc(JClass jClass) {
        var ref = new ObjectReference(this, jClass, innerIndex);
        fieldsMap.put(innerIndex, new Fields(jClass, false));
        innerIndex++;
        return ref;
    }

    public <T> ArrayReference<T> arrayAlloc(int length, Object default_) {
        var ref = new ArrayReference<T>(this, innerIndex);

        var array = new Object[length];
        arrayMap.put(innerIndex, array);
        Arrays.fill(array, default_);


        innerIndex++;
        return ref;
    }

}
