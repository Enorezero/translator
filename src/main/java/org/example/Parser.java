package org.example.Parser;

import org.example.model.Token;
import org.example.model.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int currentIndex;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentIndex = -1;
        advance();
    }

    //итератор
    private void advance() {
        currentIndex++;
        if (currentIndex < tokens.size()) {
            currentToken = tokens.get(currentIndex);
        } else {
            currentToken = new Token(TokenType.EOF, "EOF");
        }
    }

    public void parse() {
        while (currentToken.getType() != TokenType.EOF) {
            parseStatement();
        }
    }

    //виды конструкций
    private void parseStatement() {
        switch (currentToken.getType()) {
            case CLASS:
                parseClassDefinition();
                break;
            case IDENTIFIER:
                parseMethodOrVariable();
                break;
            case IF:
                parseIfStatement();
                break;
            case WHILE:
                parseWhileStatement();
                break;
            case FOR:
                parseForStatement();
                break;
            case RETURN:
                parseReturnStatement();
                break;
            default:
                advance(); // Skip unknown tokens
                break;
        }
    }

    //разбор класса
    private void parseClassDefinition() {
        eat(TokenType.CLASS);
        String className = currentToken.getValue();
        eat(TokenType.IDENTIFIER);
        eat(TokenType.LBRACE);

        while (currentToken.getType() != TokenType.RBRACE) {
            parseStatement();
        }

        eat(TokenType.RBRACE);
    }

    private void parseMethodOrVariable() {
        String identifier = currentToken.getValue();
        eat(TokenType.IDENTIFIER);

        if (currentToken.getType() == TokenType.LPAREN) { // Method definition
            parseMethodDefinition(identifier);
        } else { // Variable declaration or assignment
            // Handle variable assignment logic here
            if (currentToken.getType() == TokenType.EQUALS) {
                eat(TokenType.EQUALS);
                // Parse expression on the right side
                parseExpression();
            }
        }
    }

    private void parseMethodDefinition(String methodName) {
        eat(TokenType.LPAREN);

        List<String> parameters = new ArrayList<>();

        while (currentToken.getType() != TokenType.RPAREN) {
            parameters.add(currentToken.getValue());
            eat(TokenType.IDENTIFIER); // Assume parameters are identifiers

            if (currentToken.getType() == TokenType.COMMA) {
                eat(TokenType.COMMA);
            }
        }

        eat(TokenType.RPAREN);

        // Method body parsing can be added here
    }

    private void parseIfStatement() {
        eat(TokenType.IF);

        // Assuming a simple expression for condition
        parseExpression();

        eat(TokenType.LBRACE);

        while (currentToken.getType() != TokenType.RBRACE) {
            parseStatement();
        }

        eat(TokenType.RBRACE);

        if (currentToken.getType() == TokenType.ELSE) {
            eat(TokenType.ELSE);
            eat(TokenType.LBRACE);

            while (currentToken.getType() != TokenType.RBRACE) {
                parseStatement();
            }

            eat(TokenType.RBRACE);
        }
    }

    private void parseWhileStatement() {
        eat(TokenType.WHILE);

        // Assuming a simple expression for condition
        parseExpression();

        eat(TokenType.LBRACE);

        while (currentToken.getType() != TokenType.RBRACE) {
            parseStatement();
        }

        eat(TokenType.RBRACE);
    }

    private void parseForStatement() {
        eat(TokenType.FOR);

        // Assuming a simple variable initialization and condition
        String variableName = currentToken.getValue();
        eat(TokenType.IDENTIFIER);

        eat(TokenType.EQUALS); // Assume it's an assignment
        parseExpression(); // Initialize variable

        // Condition could be a simple expression
        parseExpression();

        eat(TokenType.LBRACE);

        while (currentToken.getType() != TokenType.RBRACE) {
            parseStatement();
        }

        eat(TokenType.RBRACE);
    }

    private void parseReturnStatement() {
        eat(TokenType.RETURN);

        // Assuming return statement has an expression
        parseExpression();
    }

    private void parseExpression() {
        // Implement expression parsing logic here
        // This could include handling binary operations, literals, etc.
        advance(); // Placeholder for actual expression parsing logic
    }

    private void eat(TokenType expectedType) {
        if (currentToken.getType() == expectedType) {
            advance();
        } else {
            throw new RuntimeException("Expected " + expectedType + " but got " + currentToken.getValue());
        }
    }
}