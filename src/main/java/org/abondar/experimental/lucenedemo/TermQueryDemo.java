package org.abondar.experimental.lucenedemo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

/**
 * Created by abondar on 3/21/17.
 */
public class TermQueryDemo {
    public static void main(String[] args) throws Exception {
        String indexDir = "/home/abondar/Doucments";

        File index = new File(indexDir);
        FSDirectory directory = FSDirectory.open(index.toPath());

        Query query =  new TermQuery(new Term("contents","activity"));

        System.out.println("Query: "+query.toString());

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs results = searcher.search(query,10);
        ScoreDoc[] hits = results.scoreDocs;

        for (int i=0; i<hits.length;i++){

            Document document = searcher.doc(hits[i].doc);
            System.out.println(document.get("filename"));
            System.out.println(document.get("contents"));

        }
    }
}
