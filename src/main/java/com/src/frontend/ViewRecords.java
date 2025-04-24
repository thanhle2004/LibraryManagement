package com.src.frontend;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


public class ViewRecords extends JFrame {


    private JTable table;
    private JButton backButton, searchButton;
    private JSpinner fromDate, toDate;


    public ViewRecords() {
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
        //Header
        JPanel headerPanel = new JPanel(null);
        headerPanel.setPreferredSize(new Dimension(1000, 230));
        headerPanel.setBackground(new Color(47, 120, 152));


        JLabel title = new JLabel("View Records");
        title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
        title.setForeground(new Color(220, 238, 229));
        title.setBounds(240, 10, 500, 100);
        title.setIcon(new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png")));
        headerPanel.add(title);


        //Date
        JLabel fromLabel = new JLabel("From");
        fromLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        fromLabel.setForeground(new Color(220, 238, 229));
        fromLabel.setBounds(60, 150, 150, 40);
        headerPanel.add(fromLabel);


        fromDate = new JSpinner(new SpinnerDateModel());
        fromDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
        fromDate.setEditor(new JSpinner.DateEditor(fromDate, "dd/MM/yyyy"));
        JFormattedTextField tf1 = ((JSpinner.DefaultEditor) fromDate.getEditor()).getTextField();
        tf1.setHorizontalAlignment(JTextField.CENTER);
        fromDate.setBounds(120, 150, 160, 40);
        headerPanel.add(fromDate);


        JLabel toLabel = new JLabel("To");
        toLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        toLabel.setForeground(new Color(220, 238, 229));
        toLabel.setBounds(400, 150, 150, 40);
        headerPanel.add(toLabel);


        toDate = new JSpinner(new SpinnerDateModel());
        toDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
        toDate.setEditor(new JSpinner.DateEditor(toDate, "dd/MM/yyyy"));
        JFormattedTextField tf2 = ((JSpinner.DefaultEditor) toDate.getEditor()).getTextField();
        tf2.setHorizontalAlignment(JTextField.CENTER);
        toDate.setBounds(450, 150, 160, 40);
        headerPanel.add(toDate);


        //Search button
        searchButton = new JButton("Search");
        searchButton.setBounds(800, 150, 150, 40);
        searchButton.setBackground(new Color(3, 47, 90));
        searchButton.setForeground(new Color(220, 238, 229));
        searchButton.setFocusPainted(false);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchButton.setBorderPainted(false);
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(5, 77, 120));
            }


            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(3, 47, 90));
            }
        });
        //Search on click
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Search code
                //Search days that borrowers issue books or return books
            }
        });
        headerPanel.add(searchButton);


        //Back button
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
        headerPanel.add(backButton);


        add(headerPanel, BorderLayout.NORTH);
   
        //Table
        String[] columnNames = {
            "No.", "BookID", "BorrowerID", "Issue Date", "Due Date", "Return Date", "Days Overdue", "Fine Amount"
        };


        Object[][] data = {
            {"1", "12345", "54321", "20/03/2025", "01/04/2025", "03/04/2025", "2", "$2"},
            {"2", "12347", "54721", "10/04/2025", "15/04/2025", "", "8", "$8"},
            {"3", "12346", "54621", "20/04/2025", "25/04/2025", "", "0", "0"},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
        };


        table = new JTable(data, columnNames);
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
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }


}



