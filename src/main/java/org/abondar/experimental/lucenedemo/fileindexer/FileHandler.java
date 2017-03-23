package org.abondar.experimental.lucenedemo.fileindexer;


import org.apache.lucene.document.Document;

import java.io.File;

public interface FileHandler {
    Document getDocument (File file);
}
