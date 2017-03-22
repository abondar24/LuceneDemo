package org.abondar.experimental.lucenedemo;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

public class SpanNearQueryDemo {
    public static void main(String[] args) throws Exception {
        String indexDir = "/home/abondar/Doucments";

        File index = new File(indexDir);
        FSDirectory directory = FSDirectory.open(index.toPath());

        SpanQuery query = new SpanTermQuery(new Term("contents", "http"));
        SpanQuery query1 = new SpanTermQuery(new Term("contents", "must"));

        SpanQuery[] queries = new SpanQuery[]{query, query1};

        SpanNearQuery spanNearQuery = new SpanNearQuery(queries, 1, true);

        System.out.println("Query: " + spanNearQuery.toString());

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs results = searcher.search(spanNearQuery, 100);
        ScoreDoc[] hits = results.scoreDocs;


        for (ScoreDoc hit : hits) {
            Document document = searcher.doc(hit.doc);
            System.out.println(document.get("filename"));

        }
    }
}
