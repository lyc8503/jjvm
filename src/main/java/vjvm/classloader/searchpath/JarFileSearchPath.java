package vjvm.classloader.searchpath;


import vjvm.util.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JarFileSearchPath extends ClassSearchPath {

    final String path;

    Vector<InputStream> streams;

    JarFileSearchPath(String path) {
        Logger.debug("JarFilePath: " + path);

        this.path = path;
        streams = new Vector<>();
    }

    @Override
    public InputStream findClass(String name) {

        Logger.debug("JarFileSearch: " + name);

        try {
            JarFile jarFile = new JarFile(path);
            InputStream stream = jarFile.getInputStream(new ZipEntry(name.substring(1).replace(";", "") + ".class"));

            if (stream != null) {
                streams.add(stream);
            }
            return stream;
        } catch (Exception e) {
            Logger.debug(e.toString());
        }

        return null;
    }

    @Override
    public void close() throws IOException {
        for (InputStream stream : streams) {
            stream.close();
        }
    }
}
