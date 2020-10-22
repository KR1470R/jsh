package kript.jsh.main.logger;

import kript.jsh.main.colors.Colors;

public class Error {

  public static void notFoundCommand (String command) {
    System.out.println(Colors.ANSI_RED + "jsh: command not found: " + command + Colors.ANSI_RESET);
  }

  public static void notSuchFileOrDirecotry (String file, String command) {
    System.out.println(Colors.ANSI_RED + command + ": " + file + ": No such file or directory" + Colors.ANSI_RESET);
  }

  public static void notADirectory (String file, String command) {
    System.out.println(command + ": not a directory: " + file);
  }
}
