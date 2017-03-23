package org.abondar.experimental.lucenedemo.handlers;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PlainTextHandler implements DocumentHandler {
    public Document getDocument(InputStream inputStream){
        String bodyText = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line= bufferedReader.readLine())!=null){
                bodyText += line;
            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        if (!bodyText.equals("")){
            Document document = new Document();
            document.add(new TextField("body",bodyText, Field.Store.NO));
            return document;
        }

        return null;
    }
}
