package vjvm.runtime;

import vjvm.classloader.JClassLoader;

import vjvm.utils.UnimplementedError;
import java.io.DataInput;

public class JClass {
  public JClass(DataInput dataInput, JClassLoader classLoader) {
    throw new UnimplementedError("TODO: you need to construct JClass from dataInput in lab 1.2; remove this for lab 1.1");
  }
}
