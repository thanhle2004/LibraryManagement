package com.src.frontend;

import javax.swing.*;
import java.awt.*;

public class ReturnBook extends JFrame {
    public ReturnBook() {
        setTitle("Return Book");
        setSize(1100, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // Left Panel (Book Details)
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 550, 640); // 50% Width
        leftPanel.setBackground(new Color(47, 120, 152)); // Updated color
        leftPanel.setLayout(null);

        // Load the Book Icon and set it larger 
        ImageIcon bookIcon = new ImageIcon(getClass().getResource("/com/res/IconBook1.png"));
        Image scaledBookImage = bookIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        bookIcon = new ImageIcon(scaledBookImage);
        
        // Book Title with Icon: Icon on the left, text on the right
        JLabel bookIconLabel = new JLabel(bookIcon);
        bookIconLabel.setBounds(80, 65, 64, 64);
        leftPanel.add(bookIconLabel);
        
        
        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 200, 40); // Adjusted position and size
        backButton.setBackground(new Color(0, 80, 160)); // Updated to match new design
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Verdana", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(30, 100, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 80, 160));
            }
        });
        
        leftPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });
        
        JLabel bookDetailsLabel = new JLabel("Book Details");
        bookDetailsLabel.setFont(new Font("Verdana", Font.BOLD, 26)); // Increased font size
        bookDetailsLabel.setForeground(Color.WHITE);
        bookDetailsLabel.setBounds(150, 80, 250, 30); // Moved below the back button
        leftPanel.add(bookDetailsLabel);
        
        JLabel bookIdInfoLabel = new JLabel("Book ID:");
        bookIdInfoLabel.setForeground(Color.WHITE);
        bookIdInfoLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        bookIdInfoLabel.setBounds(30, 140, 150, 25);
        leftPanel.add(bookIdInfoLabel);
        
        JTextField bookIdInfoField = new JTextField();
        bookIdInfoField.setBounds(30, 170, 250, 35);
        leftPanel.add(bookIdInfoField);
        
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setForeground(Color.WHITE);
        bookNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        bookNameLabel.setBounds(30, 220, 150, 25);
        leftPanel.add(bookNameLabel);
        
        JTextField bookNameField = new JTextField();
        bookNameField.setBounds(30, 250, 250, 35);
        leftPanel.add(bookNameField);
        
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setForeground(Color.WHITE);
        authorLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        authorLabel.setBounds(30, 300, 150, 25);
        leftPanel.add(authorLabel);
        
        JTextField authorField = new JTextField();
        authorField.setBounds(30, 330, 250, 35);
        leftPanel.add(authorField);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        quantityLabel.setBounds(30, 380, 150, 25);
        leftPanel.add(quantityLabel);
        
        JTextField quantityField = new JTextField();
        quantityField.setBounds(30, 410, 250, 35);
        leftPanel.add(quantityField);
        
        add(leftPanel);
        
        // Right Panel (Issue Book)
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(550, 0, 550, 640);
        rightPanel.setBackground(new Color(220, 235, 220));
        rightPanel.setLayout(null);

        // Load IssueBook Icon
        ImageIcon issueBookIcon = new ImageIcon(getClass().getResource("/com/res/IssueBook.png"));
        Image scaledIssueBookImage = issueBookIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        issueBookIcon = new ImageIcon(scaledIssueBookImage);

        // Label for IssueBook Icon
        JLabel issueBookIconLabel = new JLabel(issueBookIcon);
        issueBookIconLabel.setBounds(80, 65, 64, 64);
        rightPanel.add(issueBookIconLabel);

        // Label for Issue Book Title
        JLabel issueBookLabel = new JLabel("Issue Book");
        issueBookLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        issueBookLabel.setBounds(160, 80, 250, 30);
        rightPanel.add(issueBookLabel);

        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        bookIdLabel.setBounds(30, 140, 150, 25);
        rightPanel.add(bookIdLabel);

        JTextField bookIdField = new JTextField();
        bookIdField.setBounds(30, 170, 250, 35);
        rightPanel.add(bookIdField);

        JLabel studentIdLabel = new JLabel("Enter Student ID:");
        studentIdLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        studentIdLabel.setBounds(30, 220, 160, 25);
        rightPanel.add(studentIdLabel);

        JTextField studentIdField = new JTextField();
        studentIdField.setBounds(30, 250, 250, 35);
        rightPanel.add(studentIdField);

        JButton returnButton = new JButton("Issue Book");
        returnButton.setBounds(30, 310, 250, 45);
        returnButton.setBackground(new Color(0, 80, 160));
        returnButton.setForeground(Color.WHITE);
        returnButton.setFont(new Font("Verdana", Font.BOLD, 16));
        rightPanel.add(returnButton);

        add(rightPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReturnBook();
    }
}



