package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.Reference;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class IF_ACMP extends Instruction {

    private final int branchByte;
    private final Condition condition;
    private final ProgramCounter pc;
    private final String name;

    public static IF_ACMP IF_ACMPEQ(ProgramCounter pc, MethodInfo method) {
        return new IF_ACMP(pc.short_(), Condition.EQ, pc, "if_acmpeq");
    }

    public static IF_ACMP IF_ACMPNE(ProgramCounter pc, MethodInfo method) {
        return new IF_ACMP(pc.short_(), Condition.NE, pc, "if_acmpne");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        Reference value2 = stack.popReference();
        Reference value1 = stack.popReference();
        boolean success = false;

        switch (condition) {
            case EQ:
                success = value1.innerIndex() == value2.innerIndex();
                break;
            case NE:
                success = value1.innerIndex() != value2.innerIndex();
                break;
            default:
                assert false;
        }

        if (success) {
            System.err.println("IF DEBUG: success, byte " + branchByte);
            pc.move(branchByte - 3);
        } else {
            System.err.println("IF DEBUG: fail");
        }
    }

    @Override
    public String toString() {
        return name + " " + branchByte;
    }
}
