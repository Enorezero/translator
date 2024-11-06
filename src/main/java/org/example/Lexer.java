package org.example;

import org.example.model.Token;
import org.example.model.TokenType;

public class Lexer {

    private final String code;
    private int pos;
    private char currentChar;

    public Lexer(String code) {
        this.code = code;
        this.pos = 0;
        this.currentChar = code.charAt(0);
    }

    private void advance() {
        pos++;
        if (pos >= code.length()) {
            currentChar = code.charAt(0);
        }
        else {
            currentChar = code.charAt(pos);
        }
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private Token number() {
        StringBuilder result = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return new Token(TokenType.NUMBER, result.toString());
    }

    Token getNextToken() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) return number();
            if (currentChar == '+') {
                advance();
                return new Token(TokenType.PLUS, "+");
            }
            if (currentChar == '-') {
                advance();
                return new Token(TokenType.MINUS, "-");
            }
            if (currentChar == '*') {
                advance();
                return new Token(TokenType.TIMES, "*");
            }
            if (currentChar == '/') {
                advance();
                return new Token(TokenType.DIVIDE, "/");
            }
            if (currentChar == '(') {
                advance();
                return new Token(TokenType.LPAREN, "(");
            }
            if (currentChar == ')') {
                advance();
                return new Token(TokenType.RPAREN, ")");
            }
            throw new RuntimeException("Unknown character: " + currentChar);
        }
        return new Token(TokenType.EOF, "");
    }



}
