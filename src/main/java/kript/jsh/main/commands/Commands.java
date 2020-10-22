package kript.jsh.main.commands;

import kript.jsh.main.logger.Error;
import kript.jsh.main.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Commands {
    @Command(name = "ls")
    public static void commandLs(List<String> argv) {
      try {
        int row = 3;
        AtomicInteger i = new AtomicInteger(0);
        Files.list(new File(Path.getCurrentPath()).toPath())
                .forEach(path -> {
                  String pathToString = path.toString();
                  String getPath = pathToString.substring(pathToString.lastIndexOf("/"));
                  if (Files.isDirectory(Paths.get(pathToString))) {
                    System.out.print(getPath.substring(1)+"/" + "  ");
                  } else {
                    System.out.print(getPath.substring(1) + "  ");
                  }
                  i.getAndIncrement();
                  if (i.get() > row) {
                    System.out.print("\r");
                    i.set(0);
                  }
                });
        System.out.println();
      } catch (Throwable err) {
        System.out.println(err);
      }
    }

    @Command(name = "cat")
    public static void commandCat(List<String> argv) {
        try {
            for (int i = 1; i < argv.size(); i++) {
                File file = new File(argv.get(i));
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    System.out.println(reader.nextLine());
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            Error.notSuchFileOrDirecotry(argv.get(1), "cat");
        }
    }

    @Command(name = "cd")
    public static void commandCd(List<String> argv) {
      try {
        String[] commandPath = argv.get(1).split("/");
        if (commandPath[0].equals("..")) {
          String[] splitedCurrentPath = Path.getCurrentPath().split("/");
          splitedCurrentPath = Arrays.copyOfRange(splitedCurrentPath, 1, splitedCurrentPath.length-1);
          Path.setCurrentPath("/" + String.join("/", splitedCurrentPath));
        } else if (Files.exists(Paths.get(argv.get(1)))) {
          if (Files.isDirectory(Paths.get(argv.get(1)))) {
            Path.setCurrentPath(argv.get(1));
          } else {
            Error.notADirectory(argv.get(1), "cd");
          }
        } else {
          Error.notSuchFileOrDirecotry(argv.get(1).toString(), "cd");
        }
      } catch (Throwable err) {
        Path.setCurrentPath(Path.getFimilarPath());
      }
    }

    @Command(name = "pwd")
    public static void commandPwd (List<String> argv) {
      System.out.println(Path.getCurrentPath());
    }

    @Command(name = "exit")
    public static void commandExit(List<String> argv) {
        System.exit(0);
    }
}
