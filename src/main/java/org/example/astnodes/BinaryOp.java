package org.example.astnodes;

import org.example.ASTNode;

// Represents a binary operation (e.g., addition, subtraction)
public class BinaryOp extends ASTNode {
    public String left;
    public String operator;
    public String right;

    public BinaryOp(String left, String operator, String right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
