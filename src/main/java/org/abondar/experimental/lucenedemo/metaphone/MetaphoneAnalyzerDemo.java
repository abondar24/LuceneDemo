package org.abondar.experimental.lucenedemo.metaphone;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;


public class MetaphoneAnalyzerDemo {

    public static void main(String[] args) throws IOException {
        MetaphoneReplacementAnalyzer analyzer = new MetaphoneReplacementAnalyzer();

        String str =  "The quick brown fox jumped over lazy dogs";

        System.out.println(str);
        TokenStream stream = analyzer.tokenStream("contents", new StringReader(str));
        CharTermAttribute cta = stream.addAttribute(CharTermAttribute.class);
        stream.reset();
        System.out.println(" ");
        while (stream.incrementToken()) {

            System.out.print("["+cta.toString()+"] ");

        }
        stream.end();
        stream.close();
    }





}
