package org.example.astnodes;

import org.example.ASTNode;

// Represents a variable declaration or assignment
class VariableDeclaration extends ASTNode {
    String type;
    String name;
    ASTNode initializer;

    VariableDeclaration(String type, String name, ASTNode initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
}
