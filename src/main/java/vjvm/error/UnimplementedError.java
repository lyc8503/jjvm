package vjvm.error;

public class UnimplementedError extends Error {
    public UnimplementedError() {
    }

    public UnimplementedError(String message) {
        super(message);
    }
}
