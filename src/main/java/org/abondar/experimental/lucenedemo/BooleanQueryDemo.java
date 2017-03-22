package org.abondar.experimental.lucenedemo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;


public class BooleanQueryDemo {
    public static void main(String[] args) throws Exception {
        String indexDir = "/home/abondar/Doucments";

        File index = new File(indexDir);
        FSDirectory directory = FSDirectory.open(index.toPath());

        Query query1 = new TermQuery(new Term("contents", "all"));
        Query query2 = new TermQuery(new Term("contents", "have"));

        BooleanQuery.Builder boolQuery = new BooleanQuery.Builder();
        boolQuery.add(query1, BooleanClause.Occur.MUST);
        boolQuery.add(query2, BooleanClause.Occur.FILTER);



        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs results = searcher.search(boolQuery.build(), 100);
        ScoreDoc[] hits = results.scoreDocs;


        for (ScoreDoc hit : hits) {

            Document document = searcher.doc(hit.doc);
            System.out.println(document.get("filename"));

        }
    }
}
