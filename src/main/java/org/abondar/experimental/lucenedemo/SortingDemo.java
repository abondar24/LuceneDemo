package org.abondar.experimental.lucenedemo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.FSDirectory;
import java.io.File;


public class SortingDemo {
    public static void main(String[] args) throws Exception{
        File indexDir = new File("/home/abondar/Doucments");

        QueryParser queryParser = new QueryParser("contents", new StandardAnalyzer());
        Query query = queryParser.parse("have");

        FSDirectory directory = FSDirectory.open(indexDir.toPath());
        SortingClass sortingClass = new SortingClass(directory);
        sortingClass.displayHits(query, Sort.RELEVANCE);
    }
}
