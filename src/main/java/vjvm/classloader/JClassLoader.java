package vjvm.classloader;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.utils.UnimplementedError;
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

    /**
     * Load class
     * <p>
     * If a class is found, construct it using the data returned by ClassSearchPath.findClass and return it.
     * <p>
     * Otherwise, return null.
     */
    public JClass loadClass(String descriptor) {
        throw new UnimplementedError("TODO: load class");

        // To construct a JClass, use the following constructor
        // return new JClass(new DataInputStream(istream_from_file), this);
    }

    @Override
    @SneakyThrows
    public void close() {
        for (var s : searchPaths)
            s.close();
    }
}
