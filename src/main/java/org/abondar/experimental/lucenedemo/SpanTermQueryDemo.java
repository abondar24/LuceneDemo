package org.abondar.experimental.lucenedemo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.FSDirectory;

import java.io.File;


public class SpanTermQueryDemo {

    public static void main(String[] args) throws Exception {
        String indexDir = "/home/abondar/Doucments";

        File index = new File(indexDir);
        FSDirectory directory = FSDirectory.open(index.toPath());

         Query query = new SpanTermQuery(new Term("contents","http"));
        Query query1 = new SpanTermQuery(new Term("contents","2"));

        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(query, BooleanClause.Occur.SHOULD);
        builder.add(query1,BooleanClause.Occur.SHOULD);

        BooleanQuery booleanQuery = builder.build();
        System.out.println("Query: "+booleanQuery.toString());

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs results = searcher.search(booleanQuery,100);
        ScoreDoc[] hits = results.scoreDocs;


        for (ScoreDoc hit : hits) {
            Document document = searcher.doc(hit.doc);
            System.out.println(document.get("filename"));

        }
    }
}
