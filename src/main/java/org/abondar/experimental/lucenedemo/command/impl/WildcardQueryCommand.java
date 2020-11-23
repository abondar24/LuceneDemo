package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class WildcardQueryCommand implements Command {

    @Override
    public void execute() {
        try {
            File index = new File(INDEX_DIR);
            FSDirectory directory = FSDirectory.open(index.toPath());

            WildcardQuery query = new WildcardQuery(new Term("contents", "?ope*"));

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(indexReader);

            TopDocs results = searcher.search(query, 100);
            ScoreDoc[] hits = results.scoreDocs;


            for (ScoreDoc hit : hits) {

                Document document = searcher.doc(hit.doc);
                System.out.println(document.get("contents"));
            }
        } catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
