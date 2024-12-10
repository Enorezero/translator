package org.example.generator;

import org.apache.commons.lang3.StringUtils;
import org.example.ASTNode;
import org.example.astnodes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.substringBefore;

public class KotlinCodeGenerator {

  public String generateCode(ASTNode node) {
    if (node == null) {
      return "";
    }

    if (node instanceof Program) {
      return generateProgram((Program) node);
    } else if (node instanceof ClassDef) {
      return generateClass((ClassDef) node);
    } else if (node instanceof MethodDef) {
      return generateMethod((MethodDef) node);
    } else if (node instanceof VariableDeclaration) {
      return generateVariableDeclaration((VariableDeclaration) node);
    } else if (node instanceof BinaryOp) {
      return generateBinaryOp((BinaryOp) node);
    } else if (node instanceof UnaryOp) {
      return generateUnaryOp((UnaryOp) node);
    } else if (node instanceof IfStatement) {
      return generateIfStatement((IfStatement) node);
    } else if (node instanceof ForStatement) {
      return generateForStatement((ForStatement) node);
    } else if (node instanceof WhileStatement) {
      return generateWhileStatement((WhileStatement) node);
    } else if (node instanceof ReturnStatement) {
      return generateReturnStatement((ReturnStatement) node);
    } else if (node instanceof MethodCall) {
      return generateMethodCall((MethodCall) node);
    } else if (node instanceof VariableReference) {
      return generateVariableReference((VariableReference) node);
    } else if (node instanceof Condition) {
      return ((Condition) node).condition;
    } else if (node instanceof TripleOp) {
      return generateTripleOp((TripleOp) node);
    }

    return "";
  }

  private String generateTripleOp(TripleOp node) {
    return node.left + " " + node.interMediateOperator + " " + node.firstOperand + " " + node.operator + " " + node.secondOperand + ";";
  }

  private String generateProgram(Program program) {
    return program.classes.stream()
            .map(this::generateCode)
            .filter(line -> !line.isEmpty())
            .collect(Collectors.joining("\n"));
  }

  private String generateClass(ClassDef classDef) {
    String modifiers = String.join(" ",
            nullToEmpty(classDef.getAbstractModificator()),
            nullToEmpty(classDef.getModificator()),
            nullToEmpty(classDef.getFinalModificator())).trim();

    StringBuilder builder = new StringBuilder();
    builder.append(modifiers.isEmpty() ? "" : modifiers + " ")
            .append("class ")
            .append(classDef.getName());

    if (classDef.getBaseClass() != null) {
      builder.append(" : ").append(classDef.getBaseClass());
    }

    builder.append(" {\n");

    List<MethodDef> staticMethods = new ArrayList<>();
    List<MethodDef> instanceMethods = new ArrayList<>();

    if (classDef.getBody() != null) {
      for (ASTNode bodyNode : classDef.getBody()) {
        if (bodyNode instanceof MethodDef) {
          MethodDef methodDef = (MethodDef) bodyNode;
          if (methodDef.getStaticModificator() != null) {
            staticMethods.add(methodDef);
          } else {
            instanceMethods.add(methodDef);
          }
        }
      }

      if (!staticMethods.isEmpty()) {
        builder.append("companion object {\n");
        for (MethodDef methodDef : staticMethods) {
          builder.append(generateMethod(methodDef)).append("\n");
        }
        builder.append("}\n");
      }

      for (MethodDef methodDef : instanceMethods) {
        builder.append(generateMethod(methodDef)).append("\n");
      }
    }

    builder.append("}");
    return builder.toString();
  }

  private String generateMethod(MethodDef methodDef) {
    String modifiers = String.join(" ",
            nullToEmpty(methodDef.getAbstractModificator()),
            nullToEmpty(methodDef.getModificator()),
            nullToEmpty(methodDef.getFinalModificator())).trim();

    StringBuilder builder = new StringBuilder();
    builder.append(modifiers.isEmpty() ? "" : modifiers + " ")
            .append("fun ")
            .append(methodDef.getName())
            .append("(")
            .append(methodDef.getParams().stream()
                    .map(param -> param.getName() + ": " + javaTypeToKotlinType(param.getType()))
                    .collect(Collectors.joining(", ")))
            .append(")");

    // Если метод возвращает значение, добавляем тип возвращаемого значения
    if (methodDef.getType() != null && !methodDef.getType().equals("void") && !methodDef.getType().isEmpty()) {
      builder.append(": ").append(javaTypeToKotlinType(methodDef.getType()));
    }

    builder.append(" {\n");

    // Генерация тела метода
    if (methodDef.getBody() != null) {
      for (ASTNode bodyNode : methodDef.getBody()) {
        String code = generateCode(bodyNode);
        if (!code.isEmpty()) {
          builder.append(code).append("\n");
        }
      }
    }

    // Если есть выражение return, добавляем его
    if (methodDef.getReturnStatement() != null) {
      builder.append("return ").append(methodDef.getReturnStatement().expression).append("\n");
    }
    builder.append("}");
    return builder.toString();
  }



  private String generateVariableDeclaration(VariableDeclaration declaration) {
    String modifiers = String.join(" ",
            nullToEmpty(declaration.getModificator()),
            nullToEmpty(declaration.getStaticModificator()),
            nullToEmpty(declaration.getFinalModificator())).trim();
    if (declaration.getType().equals("String")){
      return "var "+ (modifiers.isEmpty() ? "" : modifiers + " ")
              + declaration.getName() + ": " + javaTypeToKotlinType(declaration.getType())
              + (declaration.getInitializer() != null ? " = \"" + generateCode(declaration.getInitializer())+"\"" : "");
    }
    return "var "+ (modifiers.isEmpty() ? "" : modifiers + " ")
            + declaration.getName() + ": " + javaTypeToKotlinType(declaration.getType())
            + (declaration.getInitializer() != null ? " = " + generateCode(declaration.getInitializer()) : "");
  }

  private String generateBinaryOp(BinaryOp binaryOp) {
    return binaryOp.left + " " + binaryOp.operator + " " + binaryOp.right;
  }

  private String generateUnaryOp(UnaryOp unaryOp) {
    return unaryOp.operator + unaryOp.operand;
  }

  private String generateIfStatement(IfStatement ifStatement) {
    StringBuilder builder = new StringBuilder();
    builder.append("if ").append(generateCode(ifStatement.condition)).append(" {\n");
    for (ASTNode node : ifStatement.trueBody) {
      String code = generateCode(node);
      if (!code.isEmpty()) {
        builder.append(code).append("\n");
      }
    }
    builder.append("}");
    if (ifStatement.falseBody != null && !ifStatement.falseBody.isEmpty()) {
      builder.append(" else {\n");
      for (ASTNode node : ifStatement.falseBody) {
        String code = generateCode(node);
        if (!code.isEmpty()) {
          builder.append(code).append("\n");
        }
      }
      builder.append("}");
    }
    return builder.toString();
  }


  private String generateForStatement(ForStatement forStatement) {
    StringBuilder builder = new StringBuilder();
    String condition = forStatement.condition.condition;

    if (condition != null && condition.contains(";")) {
      String[] parts = condition.split(";");
      if (parts.length == 3) {
        String initialization = parts[0].trim();
        String comparison = parts[1].trim();
        String increment = parts[2].trim();

        String variable = initialization.split(" ")[1];

        String startValue = initialization.split("=")[1].trim();

        String rangeEnd = comparison.split("<")[1].trim();

        String stepValue = "1";
        if (increment.contains("+") || increment.contains("-")) {
          String[] stepParts = increment.split("\\+|\\-");
          stepValue = stepParts[stepParts.length - 1].trim();
        }

        builder.append("for (")
                .append(variable)
                .append(" in ")
                .append(startValue)
                .append("..")
                .append(rangeEnd)
                .append(" step ")
                .append(stepValue)
                .append(" {\n");

        for (ASTNode node : forStatement.body) {
          builder.append(generateCode(node)).append("\n");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("}");
        return builder.toString();
      }
    }

    builder.append("// Unsupported 'for' loop: ").append(condition).append("\n");
    builder.append("for (/* unsupported */) {\n");

    for (ASTNode node : forStatement.body) {
      builder.append(generateCode(node)).append("\n");
    }

    builder.append("}");
    return builder.toString();
  }



  private String generateWhileStatement(WhileStatement whileStatement) {
    StringBuilder builder = new StringBuilder();
    builder.append("while ").append(generateCode(whileStatement.condition)).append(" {\n");
    for (ASTNode node : whileStatement.body) {
      String code = generateCode(node);
      if (!code.isEmpty()) {
        builder.append(code).append("\n");
      }
    }
    builder.append("}");
    return builder.toString();
  }

  private String generateReturnStatement(ReturnStatement returnStatement) {
    return "return " + returnStatement.expression + ";";
  }

  private String generateMethodCall(MethodCall methodCall) {
    return methodCall.methodName + "(" +
            methodCall.arguments.stream()
                    .map(this::generateCode)
                    .collect(Collectors.joining(", ")) +
            ")";
  }

  private String generateVariableReference(VariableReference reference) {
    return reference.name;
  }

  private String nullToEmpty(String value) {
    return value == null ? "" : value;
  }

  private String javaTypeToKotlinType(String javaType) {
    if (javaType == null) {
      return "";
    }
    if (StringUtils.contains(javaType, "[]")) {
      return "Array<" + capitalize(substringBefore(javaType, "[]")) + ">";
    }
    return capitalize(javaType);
  }
}
