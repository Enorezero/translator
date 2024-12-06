package org.example;

import org.example.astnodes.*;

public interface ASTNodeVisitor {
  String visitAssignment(Assignment node);
  String visitAttribute(Attribute node);
  String visitBinaryOp(BinaryOp node);
  String visitBlock(Block node);
  String visitClassDef(ClassDef node);
  String visitForStatement(ForStatement node);
  String visitIfStatement(IfStatement node);
  String visitImportStatement(ImportStatement node);
  String visitLiteral(Literal node);
  String visitMethodCall(MethodCall node);
  String visitMethodDef(MethodDef node);
  String visitProgram(Program node);
  String visitReturnStatement(ReturnStatement node);
  String visitTryCatchStatement(TryCatchStatement node);
  String visitUnaryOp(UnaryOp node);
  String visitVariableDeclaration(VariableDeclaration node);
  String visitVariableReference(VariableReference node);
  String visitWhileStatement(WhileStatement node);
}
