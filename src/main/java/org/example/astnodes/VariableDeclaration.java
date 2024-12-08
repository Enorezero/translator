package org.example.astnodes;

import org.example.ASTNode;

// Represents a variable declaration or assignment
public class VariableDeclaration extends ASTNode {
    public String type;
    public String name;
    public ASTNode initializer;

    VariableDeclaration(String type, String name, ASTNode initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
}
