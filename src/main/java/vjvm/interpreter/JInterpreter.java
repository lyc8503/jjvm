package vjvm.interpreter;

import lombok.var;
import org.apache.commons.lang3.tuple.Triple;
import org.jline.utils.Log;
import vjvm.classfiledefs.MethodDescriptors;
import vjvm.error.UnimplementedError;
import vjvm.interpreter.instruction.Decoder;
import vjvm.runtime.exception.JThrowable;
import vjvm.runtime.frame.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.Slots;
import vjvm.runtime.class_.MethodInfo;
import vjvm.runtime.reference.Reference;
import vjvm.util.InputUtils;
import vjvm.util.Logger;

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
        Logger.debug("Invoke Method: " + method + ", " + args);
        var frame = new JFrame(method, args);
        Logger.debug("New FRAME: " + method);
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

            int pcPos = thread.pc().position();

            var op = Decoder.decode(thread.pc(), frame.method());
            Logger.trace("Current Stack: " + frame.stack().toString());
            Logger.trace("Instruction: " + op.toString() + ", " + thread.top());

            try {
                op.run(thread);
            } catch (JThrowable throwable) {
                Logger.debug("Throwable caught: " + throwable);

                // Find handler in the Code attribute
                int handler = frame.method().code().findExceptionHandler(pcPos, throwable.ref().jClass());

                if (handler == -1) {  // No handler found, frame is popped and the exception is rethrown
                    Logger.debug("No handler, rethrown: " + throwable);
                    thread.pop();

                    throw throwable;
                } else {  // Handler found, jump to the handler and clear the frame stack
                    Logger.debug("Handler target: " + handler);

                    frame.pc().position(handler);
                    var stack = frame.stack();
                    stack.popSlots(stack.top());

                    // Push the exception reference to the stack
                    stack.pushReference(throwable.ref());
                }
            }
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
            case DESC_reference:
                s.pushReference((Reference) ret);
                break;
            default:
                throw new UnimplementedError("Invalid return type");
        }
    }


    static {
        nativeTable.put(Triple.of("util/IOUtil", "readInt", "()I"), (t, a) -> InputUtils.readInt());
        nativeTable.put(Triple.of("util/IOUtil", "readLong", "()J"), (t, a) -> InputUtils.readLong());
        nativeTable.put(Triple.of("util/IOUtil", "readChar", "()C"), (t, a) -> InputUtils.readChar());
        nativeTable.put(Triple.of("util/IOUtil", "writeInt", "(I)V"), (t, a) -> {
            Logger.println(a.int_(0));
            return null;
        });
        nativeTable.put(Triple.of("util/IOUtil", "writeFloat", "(F)V"), (t, a) -> {
            Logger.println(a.float_(0));
            return null;
        });
        nativeTable.put(Triple.of("util/IOUtil", "writeLong", "(J)V"), (t, a) -> {
            Logger.println(a.long_(0));
            return null;
        });
        nativeTable.put(Triple.of("util/IOUtil", "writeDouble", "(D)V"), (t, a) -> {
            Logger.println(a.double_(0));
            return null;
        });
        nativeTable.put(Triple.of("util/IOUtil", "writeChar", "(C)V"), (t, a) -> {
            Logger.println(a.char_(0));
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

        nativeTable.put(Triple.of("java/lang/Class", "registerNatives", "()V"), (t, a) -> {
            // TODO: ignore
            return null;
        });

        nativeTable.put(Triple.of("java/lang/Class", "desiredAssertionStatus0", "(Ljava/lang/Class;)Z"), (t, a) -> {
            // TODO: ignore
            return false;
        });

        nativeTable.put(Triple.of("java/lang/Throwable", "fillInStackTrace", "(I)Ljava/lang/Throwable;"), (t, a) -> {
            // TODO: ignore
            return null;
        });

    }
}
