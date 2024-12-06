package org.example.astnodes;

import org.example.ASTNode;

// Represents an import statement
public class ImportStatement extends ASTNode {
    public String module;
    public String alias;

    ImportStatement(String module, String alias) {
        this.module = module;
        this.alias = alias;
    }
}
