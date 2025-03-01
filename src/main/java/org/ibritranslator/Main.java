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

        // Получаем размеры экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Устанавливаем размеры окна (половина ширины и половина высоты экрана)
        setSize(screenWidth / 2, screenHeight / 2);

        // Устанавливаем позицию окна в правой верхней части экрана
        setLocation(screenWidth - getWidth(), 0);

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