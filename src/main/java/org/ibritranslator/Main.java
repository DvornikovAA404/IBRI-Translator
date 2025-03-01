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

        // Получаем размеры экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2; // Половина ширины экрана
        int height = (int) (screenSize.height * 0.75); // Три четверти высоты экрана

        // Устанавливаем размеры и позицию окна
        setSize(width, height);
        setLocation(screenSize.width - width, 0); // Правый верхний угол

        // Компоненты
        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Russian"});
        JButton menuButton = createMenuButton();
        inputTextField = new JTextField(20); // Начальная ширина текстового поля
        outputTextArea = new JTextArea(10, 30); // Начальные размеры текстового поля
        notesTextArea = new JTextArea(10, 30);
        descriptionTextArea = new JTextArea(10, 30);

        // Настройка полей ввода
        outputTextArea.setEditable(false);
        notesTextArea.setEditable(false);
        descriptionTextArea.setEditable(false);

        // Верхняя панель с GridBagLayout
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Кнопка меню (30x30)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        topPanel.add(menuButton, gbc);

        // Левый комбобокс (автоматическая ширина)
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(inputLanguageComboBox, gbc);

        // Правый комбобокс (ширина = полю под ним)
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        outputLanguageComboBox.setPreferredSize(new Dimension(inputTextField.getPreferredSize().width,
                outputLanguageComboBox.getPreferredSize().height));
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

        // Автоподстройка правого комбобокса
        inputTextField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                outputLanguageComboBox.setPreferredSize(new Dimension(inputTextField.getWidth(),
                        outputLanguageComboBox.getPreferredSize().height));
                revalidate();
                repaint();
            }
        });

        // Убедимся, что окно правильно масштабируется
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