package org.example.astnodes;

import org.example.ASTNode;

// Represents a variable declaration or assignment
public class VariableDeclaration extends ASTNode {

    private String modificator;
    private String staticModificator;
    private String finalModificator;
    private String type;
    private String name;
    private ASTNode initializer;

    public VariableDeclaration() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ASTNode getInitializer() {
        return initializer;
    }

    public void setInitializer(ASTNode initializer) {
        this.initializer = initializer;
    }
}
