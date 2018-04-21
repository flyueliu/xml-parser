package com.flyiu.test;


import com.flyiu.parser.XMLObject;
import com.flyiu.parser.XMLParser;
import org.junit.Test;

public class ParserTest {

    @Test
    public void test01() throws Exception {
        XMLParser parser = new XMLParser(ParserTest.class.getResourceAsStream("/c.xml"));
        XMLObject result = parser.getResult();
        System.out.println(result);
    }

    @Test
    public void test02() throws Exception {
        XMLParser parser = new XMLParser(ParserTest.class.getResourceAsStream("/contact.xml"));
        XMLObject result = parser.getResult();
        System.out.println(result.getTagName());
        System.out.println(result.getSingleTag("contact"));
        for (XMLObject item : result.getChildren()) {
            System.out.println(item);
        }
    }
}
