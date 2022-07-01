package vjvm.runtime;

import lombok.Getter;

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
