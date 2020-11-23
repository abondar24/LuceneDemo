package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.handlers.PDFBoxPDFHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PDFBoxHandlerDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PDFBoxPDFHandler handler = new PDFBoxPDFHandler();
        Document document = handler.getDocument(new FileInputStream(new File(args[0])));
        System.out.println(document);

    }
}
