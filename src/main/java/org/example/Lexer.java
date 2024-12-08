package org.example;

import org.example.model.Token;
import org.example.model.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

  private final String code;
  private int pos;
  private char currentChar;
  private final Map<String, Boolean> initializedVariables;

  public Lexer(String code) {
    this.code = code.replaceAll("\\s{2,}", " ").trim();
    this.pos = 0;
    this.currentChar = code.charAt(0);
    this.initializedVariables = new HashMap<>();
  }

  private void advance() {
    pos++;
    if (pos < code.length()) {
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
    boolean isDot = false;
    while (Character.isDigit(currentChar) || currentChar == '.') {
      if (currentChar == '.') {
        if (isDot) {
          throw new NumberFormatException();
        }
        isDot = true;
      }
      result.append(currentChar);
      advance();
    }
    return new Token(TokenType.NUMBER, result.toString());
  }

  private Token getCompoundToken() {
    var previousChar = currentChar;
    advance();
    var compound = String.valueOf(previousChar) + currentChar;
    var type = TokenType.fromValue(compound);
    if (type != null) {
      advance();
      return new Token(type, compound);
    }
    return new Token(TokenType.EQUALS, "=");
  }

  private Token oneCharToken() {
    var tokenType = TokenType.fromValue(String.valueOf(currentChar));
    if (tokenType != null) {
      return new Token(tokenType, String.valueOf(currentChar));
    }
    return null;
  }

  private Token stringToken() {
    if (currentChar == '\"') {
      advance();
      StringBuilder tmpToken = new StringBuilder();
      while (currentChar != '\"') {
        tmpToken.append(currentChar);
        advance();
      }
      return new Token(TokenType.STRING, tmpToken.toString());
    }
    return null;
  }


  public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();
    boolean isDataType = true;
    while (pos != code.length()) {
      if (Character.isWhitespace(currentChar)) {
        skipWhitespace();
        continue;
      }
      if (Character.isDigit(currentChar)) {
        var numberToken = number();
        tokens.add(numberToken);
        continue;
      }
      if (currentChar == '=') {
        var compoundToken = getCompoundToken();
        tokens.add(compoundToken);
        continue;
      }

      var oneCharToken = oneCharToken();
      if (oneCharToken != null) {
        tokens.add(oneCharToken);
        advance();
        continue;
      }

      var stringToken = stringToken();
      if (stringToken != null) {
        tokens.add(stringToken);
        advance();
        continue;
      }

      //keywords and identifiers
      StringBuilder tmpToken = new StringBuilder();
      while (currentChar != ' ' && currentChar != '.' && currentChar != '(' && currentChar != ')') {
        tmpToken.append(currentChar);
        advance();
      }

      //check keywords
      var keyword = TokenType.fromValue(tmpToken.toString());
      if (keyword != null) {
        if (keyword == TokenType.CLASS || keyword == TokenType.INTERFACE || keyword == TokenType.VOID) {
          isDataType = false;
        }
        tokens.add(new Token(keyword, tmpToken.toString()));
      } else if (isDataType && currentChar == ' ' && !initializedVariables.getOrDefault(tmpToken.toString(), false)) {//data type
        isDataType = false;
        tokens.add(new Token(TokenType.DATA_TYPE, tmpToken.toString()));
      } else { //identifier
        isDataType = true;
        tokens.add(new Token(TokenType.IDENTIFIER, tmpToken.toString()));
        initializedVariables.put(tmpToken.toString(), true);
      }
      tmpToken.delete(0, tmpToken.length());
    }
    return tokens;
  }

}
