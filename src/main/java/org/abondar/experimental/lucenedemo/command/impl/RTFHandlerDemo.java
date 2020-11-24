package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.handlers.RTFHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RTFHandlerDemo {
    public static void main(String[] args) throws FileNotFoundException {
        RTFHandler handler = new RTFHandler();
        Document document = handler.getDocument(new FileInputStream(new File(args[0])));
        System.out.println(document);
    }
}
