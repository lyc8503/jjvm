package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class POP extends Instruction {

    private final boolean pop2;
    private final String name;

    public static POP POP2(ProgramCounter pc, MethodInfo method) {
        return new POP(true, "pop2");
    }

    public static POP POP(ProgramCounter pc, MethodInfo method) {
        return new POP(false, "pop");
    }


    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();

        Object o1 = stack.popSlots(1).value(0);

        assert o1 != null || pop2;
        assert !(o1 instanceof Double || o1 instanceof Long);

        if (pop2) {
            Object o2 = stack.popSlots(1).value(0);
            assert o2 != null;
            assert (o2 instanceof Double || o2 instanceof Long);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
