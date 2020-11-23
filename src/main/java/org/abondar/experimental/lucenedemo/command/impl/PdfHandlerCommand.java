package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
import org.abondar.experimental.lucenedemo.handlers.PdfHandler;
import org.apache.lucene.document.Document;

import java.io.FileInputStream;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.PDF_FILE;

public class PdfHandlerCommand implements Command {

    @Override
    public void execute() {
        try {
            PdfHandler handler = new PdfHandler();
            Document document = handler.getDocument(new FileInputStream(PDF_FILE));
            System.out.println(document);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);

        }

    }
}
