package org.abondar.experimental.lucenedemo.fileindexer;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class FileIndexer {

    protected FileHandler fileHandler;

    public FileIndexer(Properties properties) throws IOException {
        fileHandler = new ExtensionFileHandler(properties);
    }

    public void index(IndexWriter writer, File file) {
        if (file.canRead()) {
            if (file.isDirectory()) {
                String[] files = file.list();
                if (files != null) {
                    for (String f : files) {
                        index(writer, new File(file, f));
                    }
                }
            } else {
                System.out.println("Indexing " + file);
                try {
                    Document document = fileHandler.getDocument(file);
                    if (document != null) {
                        writer.addDocument(document);
                    } else {
                        System.err.println("Cannot handle" + file.getAbsolutePath() + "; skipping");
                    }
                } catch (IOException e) {
                    System.err.println("Cannot index "
                           + file.getAbsolutePath() + "; skipping ("
                           + e.getMessage() + ")");
                }
            }
        }
    }
}
