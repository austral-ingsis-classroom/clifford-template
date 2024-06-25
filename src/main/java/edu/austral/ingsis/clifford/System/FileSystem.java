package edu.austral.ingsis.clifford.System;

import java.util.List;

public interface FileSystem {
  List<String> executeCommands(List<String> commands);

  Directory getRoot();

  Directory getCurrentDirectory();

  void setCurrentDirectory(Directory parent);
}
