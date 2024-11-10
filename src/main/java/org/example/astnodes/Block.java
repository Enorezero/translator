package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a block of statements, like a main method or other method bodies
class Block extends ASTNode {
    List<ASTNode> statements;

    Block(List<ASTNode> statements) {
        this.statements = statements;
    }
}
