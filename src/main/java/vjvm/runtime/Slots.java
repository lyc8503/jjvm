package vjvm.runtime;

import java.nio.ByteBuffer;
import java.util.Optional;

import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  public Slots(int slotSize) {
    // TODO: initialize data structures
    throw new UnimplementedError();
  }

  public int int_(int index) {
    // TODO: return the int at index
    throw new UnimplementedError();
  }

  public void int_(int index, int value) {
    // TODO: set the int at index
    throw new UnimplementedError();
  }

  public long long_(int index) {
    // TODO: return the long at index
    throw new UnimplementedError();
  }

  public void long_(int index, long value) {
    // TODO: set the long at index
    throw new UnimplementedError();
  }

  public float float_(int index) {
    // TODO: return the float at index
    throw new UnimplementedError();
  }

  public void float_(int index, float value) {
    // TODO: set the float at index
    throw new UnimplementedError();
  }

  public double double_(int index) {
    // TODO: return the double at index
    throw new UnimplementedError();
  }

  public void double_(int index, double value) {
    // TODO: set the double at index
    throw new UnimplementedError();
  }

  public int address(int index) {
    // TODO: return the address at index
    throw new UnimplementedError();
  }

  public void address(int index, int value) {
    // TODO: set the address at index
    throw new UnimplementedError();
  }

  public byte byte_(int index) {
    // TODO: return the byte at index
    throw new UnimplementedError();
  }

  public void byte_(int index, byte value) {
    // TODO: set the byte at index
    throw new UnimplementedError();
  }

  public char char_(int index) {
    // TODO: return the char at index
    throw new UnimplementedError();
  }

  public void char_(int index, char value) {
    // TODO: set the char at index
    throw new UnimplementedError();
  }

  public short short_(int index) {
    // TODO: return the short at index
    throw new UnimplementedError();
  }

  public void short_(int index, short value) {
    // TODO: set the short at index
    throw new UnimplementedError();
  }

  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    return Optional.empty();
  }

  public int size() {
    // TODO: return the size in the number of slots
    throw new UnimplementedError();
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // TODO: copy from this:[begin, begin+length) to dest:[destBegin,destBegin+length)
    throw new UnimplementedError();
  }
}
