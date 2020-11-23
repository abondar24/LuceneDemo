package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.SortingClass;
import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.store.FSDirectory;
import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class SortingCommand implements Command {

    @Override
    public void execute() {
        try {
            File indexDir = new File(INDEX_DIR);
            QueryParser queryParser = new QueryParser("contents", new StandardAnalyzer());
            Query query = queryParser.parse("have");

            FSDirectory directory = FSDirectory.open(indexDir.toPath());
            SortingClass sortingClass = new SortingClass(directory);
            sortingClass.displayHits(query, Sort.RELEVANCE);
        } catch (IOException | ParseException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
