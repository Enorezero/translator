package org.example;

import org.example.lexer.Lexer;
import org.example.model.Token;
import org.example.model.TokenType;
import org.example.parser.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = "public class HelloWorld { " +
                "    public static void main(String[] args) { " +
                "        int a = 10; " +
                "        int b = 3.14; " +
                "        if (a < b) {a += b; } " +
                "    } " +
                "} ";

        //String code = "int a = 5; int b = 4; if (a < b) { a+=b;}";

        List<Token> tokens = new ArrayList<>();

        // Массив токенов в виде пар (тип, значение)
        String[][] tokenArray = {
                {"PUBLIC", "public"},
                {"CLASS", "class"},
                {"IDENTIFIER", "HelloWorld"},
                {"LBRACE", "{"},
                {"PUBLIC", "public"},
                {"STATIC", "static"},
                {"VOID", "void"},
                {"IDENTIFIER", "main"},
                {"LPAREN", "("},
                {"DATA_TYPE", "String[]"},
                {"IDENTIFIER", "args"},
                {"RPAREN", ")"},
                {"LBRACE", "{"},
                {"DATA_TYPE", "int"},
                {"IDENTIFIER", "a"},
                {"EQUALS", "="},
                {"NUMBER", "10"},
                {"SEMICOLON", ";"},
                {"DATA_TYPE", "int"},
                {"IDENTIFIER", "b"},
                {"EQUALS", "="},
                {"NUMBER", "3.14"},
                {"SEMICOLON", ";"},
                {"IF", "if"},
                {"LPAREN", "("},
                {"IDENTIFIER", "a"},
                {"LESS_THAN", "<"},
                {"IDENTIFIER", "b"},
                {"RPAREN", ")"},
                {"LBRACE", "{"},
                {"IDENTIFIER", "a"},
                {"PLUS", "+"},
                {"IDENTIFIER", "b;"},
                {"RBRACE", "}"},
                {"RBRACE", "}"},
        };

        // Добавление токенов в список
        for (String[] tokenData : tokenArray) {
            TokenType type = TokenType.valueOf(tokenData[0]);
            String value = tokenData[1];
            tokens.add(new Token(type, value));
        }

        Lexer lexer = new Lexer(code);

        //System.out.println(tokens);

        Parser parser = new Parser(tokens);

        List<ASTNode> nodes = parser.parse();

        for (ASTNode node : nodes) {
            System.out.println(node.toString());
        }



        //System.out.println(lexer.tokenize());
    }
}