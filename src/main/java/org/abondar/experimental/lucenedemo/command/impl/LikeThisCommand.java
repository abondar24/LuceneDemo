package org.abondar.experimental.lucenedemo.command.impl;


import org.abondar.experimental.lucenedemo.LikeThis;
import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;

import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

import static org.abondar.experimental.lucenedemo.DirUtil.INDEX_DIR;


public class LikeThisCommand implements Command {

    @Override
    public void execute() {

        try {
            File indexDir = new File(INDEX_DIR);
            FSDirectory directory = FSDirectory.open(indexDir.toPath());
            IndexReader reader = DirectoryReader.open(directory);

            int numDocs = reader.maxDoc();
            LikeThis lt = new LikeThis(reader);

            for (int i = 0; i < numDocs; i++) {
                System.out.println();
                Document document = reader.document(i);
                System.out.println(document.get("filename"));

                Document[] docs = lt.docsLike(i, 100);
                if (docs.length == 0) {
                    System.out.println(" None like this");
                }
                for (Document likeThisDoc : docs) {
                    System.out.println(" -> " + likeThisDoc.get("title"));
                }
            }
        } catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }


    }
}
