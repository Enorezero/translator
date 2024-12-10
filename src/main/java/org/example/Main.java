package org.example;

import org.example.generator.KotlinCodeGenerator;
import org.example.lexer.Lexer;
import org.example.parser.Parser;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    String code1 = "public class HelloWorld { " +
            "    public static void main(String[] args) { " +
            "        int a = 10; " +
            "        float b = 3.14f; " +
            "        if (a < b) {a + b; } " +
            "    } " +
            "} ";

    String code2 = "public class Main {\n" +
            "\n" +
            "  private String generateString() {\n" +
            "    String res = \"\";\n" +
            "    for (int i = 0; i < 99; i = i + 1) {\n" +
            "      res = i;\n" +
            "    }\n" +
            "    return res;\n" +
            "  }\n" +
            "  \n" +
            "  public static void main(String[] args) {\n" +
            "    generateString();\n" +
            "  }\n" +
            "}";

    String code3 = "public class Main {\n" +
            "\n" +
            "  public static void main(String[] args) {\n" +
            "    int a = 10;\n" +
            "    while (a != 0) {\n" +
            "      a = a - 1;\n" +
            "    }\n" +
            "  }\n" +
            "}";

    KotlinCodeGenerator generator = new KotlinCodeGenerator();

    Lexer lexer = new Lexer(code2);

    Parser parser = new Parser(lexer.tokenize());

    List<ASTNode> nodes = parser.parse();

    StringBuilder codeBuilder = new StringBuilder();
    for (ASTNode node : nodes) {
      codeBuilder.append(generator.generateCode(node)).append('\n');
    }
    System.out.println(codeBuilder);


  }
}