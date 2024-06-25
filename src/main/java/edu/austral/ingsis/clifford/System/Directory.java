package edu.austral.ingsis.clifford.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Directory extends FileSystemItem {
  private final List<FileSystemItem> children;
  private final Directory parent;

  public Directory(String name, Directory parent) {
    super(name);
    this.children = new ArrayList<>();
    this.parent = parent;
  }

  public Directory getParent() {
    return parent;
  }

  public void addItem(FileSystemItem item) {
    children.add(item);
  }

  public void removeItem(String name) {
    children.removeIf(item -> item.getName().equals(name));
  }

  public List<FileSystemItem> listItems() {
    return new ArrayList<>(children);
  }

  public Optional<FileSystemItem> findItem(String name) {
    return children.stream().filter(item -> item.getName().equals(name)).findFirst();
  }

  @Override
  public boolean isDirectory() {
    return true;
  }
}
