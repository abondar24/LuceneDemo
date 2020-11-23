package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.CustomHitCollector;
import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class CustomHitCollectorCommand implements Command {

    @Override
    public void execute() {

        try {
            File indexDir = new File(INDEX_DIR);
            TermQuery termQuery = new TermQuery(new Term("filename","NOTICE"));

            Directory fsDir = FSDirectory.open(indexDir.toPath());
            IndexReader indexReader = DirectoryReader.open(fsDir);
            IndexSearcher searcher = new IndexSearcher(indexReader);

            CustomHitCollector collector = new CustomHitCollector(searcher);
            searcher.search(termQuery,collector);

            Map linkMap = collector.getLinks();

            System.out.println(linkMap.get("activity"));
        } catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
