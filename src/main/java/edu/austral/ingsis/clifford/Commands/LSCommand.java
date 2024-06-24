package edu.austral.ingsis.clifford.Commands;

import edu.austral.ingsis.clifford.System.Directory;
import edu.austral.ingsis.clifford.System.FileSystemItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LSCommand implements Command {
    private final Directory currentDirectory;
    private final String order;

    public LSCommand(Directory currentDirectory, String order) {
        this.currentDirectory = currentDirectory;
        this.order = order;
    }

    @Override
    public String execute() {
        List<FileSystemItem> items = currentDirectory.listItems();
        if ("asc".equals(order)) {
            items.sort(Comparator.comparing(FileSystemItem::getName));
        } else if ("desc".equals(order)) {
            items.sort((a, b) -> b.getName().compareTo(a.getName()));
        }
        return items.stream().map(FileSystemItem::getName).collect(Collectors.joining(" "));
    }
}
