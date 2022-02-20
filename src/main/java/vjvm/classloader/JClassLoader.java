package vjvm.classloader;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.vm.VMContext;

import java.io.Closeable;
import java.util.HashMap;

public class JClassLoader implements Closeable {
  private final JClassLoader parent;
  private final ClassSearchPath[] searchPaths;
  private final HashMap<String, JClass> definedClass = new HashMap<>();
  @Getter
  private final VMContext context;

  public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths, VMContext context) {
    this.context = context;
    this.parent = parent;
    this.searchPaths = searchPaths;
  }

  public JClass loadClass(String descriptor) {
    return new JClass();
  }

  @Override
  @SneakyThrows
  public void close() {
    for (var s : searchPaths)
      s.close();
  }
}
