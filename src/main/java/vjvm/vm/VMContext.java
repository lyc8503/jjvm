package vjvm.vm;

import lombok.Getter;
import vjvm.classloader.JClassLoader;
import vjvm.utils.UnimplementedError;

public class VMContext {
  @Getter
  private final JClassLoader bootstrapLoader;
  @Getter
  private final JClassLoader userLoader;

  VMContext(String userClassPath) {
    throw new UnimplementedError("TODO: construct bootstrap loader and user loader");
  }
}
