package kript.jsh.main;

import kript.jsh.main.colors.Colors;
import kript.jsh.main.commands.CommandDispatcher;
import kript.jsh.main.commands.Commands;
import kript.jsh.main.syntax.lexer.Lexer;
import java.util.List;
import java.util.Scanner;

public class App {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        System.out.println("Welcome to " + Colors.ANSI_YELLOW + "J" + Colors.ANSI_GREEN + "Sh" + Colors.ANSI_RESET +"!");
        Scanner input = new Scanner(System.in);
        CommandDispatcher dispatcher = new CommandDispatcher();
        dispatcher.add(Commands.class);

        while (true) {
            System.out.printf("%s $ ", Path.getCurrentPath());
            String question = input.nextLine();

            List<String> tokens = Lexer.tokenize(question);
            try {
                dispatcher.dispatch(tokens);
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
