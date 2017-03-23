package org.abondar.experimental.lucenedemo.fileindexer;


import org.abondar.experimental.lucenedemo.handlers.DocumentHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ExtensionFileHandler implements FileHandler {

    private Properties handlerProps;

    public ExtensionFileHandler(Properties handlerProps) {
        this.handlerProps = handlerProps;
    }

    @Override
    public Document getDocument(File file) {
        String name = file.getName();
        int dotIndex = name.indexOf(".");

        if ((dotIndex > 0) && (dotIndex < name.length())) {
            String ext = name.substring(dotIndex + 1, name.length());
            String handlerClassName = handlerProps.getProperty(ext);

            try {
                Class handlerClass = Class.forName(handlerClassName);
                DocumentHandler handler = (DocumentHandler) handlerClass.newInstance();
                return handler.getDocument(new FileInputStream(file));

            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | FileNotFoundException e) {

                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
