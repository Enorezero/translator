package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents an if statement
public class IfStatement extends ASTNode {
    public ASTNode condition;
    public List<ASTNode> trueBody;
    public List<ASTNode> falseBody;

    public IfStatement(ASTNode condition, List<ASTNode> trueBody, List<ASTNode> falseBody) {
        this.condition = condition;
        this.trueBody = trueBody;
        this.falseBody = falseBody;
    }

    public IfStatement(ASTNode condition, List<ASTNode> trueBody) {
        this.condition = condition;
        this.trueBody = trueBody;
    }
}