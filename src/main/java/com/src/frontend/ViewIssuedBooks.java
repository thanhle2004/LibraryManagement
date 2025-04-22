package com.src.frontend;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ViewIssuedBooks extends JFrame {

    private JTable table;
    private JButton backButton;
    private JPanel mainPanel;

    public ViewIssuedBooks() {
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
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Title label
        JLabel lblTitle = new JLabel("Issued Books");
        lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 40));
        lblTitle.setForeground(new Color(3, 47, 90));
        lblTitle.setBounds(320, 60, 400, 90);

        lblTitle.setIcon(new ImageIcon(getClass().getResource("/com/res/BookNameIcon.png")));

        mainPanel.add(lblTitle);

        // Table
        String[] columnNames = {"ID", "Book", "Shelf Number", "Borrower", "Due Date", "Return Date", "Status"};
        Object[][] data = {
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            {"1", "A", "2", "Duc", "16/11/2024", "1/1/2025", "pending"},
            
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setReorderingAllowed(false);
        table.setGridColor(new Color(3, 128, 128));
        table.getTableHeader().setBackground(new Color(47, 120, 152));
        table.getTableHeader().setForeground(new Color(220, 238, 229));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.setRowHeight(40);
        table.setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setBackground(new Color(220, 238, 229)); 
        table.setForeground(new Color(47, 120, 152));  
        table.setSelectionBackground(new Color(3, 47, 90));
        table.setSelectionForeground(new Color(124, 182, 192));


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120, 200, 770, 350);
        mainPanel.add(scrollPane);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 100, 40);
        backButton.setBackground(new Color(3, 47, 90));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBorderPainted(false);

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(5, 77, 120));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
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
        add(mainPanel);
    }
}
