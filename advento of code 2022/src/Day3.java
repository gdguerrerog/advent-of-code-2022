import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day3 extends Day<List<String>> {

  public Day3() {
    super(3);
  }

  @Override
  public List<String> parseInput(List<String> input) {
    return input;
  }

  private int getPriority(char ch) {
    if (ch >= 'a' && ch <= 'z') return ch - 'a' + 1;
    return ch - 'A' + 27;
  }

  @Override
  public void solve1(List<String> input) {
    int totalPrio = 0;

    for (String ruckSack: input) {
      int sackSize = ruckSack.length()/2;
      boolean found = false;
      for (int i = 0; i < sackSize && !found; i++) {
        for (int j = 0; j < sackSize && !found; j++) {
          if (ruckSack.charAt(i) == ruckSack.charAt(j + sackSize)) {
            totalPrio += getPriority(ruckSack.charAt(i));
            found = true;
          }
        }
      }
    }

    System.out.println(totalPrio);
  }

  @Override
  public void solve2(List<String> input) {
    int totalPrio = 0;
    Set<Character> possibleBadges = new TreeSet<>();
    int totalGroup = 0;
    for (String ruckSack: input) {
      switch (totalGroup) {
        case 0:
          possibleBadges.clear();
          ruckSack.chars().forEach(ch -> possibleBadges.add((char) ch));
          break;
        case 1:
        case 2:
          Set<Character> newBadges = new TreeSet<>();
          ruckSack.chars().forEach(ch -> newBadges.add((char) ch));
          possibleBadges.retainAll(newBadges);
      }

      if (totalGroup == 2) for (Character b: possibleBadges) totalPrio += getPriority(b);

      totalGroup = (totalGroup + 1) % 3 ;
    }

    System.out.println(totalPrio);
  }

  @Override
  public boolean isSolve1() {
    return false;
  }
}
