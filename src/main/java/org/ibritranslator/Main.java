package org.ibritranslator;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JComboBox<String> inputLanguageComboBox;
    private JComboBox<String> outputLanguageComboBox;
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JTextArea notesTextArea;
    private JTextArea descriptionTextArea;

    public Main() {
        setTitle("IBRI Translator");
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4, Toolkit.getDefaultToolkit().getScreenSize().height / 4);
        setLocationRelativeTo(null); // Центрирование окна на экране
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонентов
        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        inputTextField = new JTextField();
        outputTextArea = new JTextArea();
        notesTextArea = new JTextArea();
        descriptionTextArea = new JTextArea();

        // Настройка разметки
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.add(new JButton("Menu"));
        topPanel.add(inputLanguageComboBox);
        topPanel.add(outputLanguageComboBox);

        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        middlePanel.add(inputTextField);
        middlePanel.add(new JScrollPane(outputTextArea));

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(new JScrollPane(notesTextArea));
        bottomPanel.add(new JScrollPane(descriptionTextArea));

        // Добавление панелей в основное окно
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}