package vjvm.runtime;

import lombok.Getter;
import vjvm.utils.UnimplementedError;
import vjvm.vm.VMContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JThread {

  // The stack of frames
  private final ArrayList<JFrame> frames = new ArrayList<>();

  @Getter
  private final VMContext context;

  public JThread(VMContext context) {
    this.context = context;
  }

  public JFrame top() {
    throw new UnimplementedError("TODO: return the top frame");
  }

  public void pop() {
    throw new UnimplementedError("TODO: pop a frame");
  }

  public void push(JFrame frame) {
    throw new UnimplementedError("TODO: push a frame");
  }

  // Return the active pc, which is owned by the top-most frame
  public ProgramCounter pc() {
    return empty() ? null : top().pc();
  }

  public boolean empty() {
    return frames.isEmpty();
  }

  public List<JFrame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
