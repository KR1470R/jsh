package kript.jsh.main;

import kript.jsh.main.commands.CommandDispatcher;
import kript.jsh.main.commands.Commands;
import kript.jsh.main.syntax.lexer.Lexer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final String fimilarPath = System.getProperty("user.dir");
    public static String currentPath = fimilarPath;

    public static boolean checkTheCommand(String command, String line) {
        return line.replaceAll(" ", "").startsWith(command);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        System.out.println("Welcome to JSh!");
        Scanner input = new Scanner(System.in);

        CommandDispatcher dispatcher = new CommandDispatcher();
        dispatcher.add(Commands.class);

        while (true) {
            System.out.printf("%s $ ", fimilarPath);
            String question = input.nextLine();

            List<String> tokens = Lexer.tokenize(question);
            try {
                dispatcher.dispatch(tokens);
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (checkTheCommand("cd", question)) {
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
            } else {
                System.out.printf("jsh: command no found: %s\n", question);
            }
        }
    }
}
