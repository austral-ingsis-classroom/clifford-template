package edu.austral.ingsis.clifford.System;

import edu.austral.ingsis.clifford.Commands.Command;
import java.util.ArrayList;
import java.util.List;

public class MyFileSystem implements FileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public MyFileSystem() {
    this.root = new Directory("/", null);
    this.currentDirectory = root;
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String commandStr : commands) {
      Command command = parseCommand(commandStr);
      if (command != null) {
        results.add(command.execute());
      } else {
        results.add("Unknown command");
      }
    }
    return results;
  }

  @Override
  public Directory getRoot() {
    return root;
  }

  @Override
  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  @Override
  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }

  private Command parseCommand(String commandStr) {
    // Implementación del método parseCommand según tu implementación previa
    return null;
  }
}
