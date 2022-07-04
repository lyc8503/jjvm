package vjvm.runtime.heap;

import lombok.var;
import vjvm.runtime.classdata.JClass;

import java.util.HashMap;

public class JHeap {

    private int innerIndex = 0;  // used to locate Fields inside the heap.

    public HashMap<Integer, Fields> fieldsMap;

    public JHeap() {
        fieldsMap = new HashMap<>();
    }

    public Reference alloc(JClass jClass) {
        var ref = new Reference(this, jClass, innerIndex);
        fieldsMap.put(innerIndex, new Fields(jClass, false));
        innerIndex++;
        return ref;
    }

}
