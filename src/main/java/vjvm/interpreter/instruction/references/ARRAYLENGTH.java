package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.frame.ProgramCounter;
import vjvm.runtime.heap.ArrayReference;

public class ARRAYLENGTH extends Instruction {

    public ARRAYLENGTH(ProgramCounter pc, MethodInfo method) {

    }

    @Override
    public void run(JThread thread) {
        var stack = thread.top().stack();
        int len = ((ArrayReference) stack.popReference()).length();
        stack.pushInt(len);
    }

    @Override
    public String toString() {
        return "arraylength";
    }
}
