package adventOfCode2022;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day<T> {
  public final int day;

  public Day(int day) {
    this.day = day;
  }

  public abstract T parseInput(List<String> input);
  public abstract void solve1(T input);
  public abstract void solve2(T input);
  public abstract boolean isSolve1();

  public File getDayPath() {
    return Path.of("inputs", "adventOfCode2022.Day" + day).toFile();
  }

  public List<File> getFiles() {
    return Arrays.stream(getDayPath().listFiles()).filter(File::isFile).collect(Collectors.toList());
  }

}