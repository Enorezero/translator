package org.example.astnodes;

import org.example.ASTNode;

// Represents a literal value, such as an integer or string
class Literal extends ASTNode {
    Object value;

    Literal(Object value) {
        this.value = value;
    }
}
