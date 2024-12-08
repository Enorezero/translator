package org.example.astnodes;

import org.example.ASTNode;

import java.util.List;

// Represents a method definition
public class MethodDef extends ASTNode {

    private String abstractModificator;
    private String modificator;
    private String staticModificator;
    private String finalModificator;
    private String type;
    public String name;
    public List<VariableDeclaration> params;
    public List<ASTNode> body;

    public ReturnStatement returnStatement;

    public MethodDef() {
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

    public List<VariableDeclaration> getParams() {
        return params;
    }

    public void setParams(List<VariableDeclaration> params) {
        this.params = params;
    }

    public List<ASTNode> getBody() {
        return body;
    }

    public void setBody(List<ASTNode> body) {
        this.body = body;
    }

    public String getAbstractModificator() {
        return abstractModificator;
    }

    public void setAbstractModificator(String abstractModificator) {
        this.abstractModificator = abstractModificator;
    }

    public ReturnStatement getReturnStatement() {
        return returnStatement;
    }

    public void setReturnStatement(ReturnStatement returnStatement) {
        this.returnStatement = returnStatement;
    }
}
