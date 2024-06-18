package edu.austral.ingsis.clifford;

public class PrintWorking implements Command {
    private final FileSystem fileSystem;

    public PrintWorking(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute() {
        StringBuilder path = new StringBuilder();
        Directory currentDirectory = fileSystem.getCurrentDirectory();
        path.insert(0, currentDirectory.getName());
        currentDirectory = currentDirectory.getParent();
        while (currentDirectory != null) {
            path.insert(0, currentDirectory.getName() + "/");
            currentDirectory = currentDirectory.getParent();
        }

        return path.toString();
    }
}
