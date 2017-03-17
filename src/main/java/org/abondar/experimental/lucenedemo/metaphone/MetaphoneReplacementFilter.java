package org.abondar.experimental.lucenedemo.metaphone;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;


import java.io.IOException;


public class MetaphoneReplacementFilter extends TokenFilter {


    protected CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    protected PositionIncrementAttribute positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);

    private Metaphone metaphoner = new Metaphone();

    public MetaphoneReplacementFilter(TokenStream input) {
        super(input);
    }


    @Override
    public boolean incrementToken() throws IOException {

        String nextToken = null;
        while (nextToken == null) {


            if (!this.input.incrementToken()) {
                return false;
            }

            String currentToken = this.input.getAttribute(CharTermAttribute.class).toString();

            currentToken = metaphoner.encode(currentToken);
            nextToken = currentToken;

        }
        this.charTermAttribute.setEmpty().append(nextToken);
        this.positionIncrementAttribute.setPositionIncrement(1);

        return true;
    }
}
