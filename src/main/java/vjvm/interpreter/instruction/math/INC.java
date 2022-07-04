package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class INC extends Instruction {
    /**
     * INC 指令
     */

    private final ProgramCounter pc;

    public static INC IINC(ProgramCounter pc, MethodInfo method) {
        return new INC(pc);
    }

    @Override
    public void run(JThread thread) {

        var slots = thread.top().vars();

        int index = pc.ubyte();
        int value = pc.byte_();

        slots.int_(index, slots.int_(index) + value);
    }

    @Override
    public String toString() {
        return null;
    }
}
