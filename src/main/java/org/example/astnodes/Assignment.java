package org.example.astnodes;

import org.example.ASTNode;

// Represents an assignment
public class Assignment extends ASTNode {
    public ASTNode left;
    public ASTNode right;

    Assignment(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }
}
