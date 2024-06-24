package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;

public class MKDIRCommand implements Command {
    private final Directory currentDirectory;
    private final String dirName;

    public MKDIRCommand(Directory currentDirectory, String dirName) {
        this.currentDirectory = currentDirectory;
        this.dirName = dirName;
    }

    @Override
    public String execute() {
        currentDirectory.addItem(new Directory(dirName, currentDirectory));
        return "'" + dirName + "' directory created";
    }
}
