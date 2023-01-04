package adventOfCode2022.CPU;

import adventOfCode2022.Day10;

public abstract class Instruction {
    protected final SimpleCPU cpu;
    public final String name;

    public Instruction(SimpleCPU cpu, String name) {
      this.cpu = cpu;
      this.name = name;
    }

    abstract boolean hasCompleted();
    abstract void run();

    abstract void reset();

    @Override
    public String toString() {
      return name;
    }
}
