package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.handlers.SAXXMLHandler;
import org.apache.lucene.document.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SAXXMLHandlerDemo {
    public static void main(String[] args) throws FileNotFoundException {
        SAXXMLHandler handler = new SAXXMLHandler();
        Document document = handler.getDocument(new FileInputStream(args[0]));
        System.out.println(document);
    }
}
