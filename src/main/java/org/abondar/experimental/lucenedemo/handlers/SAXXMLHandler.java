package org.abondar.experimental.lucenedemo.handlers;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StoredField;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class SAXXMLHandler extends DefaultHandler implements DocumentHandler {

    private StringBuffer elementBuffer = new StringBuffer();
    private HashMap attributeMap;

    private Document document;

    public Document getDocument(InputStream is) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser parser = spf.newSAXParser();
            parser.parse(is, this);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return document;
    }

    public void startDocument() {
        document = new Document();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementBuffer.setLength(0);
        if (attributes.getLength() > 0) {
            attributeMap = new HashMap();
            for (int i = 0; i < attributes.getLength(); i++) {
                attributeMap.put(attributes.getQName(i), attributes.getValue(i));
            }
        }
    }

    public void characters(char[] text, int start, int length) {
        elementBuffer.append(text, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("project")) {
            return;
        } else if (qName.equals("dependencies")) {
            for (Object o : attributeMap.keySet()) {
                String attName = (String) o;
                String attValue = (String) attributeMap.get(attName);
                document.add(new StoredField(attName, attValue));
            }
        } else {
           document.add(new StoredField(qName,elementBuffer.toString()));
        }
    }

}
