package vjvm.interpreter;

import lombok.var;
import org.apache.commons.lang3.tuple.Triple;
import vjvm.classfiledefs.MethodDescriptors;
import vjvm.interpreter.instruction.Decoder;
import vjvm.runtime.frame.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.Slots;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.utils.InputUtils;

import java.util.HashMap;
import java.util.function.BiFunction;

import static vjvm.classfiledefs.Descriptors.*;

public class JInterpreter {
    // (ClassName, MethodName, MethodDescriptor) -> HackFunction
    private static final HashMap<Triple<String, String, String>, BiFunction<JThread, Slots, Object>> nativeTable = new HashMap<>();

    /**
     * Invoke a method when there is no frames in a thread.
     *
     * @param method the method to call
     * @param thread the thread to run
     * @param args   the supplied arguments, index begins at 0
     */
    public void invoke(MethodInfo method, JThread thread, Slots args) {
        var frame = new JFrame(method, args);
        thread.push(frame);

        if (method.native_()) {
            runNativeMethod(thread);
        } else {
            run(thread);
        }
    }


    private void run(JThread thread) {
        var frame = thread.top();

        while (thread.top() == frame) {

            System.err.println("PC: " + frame.pc().position());

            var op = Decoder.decode(thread.pc(), frame.method());

            System.err.println("Instruction: " + op.toString());

            op.run(thread);
        }
    }

    private void runNativeMethod(JThread thread) {
        var frame = thread.top();
        var method = frame.method();
        assert method.native_();

        var key = Triple.of(method.jClass().name(), method.name(), method.descriptor());
        var impl = nativeTable.get(key);
        if (impl == null) {
            throw new Error(String.format("Unimplemented native method: %s", key));
        }

        var ret = impl.apply(thread, frame.vars());
        thread.pop();
        var s = thread.top().stack();

        switch (MethodDescriptors.returnType(method.descriptor())) {
            case DESC_void:
                break;
            case DESC_boolean:
                s.pushInt(((Boolean) ret) ? 1 : 0);
                break;
            case DESC_byte:
                s.pushInt((Byte) ret);
                break;
            case DESC_char:
                s.pushInt((Character) ret);
                break;
            case DESC_double:
                s.pushDouble((Double) ret);
                break;
            case DESC_float:
                s.pushFloat((Float) ret);
                break;
            case DESC_int:
                s.pushInt((Integer) ret);
                break;
            case DESC_long:
                s.pushLong((Long) ret);
                break;
            case DESC_short:
                s.pushInt((Short) ret);
                break;
            default:
                throw new Error("Invalid return type");
        }
    }


    static {
        nativeTable.put(Triple.of("lab2/IOUtil", "readInt", "()I"), (t, a) -> InputUtils.readInt());
        nativeTable.put(Triple.of("lab2/IOUtil", "readLong", "()J"), (t, a) -> InputUtils.readLong());
        nativeTable.put(Triple.of("lab2/IOUtil", "readChar", "()C"), (t, a) -> InputUtils.readChar());
        nativeTable.put(Triple.of("lab2/IOUtil", "writeInt", "(I)V"), (t, a) -> {
            System.err.println("IOUtil: int " + a.int_(0));
            System.out.println(a.int_(0));
            return null;
        });
        nativeTable.put(Triple.of("lab2/IOUtil", "writeFloat", "(F)V"), (t, a) -> {
            System.err.println("IOUtil: float " + a.float_(0));
            System.out.println(a.float_(0));
            return null;
        });
        nativeTable.put(Triple.of("lab2/IOUtil", "writeLong", "(J)V"), (t, a) -> {
            System.err.println("IOUtil: long " + a.long_(0));
            System.out.println(a.long_(0));
            return null;
        });
        nativeTable.put(Triple.of("lab2/IOUtil", "writeDouble", "(D)V"), (t, a) -> {
            System.err.println("IOUtil: double " + a.double_(0));
            System.out.println(a.double_(0));
            return null;
        });
        nativeTable.put(Triple.of("lab2/IOUtil", "writeChar", "(C)V"), (t, a) -> {
            System.err.println("IOUtil: char " + a.char_(0));
            System.out.println(a.char_(0));
            return null;
        });

        nativeTable.put(Triple.of("java/lang/Object", "registerNatives", "()V"), (t, a) -> {
            // TODO: ignore
            return null;
        });

        nativeTable.put(Triple.of("java/lang/System", "registerNatives", "()V"), (t, a) -> {
            // TODO: ignore
            return null;
        });


    }
}
