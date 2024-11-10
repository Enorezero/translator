package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a method call
class MethodCall extends ASTNode {
    String methodName;
    List<ASTNode> arguments;

    MethodCall(String methodName, List<ASTNode> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }
}
