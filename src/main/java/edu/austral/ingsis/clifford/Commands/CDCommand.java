package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;
import edu.austral.ingsis.clifford.System.FileSystem;
import edu.austral.ingsis.clifford.System.FileSystemItem;
import java.util.Optional;

public class CDCommand implements Command {
  private final FileSystem fileSystem;
  private String path;

  public CDCommand(FileSystem fileSystem, String path) {
    this.fileSystem = fileSystem;
    this.path = path;
  }

  @Override
  public String execute() {
    Directory currentDirectory = fileSystem.getCurrentDirectory();

    if (path.equals(".")) {
      return "moved to directory '" + currentDirectory.getName() + "'";
    }

    if (path.equals("..")) {
      Directory parent = currentDirectory.getParent();
      if (parent != null) {
        fileSystem.setCurrentDirectory(parent);
      }
      return "moved to directory '" + (parent != null ? parent.getName() : "/") + "'";
    }

    if (path.equals("/")) {
      fileSystem.setCurrentDirectory(fileSystem.getRoot());
      return "moved to directory '/'";
    }

    if (path.startsWith("/")) {
      currentDirectory = fileSystem.getRoot();
    }

    String[] parts = path.split("/");
    for (String part : parts) {
      if (part.isEmpty()) {
        continue;
      }
      Optional<FileSystemItem> next = currentDirectory.findItem(part);
      if (next.isPresent() && next.get() instanceof Directory) {
        currentDirectory = (Directory) next.get();
      } else {
        return "'" + part + "' directory does not exist";
      }
    }

    fileSystem.setCurrentDirectory(currentDirectory);
    return "moved to directory '" + currentDirectory.getName() + "'";
  }
}
