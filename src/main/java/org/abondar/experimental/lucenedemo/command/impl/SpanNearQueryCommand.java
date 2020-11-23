package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
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
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;

public class SpanNearQueryCommand implements Command {

    @Override
    public void execute() {

        try {
            File index = new File(INDEX_DIR);
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
        } catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
