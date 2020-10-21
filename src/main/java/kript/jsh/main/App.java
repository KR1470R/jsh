package kript.jsh.main;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Command {
        String name();

        String args();
    }

    public static boolean checkTheCommand(String command, String line) {
        return line.replaceAll(" ", "").startsWith(command);
    }

    public static List<String> tokenize(String command) {
        List<String> tokens = Arrays.asList(command.split("[ \t]"));
        int size = tokens.size();
        for(int i = 0; i < size; i++) {
            String token = tokens.get(i);
            if(token.equals(" ") || token.equals("\t")) {
                tokens.remove(i);
                i--;
                size--;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to JSh!");
        Scanner input = new Scanner(System.in);
        String fimilarPath = System.getProperty("user.dir");
        String currentPath = fimilarPath;
        System.out.printf("%s $ ", fimilarPath);
        while (true) {
            String question = input.nextLine();
            if (checkTheCommand("ls", question)) {
                try {
                    Files.list(new File(currentPath).toPath())
                            .forEach(path -> {
                                String pathToString = path.toString();
                                String getPath = pathToString.substring(pathToString.lastIndexOf("/"));
                                if (Files.isDirectory(Paths.get(pathToString))) {
                                    System.out.print(getPath.substring(1) + "/" + "  ");
                                } else {
                                    System.out.print(getPath.substring(1) + "  ");
                                }
                            });
                    System.out.println();
                } catch (Throwable err) {
                    System.out.println(err);
                }
            } else if (checkTheCommand("cat", question)) {
                String[] commandContent = question.split(" ");
                try {
                    if (Files.exists(Paths.get(currentPath + "/" + commandContent[1]))) {
                        for (int i = 1; i < commandContent.length; i++) {
                            File file = new File(commandContent[i]);
                            Scanner reader = new Scanner(file);
                            while (reader.hasNextLine()) {
                                System.out.println(reader.nextLine());
                            }
                            reader.close();
                        }
                    } else {
                        System.out.printf("cat: %s: No such file or directory%n", commandContent[1]);
                    }
                } catch (Throwable err) {
                    System.out.println("cat: no have argument");
                }
            } else if (checkTheCommand("cd", question)) {
                String[] commandContent = question.split(" ");
                try {
                    String[] commandPath = commandContent[1].split("/");
                    if (commandPath[0].equals("..")) {
//            System.out.println(Arrays.toString(commandPath));
                        System.out.println(Integer.MAX_VALUE);
                    }

                    if (Files.exists(Paths.get(commandContent[1]))) {
                        if (Files.isDirectory(Paths.get(commandContent[1]))) {
                            currentPath = commandContent[1];
                        } else {
                            System.out.println("cd: not a directory: " + commandContent[1]);
                        }
                    } else {
                        System.out.println("cd: no such file or directory");
                    }
                } catch (Throwable err) {
                    currentPath = fimilarPath;
                }
            } else if (checkTheCommand("exit", question)) {
                break;
            } else {
                System.out.printf("jsh: command no found: %s\n", question);
            }
            System.out.printf("%s $ ", currentPath);
        }
    }
}
