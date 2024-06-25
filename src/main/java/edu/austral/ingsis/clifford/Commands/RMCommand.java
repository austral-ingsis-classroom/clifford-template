package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;
import edu.austral.ingsis.clifford.System.FileSystemItem;
import java.util.Optional;

public class RMCommand implements Command {
  private final Directory currentDirectory;
  private final String name;
  private final boolean recursive;

  public RMCommand(Directory currentDirectory, String name, boolean recursive) {
    this.currentDirectory = currentDirectory;
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute() {
    Optional<FileSystemItem> item = currentDirectory.findItem(name);
    if (item.isPresent()) {
      if (item.get().isDirectory() && !recursive) {
        return "cannot remove '" + name + "', is a directory";
      }
      currentDirectory.removeItem(name);
      return "'" + name + "' removed";
    }
    return "'" + name + "' does not exist";
  }
}
