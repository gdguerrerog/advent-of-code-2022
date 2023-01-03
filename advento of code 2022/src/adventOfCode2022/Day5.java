package adventOfCode2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 extends Day<Day5.Day5Input> {

  public Day5() {
    super(5);
  }

  @Override
  public Day5Input parseInput(List<String> input) {
    Day5Input exit = new Day5Input();
    exit.stacks = new ArrayList<>();
    exit.operations = new LinkedList<>();

    Pattern stackP = Pattern.compile("(( {3}|\\[[A-Z]\\]) ?)+");
    Pattern opP = Pattern.compile("move (?<q>\\d+) from (?<f>\\d+) to (?<t>\\d+)");
    for (String line: input) {

      if (stackP.matcher(line).matches()) {
        for (int i = 0; 4*i < line.length(); i++) {
          if (line.charAt(4*i + 1) != ' ') {
            while (exit.stacks.size() <= i) exit.stacks.add(new LinkedList<>());
            exit.stacks.get(i).addLast(line.charAt(4*i + 1));
          }
        }
      }

      Matcher m = opP.matcher(line);

      if(m.matches()) {
        exit.operations.add(new int[] {Integer.parseInt(m.group("q")), Integer.parseInt(m.group("f")), Integer.parseInt(m.group("t"))});
      }
    }

    return exit;
  }

  private void applyOperation(List<Deque<Character>> stacks, int[] op) {
    Deque q1 = stacks.get(op[1] - 1);
    Deque q2 = stacks.get(op[2] - 1);
    for (int i = 0; i < op[0]; i++) {
      q2.addFirst(q1.removeFirst());
    }
  }

  private void applyOperation2(List<Deque<Character>> stacks, int[] op) {
    Deque<Character> q1 = stacks.get(op[1] - 1);
    Deque<Character> q2 = stacks.get(op[2] - 1);
    Deque<Character> tmp = new LinkedList();

    for (int i = 0; i < op[0]; i++) {
      tmp.addFirst(q1.removeFirst());
    }

    for (int i = 0; i < op[0]; i++) {
      q2.addFirst(tmp.removeFirst());
    }
  }

  @Override
  public void solve1(Day5Input input) {
    for (int[] op: input.operations) {
      applyOperation(input.stacks, op);
    }

    String out = "";
    for (Deque stack: input.stacks) {
      out += stack.getFirst();
    }

    System.out.println(out);

  }

  @Override
  public void solve2(Day5Input input) {
    for (int[] op: input.operations) {
      applyOperation2(input.stacks, op);
    }

    String out = "";
    for (Deque stack: input.stacks) {
      out += stack.getFirst();
    }

    System.out.println(out);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }

  static class Day5Input {
    List<Deque<Character>> stacks;
    List<int[]> operations;
  }
}
