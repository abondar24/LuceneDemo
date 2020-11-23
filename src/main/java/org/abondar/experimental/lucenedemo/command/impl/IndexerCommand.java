package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import static java.util.Objects.requireNonNull;

public class IndexerCommand implements Command {
    private static int index(File indexDir, File dataDir) throws IOException {
        if (!dataDir.exists() || !dataDir.isDirectory()) {
            throw new IOException(dataDir + "does not exist or not a directory");
        }


        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        config.setUseCompoundFile(false);
        Directory indexDirectory = FSDirectory.open(indexDir.toPath());
        IndexWriter writer = new IndexWriter(indexDirectory, config);

        indexDir(writer, dataDir);

        int numIndexed = writer.numDocs();

        writer.close();
        return numIndexed;
    }

    private static void indexDir(IndexWriter writer, File dataDir) throws IOException {

        File[] files = dataDir.listFiles();

        requireNonNull(files);
        for (File f : files) {
            if (f.isDirectory()) {
                indexDir(writer, f);
            } else if (f.getName().endsWith(".txt")) {
                indexFile(writer, f);
            }
        }
    }

    private static void indexFile(IndexWriter writer, File f) throws IOException {
        if (f.isHidden() || !f.exists() || !f.canRead()) {
            return;
        }

        System.out.println("Indexing " + f.getCanonicalPath());

        Document document = new Document();
        document.add(new TextField("contents", new FileReader(f)));
        document.add(new StringField("filename", f.toString(), Field.Store.YES));
        writer.addDocument(document);
    }


    @Override
    public void execute() {
        try {
            File indexDir = new File("/home/abondar/index");

            File dataDir =  new File("/home/abondar/Documents");


            long start = new Date().getTime();
            int numIndexed = index(indexDir, dataDir);
            long end = new Date().getTime();

            System.out.println("Indexing " + numIndexed + " files took "
                    + (end - start) + " milliseconds");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
