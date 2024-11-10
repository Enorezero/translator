package org.example.astnodes;

import org.example.ASTNode;

// Represents a variable reference
class VariableReference extends ASTNode {
    String name;

    VariableReference(String name) {
        this.name = name;
    }
}