package adventOfCode2022;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import adventOfCode2022.Utils.Pair;

public class Day9 extends Day<List<Pair<Character, Integer>>> {

  public Day9() {
    super(9);
  }

  @Override
  public List<Pair<Character, Integer>> parseInput(List<String> input) {
    return input.stream()
        .map(line -> line.split(" "))
        .map(sp -> new Pair<Character, Integer>(sp[0].charAt(0), Integer.parseInt(sp[1])))
        .collect(Collectors.toList());
  }

  private int[] calculateHCoord(Character c, int hX, int hY) {
    switch (c) {
      case 'R': return new int[]{hX + 1, hY};
      case 'L': return new int[]{hX - 1, hY};
      case 'U': return new int[]{hX, hY + 1};
      case 'D': return new int[]{hX, hY - 1};
    };

    return null;
  }

  private int[] calculateTCoord(int hX, int hY, int tX, int tY) {

    int dX = hX - tX;
    int dY = hY - tY;

    if (Math.abs(dX) > 2 || Math.abs(dY) > 2) {
      System.out.println("Error: dX | dY > 2: " + dX + ", " + dY);
    }

    if (Math.abs(dX) == 2) {
      tX += Math.signum(dX);
      if (Math.abs(dY) >= 1) {
        tY += Math.signum(dY);
      }
    } else if (Math.abs(dY) == 2) {
      tY += Math.signum(dY);
      if (Math.abs(dX) >= 1) {
        tX += Math.signum(dX);
      }
    }

    return new int[]{tX, tY};
  }

  @Override
  public void solve1(List<Pair<Character, Integer>> input) {
    TreeSet<Pair<Integer, Integer>> set = new TreeSet<>();
    set.add(new Pair<>(0, 0));

    int hX = 0, hY = 0, tX = 0, tY = 0;

    for (Pair<Character, Integer> info: input) {
      for (int i = 0; i < info.p2; i++) {
        int[] tmp = calculateHCoord(info.p1, hX, hY);
        hX = tmp[0]; hY = tmp[1];
        tmp = calculateTCoord(hX, hY, tX, tY);
        tX = tmp[0]; tY = tmp[1];
        set.add(new Pair<>(tX, tY));
      }
    }

    // System.out.println(set);
    System.out.println(set.size());

  }

  @Override
  public void solve2(List<Pair<Character, Integer>> input) {
    TreeSet<Pair<Integer, Integer>> set = new TreeSet<>();
    set.add(new Pair<>(0, 0));

    final int ropes = 10;
    int[][] pos = new int[ropes][2];

    for (Pair<Character, Integer> info: input) {
      for (int i = 0; i < info.p2; i++) {
        pos[0] = calculateHCoord(info.p1, pos[0][0], pos[0][1]);
        for (int j = 1; j < ropes; j++) {
          pos[j] = calculateTCoord(pos[j - 1][0], pos[j - 1][1], pos[j][0], pos[j][1]);
        }

        set.add(new Pair<>(pos[ropes - 1][0], pos[ropes - 1][1]));
      }
    }

    // System.out.println(set);
    System.out.println(set.size());
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
