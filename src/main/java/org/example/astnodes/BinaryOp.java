package org.example.astnodes;

import org.example.ASTNode;

// Represents a binary operation (e.g., addition, subtraction)
public class BinaryOp extends ASTNode {
    public ASTNode left;
    public String operator;
    public ASTNode right;

    BinaryOp(ASTNode left, String operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
