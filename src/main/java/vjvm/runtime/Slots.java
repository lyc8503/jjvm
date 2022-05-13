package vjvm.runtime;

import java.nio.ByteBuffer;

import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  public Slots(int slotSize) {
    throw new UnimplementedError("TODO: initialize data structures");
  }

  public int int_(int index) {
    throw new UnimplementedError("TODO: return the int at index");
  }

  public void int_(int index, int value) {
    throw new UnimplementedError("TODO: set the int at index");
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
    throw new UnimplementedError("TODO: return the address at index");
  }

  public void address(int index, int value) {
    throw new UnimplementedError("TODO: set the address at index");
  }

  public byte byte_(int index) {
    throw new UnimplementedError("TODO: return the byte at index");
  }

  public void byte_(int index, byte value) {
    throw new UnimplementedError("TODO: set the byte at index");
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

  public Object value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    return null;
  }

  public int size() {
    throw new UnimplementedError("TODO: return the size in the number of slots");
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    throw new UnimplementedError("TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)");
  }
}
