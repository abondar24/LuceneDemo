package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
import org.abondar.experimental.lucenedemo.handlers.WordDocHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.abondar.experimental.lucenedemo.DirUtil.DOC_FILE;

public class WordDocHandlerCommand implements Command {

    @Override
    public void execute() {
        try {
            WordDocHandler handler = new WordDocHandler();
            Document document = handler.getDocument(new FileInputStream(new File(DOC_FILE)));
            System.out.println(document);
        } catch (FileNotFoundException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
