package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a method call
public class MethodCall extends ASTNode {
    public String methodName;
    public List<ASTNode> arguments;

    MethodCall(String methodName, List<ASTNode> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }
}
