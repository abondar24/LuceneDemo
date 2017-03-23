package org.abondar.experimental.lucenedemo.handlers;


import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlainTextHandlerDemo {
    public static void main(String[] args) throws FileNotFoundException {
       PlainTextHandler handler = new PlainTextHandler();
        Document document = handler.getDocument(new FileInputStream(new File(args[0])));
        System.out.println(document);
    }
}
