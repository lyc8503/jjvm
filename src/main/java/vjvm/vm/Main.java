package vjvm.vm;

import lombok.var;
import picocli.CommandLine;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.class_.JClass;
import vjvm.util.Logger;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(name = "jjvm", mixinStandardHelpOptions = true, version = "jjvm 0.0.1",
    description = "A toy JVM written in java")
public class Main implements Callable<Integer> {
    @Option(names = {"-cp",
        "--classpath"}, paramLabel = "CLASSPATH",
        description = "the class path to search, multiple paths should be separated by ':'")
    String userClassPath = ".";

    @Parameters(index = "0", description = "Class to run, e.g. vjvm.vm.Main")
    private String entryClass = "";

    @Parameters(index = "1..*", description = "Arguments passed to java program")
    private String[] args = {};

    @Option(names = {"--debug-dump"}, description = "Dump content for classfile")
    private boolean dump = false;

    public static void main(String[] args) {
        System.exit(new CommandLine(new Main()).execute(args));
    }

    @Override
    public Integer call() {
        var ctx = new VMContext(userClassPath);

        if (dump) {
            // the package vjvm.classfiledefs contains some constants and utility
            // functions that we provided for your convenience
            // please check the individual files for more information
            var descriptor = Descriptors.of(entryClass);

            var clazz = ctx.userLoader().loadClass(descriptor);
            if (clazz == null) {
                Logger.debug(String.format("Can not find class %s", entryClass));
                return -1;
            }

            dump(clazz);
        } else {
            ctx.run(entryClass);
        }
        return 0;
    }

    private void dump(JClass clazz) {
        Logger.println(("class name: " + clazz.thisClass()));

        Logger.println(("minor version: " + clazz.minorVersion()));
        Logger.println(("major version: " + clazz.majorVersion()));

        Logger.println(String.format("flags: 0x%x", clazz.accessFlags()));
        Logger.println("");

        Logger.println("this class: " + clazz.thisClass());
        Logger.println(("super class: " + clazz.superClass()));

        Logger.println("constant pool: ");
        for (int i = 1; i < clazz.constantPool().size(); i++) {
            if (clazz.constantPool().constant(i) != null) {
                Logger.println(String.format("#%d = %s", i, clazz.constantPool().constant(i).toString()));
                Logger.println("");
            }
        }

        Logger.println("interfaces: ");
        for (int i = 0; i < clazz.interfacesCount(); i++) {
            Logger.println(clazz.interfaceName(i));
        }

        Logger.println("fields: ");
        for (int i = 0; i < clazz.fieldsCount(); i++) {
            Logger.println(clazz.field(i).toString());
        }

        Logger.println("methods: ");
        for (int i = 0; i < clazz.methodsCount(); i++) {
            Logger.println(clazz.method(i).toString());
        }
    }
}
