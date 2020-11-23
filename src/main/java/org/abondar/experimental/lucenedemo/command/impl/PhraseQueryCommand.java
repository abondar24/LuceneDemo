package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class PhraseQueryCommand implements Command {

    @Override
    public void execute() {

        try {
            File index = new File(INDEX_DIR);
            FSDirectory directory = FSDirectory.open(index.toPath());

            int slop = 1;
            String[] phrases = new String[]{"have", "all"};


            PhraseQuery.Builder query = new PhraseQuery.Builder();
            query.setSlop(slop);
            for (String phrase : phrases) {
                query.add(new Term("contents", phrase));

            }

            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(indexReader);

            TopDocs results = searcher.search(query.build(), 100);
            ScoreDoc[] hits = results.scoreDocs;


            for (ScoreDoc hit : hits) {

                Document document = searcher.doc(hit.doc);
                System.out.println(document.get("filename"));

            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
