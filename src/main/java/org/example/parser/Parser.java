package org.example.parser;

import org.example.ASTNode;
import org.example.model.Token;
import org.example.model.TokenType;
import org.example.astnodes.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
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

    public List<ASTNode> parse() {
        List<ASTNode> nodes = new ArrayList<>();
        while (currentToken.getType() != TokenType.EOF) {
            ASTNode node = parseStatement();
            nodes.add(node);
        }

        return nodes;
    }

    //виды конструкций
    private ASTNode parseStatement() {
        switch (currentToken.getType()) {
            case IDENTIFIER -> {
                return parseOperationOrCall();
            }
            case DATA_TYPE, STATIC, PUBLIC, ABSTRACT, FINAL, PRIVATE, PROTECTED -> {
                return parseMethodOrVariableOrClass();
            }
            case IF -> {
                return parseIfStatement();
            }
            case WHILE -> {
                return parseWhileStatement();
            }
            case FOR -> {
                return parseForStatement();
            }
            case RETURN -> {
                return parseReturnStatement();
            }
            default -> {
                advance();
                return new ASTNode() {
                };
            }
        }
    }

    private ASTNode parseOperationOrCall() {
        String id = null;
        if(currentToken.getType() == TokenType.IDENTIFIER) {
            id = currentToken.getValue();
        }

        eat(TokenType.IDENTIFIER);

        String operation = null;
        if (currentToken.getType() == TokenType.EQUALS_EQUALS ||
                currentToken.getType() == TokenType.NOT_EQUALS ||
                currentToken.getType() == TokenType.LESS_THAN_OR_EQUAL_TO ||
                currentToken.getType() == TokenType.GREATER_THAN_OR_EQUAL_TO ||
                currentToken.getType() == TokenType.EQUALS ||
                currentToken.getType() == TokenType.LESS_THAN ||
                currentToken.getType() == TokenType.GREATER_THAN ||
                currentToken.getType() == TokenType.MODULO ||
                currentToken.getType() == TokenType.BITWISE_AND ||
                currentToken.getType() == TokenType.BITWISE_OR ||
                currentToken.getType() == TokenType.BITWISE_XOR ||
                currentToken.getType() == TokenType.BITWISE_NOT ||
                currentToken.getType() == TokenType.PLUS ||
                currentToken.getType() == TokenType.MINUS ||
                currentToken.getType() == TokenType.TIMES ||
                currentToken.getType() == TokenType.DIVIDE) {
            operation = currentToken.getValue();
            eat(currentToken.getType());
            if(currentToken.getType() == TokenType.SEMICOLON) {
                eat(currentToken.getType());
                return new UnaryOp(id, operation);
            } else if (currentToken.getType() == TokenType.IDENTIFIER) {
                String secId = currentToken.getValue();
                eat(currentToken.getType());
                return new BinaryOp(id, operation, secId);
            }
        } else if (currentToken.getType() == TokenType.LPAREN) {
            eat(currentToken.getType());
            List<VariableReference> params = new ArrayList<>();
            while (currentToken.getType() != TokenType.RPAREN) {
                if(currentToken.getType() == TokenType.IDENTIFIER) {
                    String prm = currentToken.getValue();
                    VariableReference reference = new VariableReference(prm);
                    params.add(reference);
                    eat(currentToken.getType());

                    if(currentToken.getType() == TokenType.COMMA) eat(currentToken.getType());
                }
            }
            eat(currentToken.getType());
            eat(TokenType.SEMICOLON);

            return new MethodCall(id, params);
        }

        return null;
    }

    //разбор класса
    private ClassDef parseClassDefinition(ClassDef classDef) {
        eat(TokenType.CLASS);
        String className = currentToken.getValue();
        classDef.setName(className);
        eat(TokenType.IDENTIFIER);
        if(currentToken.getType() == TokenType.EXTENDS) {
            classDef.setBaseClass(currentToken.getValue());
            eat(TokenType.EXTENDS);
        }
        eat(TokenType.LBRACE);

        List<ASTNode> body = new ArrayList<>();
        while (currentToken.getType() != TokenType.RBRACE) {
            body = parseBlock();
        }

        eat(TokenType.RBRACE);
        classDef.setBody(body);
        return classDef;
    }

    private ASTNode parseMethodOrVariableOrClass() {
        String modificator = null;

        if(currentToken.getType() == TokenType.PUBLIC || currentToken.getType() == TokenType.PRIVATE || currentToken.getType() == TokenType.PROTECTED) {
            modificator = currentToken.getValue();
            eat(currentToken.getType());
        }

        String abstractModificator = null;
        if(currentToken.getType() == TokenType.ABSTRACT) {
            abstractModificator = currentToken.getValue();
            eat(TokenType.ABSTRACT);
        }

        String staticModificator = null;
        if(currentToken.getType() == TokenType.STATIC) {
            staticModificator = currentToken.getValue();
            eat(TokenType.STATIC);
        }

        String finalModificator = null;
        if(currentToken.getType() == TokenType.FINAL) {
            finalModificator = currentToken.getValue();
            eat(TokenType.FINAL);
        }

        if (currentToken.getType() == TokenType.CLASS) {

            ClassDef classDef = new ClassDef();
            if(abstractModificator != null) classDef.setAbstractModificator(abstractModificator);
            if(modificator != null) classDef.setModificator(modificator);
            if(staticModificator != null) classDef.setStaticModificator(staticModificator);
            if(finalModificator != null) classDef.setFinalModificator(finalModificator);
            return parseClassDefinition(classDef);
        }

        String datatype = currentToken.getValue();
        eat(currentToken.getType());
        String identifier = currentToken.getValue();
        eat(TokenType.IDENTIFIER);

        if (currentToken.getType() == TokenType.LPAREN) {
            eat(TokenType.LPAREN);
            List<VariableDeclaration> params = new ArrayList<>();
            while (currentToken.getType() != TokenType.RPAREN) {
                String paramType = null;
                if(currentToken.getType() == TokenType.DATA_TYPE) {
                     paramType = currentToken.getValue();
                    eat(TokenType.DATA_TYPE);
                }

                String paramId = null;
                if(currentToken.getType() == TokenType.IDENTIFIER) {
                    paramId = currentToken.getValue();
                    eat(TokenType.IDENTIFIER);
                }

                VariableDeclaration param =  new VariableDeclaration();
                param.setType(paramType);
                param.setName(paramId);
                params.add(param);
                if (currentToken.getType() == TokenType.COMMA) {
                   eat(TokenType.COMMA);
                }
            }
            eat(TokenType.RPAREN);
            List<ASTNode> body = new ArrayList<>();
            if(currentToken.getType() == TokenType.LBRACE) {
                body  = parseBlock();
            }

            MethodDef definition = new MethodDef();

            if(abstractModificator != null) definition.setAbstractModificator(abstractModificator);
            if(modificator != null) definition.setModificator(modificator);
            if(staticModificator != null) definition.setStaticModificator(staticModificator);
            if(finalModificator != null) definition.setFinalModificator(finalModificator);

            definition.setType(datatype);
            definition.setName(identifier);
            definition.setParams(params);
            definition.setBody(body);


            return definition;

        } else {
            VariableDeclaration declaration = new VariableDeclaration();
            declaration.setType(datatype);
            declaration.setName(identifier);
            if (currentToken.getType() == TokenType.EQUALS) {
                eat(TokenType.EQUALS);
                if (currentToken.getType() == TokenType.NEW) eat(TokenType.NEW);
                VariableReference reference = new VariableReference();
                reference.name = getLoopString();
                eat(currentToken.getType());
                if(modificator != null) declaration.setModificator(modificator);
                if(staticModificator != null) declaration.setStaticModificator(staticModificator);
                if(finalModificator != null) declaration.setFinalModificator(finalModificator);
                declaration.setInitializer(reference);
            }
            return declaration;
        }
    }

    private List<ASTNode> parseBlock() {
        List<ASTNode> statements = new ArrayList<>();
        if(currentToken.getType() == TokenType.LBRACE) {
            eat(currentToken.getType());
        }
        while (currentToken.getType() != TokenType.RBRACE) {
            ASTNode node = parseStatement();
            statements.add(node);
        }

        return statements;
    }

    private IfStatement parseIfStatement() {
        eat(TokenType.IF);

        Condition condition = parseExpression();

        eat(TokenType.LBRACE);

        List<ASTNode> trueBlock = parseBlock();

        eat(TokenType.RBRACE);

        if (currentToken.getType() == TokenType.ELSE) {
            eat(TokenType.ELSE);
            eat(TokenType.LBRACE);

            List<ASTNode> falseBlock = parseBlock();

            eat(TokenType.RBRACE);

            return new IfStatement(condition, trueBlock, falseBlock);
        }

        return new IfStatement(condition, trueBlock);
    }

    private ASTNode parseWhileStatement() {
        eat(TokenType.WHILE);

        Condition condition = parseExpression();

        eat(TokenType.LBRACE);

        List<ASTNode> body = parseBlock();

        eat(TokenType.RBRACE);
        return new WhileStatement(condition, body);
    }

    private ASTNode parseForStatement() {
        eat(TokenType.FOR);

        Condition condition = parseExpression();

        //Условие
        eat(TokenType.LBRACE);

        List<ASTNode> body = parseBlock();

        eat(TokenType.RBRACE);
        return new ForStatement(condition, body);
    }

    private ASTNode parseReturnStatement() {
        eat(TokenType.RETURN);

        String returnStmnt = getLoopString();

        return new ReturnStatement(returnStmnt);
    }

    private String getLoopString() {
        StringBuilder returnStmnt = new StringBuilder();

        while (currentToken.getType() != TokenType.SEMICOLON) {
            if (currentToken.getType() == TokenType.COMMA) {
                returnStmnt.append(currentToken.getValue());
                returnStmnt.append(" ");
            } else {
                returnStmnt.append(currentToken.getValue());
            }

            eat(currentToken.getType());
        }
        return returnStmnt.toString();
    }

    public Condition parseExpression() {
        StringBuilder builder = new StringBuilder();
        if(currentToken.getType() == TokenType.LPAREN) {
            builder.append(currentToken.getValue());
            advance();
            while (currentToken.getType() != TokenType.RPAREN) {
                builder.append(currentToken.getValue() + " ");
                eat(currentToken.getType());
            }
            builder.append(currentToken.getValue());
            eat(currentToken.getType());
        }

        return new Condition(builder.toString());
    }

    private void eat(TokenType expectedType) {
        if (currentToken.getType() == expectedType) {
            advance();
        } else {
            throw new RuntimeException("Expected " + expectedType + " but got " + currentToken.getValue());
        }
    }
}
