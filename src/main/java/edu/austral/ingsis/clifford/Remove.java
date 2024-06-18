package edu.austral.ingsis.clifford;

import java.util.List;

public class Remove implements Command {
  private final FileSystem fileSystem;
  private final String name;
  private final boolean recursive;

  public Remove(FileSystem fileSystem, String name, boolean recursive) {
    this.fileSystem = fileSystem;
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute() {
    List<Node> items = fileSystem.getCurrentDirectory().getChildren();
    Node itemToRemove = null;
    for (Node item : items) {
      if (item.getName().equals(name)) {
        itemToRemove = item;
        break;
      }
    }
    if (itemToRemove != null) {
      if (itemToRemove instanceof Directory && !recursive) {
        return "cannot remove '" + name + "', is a directory";
      } else {
        fileSystem.getCurrentDirectory().removeChild(itemToRemove);
        return "'" + name + "' removed";
      }
    }
    return "Error: File or directory not found";
  }
}
