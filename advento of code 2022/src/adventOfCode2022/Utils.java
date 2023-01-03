package adventOfCode2022;

public class Utils {

  public static final int[][] DIR4 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public static class Pair<P1, P2> {
    public final P1 p1;
    public final P2 p2;

    public Pair(P1 p1, P2 p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
  }

}
