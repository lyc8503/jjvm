package vjvm.runtime;

import vjvm.utils.UnimplementedError;

import java.util.Optional;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {

    public Object[] slots;

    public Slots(int slotSize) {
        slots = new Object[slotSize];
    }

    public int int_(int index) {
        return (int) slots[index];
    }

    public void int_(int index, int value) {
        slots[index] = value;
    }

    public long long_(int index) {
        return (long) slots[index];
    }

    public void long_(int index, long value) {
        slots[index] = value;
    }

    public float float_(int index) {
        return (float) slots[index];
    }

    public void float_(int index, float value) {
        slots[index] = value;
    }

    public double double_(int index) {
        return (double) slots[index];
    }

    public void double_(int index, double value) {
        slots[index] = value;
    }

    public byte byte_(int index) {
        return (byte) slots[index];
    }

    public void byte_(int index, byte value) {
        slots[index] = value;
    }

    public char char_(int index) {
        return (char) (int) slots[index];
    }

    public void char_(int index, char value) {
        slots[index] = value;
    }

    public short short_(int index) {
        return (short) slots[index];
    }

    public void short_(int index, short value) {
        slots[index] = value;
    }

    public Optional<Object> value(int index) {
        if (slots[index] != null) {
            return Optional.of(slots[index]);
        }
        return Optional.empty();
    }

    public int size() {
        return slots.length;
    }

    public void copyTo(int begin, int length, Slots dest, int destBegin) {
        for (int i = begin; i < begin + length; i++) {
            dest.slots[destBegin + i - begin] = this.slots[i];
        }
    }

}
