import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {

    var day = new Day3();
    for (File f: day.getFiles()) {
      List<String> input = Files.readAllLines(f.toPath());
      System.out.println("########## File: " + f.getName());
      var inputObj = day.parseInput(input);
      if (day.isSolve1()) day.solve1(inputObj);
      else day.solve2(inputObj);
    }

  }
}