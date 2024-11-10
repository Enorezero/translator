package org.example;

import java.util.List;

public class Parser {
    private List<Token> tokens;
    private Token currentToken;
    private int tokenIndex;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.tokenIndex = -1;
    }

    public void parse() {

    }

    private void advance() {
        tokenIndex++;
        if (tokenIndex < tokens.size()) {
            currentToken = tokens.get(tokenIndex);
        } else {
            //заглушка на EOF
            currentToken = null;
        }
    }


}
