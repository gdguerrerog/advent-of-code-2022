package adventOfCode2022;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day11 extends Day<Day11.Monkey[]>{

  public Day11() {
    super(11);
  }

  @Override
  public Monkey[] parseInput(List<String> input) {

    List<Monkey> monkeys = new LinkedList<>();

    String[] arr = new String[7];
    int lineCounter = 0;
    for (String line: input) {

      arr[lineCounter] = line;

      lineCounter++;
      if (lineCounter == 6) {
        monkeys.add(Monkey.instanceMonkey(arr));
      }

      if (lineCounter == 7) {
        lineCounter = 0;
      }
    }

    return monkeys.toArray(new Monkey[monkeys.size()]);
  }

  private void printMonkeyState(Monkey[] input) {
    for (Monkey m: input) {
      System.out.printf("Monkey %d (%d): %s\n", m.id, m.timesInspect, m.items);
    }
  }

  @Override
  public void solve1(Monkey[] input) {
    for (int i = 0; i < 20; i++) {
      for (Monkey m: input) m.turn(input);
    }

    printMonkeyState(input);

    Arrays.sort(input, Comparator.comparingInt(m -> m.timesInspect));
    System.out.println(input[input.length - 1].timesInspect*input[input.length - 2].timesInspect);

  }

  @Override
  public void solve2(Monkey[] input) {
    for (int i = 0; i < 10000; i++) {
      for (Monkey m: input) m.turn2(input);
    }

    printMonkeyState(input);

    Arrays.sort(input, Comparator.comparingInt(m -> m.timesInspect));
    BigInteger i1 = BigInteger.valueOf(input[input.length - 1].timesInspect);
    BigInteger i2 = BigInteger.valueOf(input[input.length - 2].timesInspect);
    System.out.println(i1.multiply(i2));
  }

  @Override
  public boolean isSolve1() {
    return false;
  }

  public static class Monkey {
    public final int id;
    public final LinkedList<Long> items;
    public final char operationType;
    public final int operationValue;
    public final int divisibleBy;
    public final int throwTrue;
    public final int throwFalse;
    public int timesInspect;


    private Monkey(int id, List<Long> items, char operationType, int operationValue, int divisibleBy, int throwTrue, int throwFalse) {
      this.id = id;
      this.items = new LinkedList<>(items);
      this.operationType = operationType;
      this.operationValue = operationValue;
      this.divisibleBy = divisibleBy;
      this.throwTrue = throwTrue;
      this.throwFalse = throwFalse;

      timesInspect = 0;
    }

    public static Monkey instanceMonkey(String[] lines) {
      Matcher matcher = Pattern.compile("Monkey (?<monkeyId>\\d+):").matcher(lines[0]);
      matcher.matches();
      int id = Integer.parseInt(matcher.group("monkeyId"));

      matcher = Pattern.compile(" *Starting items: (?<items>(\\d+(, )?)+)").matcher(lines[1]);
      matcher.matches();
      List<Long> items = Arrays.stream(matcher.group("items").split(", ")).map(Long::parseLong).collect(Collectors.toList());

      matcher = Pattern.compile(" *Operation: new = old (?<opId>\\+|\\*) (?<opVal>\\d+|old)").matcher(lines[2]);
      matcher.matches();
      char opType = matcher.group("opId").charAt(0);
      String val = matcher.group("opVal");
      int opValue = (val.compareTo("old") == 0) ? -1 : Integer.parseInt(val);

      matcher = Pattern.compile(" *Test: divisible by (?<divVal>\\d+)").matcher(lines[3]);
      matcher.matches();
      int divVal = Integer.parseInt(matcher.group("divVal"));

      Pattern throwPattern = Pattern.compile(" *If (true|false): throw to monkey (?<mokeyId>\\d+)");
      matcher = throwPattern.matcher(lines[4]);
      matcher.matches();
      int throwTrue = Integer.parseInt(matcher.group("mokeyId"));

      matcher = throwPattern.matcher(lines[5]);
      matcher.matches();
      int throwFalse = Integer.parseInt(matcher.group("mokeyId"));

      return new Monkey(id, items, opType, opValue, divVal, throwTrue, throwFalse);
    }

    private Long applyWorry(Long i) {

      long val = operationValue == -1 ? i : operationValue;

      switch(operationType) {
        case '+': return i + val;
        case '*': return i * val;
      }

      System.out.println("Unknown operation: " + operationType);

      return null;
    }

    private boolean check(Long i) {
      return i % divisibleBy == 0;
    }

    public void turn(Monkey[] monkeys) {
      while (!items.isEmpty()) {

        Long item = items.pop();

        timesInspect++;

        item = applyWorry(item);
        item = item/3;

        if (check(item)) monkeys[throwTrue].items.add(item);
        else monkeys[throwFalse].items.add(item);
      }
    }

    public void turn2(Monkey[] monkeys) {

      long totalDivisors = Arrays.stream(monkeys).mapToLong(m -> m.divisibleBy).reduce(1, (l1, l2) -> l1*l2);
      while (!items.isEmpty()) {

        Long item = items.pop();

        timesInspect++;

        item = applyWorry(item);
        item = item % totalDivisors;

        if (check(item)) monkeys[throwTrue].items.add(item);
        else monkeys[throwFalse].items.add(item);
      }
    }

  }




}
