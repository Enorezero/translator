package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents an if statement
class IfStatement extends ASTNode {
    ASTNode condition;
    List<ASTNode> trueBody;
    List<ASTNode> falseBody;

    IfStatement(ASTNode condition, List<ASTNode> trueBody, List<ASTNode> falseBody) {
        this.condition = condition;
        this.trueBody = trueBody;
        this.falseBody = falseBody;
    }
}