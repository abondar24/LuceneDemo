package org.abondar.experimental.lucenedemo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

public class IndexTuningDemo {

    public static void main(String[] args) throws Exception {
        int docsInIndex = 100;
        File indexDir = new File("/home/abondar/Doucments");

        if (!indexDir.exists() || !indexDir.isDirectory()) {
            throw new Exception(indexDir + "does not exist or is not a directory");
        }

        Directory directory = FSDirectory.open(indexDir.toPath());

        StandardAnalyzer analyzer = new StandardAnalyzer();

        //tune indexing speed
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setMaxBufferedDocs(10);
        config.setRAMBufferSizeMB(30);
        config.setInfoStream(System.out);


        IndexWriter writer = new IndexWriter(directory, config);
        writer.forceMerge(10);

        long start = System.currentTimeMillis();
        for (int i = 0; i < docsInIndex; i++) {
            Document document = new Document();
            document.add(new TextField("field", "Bibizyan", Field.Store.YES));
            writer.addDocument(document);
        }
        writer.close();
        long stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start) + " ms");

    }
}
