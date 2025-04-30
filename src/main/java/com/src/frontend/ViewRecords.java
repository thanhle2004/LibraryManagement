package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.src.dao.BorrowingDAO;
import com.src.view.viewRecord.BorrowingManageTable;

public class ViewRecords extends JFrame {

    private JTable table;
    private JButton backButton, searchButton;
    private JSpinner fromDate, toDate;
    private BorrowingManageTable borrowingManageTable;

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

        JPanel headerPanel = new JPanel(null);
        headerPanel.setPreferredSize(new Dimension(1000, 230));
        headerPanel.setBackground(new Color(47, 120, 152));

        JLabel title = new JLabel("View Records");
        title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 40));
        title.setForeground(new Color(220, 238, 229));
        title.setBounds(240, 10, 500, 100);
        title.setIcon(new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png")));
        headerPanel.add(title);

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRecords();
            }
        });
        headerPanel.add(searchButton);

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

        String[] columnNames = {
                "Borrowing ID", "Book ID", "Borrower ID", "Issue Date", "Due Date", "Return Date", "Days Overdue", "Fine Amount"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        borrowingManageTable = new BorrowingManageTable();
        borrowingManageTable.loadBorrowingData(table);
    }

    private void searchRecords() {
        Date from = (Date) fromDate.getValue();
        Date to = (Date) toDate.getValue();

        if (from.after(to)) {
            JOptionPane.showMessageDialog(this, "The 'From' date must be before the 'To' date.", "Date Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        BorrowingDAO borrowingDAO = new BorrowingDAO();
        try {
            List<Map<String, Object>> borrowings = borrowingDAO.findAll();

            borrowings.removeIf(borrowing -> {
                Date issueDate = (Date) borrowing.get("BorrowerDate");
                Date returnDate = (Date) borrowing.get("ReturnDay");
                boolean matchesIssueDate = issueDate != null && !issueDate.before(from) && !issueDate.after(to);
                boolean matchesReturnDate = returnDate != null && !returnDate.before(from) && !returnDate.after(to);
                return !(matchesIssueDate || matchesReturnDate);
            });

            borrowingManageTable.loadSearchResults(table, borrowings);

            if (borrowings.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No records found for the specified date range.", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}