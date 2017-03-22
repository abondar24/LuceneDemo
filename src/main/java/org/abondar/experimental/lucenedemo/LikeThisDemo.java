package org.abondar.experimental.lucenedemo;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;

import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;


public class LikeThisDemo {

    private IndexReader reader;
    private IndexSearcher searcher;

    public static void main(String[] args) throws IOException {
        File indexDir = new File("/home/abondar/Doucments");
        FSDirectory directory = FSDirectory.open(indexDir.toPath());
        IndexReader reader = DirectoryReader.open(directory);

        int numDocs = reader.maxDoc();
        LikeThisDemo ltd = new LikeThisDemo(reader);

        for (int i = 0; i < numDocs; i++) {
            System.out.println();
            Document document = reader.document(i);
            System.out.println(document.get("filename"));

            Document[] docs = ltd.docsLike(i, 100);
            if (docs.length == 0) {
                System.out.println(" None like this");
            }
            for (int j = 0; j < docs.length; j++) {
                Document likeThisDoc = docs[j];
                System.out.println(" -> " + likeThisDoc.get("title"));
            }
        }
    }

    public LikeThisDemo(IndexReader reader) {
        this.reader = reader;
        searcher = new IndexSearcher(reader);

    }

    public Document[] docsLike(int id, int max) throws IOException {
        Document document = reader.document(id);
        String[] filenames = document.getValues("filename");

        BooleanQuery.Builder fileNameQuery = new BooleanQuery.Builder();

        for (int i = 0; i < filenames.length; i++) {
            String filename = filenames[i];
            fileNameQuery.add(new TermQuery(new Term("filename", filename)), BooleanClause.Occur.MUST);
        }
        fileNameQuery.setMinimumNumberShouldMatch(2);

        Terms termVector = reader.getTermVector(id, "contents");

        BooleanQuery.Builder contentsQuery = new BooleanQuery.Builder();
        for (int i = 0; i < termVector.size(); i++) {
            TermQuery termQuery = new TermQuery(
                    new Term("contents", termVector.iterator().term())
            );
            contentsQuery.add(termQuery, BooleanClause.Occur.MUST);
        }


        BooleanQuery.Builder likeThisQuery = new BooleanQuery.Builder();
        likeThisQuery.add(fileNameQuery.build(), BooleanClause.Occur.MUST);
        likeThisQuery.add(contentsQuery.build(), BooleanClause.Occur.MUST);

        TopDocs results = searcher.search(likeThisQuery.build(), 10);

        int size = max;

        if (max > results.totalHits) {
            size = results.totalHits;
        }

        Document[] docs = new Document[size];
        for (int i=0;i<size; i++){
            docs[i] = searcher.doc(results.scoreDocs[i].doc);
        }

        return docs;
    }

}
