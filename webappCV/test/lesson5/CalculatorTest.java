package lesson5;

import by.gurianchyck.webapp.WebappException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alexey Gurianchyck
 * 16.09.2015.
 */
public class CalculatorTest {
    static Calculator calc;
    static {
        calc = new Calculator();
    }

    @Test(expected = WebappException.class)
    public void testAbs() throws Exception {
        assertEquals(5, calc.abs(-5));
        throw new WebappException("");
    }
}