package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.class_.MethodInfo;
import vjvm.util.Logger;


@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class IF_ICMP extends Instruction {

    private final int branchByte;
    private final Condition condition;
    private final ProgramCounter pc;
    private final String name;


    public static IF_ICMP IF_ICMPEQ(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.EQ, pc, "if_icmpeq");
    }

    public static IF_ICMP IF_ICMPNE(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.NE, pc, "if_icmpne");
    }

    public static IF_ICMP IF_ICMPLT(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.LT, pc, "if_icmplt");
    }

    public static IF_ICMP IF_ICMPGE(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.GE, pc, "if_icmpge");
    }

    public static IF_ICMP IF_ICMPGT(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.GT, pc, "if_icmpgt");
    }

    public static IF_ICMP IF_ICMPLE(ProgramCounter pc, MethodInfo method) {
        return new IF_ICMP(pc.short_(), Condition.LE, pc, "if_icmple");
    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        boolean success = false;

        switch (condition) {
            case EQ:
                success = value1 == value2;
                break;
            case GE:
                success = value1 >= value2;
                break;
            case GT:
                success = value1 > value2;
                break;
            case LE:
                success = value1 <= value2;
                break;
            case LT:
                success = value1 < value2;
                break;
            case NE:
                success = value1 != value2;
                break;
            default:
                assert false;
        }

        if (success) {
            Logger.debug("IF DEBUG: success, byte " + branchByte);
            pc.move(branchByte - 3);
        } else {
            Logger.debug("IF DEBUG: fail");
        }
    }

    @Override
    public String toString() {
        return name + " " + branchByte;
    }
}
