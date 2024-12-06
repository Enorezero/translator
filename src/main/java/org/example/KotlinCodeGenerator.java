package org.example;

import org.example.astnodes.*;

public class KotlinCodeGenerator implements ASTNodeVisitor {
  @Override
  public String visitAssignment(Assignment node) {
    return visit(node.left) + " = " + visit(node.right);
  }

  @Override
  public String visitAttribute(Attribute node) {
    String initializer = node.initializer != null ? " = " + visit(node.initializer) : "";
    return "val " + node.name + ": " + node.type + initializer;
  }

  @Override
  public String visitBinaryOp(BinaryOp node) {
    return visit(node.left) + " " + node.operator + " " + visit(node.right);
  }

  @Override
  public String visitBlock(Block node) {
    return node.statements.stream()
            .map(this::visit)
            .reduce("", (a, b) -> a + "\n" + b);
  }

  @Override
  public String visitClassDef(ClassDef node) {
    String fields = node.fields.stream()
            .map(this::visit)
            .reduce("", (a, b) -> a + "\n    " + b);

    String methods = node.methods.stream()
            .map(this::visit)
            .reduce("", (a, b) -> a + "\n\n    " + b);

    return "class " + node.name +
            (node.baseClass != null ? " : " + node.baseClass : "") +
            " {\n" + fields + methods + "\n}";
  }

  @Override
  public String visitForStatement(ForStatement node) {
    return "for (" + visit(node.initializer) + "; " + visit(node.condition) + "; " + visit(node.update) + ") {\n" +
            visitBlock(new Block(node.body)) + "\n}";
  }

  @Override
  public String visitIfStatement(IfStatement node) {
    String trueBody = visitBlock(new Block(node.trueBody));
    String falseBody = node.falseBody != null ? " else {\n" + visitBlock(new Block(node.falseBody)) + "\n}" : "";
    return "if (" + visit(node.condition) + ") {\n" + trueBody + "\n}" + falseBody;
  }

  @Override
  public String visitImportStatement(ImportStatement node) {
    return "import " + node.module + (node.alias != null ? " as " + node.alias : "");
  }

  @Override
  public String visitLiteral(Literal node) {
    return node.value.toString();
  }

  @Override
  public String visitMethodCall(MethodCall node) {
    String args = node.arguments.stream()
            .map(this::visit)
            .reduce((a, b) -> a + ", " + b)
            .orElse("");
    return node.methodName + "(" + args + ")";
  }

  @Override
  public String visitMethodDef(MethodDef node) {
    String params = String.join(", ", node.params);
    String body = visitBlock(new Block(node.body));
    return "fun " + node.name + "(" + params + ") {\n" + body + "\n}";
  }

  @Override
  public String visitProgram(Program node) {
    return node.classes.stream()
            .map(this::visit)
            .reduce("", (a, b) -> a + "\n\n" + b);
  }

  @Override
  public String visitReturnStatement(ReturnStatement node) {
    return "return " + visit(node.expression);
  }

  @Override
  public String visitTryCatchStatement(TryCatchStatement node) {
    String tryBody = visitBlock(new Block(node.tryBody));
    String catchBlocks = node.catchBlocks.entrySet().stream()
            .map(entry -> "catch (" + entry.getKey() + ") {\n" + visitBlock(new Block(entry.getValue())) + "\n}")
            .reduce("", (a, b) -> a + "\n" + b);

    String finallyBody = node.finallyBody != null ? "finally {\n" + visitBlock(new Block(node.finallyBody)) + "\n}" : "";
    return "try {\n" + tryBody + "\n}" + catchBlocks + "\n" + finallyBody;
  }

  @Override
  public String visitUnaryOp(UnaryOp node) {
    return node.operator + visit(node.operand);
  }

  @Override
  public String visitVariableDeclaration(VariableDeclaration node) {
    String initializer = node.initializer != null ? " = " + visit(node.initializer) : "";
    return "var " + node.name + ": " + node.type + initializer;
  }

  @Override
  public String visitVariableReference(VariableReference node) {
    return node.name;
  }

  @Override
  public String visitWhileStatement(WhileStatement node) {
    return "while (" + visit(node.condition) + ") {\n" + visitBlock(new Block(node.body)) + "\n}";
  }

  private String visit(ASTNode node) {
    if (node instanceof Assignment) return visitAssignment((Assignment) node);
    if (node instanceof Attribute) return visitAttribute((Attribute) node);
    if (node instanceof BinaryOp) return visitBinaryOp((BinaryOp) node);
    if (node instanceof Block) return visitBlock((Block) node);
    if (node instanceof ClassDef) return visitClassDef((ClassDef) node);
    if (node instanceof ForStatement) return visitForStatement((ForStatement) node);
    if (node instanceof IfStatement) return visitIfStatement((IfStatement) node);
    if (node instanceof ImportStatement) return visitImportStatement((ImportStatement) node);
    if (node instanceof Literal) return visitLiteral((Literal) node);
    if (node instanceof MethodCall) return visitMethodCall((MethodCall) node);
    if (node instanceof MethodDef) return visitMethodDef((MethodDef) node);
    if (node instanceof Program) return visitProgram((Program) node);
    if (node instanceof ReturnStatement) return visitReturnStatement((ReturnStatement) node);
    if (node instanceof TryCatchStatement) return visitTryCatchStatement((TryCatchStatement) node);
    if (node instanceof UnaryOp) return visitUnaryOp((UnaryOp) node);
    if (node instanceof VariableDeclaration) return visitVariableDeclaration((VariableDeclaration) node);
    if (node instanceof VariableReference) return visitVariableReference((VariableReference) node);
    if (node instanceof WhileStatement) return visitWhileStatement((WhileStatement) node);

    throw new IllegalArgumentException("Unknown ASTNode type: " + node.getClass());
  }
}
