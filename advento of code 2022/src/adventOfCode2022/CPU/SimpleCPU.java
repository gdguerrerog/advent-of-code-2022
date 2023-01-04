package adventOfCode2022.CPU;

import java.util.Map;
import java.util.TreeMap;

public class SimpleCPU {

  private final Map<String, Integer> registers;

  public SimpleCPU() {
    registers = new TreeMap<>();

  }

  public Integer getReg(String regName) {
    return registers.getOrDefault(regName, 0);
  }

  public Integer setReg(String regName, Integer value) {
    return registers.put(regName, value);
  }

}
