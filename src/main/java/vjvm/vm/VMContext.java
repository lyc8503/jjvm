package vjvm.vm;

import lombok.Getter;
import vjvm.classloader.JClassLoader;
import vjvm.classloader.searchpath.ClassSearchPath;

public class VMContext {
  @Getter
  private final JClassLoader bootstrapLoader;
  @Getter
  private final JClassLoader userLoader;

  VMContext(String userClassPath) {
    bootstrapLoader = new JClassLoader(
      null,
      ClassSearchPath.constructSearchPath("sun.boot.class.path"),
      this
    );

    userLoader = new JClassLoader(
      bootstrapLoader,
      ClassSearchPath.constructSearchPath(userClassPath),
      this
    );
  }
}
