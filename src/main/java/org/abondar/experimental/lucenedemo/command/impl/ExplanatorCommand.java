package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;

public class ExplanatorCommand implements Command {

    @Override
    public void execute() {
        try {
            String queryExpression = "intent OR activity";

            File index = new File(INDEX_DIR);
            FSDirectory directory = FSDirectory.open(index.toPath());

            Query query =  new QueryParser("contents", new StandardAnalyzer()).parse(queryExpression);

            System.out.println("Query: "+queryExpression);

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(indexReader);

            TopDocs results = searcher.search(query,10);
            ScoreDoc[] hits = results.scoreDocs;

            for (ScoreDoc hit : hits) {

                Explanation explanation = searcher.explain(query, hit.doc);
                System.out.println("----------");
                Document document = searcher.doc(hit.doc);
                System.out.println(document.get("filename"));
                System.out.println(explanation.toString());
            }
        } catch (IOException | ParseException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
