package adventOfCode2022;

import adventOfCode2022.CPU.AddXInstruction;
import adventOfCode2022.CPU.InstructionExecution;
import adventOfCode2022.CPU.InstructionSet;
import adventOfCode2022.CPU.SimpleCPU;

import java.util.List;

public class Day10 extends Day<InstructionSet>{

  public Day10() {
    super(10);
  }

  @Override
  public InstructionSet parseInput(List<String> input) {
    SimpleCPU cpu = new SimpleCPU();
    InstructionSet is = new InstructionSet(cpu);

    for (String line: input) {
      boolean result = is.addInstruction(line);
      if (!result) System.out.println("Impossible parse instruction: " + line);
    }

    return is;
  }

  @Override
  public void solve1(InstructionSet input) {
    int signalStr = 0;
    InstructionExecution exec = new InstructionExecution(input);
    SimpleCPU cpu = input.getCpu();
    cpu.setReg("X", 1);

    while(!exec.isHalted()) {
      if ((exec.getCycle() - 19) % 40 == 0) {
        int currentStr = (exec.getCycle() + 1) * cpu.getReg("X");
        // System.out.printf("Cycle: %d, Xreg: %d\n", exec.getCycle(), cpu.getReg("X"));
        // System.out.printf("str: %d\n", currentStr);
        signalStr += currentStr;
      }
      exec.tick();
    }

//    System.out.println(input.getInstructions().stream()
//        .filter(i -> i instanceof AddXInstruction)
//        .mapToInt(i -> ((AddXInstruction) i).getValue()).sum());
//
//    System.out.println(exec.getCycle());
    System.out.println(signalStr);

  }

  @Override
  public void solve2(InstructionSet input) {
    int currentDraw = 0;
    InstructionExecution exec = new InstructionExecution(input);
    SimpleCPU cpu = input.getCpu();
    cpu.setReg("X", 1);


    StringBuilder sprite = new StringBuilder();
    while(!exec.isHalted()) {

      int x = cpu.getReg("X");
      int drawH = currentDraw % 40;
      if (Math.abs(drawH - x) <= 1) sprite.append("#");
      else sprite.append(" ");

      exec.tick();
      currentDraw++;
      if (exec.getCycle() % 40 == 0) sprite.append('\n');
    }

    System.out.println(sprite);

  }

  @Override
  public boolean isSolve1() {
    return false;
  }

}
