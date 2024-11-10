package org.example.astnodes;

import org.example.ASTNode;

// Represents an attribute access
class Attribute extends ASTNode {
    String type;
    String name;
    ASTNode initializer;

    Attribute(String type, String name, ASTNode initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
}
