package org.abondar.experimental.lucenedemo.handlers;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

public class PDFBoxPDFHandler implements DocumentHandler {

    public Document getDocument(InputStream is) {
        COSDocument cosDoc = null;

        try {
            cosDoc = parseDocument(is);
        } catch (IOException e) {
            closeCOSDocument(cosDoc);
            System.out.println(e.getMessage());
        }

        //extract text
        String docText = null;
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            docText = stripper.getText(new PDDocument(cosDoc));
        } catch (IOException e) {
            closeCOSDocument(cosDoc);
            System.out.println(e.getMessage());
        }

        Document document = new Document();
        if (docText != null) {
            document.add(new TextField("body", docText, Field.Store.NO));
        }

        //extract meta-data
        PDDocument pdDocument = null;
        try {
            PDDocumentInformation info = pdDocument.getDocumentInformation();
            String author = info.getAuthor();
            String title = info.getTitle();
            String keywords = info.getKeywords();
            String summary = info.getSubject();

            if ((author) != null && !author.equals("")) {
                document.add(new StringField("author", author, Field.Store.YES));
            }

            if ((title) != null && !title.equals("")) {
                document.add(new StringField("title", title, Field.Store.YES));
            }

            if ((keywords) != null && !keywords.equals("")) {
                document.add(new StringField("keywords", keywords, Field.Store.YES));
            }

            if ((summary) != null && !summary.equals("")) {
                document.add(new StringField("summary", summary, Field.Store.YES));
            }
        } catch (Exception e) {
            closeCOSDocument(cosDoc);
            closePDDocument(pdDocument);
            System.out.println(e.getMessage());
        }

        return document;
    }



    private COSDocument parseDocument(InputStream is) throws IOException {
        PDFParser parser = new PDFParser(new RandomAccessBuffer(is));
        parser.parse();

        return parser.getDocument();
    }

    private void closeCOSDocument(COSDocument cosDoc) {
        if (cosDoc != null) {
            try {
                cosDoc.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void closePDDocument(PDDocument pdDocument) {
        if (pdDocument!=null){
            try {
                pdDocument.close();
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

    }


}
