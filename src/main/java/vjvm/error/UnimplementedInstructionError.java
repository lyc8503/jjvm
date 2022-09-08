package vjvm.error;

public class UnimplementedInstructionError extends UnimplementedError {
    public UnimplementedInstructionError(int opcode) {
        super(String.format("Unimplemented opcode %d", opcode));
    }
}
