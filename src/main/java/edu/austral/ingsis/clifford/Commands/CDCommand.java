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
            if (currentDirectory.getParent() != null) {
                fileSystem.setCurrentDirectory(currentDirectory.getParent());
            }
            return "moved to directory '/'";
        }
        if (path.startsWith("/")) {
            currentDirectory = fileSystem.getRoot();
            path = path.substring(1);
        }
        String[] parts = path.split("/");
        for (String part : parts) {
            Optional<FileSystemItem> next = currentDirectory.findItem(part);
            if (next.isPresent() && next.get().isDirectory()) {
                currentDirectory = (Directory) next.get();
            } else {
                return "'" + part + "' directory does not exist";
            }
        }
        fileSystem.setCurrentDirectory(currentDirectory);
        return "moved to directory '" + currentDirectory.getName() + "'";
    }
}
