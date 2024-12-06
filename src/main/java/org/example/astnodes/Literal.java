package org.example.astnodes;

import org.example.ASTNode;

// Represents a literal value, such as an integer or string
public class Literal extends ASTNode {
    public Object value;

    Literal(Object value) {
        this.value = value;
    }
}
