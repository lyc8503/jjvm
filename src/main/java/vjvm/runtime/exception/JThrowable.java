package vjvm.runtime.exception;

import lombok.Getter;
import lombok.var;
import vjvm.runtime.reference.ObjectReference;

public class JThrowable extends RuntimeException{

    @Getter
    private final ObjectReference ref;

    public JThrowable(ObjectReference reference) {
        var throwable = reference.jClass().classLoader().loadClass("Ljava/lang/Throwable;");
        assert reference.jClass().isSubclassOf(throwable);
        this.ref = reference;
    }

}
