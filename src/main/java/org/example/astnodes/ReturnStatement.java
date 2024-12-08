package org.example.astnodes;

import org.example.ASTNode;

// Represents a return statement
public class ReturnStatement extends ASTNode {
    public String expression;

    public ReturnStatement(String expression) {
        this.expression = expression;
    }
}