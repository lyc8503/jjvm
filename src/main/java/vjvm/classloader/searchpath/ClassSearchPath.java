package vjvm.classloader.searchpath;

import vjvm.util.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
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
        return Arrays.stream(path.split(System.getProperty("path.separator")))
            .map(s -> s.endsWith(".jar") ? new JarFileSearchPath(s) : new ClassFileSearchPath(s))
            .toArray(ClassSearchPath[]::new);
    }

    /**
     * Find a class with specified name.
     *
     * @param name name of the class to find.
     * @return Returns a stream containing the binary data if such class is found, or null if not.
     */
    public abstract InputStream findClass(String name);
}
