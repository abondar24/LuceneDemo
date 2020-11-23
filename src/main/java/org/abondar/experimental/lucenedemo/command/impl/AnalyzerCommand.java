package org.abondar.experimental.lucenedemo.command.impl;

import org.abondar.experimental.lucenedemo.command.Command;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;
import java.io.StringReader;


public class AnalyzerCommand implements Command {
    private static final String[] examples = {
            "The quick brown fox jumped over lazy dogs",
            "XY&Z Corporation - xyz@example.com"
    };

    private static final Analyzer[] analyzers = new Analyzer[]{
            new StandardAnalyzer(),
            new StopAnalyzer(),
            new WhitespaceAnalyzer(),
            new SimpleAnalyzer(),
    };


    private static void analyze(String text) throws IOException {
        System.out.println("Analyzing \"" + text + "\"");
        for (Analyzer analyzer : analyzers) {
            String name = analyzer.getClass().getName();
            name = name.substring(name.lastIndexOf(".") + 1);
            System.out.println(name);
            tokenFromAnalysis(analyzer, text);
            System.out.println("\n");
        }
    }


    private static void tokenFromAnalysis(Analyzer analyzer, String text) throws IOException {
        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
        CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
        OffsetAttribute oa = stream.addAttribute(OffsetAttribute.class);

        stream.reset();
        while (stream.incrementToken()) {
            System.out.println("Start offset: " + oa.startOffset());
            System.out.println("End offset: " + oa.endOffset());
            System.out.println("Token: " + cta.toString());

        }
        stream.end();
        stream.close();


    }

    @Override
    public void execute() {
        try {
            for (String string : examples) {
                analyze(string);
            }
        }catch (IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

    }
}
