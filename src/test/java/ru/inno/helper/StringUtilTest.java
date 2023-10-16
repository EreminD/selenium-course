package ru.inno.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class StringUtilTest {

    @Test
    public void shouldReturnNonEmptyString(){
        StringUtil util = new StringUtil();
        String result = util.normalizeName("диМа");
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    public void firstCharShouldBeUpper(){
        StringUtil util = new StringUtil();
        String result = util.normalizeName("диМа");
        char first = result.charAt(0);
        Assertions.assertEquals('Д', first);
    }

    @Test
    public void tailShouldBeLowerCase(){
        StringUtil util = new StringUtil();
        String result = util.normalizeName("кОнсТанТин");
        String tail = result.substring(1);
        Assertions.assertEquals("онстантин", tail);
    }
}
