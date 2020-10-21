package kript.jsh.main.syntax.lexer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LexerTest {
    public static String command = "\t command-name argument\t--named-argument value";

    @Test
    public void testTokenize() {
        final List<String> expectedTokens = Arrays.asList("command-name", "argument", "--named-argument", "value");
        Assert.assertArrayEquals(expectedTokens.toArray(), Lexer.tokenize(command).toArray());
    }
}
