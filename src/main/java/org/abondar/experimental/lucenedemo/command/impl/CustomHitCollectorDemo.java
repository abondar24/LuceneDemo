package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.CustomHitCollector;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.util.Map;


public class CustomHitCollectorDemo {
    public static void main(String[] args) throws Exception{
        File indexDir = new File("/home/abondar/Doucments");
        TermQuery termQuery = new TermQuery(new Term("filename","NOTICE"));

        Directory fsDir = FSDirectory.open(indexDir.toPath());
        IndexReader indexReader = DirectoryReader.open(fsDir);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        CustomHitCollector collector = new CustomHitCollector(searcher);
        searcher.search(termQuery,collector);

        Map linkMap = collector.getLinks();

        System.out.println(linkMap.get("activity"));

    }
}
