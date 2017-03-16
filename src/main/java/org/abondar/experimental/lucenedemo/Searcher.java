package org.abondar.experimental.lucenedemo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.QueryParser;

import java.io.File;
import java.util.Date;


public class Searcher {

    public static void main(String[] args) throws Exception {
        File indexDir = new File("/home/abondar/Doucments");
        String q = "intent";

        if (!indexDir.exists() || !indexDir.isDirectory()) {
            throw new Exception(indexDir + "does not exist or is not a directory");
        }

        search(indexDir, q);
    }

    private static void search(File indexDir, String q) throws Exception {
        Directory fsDir = FSDirectory.open(indexDir.toPath());
        IndexReader indexReader = DirectoryReader.open(fsDir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser("contents", new StandardAnalyzer());
        Query query = queryParser.parse(q);

        long start = new Date().getTime();

        int hitsPerPage = 10;
        TopDocs results = indexSearcher.search(query, 5 * hitsPerPage);

        long end = new Date().getTime();

        ScoreDoc[] hits = results.scoreDocs;
        System.out.println("Found " + results.totalHits +
                " document(s) (in " + (end - start) +
                " milliseconds) that matched query " +
                q + ":");



        for (int i=0; i<results.totalHits;i++){
            Document document = indexSearcher.doc(hits[i].doc);
            System.out.println(document.get("filename"));
        }
    }
}
