package kript.jsh.main.syntax.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    public static List<String> tokenize(String command) {
        List<String> tokens = new ArrayList<>(Arrays.asList(command.split("[ \t]")));
        int size = tokens.size();
        for (int i = 0; i < size; i++) {
            String token = tokens.get(i);
            if (token.equals(" ") || token.equals("\t") || token.equals("")) {
                tokens.remove(i);
                i--;
                size--;
            }
        }
        return tokens;
    }
}
