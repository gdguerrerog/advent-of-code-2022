package adventOfCode2022.CPU;

import adventOfCode2022.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstructionSet {

  private static List<InstructionBuilder> builders = new LinkedList<>();
  static {
    builders.add(new NoopInstruction.NoopInstructionBuilder());
    builders.add(new AddXInstruction.AddXInstructionBuilder());
  }

  private List<InstructionBuilder.InstructionBuilderCheckResult> instructions;
  private final SimpleCPU cpu;

  public InstructionSet(SimpleCPU cpu) {
    this.cpu = cpu;
    instructions = new LinkedList<>();
  }

  public boolean addInstruction(String instruction) {
    Optional<InstructionBuilder.InstructionBuilderCheckResult> oib = builders.stream().map(lb -> lb.check(instruction.strip())).filter(cr -> cr.valid()).findAny();
    if (oib.isEmpty()) return false;
    instructions.add(oib.get());
    return true;
  }

  public List<Instruction> getInstructions() {
    return instructions.stream().map(ib -> ib.build(cpu)).collect(Collectors.toList());
  }

  public SimpleCPU getCpu() {
    return cpu;
  }

  @Override
  public String toString() {
    return instructions.toString();
  }

}
