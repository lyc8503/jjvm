package vjvm.runtime.reference;

import lombok.Getter;
import vjvm.runtime.heap.JHeap;

public class ArrayReference<T> extends Reference{

    @Getter
    private final JHeap heap;
    @Getter
    private final int innerIndex;

    public ArrayReference(JHeap heap, int innerIndex) {
        super();
        this.heap = heap;
        this.innerIndex = innerIndex;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ArrayReference)) {
            return false;
        }

        return ((ArrayReference<?>) obj).innerIndex == this.innerIndex;
    }

}
