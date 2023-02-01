package adventOfCode2022;

import java.util.*;

public class Day12 extends Day<Day12.Heightmap>{


  public Day12() {
    super(12);
  }

  @Override
  public Heightmap parseInput(List<String> input) {
    return new Heightmap(input);
  }

  private int getDistanceFrom(int[][] heights, int[] start, int[] end) {
    int[][] minLen = new int[heights.length][heights[0].length];
    for (int[] arr: minLen) Arrays.fill(arr, Integer.MAX_VALUE);
    minLen[start[0]][start[1]] = 0;


    Set<int[]> used = new TreeSet<>((o1, o2) -> (o1[0] != o2[0]) ? o1[0] - o2[0] : o1[1] - o2[1]);
    used.add(start);

    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2])); // For dijkstra algorithm
    pq.add(new int[]{start[0], start[1], 0});

    while (!pq.isEmpty()) {
      int[] less = pq.poll(); // Get the location with the lesser height

      if (less[2] > minLen[less[0]][less[1]]) continue;

      // Check around the actual location
      for (int[] dir: Utils.DIR4) {
        int[] newDir = {less[0] + dir[0], less[1] + dir[1]};
        // if current location is in a border, check invalid locations
        if(newDir[0] < 0 || newDir[0] >= minLen.length || newDir[1] < 0 || newDir[1] >= minLen[newDir[0]].length) continue;;
        // if the minimum is already set in the current location
        if(used.contains(newDir)) continue;
        // if can't move due to the height
        if (heights[newDir[0]][newDir[1]] > heights[less[0]][less[1]] + 1) continue;
        // Set the min len in the current location
        minLen[newDir[0]][newDir[1]] = Math.min(minLen[newDir[0]][newDir[1]], less[2] + 1);

        // Found the shortest path to end
        if (newDir[0] == end[0] && newDir[1] == end[1]) {
          return minLen[newDir[0]][newDir[1]];
        }

        used.add(newDir);
        pq.add(new int[]{newDir[0], newDir[1], minLen[newDir[0]][newDir[1]]});
      }
    }

    return -1;
  }

  @Override
  public void solve1(Heightmap input) {
    System.out.println(getDistanceFrom(input.heights, input.start, input.end));
  }

  @Override
  public void solve2(Heightmap input) {
    int minDis = Integer.MAX_VALUE;
    for (int i = 0; i < input.heights.length; i++) {
      for (int j = 0; j < input.heights[i].length; j++) {
        if (input.heights[i][j] == 0) {
          int tmpDis = getDistanceFrom(input.heights, new int[] {i, j}, input.end);
          if (tmpDis != -1 && tmpDis < minDis) minDis = tmpDis;
        }
      }
    }

    System.out.println(minDis);

  }

  @Override
  public boolean isSolve1() {
    return false;
  }

  public class Heightmap {
    final int[] start;
    final int[] end;
    final int[][] heights;

    public Heightmap(List<String> in) {

      heights = new int[in.size()][];
      int[] start = null, end = null;

      int lineCount = 0;
      for (String line: in) {
        heights[lineCount] = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
          switch (line.charAt(i)) {
            case 'S':
              start = new int[]{lineCount, i};
              heights[lineCount][i] = 0;
              break;
            case 'E':
              end = new int[]{lineCount, i};
              heights[lineCount][i] = 'z' - 'a';
              break;
            default:
              heights[lineCount][i] = line.charAt(i) - 'a';
          }
        }

        lineCount++;
      }

      this.start = start;
      this.end = end;
    }
  }
}
