package org.example.astnodes;

import org.example.ASTNode;

// Represents a unary operation (e.g., negation)
public class UnaryOp extends ASTNode {
    public String operator;
    public String operand;

    public UnaryOp(String operator, String operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
