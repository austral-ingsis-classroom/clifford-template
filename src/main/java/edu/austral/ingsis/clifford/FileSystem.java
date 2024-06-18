package edu.austral.ingsis.clifford;

public class FileSystem {
  private final Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    this.root = new Directory("");
    this.currentDirectory = root;
  }

  public FileSystem(Directory root) {
    this.root = root;
    this.currentDirectory = root;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory directory) {
    currentDirectory = directory;
  }

  public Directory getRoot() {
    return root;
  }

  public String executeCommand(String commandLine) {
    String[] tokens = commandLine.split(" ");
    String commandName = tokens[0];

    Command command = null;

    switch (commandName) {
      case "ls":
        String order = tokens.length > 1 ? tokens[1].split("=")[1] : null;
        command = new ListChildren(this, order);
        break;
      case "cd":
        String path = tokens[1];
        command = new ChangeDirectory(this, path);
        break;
      case "touch":
        String fileName = tokens[1];
        command = new Touch(this, fileName);
        break;
      case "mkdir":
        String dirName = tokens[1];
        command = new CreateDirectory(this, dirName);
        break;
      case "rm":
        String name;
        boolean recursive = false;
        if (tokens.length > 2) {
          name = tokens[2];
          recursive = tokens[1].equals("--recursive");
        } else {
          name = tokens[1];
        }
        command = new Remove(this, name, recursive);
        break;
      case "pwd":
        command = new PrintWorking(this);
        break;
      default:
        return "Error: Unknown command";
    }

    return command.execute();
  }
}
