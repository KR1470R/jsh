package kript.jsh.main.syntax.parser.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvironmentExpression extends Expression {
    private final String variable;
    private final String value;

    public EnvironmentExpression(String string, String _variable, String _value) {
        super(string);
        variable = _variable;
        value = _value;
    }

    public String getVariable() {
        return variable;
    }

    public String getValue() {
        return value;
    }

    private static final Pattern regexp = Pattern.compile("(\\w+)=(\\w+)");

    @ExpressionHandler
    public static EnvironmentExpression handler(String string) {
        Matcher m = regexp.matcher(string);
        if(m.matches()) {
            return new EnvironmentExpression(string, m.group(0), m.group(1));
        } else {
            return null;
        }
    }
}
