package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;
import java.util.Map;

// Represents a try-catch statement
class TryCatchStatement extends ASTNode {
    List<ASTNode> tryBody;
    Map<String, List<ASTNode>> catchBlocks;
    List<ASTNode> finallyBody;

    TryCatchStatement(List<ASTNode> tryBody, Map<String, List<ASTNode>> catchBlocks, List<ASTNode> finallyBody) {
        this.tryBody = tryBody;
        this.catchBlocks = catchBlocks;
        this.finallyBody = finallyBody;
    }
}
