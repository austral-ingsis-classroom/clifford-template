package edu.austral.ingsis.clifford;

import java.util.List;

public class CreateDirectory implements Command {
    private final FileSystem fileSystem;
    private final String name;

    public CreateDirectory(FileSystem fileSystem, String name) {
        this.fileSystem = fileSystem;
        this.name = name;
    }

    @Override
    public String execute() {
        new Directory(name, fileSystem.getCurrentDirectory());
        return "'" + name + "' directory created";
    }
}
