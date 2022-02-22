package vjvm.vm;

import lombok.Getter;
import vjvm.classloader.JClassLoader;
import vjvm.utils.UnimplementedError;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.classloader.searchpath.ModuleSearchPath;

import java.lang.module.ModuleFinder;

public class VMContext {
  @Getter
  private final JClassLoader bootstrapLoader;
  @Getter
  private final JClassLoader userLoader;

  VMContext(String userClassPath) {
    bootstrapLoader = new JClassLoader(
      null,
      new ClassSearchPath[]{new ModuleSearchPath(ModuleFinder.ofSystem())},
      this
    );

    userLoader = new JClassLoader(
      bootstrapLoader,
      ClassSearchPath.constructSearchPath(userClassPath),
      this
    );
  }
}
