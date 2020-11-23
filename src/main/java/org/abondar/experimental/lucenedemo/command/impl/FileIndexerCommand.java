package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
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
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import static org.abondar.experimental.lucenedemo.DirUtil.DATA_DIR;
import static org.abondar.experimental.lucenedemo.DirUtil.NEW_FILE;

public class FileIndexerCommand implements Command {


    @Override
    public void execute() {
        try {
            File indexDir = new File(DATA_DIR);
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
            indexer.index(writer, new File(NEW_FILE));
            writer.close();
            long end = new Date().getTime();

            System.out.println();
            IndexReader reader = DirectoryReader.open(directory);
            System.out.println("Documents indexed: " + reader.numDocs());
            System.out.println("Total time: " + (end - start) + " ms");
            reader.close();
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
