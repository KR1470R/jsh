package kript.jsh.main.syntax.parser.expressions;

import org.junit.Assert;
import org.junit.Test;

public class TestEnvironmentExpression {
    @Test
    public void testHandler() {
        String nonExpression = "foo";
        String expression = "FOO=bar";

//        Assert.assertNull(EnvironmentExpression.handler(nonExpression));
//
//        EnvironmentExpression eExpression = EnvironmentExpression.handler(expression);
//        Assert.assertNotNull(eExpression);
//        Assert.assertEquals("FOO", eExpression.getVariable());
//        Assert.assertEquals("bar", eExpression.getValue());
    }
}
