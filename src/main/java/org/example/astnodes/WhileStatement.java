package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a while loop
public class WhileStatement extends ASTNode {
    public ASTNode condition;
    public List<ASTNode> body;

    WhileStatement(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }
}
