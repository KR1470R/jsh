package kript.jsh.main.syntax.parser.expressions;

public class Expression implements IExpression {
    private final String string;

    public Expression(String _string) {
        string = _string;
    }

    public String String() {
        return string;
    }
}
