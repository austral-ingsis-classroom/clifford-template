package edu.austral.ingsis.clifford;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListChildren implements Command {
  private final FileSystem fileSystem;
  private final String ord;

  public ListChildren(FileSystem fileSystem, String ord) {
    this.fileSystem = fileSystem;
    this.ord = ord;
  }

  @Override
  public String execute() {
    List<Node> children = fileSystem.getCurrentDirectory().getChildren();
    List<String> names = children.stream().map(Node::getName).collect(Collectors.toList());

    if (Objects.equals(ord, "desc")) {
      Collections.sort(names, Collections.reverseOrder());
    } else if (ord == "asc") {
      Collections.sort(names);
    }

    return String.join(" ", names);
  }
}
