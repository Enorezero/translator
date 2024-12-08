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
                "        if (a < b) {a + b; } " +
                "    } " +
                "} ";

        //String code = "int a = 5; int b = 4; if (a < b) { a+=b;}";



        Lexer lexer = new Lexer(code);

        //System.out.println(lexer.tokenize());

        Parser parser = new Parser(lexer.tokenize());
//
//        //System.out.println(lexer.tokenize());
//
        List<ASTNode> nodes = parser.parse();

        for (ASTNode node : nodes) {
            System.out.println(node.toString());
        }



        //System.out.println(lexer.tokenize());
    }
}