package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

enum Condition {
    EQ, NE, LT, GE, GT, LE
}

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class IF extends Instruction {

    private final int branchByte;
    private final Condition condition;
    private final ProgramCounter pc;
    private final String name;


    public static IF IFEQ(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.EQ, pc, "ifeq");
    }

    public static IF IFNE(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.NE, pc, "ifne");
    }

    public static IF IFLT(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.LT, pc, "iflt");
    }

    public static IF IFGE(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.GE, pc, "ifge");
    }

    public static IF IFGT(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.GT, pc, "ifgt");
    }

    public static IF IFLE(ProgramCounter pc, MethodInfo method) {
        return new IF(pc.short_(), Condition.LE, pc, "ifle");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int value1 = stack.popInt();
        boolean success = false;

        switch (condition) {
            case EQ:
                success = value1 == 0;
                break;
            case GE:
                success = value1 >= 0;
                break;
            case GT:
                success = value1 > 0;
                break;
            case LE:
                success = value1 <= 0;
                break;
            case LT:
                success = value1 < 0;
                break;
            case NE:
                success = value1 != 0;
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
