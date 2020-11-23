package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.DATA_DIR;


public class TermQueryCommand implements Command {

    @Override
    public void execute() {
        try {
            File index = new File(DATA_DIR);
            FSDirectory directory = FSDirectory.open(index.toPath());

            Query query = new TermQuery(new Term("contents", "except"));


            System.out.println("Query: " + query.toString());

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(indexReader);

            TopDocs results = searcher.search(query, 100);
            ScoreDoc[] hits = results.scoreDocs;


            for (ScoreDoc hit : hits) {
                Document document = searcher.doc(hit.doc);
                System.out.println(document.get("filename"));
            }
        }catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
