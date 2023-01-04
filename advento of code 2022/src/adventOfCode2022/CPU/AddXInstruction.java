package adventOfCode2022.CPU;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddXInstruction extends Instruction {

  private int executedTicks;
  private final int value;

  public AddXInstruction(SimpleCPU cpu, int value) {
    super(cpu, "ADDX");
    this.value = value;
    executedTicks = 0;
  }

  @Override
  boolean hasCompleted() {
    return executedTicks >= 2;
  }

  @Override
  void run() {
    if (executedTicks == 1) {
      cpu.setReg("X", cpu.getReg("X") + value);
    }

    executedTicks++;
  }

  @Override
  void reset() {
    executedTicks = 0;
  }

  public int getValue() {
    return value;
  }

  public static class AddXInstructionBuilder extends BaseInstructionBuilder {

    public AddXInstructionBuilder() {
      super(Pattern.compile("^addx (?<value>-?\\d+)"));
    }

    @Override
    protected InstructionBuilderCheckResult getCheckResult(Matcher matcher) {
      return new AddXCheckResult(matcher);
    }
  }

  private static class AddXCheckResult extends BaseInstructionBuilder.BaseCheckResult {

    public AddXCheckResult(Matcher matcher) {
      super(matcher);
    }

    @Override
    public AddXInstruction build(SimpleCPU cpu) {
      int value = Integer.parseInt(getMatcher().group("value"));
      return new AddXInstruction(cpu, value);
    }
  }

}
