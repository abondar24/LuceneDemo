package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
import org.abondar.experimental.lucenedemo.handlers.SaxXmlHandler;
import org.apache.lucene.document.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.abondar.experimental.lucenedemo.DirUtil.XML_FILE;

public class SaxXmlHandlerCommand implements Command {


    @Override
    public void execute() {
        try {
            SaxXmlHandler handler = new SaxXmlHandler();
            Document document = handler.getDocument(new FileInputStream(XML_FILE));
            System.out.println(document);
        } catch (FileNotFoundException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
