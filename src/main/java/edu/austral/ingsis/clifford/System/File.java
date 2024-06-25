package edu.austral.ingsis.clifford.System;

public class File extends FileSystemItem {
  public File(String name) {
    super(name);
  }

  @Override
  public boolean isDirectory() {
    return false;
  }
}
