package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class SpanTermQueryCommand implements Command {


    @Override
    public void execute() {

        try {
            File index = new File(INDEX_DIR);
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
        }catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
