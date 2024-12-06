package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a block of statements, like a main method or other method bodies
public class Block extends ASTNode {
    public List<ASTNode> statements;

    public Block(List<ASTNode> statements) {
        this.statements = statements;
    }
}
