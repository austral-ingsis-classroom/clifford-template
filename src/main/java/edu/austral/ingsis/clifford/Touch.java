package edu.austral.ingsis.clifford;

public class Touch implements Command {
    final private FileSystem fileSystem;
    final private String name;

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
