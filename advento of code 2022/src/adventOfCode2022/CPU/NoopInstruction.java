package adventOfCode2022.CPU;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoopInstruction extends Instruction {

  public NoopInstruction(SimpleCPU cpu) {
    super(cpu, "NOOP");
  }

  @Override
  boolean hasCompleted() {
    return true;
  }

  @Override
  void run() {}

  @Override
  void reset() {}

  public static class NoopInstructionBuilder extends BaseInstructionBuilder {

    public NoopInstructionBuilder() {
      super(Pattern.compile("^noop"));
    }

    @Override
    protected InstructionBuilderCheckResult getCheckResult(Matcher matcher) {
      return new NoopCheckResult(matcher);
    }
  }

  private static class NoopCheckResult extends BaseInstructionBuilder.BaseCheckResult {

    public NoopCheckResult(Matcher matcher) {
      super(matcher);
    }

    @Override
    public Instruction build(SimpleCPU cpu) {
      return new NoopInstruction(cpu);
    }
  }
}
