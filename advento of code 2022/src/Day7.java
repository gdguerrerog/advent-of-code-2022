import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends Day<Day7.DirectoryNode> {

  public Day7() {
    super(7);
  }

  @Override
  public DirectoryNode parseInput(List<String> input) {
    DirectoryNode root = new DirectoryNode("/", null);
    DirectoryNode currentDirectory = root;

    for (String line: input) {
      String[] values = line.split(" ");
      if (values[0].compareTo("$") == 0) {
        if (values[1].compareTo("cd") == 0) {
          switch (values[2]) {
            case "/": currentDirectory = root; break;
            case "..": currentDirectory = currentDirectory.father; break;
            default: currentDirectory = (DirectoryNode) currentDirectory.getFileByName(values[2]);
          }
        }
      } else { // ls files
        if (values[0].compareTo("dir") == 0) {
          currentDirectory.files.add(new DirectoryNode(values[1], currentDirectory));
        } else {
          currentDirectory.files.add(new FileNode(values[1], Long.parseLong(values[0]), currentDirectory));
        }
      }
    }

    return root;
  }

  private long[] solve1GetSize(DirectoryNode dir) {
    long cSize = dir.getFiles().stream().mapToLong(f -> f.getSize()).sum();
    long less10kSize = 0;
    for (DirectoryNode childDir: dir.getDirectories()) {
      long[] result = solve1GetSize(childDir);
      cSize += result[0];
      less10kSize += result[1];
    }

    if (cSize <= 100000) less10kSize += cSize;

    return new long[]{cSize, less10kSize};
  }

  @Override
  public void solve1(DirectoryNode input) {
    long[] result = solve1GetSize(input);
    System.out.println(result[1]);
  }


  public long solve2GetSizeMinDirectory(DirectoryNode dir, long requiredSpace) {
    long size = dir.getSize();
    long minRequiredSpace = (size >= requiredSpace) ? size: Integer.MAX_VALUE;
    for (DirectoryNode childDir: dir.getDirectories()) {
      long minChildSize = solve2GetSizeMinDirectory(childDir, requiredSpace);
      if (minRequiredSpace - requiredSpace > minChildSize - requiredSpace) {
        minRequiredSpace = minChildSize;
      }
    }

    return minRequiredSpace;
  }
  @Override
  public void solve2(DirectoryNode input) {
    System.out.println((solve2GetSizeMinDirectory(input, 30000000 - (70000000 - input.getSize()))));
  }

  @Override
  public boolean isSolve1() {
    return false;
  }

  static abstract class Node {

    public final String name;
    public final DirectoryNode father;
    public Node(String name, DirectoryNode father) {
      this.name = name;
      this.father = father;
    }
    public abstract long getSize();
  }

  static class FileNode extends Node {

    private final long size;
    public FileNode(String name, long size, DirectoryNode father) {
      super(name, father);
      this.size = size;
    }

    @Override
    public long getSize() {
      return size;
    }
  }
  static class DirectoryNode extends Node {

    public final List<Node> files;
    private long size;

    DirectoryNode(String name, DirectoryNode father) {
      super(name, father);
      files = new LinkedList<>();
      size = -1;
    }

    @Override
    public long getSize() {
      if (size == -1) {
        return size = files.stream().mapToLong(n -> n.getSize()).sum();
      }

      return size;
    }

    public Node getFileByName(String name) {
      return files.stream().filter(f -> f.name.compareTo(name) == 0).findAny().get();
    }

    public List<DirectoryNode> getDirectories() {
      return files.stream().filter(f -> f instanceof DirectoryNode).map(d -> (DirectoryNode) d).collect(Collectors.toList());
    }

    public List<FileNode> getFiles() {
      return files.stream().filter(f -> f instanceof FileNode).map(f -> (FileNode) f).collect(Collectors.toList());
    }
  }

}
