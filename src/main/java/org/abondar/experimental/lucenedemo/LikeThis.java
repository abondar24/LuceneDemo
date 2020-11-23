package org.abondar.experimental.lucenedemo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

public class LikeThis {

    private IndexReader reader;
    private IndexSearcher searcher;


    public LikeThis(IndexReader reader) {
        this.reader = reader;
        searcher = new IndexSearcher(reader);

    }

    public Document[] docsLike(int id, int max) throws IOException {
        Document document = reader.document(id);
        String[] filenames = document.getValues("filename");

        BooleanQuery.Builder fileNameQuery = new BooleanQuery.Builder();

        for (String filename : filenames) {
            fileNameQuery.add(new TermQuery(new Term("filename", filename)), BooleanClause.Occur.MUST);
        }
        fileNameQuery.setMinimumNumberShouldMatch(2);

        Terms termVector = reader.getTermVector(id, "contents");

        BooleanQuery.Builder contentsQuery = new BooleanQuery.Builder();
        if (termVector!=null){
            for (int i = 0; i < termVector.size(); i++) {
                TermQuery termQuery = new TermQuery(
                        new Term("contents", termVector.iterator().term())
                );
                contentsQuery.add(termQuery, BooleanClause.Occur.MUST);
            }
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
