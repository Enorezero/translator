package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a for loop
class ForStatement extends ASTNode {
    VariableDeclaration initializer;
    ASTNode condition;
    ASTNode update;
    List<ASTNode> body;

    ForStatement(VariableDeclaration initializer, ASTNode condition, ASTNode update, List<ASTNode> body) {
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }
}
