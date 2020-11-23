package org.abondar.experimental.lucenedemo.command.impl;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class ChineseDemo {

    private static String[] strings = {"指事字"};

    private static Analyzer[] analyzers = {
            new SimpleAnalyzer(),
            new StandardAnalyzer(),
            new SmartChineseAnalyzer(),
            new CJKAnalyzer()
    };

    public static void main(String[] args)  throws IOException{
        for (int i=0; i<strings.length;i++){
            String string = strings[i];
            for (int j=0; j<analyzers.length;j++){
                Analyzer analyzer = analyzers[j];
                String name = analyzer.getClass().getName();
                name = name.substring(name.lastIndexOf(".") + 1);
                System.out.println("Analyzer: "+name);
                analyze(string,analyzer);
            }
        }
    }

    private static void analyze(String string, Analyzer analyzer) throws IOException {
        StringBuffer buffer = new StringBuffer();
        List<String> tokens = tokenFromAnalysis(analyzer,string);

        for (String token : tokens){
            buffer.append("[");
            buffer.append(token);
            buffer.append("] ");
        }

        System.out.println(buffer.toString());

    }



    private static List<String> tokenFromAnalysis(Analyzer analyzer, String text) throws IOException {
        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
        CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
        List<String> tokens = new ArrayList<>();

        stream.reset();
        while (stream.incrementToken()) {
            tokens.add(cta.toString());
        }
        stream.end();
        stream.close();

        return tokens;

    }
}
