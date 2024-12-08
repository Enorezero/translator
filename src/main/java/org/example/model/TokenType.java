package org.example.model;

public enum TokenType {
    NUMBER("NUMBER"),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    LPAREN("("),
    RPAREN(")"),
    EOF("EOF"),
    CLASS("class"),
    IDENTIFIER("identifier"),
    DATA_TYPE("dataType"),
    FUNCTION("FUNCTION"),
    IF("if"),
    ELSE("else"),
    LOOP("LOOP"),
    EQUALS_EQUALS("=="),
    NOT_EQUALS("!="),
    LESS_THAN_OR_EQUAL_TO("<="),
    GREATER_THAN_OR_EQUAL_TO(">="),
    EQUALS("="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    MODULO("%"),
    BITWISE_AND("&"),
    BITWISE_OR("|"),
    BITWISE_XOR("^"),
    BITWISE_NOT("~"),
    LBRACE("{"),
    RBRACE("}"),
    LBRACKET("["),
    RBRACKET("]"),
    COMMA(","),
    DOT("."),
    COLON(":"),
    SEMICOLON(";"),
    PLUS_EQUALS("+="),
    MINUS_EQUALS("-="),
    TIMES_EQUALS("*="),
    DIVIDE_EQUALS("/="),
    MOD_EQUALS("%="),
    AND_EQUALS("&="),
    OR_EQUALS("|="),
    XOR_EQUALS("^="),
    STRING("string value"),

    // Модификаторы доступа
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),

    // Служебные слова Java
    EXTENDS("extends"),
    STATIC("static"),
    FINAL("final"),
    ABSTRACT("abstract"),
    SYNCHRONIZED("synchronized"),
    TRANSIENT("transient"),
    VOLATILE("volatile"),
    NATIVE("native"),
    STRICTFP("strictfp"),
    SUPER("super"),
    THIS("this"),
    RETURN("return"),
    NEW("new"),
    INSTANCEOF("instanceof"),
    ASSERT("assert"),
    BREAK("break"),
    CASE("case"),
    CATCH("catch"),
    CONTINUE("continue"),
    DEFAULT("default"),
    DO("do"),
    FINALLY("finally"),
    FOR("for"),
    IMPORT("import"),
    INTERFACE("interface"),
    NULL("null"),
    PACKAGE("package"),
    SWITCH("switch"),
    THROW("throw"),
    THROWS("throws"),
    TRY("try"),
    VOID("void"),
    WHILE("while"),
    TRUE("true"),
    FALSE("false");

    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TokenType fromValue(String value) {
        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.getValue().equals(value)) {
                return tokenType;
            }
        }
        return null;
    }

}
