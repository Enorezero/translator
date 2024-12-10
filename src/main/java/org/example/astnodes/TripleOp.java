package org.example.astnodes;

import org.example.ASTNode;

public class TripleOp  extends ASTNode {
    //res = res + i
    public String left;
    public String interMediateOperator;
    public String firstOperand;
    public String operator;
    public String secondOperand;

    public TripleOp(String left, String interMediateOperator, String firstOperand, String operator, String secondOperand) {
        this.left = left;
        this.interMediateOperator = interMediateOperator;
        this.firstOperand = firstOperand;
        this.operator = operator;
        this.secondOperand = secondOperand;
    }
}
