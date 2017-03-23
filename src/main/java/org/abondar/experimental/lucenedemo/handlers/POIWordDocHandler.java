package org.abondar.experimental.lucenedemo.handlers;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.poi.hwpf.HWPFDocument;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class POIWordDocHandler implements DocumentHandler {

    public Document getDocument(InputStream is){
        String bodyText = null;

        try {
            HWPFDocument wordDoc = new HWPFDocument(is);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            wordDoc.write(outputStream);
            bodyText = new String(outputStream.toByteArray(),"UTF-8");

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        if ((bodyText!=null) && (bodyText.trim().length() >0)){
            Document document = new Document();
            document.add(new TextField("body",bodyText,Field.Store.NO));
            return document;
        }

        return null;
    }
}
