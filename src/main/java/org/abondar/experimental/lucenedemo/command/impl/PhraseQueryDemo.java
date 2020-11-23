package org.abondar.experimental.lucenedemo.command.impl;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;


import java.io.File;


public class PhraseQueryDemo {

    public static void main(String[] args) throws Exception {
        String indexDir = "/home/abondar/Doucments";

        File index = new File(indexDir);
        FSDirectory directory = FSDirectory.open(index.toPath());

        int slop = 1;
        String[] phrases = new String[] {"have", "all"};


        PhraseQuery.Builder query = new PhraseQuery.Builder();
        query.setSlop(slop);
        for (String phrase : phrases) {
            query.add(new Term("contents", phrase));

        }

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs results = searcher.search(query.build(),100);
        ScoreDoc[] hits = results.scoreDocs;


        for (ScoreDoc hit : hits) {

            Document document = searcher.doc(hit.doc);
            System.out.println(document.get("filename"));

        }
    }
}
