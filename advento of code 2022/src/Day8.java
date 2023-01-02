import java.util.List;

public class Day8 extends Day<int[][]>{

  public Day8() {
    super(8);
  }

  @Override
  public int[][] parseInput(List<String> input) {
    int[][] exit = new int[input.size()][];

    int i = 0;
    for (String line: input) {
      exit[i] = line.chars().map(ch -> ch - '0').toArray();
      i++;
    }

    return exit;
  }

  private boolean isVisible(int[][] input, int x, int y) {
    int hb = -1, ha = -1, h = input[y][x]; // height before, height after, height

    for (int i = 0; i < input.length; i++) {
      if (i < y) hb = Math.max(hb, input[i][x]);
      else if(i > y) ha = Math.max(ha, input[i][x]);
    }

    if (hb < h || ha < h) return true;

    hb = ha = -1;

    for (int i = 0; i < input[y].length; i++) {
      if (i < x) hb = Math.max(hb, input[y][i]);
      else if(i > x) ha = Math.max(ha, input[y][i]);
    }

    return hb < h || ha < h;
  }


  @Override
  public void solve1(int[][] input) {
    int visible = 0;
    for (int y = 0; y < input.length; y++) {
      for (int x = 0; x < input[y].length; x++) {
        if (isVisible(input, x, y)) visible++;
      }
    }

    System.out.println(visible);
  }


  public int getPointInDirection(int[][] input, int x, int y, int[] dir) {
    int h = input[y][x];
    int cX = x + dir[0], cY = y + dir[1];
    int points = 0;
    while(cX >= 0 && cY >= 0 && cY < input.length && cX < input[cY].length) {
      if (input[cY][cX] >= h) {
        points++;
        break;
      }
      points++;
      cX += dir[0];
      cY += dir[1];
    }

    return points;
  }

  @Override
  public void solve2(int[][] input) {
    int maxPoint = 0;
    for (int y = 0; y < input.length; y++) {
      for (int x = 0; x < input[y].length; x++) {
        int points = 1;
        for (int[] dir: Utils.DIR4) {
          points *= getPointInDirection(input, x, y, dir);
        }
        maxPoint = Math.max(maxPoint, points);
      }
    }

    System.out.println(maxPoint);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
