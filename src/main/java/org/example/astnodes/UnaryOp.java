package org.example.astnodes;

import org.example.ASTNode;

// Represents a unary operation (e.g., negation)
public class UnaryOp extends ASTNode {
    public String operator;
    public ASTNode operand;

    UnaryOp(String operator, ASTNode operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
