package adventOfCode2022;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Day13 extends Day<List<List>> {

  private InputParser parser;

  public Day13() {
    super(13);
    parser = new InputParser();
  }

  @Override
  public List<List> parseInput(List<String> input) {
    List<List> exit = new LinkedList<>();
    for (String str: input) if(!str.isBlank()) exit.add(parser.parse(str));
    return exit;
  }

  private int compare(Object o1, Object o2) {
    if (o1 instanceof Integer) {
      if (o2 instanceof Integer) return (Integer) o1 - (Integer) o2;
      else return compare(List.of(o1), o2);
    }

    // o1 is list

    if (o2 instanceof Integer) return compare(o1, List.of(o2));

    // o2 is list


    List l1 = (List) o1, l2 = (List) o2;
    Iterator i1 = l1.iterator(), i2 = l2.iterator();

    while (true) {

      boolean l1End = i1.hasNext();
      boolean l2End = i2.hasNext();

      if (!l1End) {
        if (!l2End) return 0; // Both lists have the same size
        return -1; // Left list is in order
      }

      if (!l2End) return 1; // Right list has fewer items tha left. In disorder

      Object co1 = i1.next();
      Object co2 = i2.next();

      int compResult = compare(co1, co2);
      if (compResult != 0) return compResult;
    }

  }

  @Override
  public void solve1(List<List> input) {

    int sum = 0;
    int pairIdx = 1;
    List lTmp = null;
    for (List list: input) {

      if (pairIdx % 2 == 1) lTmp = list;
      else {
        int comp = compare(lTmp, list);
        if (comp <= -1) {
          sum += pairIdx/2;
        }
      }

      pairIdx++;
    }

    System.out.println(">> " + sum);

  }

  @Override
  public void solve2(List<List> input) {
    input.add(List.of(List.of(2)));
    input.add(List.of(List.of(6)));


    List[] lArr = input.toArray(new List[input.size()]);

    Arrays.sort(lArr, (l1, l2) -> compare(l1, l2));

    int i2 = 0, i6 = 0;
    int cIndex = 0;
    for (List list: lArr) {
      cIndex++;
      if (list.size() != 1) continue;
      if (!(list.get(0) instanceof List)) continue;
      if (((List) list.get(0)).size() != 1) continue;
      if (!(((List) list.get(0)).get(0) instanceof Integer)) continue;
      if ((Integer)(((List) list.get(0)).get(0)) == 2) i2 = cIndex;
      if ((Integer)(((List) list.get(0)).get(0)) == 6) i6 = cIndex;
    }

    System.out.printf("i2: %d, i6: %d, mult: %d\n", i2, i6, i2 * i6);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }

  private static class InputParser {

    private int chIdx;
    private String s;
    public InputParser() {
      chIdx = 0;
    }

    public List parse(String s) {
      chIdx = 0;
      this.s = s;
      return parseList();
    }

    private Object internalParse() {
      if (s.isBlank()) return null;
      if (cChar() == '[') return parseList();
      return parseInteger();
    }

    private Integer parseInteger() {
      String numStr = "";
      while ('0' <= cChar() && cChar() <= '9') {
        numStr += cChar();
        chIdx++;
      }

      return Integer.parseInt(numStr);
    }

    private List parseList() {
      List exit = new LinkedList();
      chIdx++; // Skip first '['

      while(cChar() != ']') {
        exit.add(internalParse());
        if (cChar() == ',') chIdx++;
      }

      chIdx++; // Skip last ']'
      return exit;
    }

    private char cChar() {
      return s.charAt(chIdx);
    }

  }

}
