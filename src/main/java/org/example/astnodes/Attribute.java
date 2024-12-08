package org.example.astnodes;

import org.example.ASTNode;

// Represents an attribute access
public class Attribute extends ASTNode {
    public String type;
    public String name;
    public ASTNode initializer;

    Attribute(String type, String name, ASTNode initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
}
