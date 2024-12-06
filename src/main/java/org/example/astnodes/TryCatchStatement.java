package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;
import java.util.Map;

// Represents a try-catch statement
public class TryCatchStatement extends ASTNode {
    public List<ASTNode> tryBody;
    public Map<String, List<ASTNode>> catchBlocks;
    public List<ASTNode> finallyBody;

    TryCatchStatement(List<ASTNode> tryBody, Map<String, List<ASTNode>> catchBlocks, List<ASTNode> finallyBody) {
        this.tryBody = tryBody;
        this.catchBlocks = catchBlocks;
        this.finallyBody = finallyBody;
    }
}
