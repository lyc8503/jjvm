package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

public class OperandStack {
  @Getter
  private final Slots slots;

  @Getter
  private int top;

  public OperandStack(int stackSize) {
    // TODO: initialize data structures
    throw new UnimplementedError();
  }

  public void pushInt(int value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public int popInt() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushFloat(float value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public float popFloat() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushLong(long value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public long popLong() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushDouble(double value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public double popDouble() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushAddress(int value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public int popAddress() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushByte(byte value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public byte popByte() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushChar(char value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public char popChar() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushShort(short value) {
    // TODO: push value
    throw new UnimplementedError();
  }

  public short popShort() {
    // TODO: pop value
    throw new UnimplementedError();
  }

  public void pushSlots(Slots slots) {
    // TODO: push slots
    throw new UnimplementedError();
  }

  public Slots popSlots(int count) {
    // TODO: pop count slots and return
    throw new UnimplementedError();
  }

  public void clear() {
    // TODO: pop all slots
    throw new UnimplementedError();
  }
}
