package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a for loop
public class ForStatement extends ASTNode {
    public Condition condition;
    public List<ASTNode> body;

    public ForStatement(Condition condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }
}
