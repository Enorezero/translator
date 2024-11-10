package org.example.astnodes;

import org.example.ASTNode;

// Represents an import statement
class ImportStatement extends ASTNode {
    String module;
    String alias;

    ImportStatement(String module, String alias) {
        this.module = module;
        this.alias = alias;
    }
}
