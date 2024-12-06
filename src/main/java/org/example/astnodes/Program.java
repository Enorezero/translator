package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

public class Program extends ASTNode {
    public List<ClassDef> classes;

    Program(List<ClassDef> classes) {
        this.classes = classes;
    }
}