package edu.austral.ingsis;

import edu.austral.ingsis.clifford.Commands.*;
import edu.austral.ingsis.clifford.System.FileSystem;
import edu.austral.ingsis.clifford.System.MyFileSystem;

import java.util.ArrayList;
import java.util.List;

public class SimpleFileSystemRunner implements FileSystemRunner {
    private final FileSystem fileSystem;

    public SimpleFileSystemRunner() {
        this.fileSystem = new MyFileSystem();
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

    private Command parseCommand(String commandStr) {
        String[] parts = commandStr.split(" ");
        String commandName = parts[0];
        String[] args = parts.length > 1 ? commandStr.substring(commandName.length()).trim().split(" ") : new String[0];

        switch (commandName) {
            case "ls":
                String order = args.length > 0 && args[0].startsWith("--ord=") ? args[0].substring(6) : null;
                return new LSCommand(fileSystem.getCurrentDirectory(), order);
            case "cd":
                return new CDCommand(fileSystem, args.length > 0 ? args[0] : ".");
            case "touch":
                return args.length > 0 ? new TouchCommand(fileSystem.getCurrentDirectory(), args[0]) : null;
            case "mkdir":
                return args.length > 0 ? new MKDIRCommand(fileSystem.getCurrentDirectory(), args[0]) : null;
            case "rm":
                boolean recursive = args.length > 1 && "--recursive".equals(args[0]);
                String name = recursive ? args[1] : args[0];
                return new RMCommand(fileSystem.getCurrentDirectory(), name, recursive);
            case "pwd":
                return new PWDCommand(fileSystem.getCurrentDirectory());
            default:
                return null;
        }
    }
}