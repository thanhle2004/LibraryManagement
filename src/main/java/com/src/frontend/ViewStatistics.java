package com.src.frontend;


import javax.swing.*;
import javax.swing.table.JTableHeader;


import java.awt.*;
import java.awt.event.*;


public class ViewStatistics extends JFrame {


    private JButton backButton;
    private JPanel mainPanel;


    public ViewStatistics() {
        setPreferredSize(new Dimension(1000, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        pack();


        initComponents();


        setVisible(true);
        setLocationRelativeTo(null);
    }


    private void initComponents() {
        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220,238,229);


        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(47, 120, 152));


        // Title
        JLabel lblTitle = new JLabel("Statistics");
        lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 40));
        lblTitle.setForeground(LightColor);
        lblTitle.setBounds(380, 30, 400, 60);
        mainPanel.add(lblTitle);


        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(20, 20, 100, 40);
        backButton.setBackground(new Color(3, 47, 90));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBorderPainted(false);


        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                backButton.setBackground(new Color(5, 77, 120));
            }


            public void mouseExited(MouseEvent evt) {
                backButton.setBackground(new Color(3, 47, 90));
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });


        mainPanel.add(backButton);


        String[] titles = { "Books Borrowed", "Late Returns", "Fines Collected", "Most Active Member" };
        String[] values = { "1,234", "98", "$245.50", "Nguyen Van A" };


        for (int i = 0; i < 4; i++) {
            JPanel statCard = new JPanel();
            statCard.setLayout(null);
            statCard.setBackground(new Color(220, 238, 229));
            statCard.setBounds(60 + i * 220, 120, 200, 100);
            statCard.setBorder(BorderFactory.createLineBorder(DarkColor, 2));


            JLabel statTitle = new JLabel(titles[i]);
            statTitle.setOpaque(true);
            statTitle.setBackground(DarkColor);
            statTitle.setBounds(0, 0, 200, 30);
            statTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
            statTitle.setForeground(LightColor);
            statTitle.setHorizontalAlignment(SwingConstants.CENTER);
            statCard.add(statTitle);


            JLabel statValue = new JLabel(values[i]);
            statValue.setFont(new Font("Tahoma", Font.BOLD, 16));
            statValue.setForeground(DarkColor);
            statValue.setBounds(0, 30, 200, 70);
            statValue.setHorizontalAlignment(SwingConstants.CENTER);
            statCard.add(statValue);


            mainPanel.add(statCard);
        }


        String[] columnNames = { "Rank", "Book Title", "Times Borrowed", "Last Borrowed" };
        Object[][] data = {
            {1, "Clean Code", 45, "2025-04-22"},
            {2, "Trash Code", 38, "2025-04-20"},
            {3, "Java: The Complete Reference", 35, "2025-04-19"},
            {4, "Effective Java", 32, "2025-04-18"},
            {5, "The Pragmatic Programmer", 30, "2025-04-17"},
            {6, "Design Patterns", 29, "2025-04-16"},
            {7, "Head First Java", 28, "2025-04-15"},
            {8, "Refactoring", 27, "2025-04-14"},
            {9, "Code Complete", 25, "2025-04-13"},
            {10, "Introduction to Algorithms", 24, "2025-04-12"}
        };


        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setForeground(DarkColor); // màu chữ
        table.setBackground(LightColor);


        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setBackground(DarkColor);
        header.setForeground(LightColor);
        header.setFont(new Font("Tahoma", Font.BOLD, 14));


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 260, 860, 250);
        mainPanel.add(scrollPane);


        add(mainPanel);
    }
}



