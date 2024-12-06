package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a method definition
public class MethodDef extends ASTNode {
    public String name;
    public List<String> params;
    public List<ASTNode> body;

    MethodDef(String name, List<String> params, List<ASTNode> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }
}
