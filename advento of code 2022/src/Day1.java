import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day<List<Integer>>{
  public Day1() {
    super(1);
  }

  @Override
  public List<Integer> parseInput(List<String> input) {
    return input.stream()
        .map(line -> line.isBlank() ? null : Integer.parseInt(line))
        .collect(Collectors.toList());
  }

  @Override
  public void solve1(List<Integer> input) {
    int max = 0, cSum = 0;
    for (Integer i: input) {
      if (i == null) {
        max = Math.max(max, cSum);
        cSum = 0;
      }
      else cSum += i;
    }

    max = Math.max(max, cSum);

    System.out.println(max);

  }

  @Override
  public void solve2(List<Integer> input) {
    List<Integer> totalElf = new LinkedList<>();

    int cSum = 0;
    for (Integer i: input) {
      if (i == null) {
        totalElf.add(cSum);
        cSum = 0;
      }
      else cSum += i;
    }

    totalElf.add(cSum);

    totalElf.sort(Integer::compare);
    cSum = 0;
    for (int i = 0; i < 3; i++ ){
      cSum += totalElf.get(totalElf.size() - 1 - i);
    }

    System.out.println(cSum);

  }

  @Override
  public boolean isSolve1() {
    return false;
  }



}
