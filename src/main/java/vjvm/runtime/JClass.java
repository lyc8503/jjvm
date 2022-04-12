package vjvm.runtime;

import vjvm.classloader.JClassLoader;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.utils.UnimplementedError;
import java.io.DataInput;
import java.io.InvalidClassException;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;

public class JClass {
  @Getter
  private final JClassLoader classLoader;
  @Getter
  private final int minorVersion;
  @Getter
  private final int majorVersion;
  @Getter
  private final ConstantPool constantPool;
  @Getter
  private final int accessFlags;
  private final Attribute[] attributes;

  @SneakyThrows
  public JClass(DataInput dataInput, JClassLoader classLoader) {
    this.classLoader = classLoader;

    // check magic number
    var magic = dataInput.readInt();
    if (magic != 0xcafebabe) {
      throw new InvalidClassException(String.format(
        "Wrong magic number, expected: 0xcafebabe, got: 0x%x", magic));
    }

    minorVersion = dataInput.readUnsignedShort();
    majorVersion = dataInput.readUnsignedShort();

    constantPool = new ConstantPool(dataInput, this);
    accessFlags = dataInput.readUnsignedShort();

    throw new UnimplementedError(
        "TODO: you need to construct thisClass, superClass, interfaces, fields, "
        + "methods, and attributes from dataInput in lab 1.2; remove this for lab 1.1");
  }
}
