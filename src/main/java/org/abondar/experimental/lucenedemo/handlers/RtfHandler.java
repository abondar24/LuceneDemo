package org.abondar.experimental.lucenedemo.handlers;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.IOException;
import java.io.InputStream;

public class RtfHandler implements DocumentHandler {
    public Document getDocument(InputStream inputStream) {
        String bodyText = null;

        DefaultStyledDocument styledDoc = new DefaultStyledDocument();
        try {
            new RTFEditorKit().read(inputStream,styledDoc,0);
            bodyText = styledDoc.getText(0,styledDoc.getLength());
        } catch (IOException | BadLocationException e){
            System.out.println(e.getMessage());
        }

        if (bodyText!=null){
            Document document = new Document();
            document.add(new TextField("body",bodyText, Field.Store.NO));
            return document;
        }

        return null;
    }
}
