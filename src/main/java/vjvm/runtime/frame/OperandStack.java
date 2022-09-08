package vjvm.runtime.frame;

import lombok.Getter;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.reference.Reference;

public class OperandStack {
    @Getter
    private final Slots slots;

    @Getter
    private int top;

    public OperandStack(int stackSize) {
        slots = new Slots(stackSize);
        top = 0;
    }

    public void pushInt(int value) {
        slots.int_(top, value);
        top += 1;
    }

    public int popInt() {
        top -= 1;
        return slots.int_(top);
    }

    public void pushFloat(float value) {
        slots.float_(top, value);
        top += 1;
    }

    public float popFloat() {
        top -= 1;
        return slots.float_(top);
    }

    public void pushLong(long value) {
        slots.long_(top, value);
        top += 2;
    }

    public long popLong() {
        top -= 2;
        return slots.long_(top);
    }

    public void pushDouble(double value) {
        slots.double_(top, value);
        top += 2;
    }

    public double popDouble() {
        top -= 2;
        return slots.double_(top);
    }

    public void pushByte(byte value) {
        slots.byte_(top, value);
        top += 1;
    }

    public byte popByte() {
        top -= 1;
        return slots.byte_(top);
    }

    public void pushChar(char value) {
        slots.char_(top, value);
        top += 1;
    }

    public char popChar() {
        top -= 1;
        return slots.char_(top);
    }

    public void pushShort(short value) {
        slots.short_(top, value);
        top += 1;
    }

    public short popShort() {
        top -= 1;
        return slots.short_(top);
    }

    public void pushReference(Reference reference) {
        slots.reference(top, reference);
        top += 1;
    }

    public Reference popReference() {
        top -= 1;
        return slots.reference(top);
    }

    public void push(String desc, Object value) {
        switch (desc.charAt(0)) {
            case Descriptors.DESC_byte:
            case Descriptors.DESC_char:
            case Descriptors.DESC_int:
            case Descriptors.DESC_short:
            case Descriptors.DESC_boolean:
                pushInt((int) value);
                break;
            case Descriptors.DESC_double:
                pushDouble((double) value);
                break;
            case Descriptors.DESC_float:
                pushFloat((float) value);
                break;
            case Descriptors.DESC_long:
                pushLong((long) value);
                break;
            case Descriptors.DESC_reference:
            case Descriptors.DESC_array:
                pushReference((Reference) value);
                break;
            default:
                throw new AssertionError();
        }
    }

    public Object pop(String desc) {
        switch (desc.charAt(0)) {
            case Descriptors.DESC_byte:
            case Descriptors.DESC_char:
            case Descriptors.DESC_int:
            case Descriptors.DESC_short:
            case Descriptors.DESC_boolean:
                return popInt();
            case Descriptors.DESC_double:
                return popDouble();
            case Descriptors.DESC_float:
                return popFloat();
            case Descriptors.DESC_long:
                return popLong();
            case Descriptors.DESC_reference:
            case Descriptors.DESC_array:
                return popReference();
            default:
                throw new AssertionError();
        }
    }

    public void pushSlots(Slots slots) {
        slots.copyTo(0, slots.size(), this.slots, top);
        top += slots.size();
    }

    public Slots popSlots(int count) {
        Slots slots1 = new Slots(count);
        top -= count;
        slots.copyTo(top, count, slots1, 0);
        return slots1;
    }

    public void clear() {
        top = 0;
    }
}
