package adventOfCode2022;

import java.util.regex.Pattern;

public class Utils {

  public static final int[][] DIR4 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  public static final Pattern INTEGER = Pattern.compile("-?\\d+");

  public static class UPair<P1, P2> {

    public final P1 p1;
    public final P2 p2;

    public UPair(P1 p1, P2 p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
    @Override
    public String toString() {
      return String.format("pair(%s, %s)", p1, p2);
    }

  }


  public static class Pair<P1 extends Comparable<P1>, P2 extends Comparable<P2>> extends UPair<P1, P2> implements Comparable<Pair<P1, P2>>{

    public Pair(P1 p1, P2 p2) {
      super(p1, p2);
    }

    @Override
    public int compareTo(Pair<P1, P2> o) {
      int comparation = p1.compareTo(o.p1);
      if (comparation != 0) return comparation;
      return p2.compareTo(o.p2);
    }

  }


}
