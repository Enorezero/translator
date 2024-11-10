package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a while loop
class WhileStatement extends ASTNode {
    ASTNode condition;
    List<ASTNode> body;

    WhileStatement(ASTNode condition, List<ASTNode> body) {
        this.condition = condition;
        this.body = body;
    }
}
