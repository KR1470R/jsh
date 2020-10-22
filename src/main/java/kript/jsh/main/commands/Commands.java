package kript.jsh.main.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Commands {
    @Command(name = "ls")
    public static void commandLs(List<String> argv) {
        System.out.println("Hmm... Not implemented yet");
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
            System.out.printf("cat: %s: No such file or directory%n", argv.get(1));
        }
    }

    @Command(name = "exit")
    public static void commandExit(List<String> argv) {
        System.exit(0);
    }
}
