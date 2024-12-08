package org.example.astnodes;

import org.example.ASTNode;

public class Condition extends ASTNode {
    public String condition;

    public Condition(String condition) {
        this.condition = condition;
    }
}
