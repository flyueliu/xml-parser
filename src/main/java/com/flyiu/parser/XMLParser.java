package com.flyiu.parser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XMLParser extends DefaultHandler {

    private XMLObject result;

    private SAXParser parser;

    private InputSource inputSource;

    public XMLParser(File xmlResource) throws FileNotFoundException {
        this.inputSource = new InputSource(new FileInputStream(xmlResource));
    }

    public XMLParser(InputStream xmlInputStream) {
        this.inputSource = new InputSource(xmlInputStream);
    }

    private void parser() throws Exception {
        this.parser = SAXParserFactory.newInstance().newSAXParser();
        this.parser.parse(inputSource, this);
    }


    /**
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        result.addContent(new String(ch, start, length).trim());
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
        if (result == null) {
            result = new XMLObject();
        } else {
            XMLObject childTag = new XMLObject();
            childTag.setParent(result);
            result.addChild(childTag);
            result = childTag;
        }
        result.setTagName(qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            String attrName = attributes.getQName(i);
            result.addAttribute(attrName, attributes.getValue(attrName));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (result.getParent() != null) {
            result = result.getParent();
        }
    }


    public XMLObject getResult() throws Exception {
        this.parser();
        return result;
    }


}


