package org.example.astnodes;

import org.example.ASTNode;

// Represents a unary operation (e.g., negation)
class UnaryOp extends ASTNode {
    String operator;
    ASTNode operand;

    UnaryOp(String operator, ASTNode operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
