package org.abondar.experimental.lucenedemo.metaphone;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LetterTokenizer;


public class MetaphoneReplacementAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {

        Tokenizer tokenizer = new LetterTokenizer();
        TokenStream filter = new MetaphoneReplacementFilter(tokenizer);

        return new TokenStreamComponents(tokenizer,filter);
    }
}
