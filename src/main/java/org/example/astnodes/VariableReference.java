package org.example.astnodes;

import org.example.ASTNode;

// Represents a variable reference
public class VariableReference extends ASTNode {
    public String name;

    public VariableReference(String name) {
        this.name = name;
    }

    public VariableReference() {

    }
}