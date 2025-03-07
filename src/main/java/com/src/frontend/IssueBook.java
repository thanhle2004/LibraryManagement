package com.src.frontend;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class IssueBook extends JFrame {
    private JPanel navigationPanel, issuePanel, bookPanel, studentPanel, mainPanel;
    private JButton backButton, issueButton;
    private JTextField bookIdField, studentIdField;
    private JFormattedTextField issueDateField, dueDateField;
    // Fields for Book Details
    private JTextField bookDetailIdField, bookNameField, authorField, quantityField;
    // Fields for Student Details
    private JTextField studentDetailIdField, studentNameField, courseField, branchField;
    
    public IssueBook() {
        setPreferredSize(new Dimension(1100, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        initComponents();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);
        
        // Navigation Panel (Left)
        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(5, 77, 120));
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);
        
        // Back Button
        backButton = new RoundedButton("Back");
        backButton.setBounds(10, 10, 280, 45);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        navigationPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });
        
        // Issue Book Panel (Left Form)
        issuePanel = new JPanel();
        issuePanel.setBackground(new Color(5, 77, 120));
        issuePanel.setBounds(0, 60, 300, 580);
        issuePanel.setLayout(null);
        navigationPanel.add(issuePanel);
        
        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setForeground(Color.WHITE);
        bookIdLabel.setBounds(20, 30, 200, 25);
        issuePanel.add(bookIdLabel);
        
        bookIdField = new JTextField();
        bookIdField.setBounds(20, 60, 260, 30);
        issuePanel.add(bookIdField);
        
        JLabel studentIdLabel = new JLabel("Enter Student ID:");
        studentIdLabel.setForeground(Color.WHITE);
        studentIdLabel.setBounds(20, 100, 200, 25);
        issuePanel.add(studentIdLabel);
        
        studentIdField = new JTextField();
        studentIdField.setBounds(20, 130, 260, 30);
        issuePanel.add(studentIdField);
        
        JLabel issueDateLabel = new JLabel("Issue Date:");
        issueDateLabel.setForeground(Color.WHITE);
        issueDateLabel.setBounds(20, 170, 200, 25);
        issuePanel.add(issueDateLabel);
        
        issueDateField = new JFormattedTextField();
        issueDateField.setBounds(20, 200, 260, 30);
        issuePanel.add(issueDateField);
        
        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setForeground(Color.WHITE);
        dueDateLabel.setBounds(20, 240, 200, 25);
        issuePanel.add(dueDateLabel);
        
        dueDateField = new JFormattedTextField();
        dueDateField.setBounds(20, 270, 260, 30);
        issuePanel.add(dueDateField);
        
        issueButton = new JButton("Issue Book");
        issueButton.setBounds(20, 320, 260, 40);
        issueButton.setBackground(new Color(47, 120, 152));
        issueButton.setForeground(Color.WHITE);
        issuePanel.add(issueButton);
        
        // Book Details Panel (Middle)
        bookPanel = createPanel(new Color(220, 238, 229), 310, 0, 400, 640);
        bookPanel.setLayout(null);
        mainPanel.add(bookPanel);
        
        // Load the Book Icon and set it larger 
        ImageIcon bookIcon = new ImageIcon(getClass().getResource("/com/res/IconBook.png"));
        Image scaledBookImage = bookIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        bookIcon = new ImageIcon(scaledBookImage);
        
        // Book Title with Icon: Icon on the left, text on the right
        JLabel bookIconLabel = new JLabel(bookIcon);
        bookIconLabel.setBounds(40, 7, 64, 64);
        bookPanel.add(bookIconLabel);
        
        JLabel bookTitle = new JLabel("Book Details", SwingConstants.LEFT);
        bookTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        bookTitle.setBounds(110, 20, 300, 32);
        bookPanel.add(bookTitle);
        
        JLabel bookIdDetailLabel = new JLabel("Book ID:");
        bookIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookIdDetailLabel.setBounds(20, 80, 150, 25);
        bookPanel.add(bookIdDetailLabel);
        
        bookDetailIdField = new JTextField();
        bookDetailIdField.setBounds(20, 110, 360, 30);
        bookPanel.add(bookDetailIdField);
        
        JLabel bookNameDetailLabel = new JLabel("Book Name:");
        bookNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookNameDetailLabel.setBounds(20, 160, 150, 25);
        bookPanel.add(bookNameDetailLabel);
        
        bookNameField = new JTextField();
        bookNameField.setBounds(20, 190, 360, 30);
        bookPanel.add(bookNameField);
        
        JLabel authorDetailLabel = new JLabel("Author:");
        authorDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        authorDetailLabel.setBounds(20, 240, 150, 25);
        bookPanel.add(authorDetailLabel);
        
        authorField = new JTextField();
        authorField.setBounds(20, 270, 360, 30);
        bookPanel.add(authorField);
        
        JLabel quantityDetailLabel = new JLabel("Quantity:");
        quantityDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        quantityDetailLabel.setBounds(20, 320, 150, 25);
        bookPanel.add(quantityDetailLabel);
        
        quantityField = new JTextField();
        quantityField.setBounds(20, 350, 360, 30);
        bookPanel.add(quantityField);
        
        // Student Details Panel (Right)
        studentPanel = createPanel(new Color(220, 238, 229), 720, 0, 380, 640);
        studentPanel.setLayout(null);
        mainPanel.add(studentPanel);
        
        // Load the Student Icon from StudentDetail.png and set it larger (64x64)
        ImageIcon studentIcon = new ImageIcon(getClass().getResource("/com/res/StudentDetail.png"));
        Image scaledStudentImage = studentIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        studentIcon = new ImageIcon(scaledStudentImage);
        
        // Student Title with Icon: Icon on the left, text on the right
        JLabel studentIconLabel = new JLabel(studentIcon);
        studentIconLabel.setBounds(20, 10, 64, 64);
        studentPanel.add(studentIconLabel);
        
        JLabel studentTitle = new JLabel("Student Details", SwingConstants.LEFT);
        studentTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        studentTitle.setBounds(90, 20, 300, 32);
        studentPanel.add(studentTitle);
        
        JLabel studentIdDetailLabel = new JLabel("Student ID:");
        studentIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        studentIdDetailLabel.setBounds(20, 80, 150, 25);
        studentPanel.add(studentIdDetailLabel);
        
        studentDetailIdField = new JTextField();
        studentDetailIdField.setBounds(20, 110, 340, 30);
        studentPanel.add(studentDetailIdField);
        
        JLabel studentNameDetailLabel = new JLabel("Student Name:");
        studentNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        studentNameDetailLabel.setBounds(20, 160, 200, 25);
        studentPanel.add(studentNameDetailLabel);
        
        studentNameField = new JTextField();
        studentNameField.setBounds(20, 190, 340, 30);
        studentPanel.add(studentNameField);
        
        JLabel courseDetailLabel = new JLabel("Course Name:");
        courseDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        courseDetailLabel.setBounds(20, 240, 150, 25);
        studentPanel.add(courseDetailLabel);
        
        courseField = new JTextField();
        courseField.setBounds(20, 270, 340, 30);
        studentPanel.add(courseField);
        
        JLabel branchDetailLabel = new JLabel("Branch:");
        branchDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        branchDetailLabel.setBounds(20, 320, 150, 25);
        studentPanel.add(branchDetailLabel);
        
        branchField = new JTextField();
        branchField.setBounds(20, 350, 340, 30);
        studentPanel.add(branchField);
    }
    
    private JPanel createPanel(Color color, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        return panel;
    }
    
    class RoundedButton extends JButton {
        private int radius = 5;
        private Color borderColor = new Color(5, 77, 120);
        
        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    setBackground(new Color(0, 0, 0, 50));
                    repaint();
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    setBackground(new Color(47, 120, 152));
                    repaint();
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int arc = radius * 5;
            g2.setColor(getBackground());
            g2.fillRoundRect(1, 1, width - 3, height - 2, arc, arc);
            g2.setColor(borderColor);
            g2.drawRoundRect(1, 1, width - 3, height - 3, arc, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}