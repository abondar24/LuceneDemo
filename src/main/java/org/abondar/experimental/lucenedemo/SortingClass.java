package org.abondar.experimental.lucenedemo;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.text.DecimalFormat;


public class SortingClass {
    private Directory directory;

    public SortingClass(Directory directory) {
        this.directory = directory;
    }

    public void displayHits(Query query, Sort sort) throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs hits = searcher.search(query, 50, sort);


        System.out.println("\nResults for: " +
                query.toString() + " sorted by " + sort);


        System.out.println(
                StringUtils.rightPad("contents", 10) +
                        StringUtils.center("id",  4)+
                StringUtils.center("filename",  10));

        DecimalFormat scoreFormatter = new DecimalFormat("0.######");
        for (int i = 0; i < hits.totalHits; i++) {
            Document document = searcher.doc(hits.scoreDocs[i].doc);
            System.out.println(StringUtils.rightPad(
                    StringUtils.abbreviate(document.get("contents"), 9), 10)
                    + StringUtils.rightPad(document.get("filename"), 4)
                    + StringUtils.center("" + searcher.doc(hits.scoreDocs[i].doc), 10)
                    + StringUtils.leftPad(scoreFormatter.format(hits.scoreDocs[i].score),30));
            System.out.println("   "+document.get("category"));

        }

    }
}
