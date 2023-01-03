package adventOfCode2022;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day<List<String[]>>{

  public Day2() {
    super(2);
  }

  @Override
  public List<String[]> parseInput(List<String> input) {
    return input.stream().map(line -> line.split(" ")).collect(Collectors.toList());
  }

  private static int parseInput1(String str) {
    switch (str) {
      case "X":
      case "A": return 0;
      case "Y":
      case "B": return 1;
      case "Z":
      case "C": return 2;
    }
    return -1;
  }

  private static int getPoints(int played) {
    switch (played) {
      case 0: return 1;
      case 1: return 2;
      case 2: return 3;
    }

    return Integer.MIN_VALUE;
  }

  private static int getRoundPoints(Integer[] round) {
    if(round[0] == round[1]) return 3 + getPoints(round[1]);

    boolean win = round[1] == (round[0] + 1) % 3;

    if(win) return 6 + getPoints(round[1]);
    else return getPoints(round[1]);
  }

  @Override
  public void solve1(List<String[]> input) {
    List<Integer[]> mappedInput = input.stream()
        .map(iStr -> new Integer[]{parseInput1(iStr[0]), parseInput1(iStr[1])})
        .collect(Collectors.toList());

    int total = mappedInput.stream().map(Day2::getRoundPoints).mapToInt(x -> x).sum();
    System.out.println(total);
  }

  @Override
  public void solve2(List<String[]> input) {
    List<Integer[]> mappedInput = input.stream()
        .map(strArr -> {
          int i1 = parseInput1(strArr[0]), i2;
          switch (strArr[1]) {
            case "X":
              i2 = (i1 + 2) % 3;
              break;
            case "Y":
              i2 = i1;
              break;
            case "Z":
              i2 = (i1 + 1) % 3;
              break;
            default:
              i2 = -1;
          }

          return new Integer[]{i1, i2};
        })
        .collect(Collectors.toList());

    int total = mappedInput.stream().map(Day2::getRoundPoints).mapToInt(x -> x).sum();
    System.out.println(total);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
