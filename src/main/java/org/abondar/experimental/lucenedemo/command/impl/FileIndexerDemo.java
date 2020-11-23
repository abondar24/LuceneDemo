package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.fileindexer.FileIndexer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class FileIndexerDemo {

    public static void main(String[] args) throws Exception {
        File indexDir = new File("/home/abondar/Doucments");
        Properties properties = new Properties();
        InputStream inputStream = FileIndexer.class
                .getClassLoader().getResourceAsStream("extensions.properties");
        properties.load(inputStream);

        Directory directory = FSDirectory.open(indexDir.toPath());
        Analyzer analyzer = new SimpleAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setUseCompoundFile(true);
        IndexWriter writer = new IndexWriter(directory, config);

        FileIndexer indexer = new FileIndexer(properties);

        long start = new Date().getTime();
        indexer.index(writer, new File(args[0]));
        writer.close();
        long end = new Date().getTime();

        System.out.println();
        IndexReader reader = DirectoryReader.open(directory);
        System.out.println("Documents indexed: " + reader.numDocs());
        System.out.println("Total time: " + (end -start) + " ms");
        reader.close();
    }
}
