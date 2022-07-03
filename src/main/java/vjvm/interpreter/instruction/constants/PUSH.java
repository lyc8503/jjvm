package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PUSH extends Instruction {
    private final int value;
    private final String name;

    public static PUSH BIPUSH(ProgramCounter pc, MethodInfo method) {
        return new PUSH(pc.byte_(), "bipush");
    }

    public static PUSH SIPUSH(ProgramCounter pc, MethodInfo method) {
        return new PUSH(pc.short_(), "sipush");
    }

    @Override
    public void run(JThread thread) {
        var opStack = thread.top().stack();
        opStack.pushInt(value);
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, value);
    }
}
