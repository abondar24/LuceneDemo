package org.abondar.experimental.lucenedemo;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TotalHitCountCollector;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abondar on 3/21/17.
 */
public class CustomHitCollector extends TotalHitCountCollector {

    private IndexSearcher searcher;
    private HashMap docs = new HashMap();

    public CustomHitCollector(IndexSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public void collect (int id){
        try {
            Document document = searcher.doc(id);

            docs.put(document.get("filename"),document.get("contents"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map getLinks(){
        return Collections.unmodifiableMap(docs);
    }



}
