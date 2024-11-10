package org.example.astnodes;

import org.example.ASTNode;

// Represents a binary operation (e.g., addition, subtraction)
class BinaryOp extends ASTNode {
    ASTNode left;
    String operator;
    ASTNode right;

    BinaryOp(ASTNode left, String operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
