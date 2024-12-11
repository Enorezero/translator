package org.example;

import org.example.generator.KotlinCodeGenerator;
import org.example.lexer.Lexer;
import org.example.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TranslatorApp extends JFrame {

    private JTextArea javaResultArea;
    private JTextArea kotlinResultArea;
    private JTextArea errorOutputArea;

    public TranslatorApp() {
        setTitle("Translator Application");

        // Set the layout of the main frame
        setLayout(new BorderLayout());

        javaResultArea = new JTextArea();
        kotlinResultArea = new JTextArea();

        javaResultArea.setPreferredSize(new Dimension(400, 200));
        kotlinResultArea.setPreferredSize(new Dimension(400, 200));

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1, 2));
        resultPanel.add(new JScrollPane(javaResultArea));
        resultPanel.add(new JScrollPane(kotlinResultArea));

        errorOutputArea = new JTextArea();
        errorOutputArea.setPreferredSize(new Dimension(400, 100));
        errorOutputArea.setEditable(false);

        JButton translateButton = new JButton("Транслировать");
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translate();
            }
        });

        add(resultPanel, BorderLayout.NORTH);
        add(new JScrollPane(errorOutputArea), BorderLayout.CENTER);
        add(translateButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void translate() {
        try {

            String textFromJavaResultArea = javaResultArea.getText();

            if(textFromJavaResultArea.equals("")) {
                throw new RuntimeException("Ошибка: Введите код в форму");
            }
            KotlinCodeGenerator generator = new KotlinCodeGenerator();

            Lexer lexer = new Lexer(textFromJavaResultArea);

            Parser parser = new Parser(lexer.tokenize());

            List<ASTNode> nodes = parser.parse();

            StringBuilder codeBuilder = new StringBuilder();
            for (ASTNode node : nodes) {
                codeBuilder.append(generator.generateCode(node)).append('\n');
            }
            kotlinResultArea.setText("");
            kotlinResultArea.setText(codeBuilder.toString());

            errorOutputArea.setText("Транслировано успешно!");

        } catch (Exception ex) {
            errorOutputArea.setText("");
            errorOutputArea.setText("Ошибка: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println(javaResultArea.getText());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TranslatorApp app = new TranslatorApp();
            app.setVisible(true);
        });
    }
}
