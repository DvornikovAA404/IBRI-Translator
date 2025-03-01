package org.ibritranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main extends JFrame {
    private JComboBox<String> inputLanguageComboBox;
    private JComboBox<String> outputLanguageComboBox;
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JTextArea notesTextArea;
    private JTextArea descriptionTextArea;

    public Main() {
        setTitle("IBRI Translator");

        // Размеры окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocation(screenSize.width - width, 0);

        // Компоненты
        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        JButton menuButton = createMenuButton();
        inputTextField = new JTextField(20); // Левое поле ввода
        outputTextArea = new JTextArea(10, 30); // Правое поле вывода
        notesTextArea = new JTextArea(10, 30);
        descriptionTextArea = new JTextArea(10, 30);

        // Настройка полей ввода
        outputTextArea.setEditable(false);
        notesTextArea.setEditable(false);
        descriptionTextArea.setEditable(false);

        // Верхняя панель с GridBagLayout
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Кнопка меню
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        topPanel.add(menuButton, gbc);

        // Левый комбобокс
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(inputLanguageComboBox, gbc);

        // Правый комбобокс (ширина = полю под ним)
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        outputLanguageComboBox.setPreferredSize(new Dimension(
                outputTextArea.getPreferredSize().width, // Теперь берем ширину правого поля
                outputLanguageComboBox.getPreferredSize().height
        ));
        topPanel.add(outputLanguageComboBox, gbc);

        // Центральная панель
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        middlePanel.add(inputTextField);
        middlePanel.add(new JScrollPane(outputTextArea));

        // Нижняя панель
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(new JScrollPane(notesTextArea));
        bottomPanel.add(new JScrollPane(descriptionTextArea));

        // Основные панели
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Автоподстройка правого комбобокса при изменении размера окна
        outputTextArea.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                outputLanguageComboBox.setPreferredSize(new Dimension(
                        outputTextArea.getWidth(),
                        outputLanguageComboBox.getPreferredSize().height
                ));
                revalidate();
                repaint();
            }
        });

        // Исправление размеров компонентов
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }

    private JButton createMenuButton() {
        JButton button = new JButton(new MenuIcon());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(30, 30));
        button.setMaximumSize(new Dimension(30, 30));
        button.setMinimumSize(new Dimension(30, 30));
        return button;
    }

    private static class MenuIcon implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(Color.BLACK);
            g.fillRect(x + 3, y + 6, 24, 2);
            g.fillRect(x + 3, y + 12, 24, 2);
            g.fillRect(x + 3, y + 18, 24, 2);
        }

        @Override
        public int getIconWidth() {
            return 30;
        }

        @Override
        public int getIconHeight() {
            return 30;
        }
    }
}