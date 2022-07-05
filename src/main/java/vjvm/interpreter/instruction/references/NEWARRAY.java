package vjvm.interpreter.instruction.references;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.ArrayReference;
import vjvm.runtime.heap.Reference;

import static vjvm.runtime.heap.Fields.getDefaultValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NEWARRAY extends Instruction {

    private final int type;

    private final String name;


    public static NEWARRAY NEWARRAY(ProgramCounter pc, MethodInfo method) {
        return new NEWARRAY(pc.ubyte(), "newarray");
    }

    public static NEWARRAY ANEWARRAY(ProgramCounter pc, MethodInfo method) {
        return new NEWARRAY(pc.ushort(), "anewarray");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int len = stack.popInt();

        Object default_ = null;

        if ("newarray".equals(name)) {
            switch (type) {
                case 6:  // Float
                    default_ = 0.0f;
                    break;
                case 7:  // Double
                    default_ = 0.0d;
                    break;
                case 4:  // Bool
                case 5:  // Char
                case 8:  // Byte
                case 9:  // Short
                case 10:  // Int
                    default_ = 0;
                    break;
                case 11:  // Long
                    default_ = 0L;
                    break;
            }
        } else {
            default_ = Reference.NULL;
        }

        ArrayReference<Object> reference = thread.context().heap().arrayAlloc(len, default_);
        stack.pushReference(reference);
    }

    @Override
    public String toString() {
        return name + " " + type;
    }
}
