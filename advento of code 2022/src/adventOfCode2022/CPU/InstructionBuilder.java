package adventOfCode2022.CPU;

public interface InstructionBuilder {
  InstructionBuilderCheckResult check(String instruction);

  interface InstructionBuilderCheckResult {
    Instruction build(SimpleCPU cpu);
    boolean valid();
  }
}
