package adventOfCode2022;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day4 extends Day<List<int[]>>{

  public Day4() {
    super(4);
  }

  @Override
  public List<int[]> parseInput(List<String> input) {
    List<int[]> exit = new LinkedList<>();
    for (String str: input) {
      int[] arr = Arrays.stream(str.split("-|,")).map(Integer::parseInt).mapToInt(x->x).toArray();
      exit.add(arr);
    }

    return exit;
  }

  @Override
  public void solve1(List<int[]> input) {
    int totalOverlap = 0;

    for (int[] intervals: input) {

      int[] minInt = new int[]{intervals[0], intervals[1]};
      int[] maxInt = new int[]{intervals[2], intervals[3]};

      if (maxInt[0] == minInt[0]) {
        totalOverlap++;
        continue;
      }

      if (maxInt[0] < minInt[0]) {
        int[] tmp = minInt;
        minInt = maxInt;
        maxInt = tmp;
      }

      // System.out.println(Arrays.toString(minInt) + " --- " + Arrays.toString(maxInt));
      if (minInt[1] >= maxInt[1]) totalOverlap++;
    }

    System.out.println(totalOverlap);
  }

  @Override
  public void solve2(List<int[]> input) {
    int totalOverlap = 0;

    for (int[] intervals: input) {

      int[] minInt = new int[]{intervals[0], intervals[1]};
      int[] maxInt = new int[]{intervals[2], intervals[3]};

      if (maxInt[0] == minInt[0]) {
        totalOverlap++;
        continue;
      }

      if (maxInt[0] < minInt[0]) {
        int[] tmp = minInt;
        minInt = maxInt;
        maxInt = tmp;
      }

      // System.out.println(Arrays.toString(minInt) + " --- " + Arrays.toString(maxInt));
      if (minInt[1] >= maxInt[0]) totalOverlap++;
    }

    System.out.println(totalOverlap);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
