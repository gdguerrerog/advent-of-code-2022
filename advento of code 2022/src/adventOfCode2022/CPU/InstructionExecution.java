package adventOfCode2022.CPU;

import java.util.ArrayList;
import java.util.List;

public class InstructionExecution {

  private List<Instruction> instructions;
  private SimpleCPU cpu;
  private int instructionCounter;
  private int cycle;
  private boolean halted;

  public InstructionExecution(InstructionSet is) {
    instructions = new ArrayList<>(is.getInstructions());
    cpu = is.getCpu();
    instructionCounter = 0;
    cycle = 0;
    halted = false;
  }

  public boolean tick() {

    if (halted) return true;

    Instruction ins = instructions.get(instructionCounter);
    ins.run();
    cycle++;
    boolean endInstruction = ins.hasCompleted();
    if (endInstruction) {
      instructionCounter++;
    }

    if (instructionCounter >= instructions.size()) halted = true;

    return endInstruction;
  }

  public void instruction() {
    while(!tick());
  }

  public void runLeft() {
    while (!halted) tick();
  }

  public int getInstructionCounter() {
    return instructionCounter;
  }

  public int getCycle() {
    return cycle;
  }

  public boolean isHalted() {
    return halted;
  }

}
