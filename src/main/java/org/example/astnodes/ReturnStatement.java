package org.example.astnodes;

import org.example.ASTNode;

// Represents a return statement
public class ReturnStatement extends ASTNode {
    public ASTNode expression;

    ReturnStatement(ASTNode expression) {
        this.expression = expression;
    }
}