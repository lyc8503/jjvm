package vjvm.classloader;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.utils.UnimplementedError;
import vjvm.vm.VMContext;

import java.io.Closeable;
import java.io.DataInputStream;
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

        // 如果已经获取就直接返回
        JClass defined = definedClass.get(descriptor);
        if (defined != null) {
            return defined;
        }

        // 使用 parent 加载 class 如果 parent 不为 null
        if (parent != null) {
            JClass parentResult = parent.loadClass(descriptor);
            if (parentResult != null) {
                return parentResult;
            }
        }

        // 依次尝试使用各个 ClassSearchPath 加载类
        for (ClassSearchPath searchPath : searchPaths) {
            var result = searchPath.findClass(descriptor);
            if (result != null) {
                JClass clazz = new JClass(new DataInputStream(result), this);
                definedClass.put(descriptor, clazz);  // 缓存结果
                return clazz;
            }
        }

        return null;
    }

    @Override
    @SneakyThrows
    public void close() {
        for (var s : searchPaths)
            s.close();
    }
}
