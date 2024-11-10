package org.example.astnodes;

import org.example.ASTNode;

// Represents an assignment
class Assignment extends ASTNode {
    ASTNode left;
    ASTNode right;

    Assignment(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }
}
