package edu.austral.ingsis.clifford;

public class Touch implements Command {
  private final FileSystem fileSystem;
  private final String name;

  public Touch(FileSystem fileSystem, String name) {
    this.fileSystem = fileSystem;
    this.name = name;
  }

  @Override
  public String execute() {
    new File(name, fileSystem.getCurrentDirectory());
    return "'" + name + "' file created";
  }
}
