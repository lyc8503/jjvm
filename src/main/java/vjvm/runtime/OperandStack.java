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
    throw new UnimplementedError("TODO: initialize data structures");
  }

  public void pushInt(int value) {
    throw new UnimplementedError("TODO: push value");
  }

  public int popInt() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushFloat(float value) {
    throw new UnimplementedError("TODO: push value");
  }

  public float popFloat() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushLong(long value) {
    throw new UnimplementedError("TODO: push value");
  }

  public long popLong() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushDouble(double value) {
    throw new UnimplementedError("TODO: push value");
  }

  public double popDouble() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushAddress(int value) {
    throw new UnimplementedError("TODO: push value");
  }

  public int popAddress() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushByte(byte value) {
    throw new UnimplementedError("TODO: push value");
  }

  public byte popByte() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushChar(char value) {
    throw new UnimplementedError("TODO: push value");
  }

  public char popChar() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushShort(short value) {
    throw new UnimplementedError("TODO: push value");
  }

  public short popShort() {
    throw new UnimplementedError("TODO: pop value");
  }

  public void pushSlots(Slots slots) {
    throw new UnimplementedError("TODO: push value");
  }

  public Slots popSlots(int count) {
    throw new UnimplementedError("TODO: pop value");
  }

  public void clear() {
    throw new UnimplementedError("TODO: pop all slots");
  }
}
