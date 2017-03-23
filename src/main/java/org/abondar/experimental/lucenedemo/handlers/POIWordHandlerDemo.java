package org.abondar.experimental.lucenedemo.handlers;


import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;

public class POIWordHandlerDemo {
    public static void main(String[] args)throws Exception {
        POIWordDocHandler handler = new POIWordDocHandler();
        Document document = handler.getDocument(new FileInputStream(new File(args[0])));
        System.out.println(document);
    }
}
