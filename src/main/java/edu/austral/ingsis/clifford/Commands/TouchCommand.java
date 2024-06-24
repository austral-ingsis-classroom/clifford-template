package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;
import edu.austral.ingsis.clifford.System.File;

public class TouchCommand implements Command {
    private final Directory currentDirectory;
    private final String fileName;

    public TouchCommand(Directory currentDirectory, String fileName) {
        this.currentDirectory = currentDirectory;
        this.fileName = fileName;
    }

    @Override
    public String execute() {
        currentDirectory.addItem(new File(fileName));
        return "'" + fileName + "' file created";
    }
}
