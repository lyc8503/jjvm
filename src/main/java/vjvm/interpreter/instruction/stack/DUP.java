package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.frame.Slots;
import vjvm.runtime.class_.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class DUP extends Instruction {

    private final int category;
    private final int xDown;
    private final String name;

    public static DUP DUP(ProgramCounter pc, MethodInfo method) {
        return new DUP(1, 0, "dup");
    }

    public static DUP DUP_X1(ProgramCounter pc, MethodInfo method) {
        return new DUP(1, 1, "dup_x1");
    }

    public static DUP DUP_X2(ProgramCounter pc, MethodInfo method) {
        return new DUP(1, 2, "dup_x2");
    }

    public static DUP DUP2(ProgramCounter pc, MethodInfo method) {
        return new DUP(2, 0, "dup2");
    }

    public static DUP DUP2_X1(ProgramCounter pc, MethodInfo method) {
        return new DUP(2, 1, "dup2_x1");
    }

    public static DUP DUP2_X2(ProgramCounter pc, MethodInfo method) {
        return new DUP(2, 2, "dup_x2");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();

        // TODO: right order??

        Slots x_slots = null;
        if (xDown != 0) {
            x_slots = stack.popSlots(category * xDown);
        }

        Slots target = stack.popSlots(category);
        stack.pushSlots(target);
        if (xDown != 0) {
            stack.pushSlots(x_slots);
        }
        stack.pushSlots(target);
    }

    @Override
    public String toString() {
        return name;
    }
}
