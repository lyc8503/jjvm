package vjvm.classloader.searchpath;


import org.jline.utils.Log;
import vjvm.util.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class ClassFileSearchPath extends ClassSearchPath {

    final String path;

    Vector<InputStream> streams;

    ClassFileSearchPath(String path) {
        Logger.debug("ClassFilePath: " + path);

        this.path = path;
        streams = new Vector<>();
    }

    @Override
    public InputStream findClass(String name) {

        Logger.debug("ClassFileSearch: " + name);

        try {
            String actualPath = path + File.separator + name.substring(1).replace(";", "") + ".class";
            InputStream stream = Files.newInputStream(Paths.get(actualPath));

            streams.add(stream);
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
