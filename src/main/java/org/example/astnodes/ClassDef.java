package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a class definition
public class ClassDef extends ASTNode {

    private String abstractModificator;
    private String modificator;
    private String staticModificator;
    private String finalModificator;
    public String name;
    public String baseClass;

    public List<ASTNode> getBody() {
        return body;
    }

    public void setBody(List<ASTNode> body) {
        this.body = body;
    }

    public List<ASTNode> body;

    public ClassDef() {}

    public String getAbstractModificator() {
        return abstractModificator;
    }

    public void setAbstractModificator(String abstractModificator) {
        this.abstractModificator = abstractModificator;
    }

    public String getModificator() {
        return modificator;
    }

    public void setModificator(String modificator) {
        this.modificator = modificator;
    }

    public String getStaticModificator() {
        return staticModificator;
    }

    public void setStaticModificator(String staticModificator) {
        this.staticModificator = staticModificator;
    }

    public String getFinalModificator() {
        return finalModificator;
    }

    public void setFinalModificator(String finalModificator) {
        this.finalModificator = finalModificator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }

}