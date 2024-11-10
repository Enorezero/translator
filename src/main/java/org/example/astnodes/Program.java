package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

class Program extends ASTNode {
    List<ClassDef> classes;

    Program(List<ClassDef> classes) {
        this.classes = classes;
    }
}