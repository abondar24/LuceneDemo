package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.abondar.experimental.lucenedemo.handlers.RtfHandler;
import org.apache.lucene.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.abondar.experimental.lucenedemo.DirUtil.RTF_FILE;

public class RtfHandlerCommand implements Command {

    @Override
    public void execute() {
          try {
              RtfHandler handler = new RtfHandler();
              Document document = handler.getDocument(new FileInputStream(new File(RTF_FILE)));
              System.out.println(document);
          }catch (FileNotFoundException ex){
              System.err.println(ex.getMessage());
              System.exit(1);
          }
    }
}
