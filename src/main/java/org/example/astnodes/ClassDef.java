package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a class definition
class ClassDef extends ASTNode {
    String name;
    String baseClass;
    List<MethodDef> methods;
    List<Attribute> fields;

    ClassDef(String name, String baseClass, List<MethodDef> methods, List<Attribute> fields) {
        this.name = name;
        this.baseClass = baseClass;
        this.methods = methods;
        this.fields = fields;
    }
}