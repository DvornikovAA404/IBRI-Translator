package org.ibritranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main extends JFrame {
    // ГЛОБАЛЬНЫЕ ЦВЕТА
    public static final Color PRIMARY_COLOR = new Color(0x000000); // Черный
    public static final Color ACCENT_COLOR = new Color(0xFFD7D7);  // Белый с розовым оттенком

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
        inputTextField = new JTextField(20);
        outputTextArea = new JTextArea(10, 30);
        notesTextArea = new JTextArea(10, 30);
        descriptionTextArea = new JTextArea(10, 30);

        // Настройка полей ввода
        outputTextArea.setEditable(false);
        notesTextArea.setEditable(false);
        descriptionTextArea.setEditable(false);

        // Верхняя панель
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel.setBackground(PRIMARY_COLOR);

        // Кнопка меню
        gbc.gridx = 0; gbc.gridy = 0;
        topPanel.add(menuButton, gbc);

        // Левый комбобокс
        gbc.gridx = 1; gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        inputLanguageComboBox.setBackground(PRIMARY_COLOR);
        inputLanguageComboBox.setForeground(ACCENT_COLOR);
        inputLanguageComboBox.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        inputLanguageComboBox.setRenderer(new ComboBoxRenderer(ACCENT_COLOR)); // Изменяем цвет стрелочки
        topPanel.add(inputLanguageComboBox, gbc);

        // Правый комбобокс
        gbc.gridx = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        outputLanguageComboBox.setBackground(PRIMARY_COLOR);
        outputLanguageComboBox.setForeground(ACCENT_COLOR);
        outputLanguageComboBox.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        outputLanguageComboBox.setRenderer(new ComboBoxRenderer(ACCENT_COLOR)); // Изменяем цвет стрелочки
        outputLanguageComboBox.setPreferredSize(new Dimension(
                outputTextArea.getPreferredSize().width,
                outputLanguageComboBox.getPreferredSize().height
        ));
        topPanel.add(outputLanguageComboBox, gbc);

        // Центральная панель
        JPanel middlePanel = new JPanel(new GridLayout(1, 2));
        middlePanel.setBackground(PRIMARY_COLOR);
        inputTextField.setBackground(PRIMARY_COLOR);
        inputTextField.setForeground(ACCENT_COLOR);
        inputTextField.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        outputTextArea.setBackground(PRIMARY_COLOR);
        outputTextArea.setForeground(ACCENT_COLOR);
        outputTextArea.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        middlePanel.add(inputTextField);
        middlePanel.add(new JScrollPane(outputTextArea));

        // Нижняя панель
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.setBackground(PRIMARY_COLOR);
        notesTextArea.setBackground(PRIMARY_COLOR);
        notesTextArea.setForeground(ACCENT_COLOR);
        notesTextArea.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        descriptionTextArea.setBackground(PRIMARY_COLOR);
        descriptionTextArea.setForeground(ACCENT_COLOR);
        descriptionTextArea.setBorder(BorderFactory.createEmptyBorder()); // Убираем границу
        bottomPanel.add(new JScrollPane(notesTextArea));
        bottomPanel.add(new JScrollPane(descriptionTextArea));

        // Основные настройки окна
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Автоподстройка правого комбобокса
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

        // Финальные настройки
        setForeground(ACCENT_COLOR);
        setBackground(PRIMARY_COLOR);
        setUndecorated(false); // Убираем стандартную рамку, если нужно
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JButton createMenuButton() {
        JButton button = new JButton(new MenuIcon());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(PRIMARY_COLOR);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

    // Иконка меню с цветом из глобальных переменных
    private static class MenuIcon implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(ACCENT_COLOR);
            g2d.setStroke(new BasicStroke(2)); // Толщина линий
            g2d.drawLine(x + 5, y + 10, x + 25, y + 10);
            g2d.drawLine(x + 5, y + 18, x + 25, y + 18);
            g2d.drawLine(x + 5, y + 26, x + 25, y + 26);
            g2d.dispose();
        }

        @Override
        public int getIconWidth() { return 30; }
        @Override
        public int getIconHeight() { return 30; }
    }

    // Кастомный рендерер для JComboBox
    private static class ComboBoxRenderer extends DefaultListCellRenderer {
        private final Color arrowColor;

        public ComboBoxRenderer(Color arrowColor) {
            this.arrowColor = arrowColor;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setBackground(PRIMARY_COLOR);
            label.setForeground(ACCENT_COLOR);
            return label;
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width += 20; // Добавляем место для стрелочки
            return size;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(arrowColor);
            int x = getWidth() - 15;
            int y = getHeight() / 2 - 4;
            g2d.fillPolygon(new int[]{x, x + 8, x + 4}, new int[]{y, y, y + 8}, 3);
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}