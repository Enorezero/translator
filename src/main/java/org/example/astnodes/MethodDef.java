package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a method definition
class MethodDef extends ASTNode {
    String name;
    List<String> params;
    List<ASTNode> body;

    MethodDef(String name, List<String> params, List<ASTNode> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }
}
