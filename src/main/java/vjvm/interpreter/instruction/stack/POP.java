package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.LongConstant;

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

        Optional<Object> o1 = stack.popSlots(1).value(0);

        assert o1.isPresent() || pop2;
        assert !(o1.get() instanceof Double || o1.get() instanceof Long);

        if (pop2) {
            Optional<Object> o2 = stack.popSlots(1).value(0);
            assert o2.isPresent();
            assert (o2.get() instanceof Double || o2.get() instanceof Long);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
