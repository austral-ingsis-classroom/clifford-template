package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.clifford.*;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CommandTests {
  @Test
  public void testLSEmpty() {
    FileSystem fileSystem = new FileSystem();
    assertEquals(new ListChildren(fileSystem, null).execute(), "");
  }

  @Test
  public void testLSDirectories() {
    Directory root = new Directory("");
    new Directory("directory", root);
    new Directory("directory1", root);

    FileSystem fileSystem = new FileSystem(root);

    assertEquals(new ListChildren(fileSystem, null).execute(), "directory directory1");
  }

  @Test
  public void testLSDescending() {
    Directory root = new Directory("");
    new Directory("directory", root);
    new Directory("directory1", root);

    FileSystem fileSystem = new FileSystem(root);

    assertEquals(new ListChildren(fileSystem, "desc").execute(), "directory1 directory");
  }

  @Test
  public void testTouchFile() {
    FileSystem fileSystem = new FileSystem();
    assertEquals(new Touch(fileSystem, "file").execute(), "'file' file created");
    assertEquals(fileSystem.getRoot().getChildren().getFirst().getName(), "file");
  }

  @Test
  public void testTouchFileInDirectory() {
    Directory root = new Directory("");
    FileSystem fileSystem = new FileSystem(root);
    new Directory("directory", root);

    assertEquals(new Touch(fileSystem, "file").execute(), "'file' file created");
    assertEquals(fileSystem.getRoot().getChildren().getFirst().getName(), "directory");
    assertEquals(fileSystem.getRoot().getChildren().getLast().getName(), "file");
  }

  @Test
  public void testRemoveChild() {
    Directory root = new Directory("");
    Directory directory = new Directory("directory", root);
    Directory directory1 = new Directory("directory1", root);
    FileSystem fileSystem = new FileSystem(root);

    new Remove(fileSystem, "directory", true).execute();

    assertEquals(root.getChildren(), List.of(directory1));
  }
}
