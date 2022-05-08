package vjvm.classloader.searchpath;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Represents a path to search class files in.
 * A search path may hold resources, such as jar files, so it must implement the Closeable interface.
 * If a subclass doesn't hold any resources, then just do nothing.
 */
public abstract class ClassSearchPath implements Closeable {
    /**
     * Construct search path objects with a given path.
     */
    public static ClassSearchPath[] constructSearchPath(String path) {
        return Arrays.stream(path.split(System.getProperty("path.separator"))).map((s) -> new ClassSearchPath() {
            @Override
            public InputStream findClass(String name) {
                InputStream stream = null;

                if (s.endsWith(".jar")) {
                    // 在 jar 文件中查找
                    try {
                        JarFile jarFile = new JarFile(s);
                        stream = jarFile.getInputStream(new ZipEntry(name.substring(1).replace(";", "") + ".class"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // 在目录下查找
                    try {
                        String actualPath = s + File.separator + name.substring(1).replace(";", "") + ".class";
                        System.err.println("Searching for path: " + actualPath);
                        stream = Files.newInputStream(Paths.get(actualPath));
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }

                return stream;
            }

            @Override
            public void close() throws IOException {
                // TODO
            }
        }).toArray((IntFunction<ClassSearchPath[]>) ClassSearchPath[]::new);
    }

    /**
     * Find a class with specified name.
     *
     * @param name name of the class to find.
     * @return Returns a stream containing the binary data if such class is found, or null if not.
     */
    public abstract InputStream findClass(String name);
}
