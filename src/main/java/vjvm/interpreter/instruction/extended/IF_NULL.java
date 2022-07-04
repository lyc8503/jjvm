package vjvm.interpreter.instruction.extended;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.interpreter.instruction.comparisons.Condition;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.Reference;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_NULL extends Instruction {
    private final int branchByte;
    private final Condition condition;
    private final ProgramCounter pc;

    private final String name;

    public static IF_NULL IFNULL(ProgramCounter pc, MethodInfo method) {
        return new IF_NULL(pc.short_(), Condition.EQ, pc, "ifnull");
    }

    public static IF_NULL IFNONNULL(ProgramCounter pc, MethodInfo method) {
        return new IF_NULL(pc.short_(), Condition.NE, pc, "ifnonnull");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        var value = stack.popReference();
        boolean success = false;

        switch (condition) {
            case EQ:
                success = value == Reference.NULL;
                break;
            case NE:
                success = value != Reference.NULL;
                break;
            default:
                assert false;
        }

        if (success) {
            pc.move(branchByte - 3);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
