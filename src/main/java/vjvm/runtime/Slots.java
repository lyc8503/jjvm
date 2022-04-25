package vjvm.runtime;

import java.nio.ByteBuffer;

import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification.
 * It supports getting and putting primitive data types, including address.
 */
public class Slots {
  private final ByteBuffer buf;

  public Slots(int slotSize) {
    buf = ByteBuffer.allocate(slotSize * 4);
  }

  public int int_(int index) {
    return buf.getInt(index * 4);
  }

  public void int_(int index, int value) {
    buf.putInt(index * 4, value);
  }

  public long long_(int index) {
    throw new UnimplementedError("TODO: return the long at index");
  }

  public void long_(int index, long value) {
    throw new UnimplementedError("TODO: set the long at index");
  }

  public float float_(int index) {
    throw new UnimplementedError("TODO: return the float at index");
  }

  public void float_(int index, float value) {
    throw new UnimplementedError("TODO: set the float at index");
  }

  public double double_(int index) {
    throw new UnimplementedError("TODO: return the double at index");
  }

  public void double_(int index, double value) {
    throw new UnimplementedError("TODO: set the double at index");
  }

  public int address(int index) {
    return buf.getInt(index * 4);
  }

  public void address(int index, int value) {
    buf.putInt(index, value);
  }

  public byte byte_(int index) {
    return (byte)int_(index);
  }

  public void byte_(int index, byte value) {
    int_(index, value);
  }

  public char char_(int index) {
    throw new UnimplementedError("TODO: return the char at index");
  }

  public void char_(int index, char value) {
    throw new UnimplementedError("TODO: set the char at index");
  }

  public short short_(int index) {
    throw new UnimplementedError("TODO: return the short at index");
  }

  public void short_(int index, short value) {
    throw new UnimplementedError("TODO: set the short at index");
  }

  public int size() {
    return buf.limit() / 4;
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    if (dest == this && destBegin > begin)
      for (int i = length - 1; i >= 0; --i)
        dest.int_(destBegin + i, int_(begin + i));
    else
      for (int i = 0; i < length; ++i) {
        dest.int_(destBegin + i, int_(begin + i));
      }
  }
}
