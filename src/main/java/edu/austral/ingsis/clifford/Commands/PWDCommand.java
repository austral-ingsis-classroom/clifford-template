package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;
import java.util.Objects;

public class PWDCommand implements Command {
  private final Directory currentDirectory;

  public PWDCommand(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  @Override
  public String execute() {
    StringBuilder path = new StringBuilder();
    Directory dir = currentDirectory;
    while (dir != null && !Objects.equals(dir.getName(), "/")) {
      path.insert(0, "/" + dir.getName());
      dir = dir.getParent();
    }
    return path.toString().replace("//", "/");
  }
}
