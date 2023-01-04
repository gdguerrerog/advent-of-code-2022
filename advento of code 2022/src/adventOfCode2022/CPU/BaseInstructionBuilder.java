package adventOfCode2022.CPU;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseInstructionBuilder implements InstructionBuilder {

  private final Pattern checkPattern;

  public BaseInstructionBuilder(Pattern checkPattern) {
    this.checkPattern = checkPattern;
  }

  @Override
  public InstructionBuilderCheckResult check(String instruction) {
    Matcher matcher = checkPattern.matcher(instruction);
    return getCheckResult(matcher);
  }

  protected abstract InstructionBuilderCheckResult getCheckResult(Matcher matcher);

  public static abstract class BaseCheckResult implements InstructionBuilderCheckResult {

    private final Matcher matcher;

    public BaseCheckResult(Matcher matcher) {
      this.matcher = matcher;
    }

    @Override
    public abstract Instruction build(SimpleCPU cpu);

    @Override
    public boolean valid() {
      return matcher.matches();
    }

    protected Matcher getMatcher() {
      return matcher;
    }
  }


}
