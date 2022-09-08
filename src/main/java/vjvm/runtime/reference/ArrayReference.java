package vjvm.runtime.reference;

import lombok.Getter;
import vjvm.runtime.class_.constant.FieldRefConstant;
import vjvm.runtime.heap.JHeap;

public class ArrayReference<T> extends Reference{
    @Getter
    private final JHeap heap;
    @Getter
    private final int innerIndex;


    public ArrayReference(JHeap heap, int innerIndex) {
        super(heap, null, innerIndex);
        this.heap = heap;
        this.innerIndex = innerIndex;
    }

    @Override
    public Object getField(FieldRefConstant ref) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putField(FieldRefConstant ref, Object value) {
        throw new UnsupportedOperationException();
    }

    public T get(int index) {
        return (T) heap.arrayMap.get(innerIndex)[index];
    }

    public void put(int index, T value) {
        heap.arrayMap.get(innerIndex)[index] = value;
    }

    public int length() {
        return heap.arrayMap.get(innerIndex).length;
    }

}
