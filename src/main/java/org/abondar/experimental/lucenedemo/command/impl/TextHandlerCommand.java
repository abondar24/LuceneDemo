package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
import org.abondar.experimental.lucenedemo.handlers.TextHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.abondar.experimental.lucenedemo.DirUtil.TEXT_FILE;

public class TextHandlerCommand implements Command {

    @Override
    public void execute() {
        try {
            TextHandler handler = new TextHandler();
            Document document = handler.getDocument(new FileInputStream(new File(TEXT_FILE)));
            System.out.println(document);
        } catch (FileNotFoundException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
