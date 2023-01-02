import java.util.List;

public class Day6 extends Day<String> {

  public Day6() {
    super(6);
  }

  @Override
  public String parseInput(List<String> input) {
    return input.get(0);
  }

  private int solve(String input, int markerSize) {
    char[] arr = input.toCharArray();
    int[] used = new int['z' - 'a' + 1];

    for (int i = 0; i < markerSize; i++) used[arr[i] - 'a']++;

    for (int i = markerSize; i < arr.length; i++) {

      boolean found = true;

      for (int j = 1; j <= markerSize && found; j++) {
        if (used[arr[i - j] - 'a'] > 1) found = false;
      }

      if (found) return i;

      used[arr[i - markerSize] - 'a']--;
      used[arr[i] - 'a']++;
    }

    return -1;
  }

  @Override
  public void solve1(String input) {
    System.out.println(solve(input, 4));
  }

  @Override
  public void solve2(String input) {
    System.out.println(solve(input, 14));
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
