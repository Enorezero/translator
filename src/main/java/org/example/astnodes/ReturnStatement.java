package org.example.astnodes;

import org.example.ASTNode;

// Represents a return statement
class ReturnStatement extends ASTNode {
    ASTNode expression;

    ReturnStatement(ASTNode expression) {
        this.expression = expression;
    }
}