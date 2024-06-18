package edu.austral.ingsis.clifford;

import java.util.Objects;

public class ChangeDirectory implements Command {
    private final FileSystem fileSystem;
    private final String path;

    public ChangeDirectory(FileSystem fileSystem, String path) {
        this.fileSystem = fileSystem;
        this.path = path;
    }

    public String execute() {
        Directory currentDirectory = fileSystem.getCurrentDirectory();

        if (Objects.equals(path, "/")) {
            fileSystem.setCurrentDirectory(fileSystem.getRoot());
            return "moved to directory '/'";
        }

        String[] components = path.split("/");

        if (Objects.equals(components[0], "..")) {
            Directory parent = currentDirectory.getParent();
            if (parent != null) {
                fileSystem.setCurrentDirectory(parent);
                if (parent == fileSystem.getRoot()) {
                    return "moved to directory '/'";
                }
                return "Moved to directory: '" + parent.getName() + "'";
            } else {
                return "Already at root directory";
            }
        } else if (Objects.equals(components[0], ".")) {
            return "Moved to directory: '" + currentDirectory.getName() + "'";
        } else {
            Directory targetDirectory = navigateToDirectory(currentDirectory, components);
            if (targetDirectory != null) {
                fileSystem.setCurrentDirectory(targetDirectory);
                if (targetDirectory == fileSystem.getRoot()) {
                    return "moved to directory '/'";
                }
                return "moved to directory '" + targetDirectory.getName() + "'";
            } else {
                return "'" + path + "' directory does not exist";
            }
        }
    }

    private Directory navigateToDirectory(Directory start, String[] components) {
        Directory current = start;
        for (String component : components) {
            boolean found = false;
            for (Node node : current.getChildren()) {
                if (node instanceof Directory && node.getName().equals(component)) {
                    current = (Directory) node;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return null;
            }
        }
        return current;
    }
}
