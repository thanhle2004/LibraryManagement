package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.src.auth.DatabaseAccessManager;

public class ViewStatistics extends JFrame {

    private JButton backButton;
    private JPanel mainPanel;
    private JLabel[] statValues;

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
        Color LightColor = new Color(220, 238, 229);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(47, 120, 152));

        JLabel lblTitle = new JLabel("Statistics");
        lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 40));
        lblTitle.setForeground(LightColor);
        lblTitle.setBounds(380, 30, 400, 60);
        mainPanel.add(lblTitle);

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
        statValues = new JLabel[titles.length];

        for (int i = 0; i < titles.length; i++) {
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

            statValues[i] = new JLabel("");
            statValues[i].setFont(new Font("Tahoma", Font.BOLD, 16));
            statValues[i].setForeground(DarkColor);
            statValues[i].setBounds(0, 30, 200, 70);
            statValues[i].setHorizontalAlignment(SwingConstants.CENTER);
            statCard.add(statValues[i]);

            mainPanel.add(statCard);
        }

        loadStatistics();

        String[] columnNames = { "Rank", "Book Title", "Times Borrowed", "Last Borrowed" };
        Object[][] data = loadMostBorrowedBooks();

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setForeground(DarkColor);
        table.setBackground(LightColor);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JTableHeader header = table.getTableHeader();
        header.setBackground(DarkColor);
        header.setForeground(LightColor);
        header.setFont(new Font("Tahoma", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 260, 860, 250);
        mainPanel.add(scrollPane);

        add(mainPanel);
    }

    private void loadStatistics() {
        try {
            String booksBorrowedQuery = "SELECT COUNT(Borrowing_id) AS TotalBorrowed FROM Borrowing";
            int totalBorrowed = 0;
            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(booksBorrowedQuery)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    totalBorrowed = rs.getInt("TotalBorrowed");
                }
            }
            statValues[0].setText(String.valueOf(totalBorrowed));

            String lateReturnsQuery = "SELECT COUNT(*) AS LateReturns FROM Borrowing WHERE " +
                    "(ReturnDay IS NOT NULL AND ReturnDay > DueDate) OR " +
                    "(ReturnDay IS NULL AND DueDate < ?)";
            int lateReturns = 0;
            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(lateReturnsQuery)) {
                stmt.setDate(1, new java.sql.Date(new Date().getTime()));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    lateReturns = rs.getInt("LateReturns");
                }
            }
            statValues[1].setText(String.valueOf(lateReturns));

            String finesQuery = "SELECT SUM(Fine_AMOUNT) AS TotalFines FROM Borrowing WHERE Fine_AMOUNT IS NOT NULL";
            double totalFines = 0.0;
            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(finesQuery)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    totalFines = rs.getDouble("TotalFines");
                }
            }
            statValues[2].setText(String.format("$%.2f", totalFines));

            String mostActiveQuery = "SELECT Borrower_id, COUNT(*) AS BorrowCount FROM Borrowing " +
                    "GROUP BY Borrower_id ORDER BY BorrowCount DESC LIMIT 1";
            String mostActiveMember = "Unknown";
            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(mostActiveQuery)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int borrowerId = rs.getInt("Borrower_id");
                    String nameQuery = "SELECT First_name, Last_name FROM Borrower WHERE Borrower_id = ?";
                    try (PreparedStatement nameStmt = conn.prepareStatement(nameQuery)) {
                        nameStmt.setInt(1, borrowerId);
                        ResultSet nameRs = nameStmt.executeQuery();
                        if (nameRs.next()) {
                            mostActiveMember = nameRs.getString("First_name") + " " + nameRs.getString("Last_name");
                        }
                    }
                }
            }
            statValues[3].setText(mostActiveMember);

        } catch (Exception e) {
            e.printStackTrace();

            statValues[0].setText("0");
            statValues[1].setText("0");
            statValues[2].setText("$0.00");
            statValues[3].setText("Unknown");
        }
    }

    private Object[][] loadMostBorrowedBooks() {
        List<Object[]> bookList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (Connection conn = DatabaseAccessManager.getConnection()) {
            String query = "SELECT ISBN, COUNT(*) AS BorrowCount, MAX(BorrowerDate) AS LastBorrowed " +
                    "FROM Borrowing GROUP BY ISBN ORDER BY BorrowCount DESC LIMIT 10";
            Map<String, String> bookTitles = new HashMap<>();

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String isbn = rs.getString("ISBN");
                    int borrowCount = rs.getInt("BorrowCount");
                    Date lastBorrowed = rs.getDate("LastBorrowed");

                    String titleQuery = "SELECT Title FROM Book WHERE ISBN = ?";
                    String title = "Unknown";
                    try (PreparedStatement titleStmt = conn.prepareStatement(titleQuery)) {
                        titleStmt.setString(1, isbn);
                        ResultSet titleRs = titleStmt.executeQuery();
                        if (titleRs.next()) {
                            title = titleRs.getString("Title");
                        }
                    }

                    bookList.add(new Object[] {
                            0,
                            title,
                            borrowCount,
                            lastBorrowed != null ? dateFormat.format(lastBorrowed) : "N/A"
                    });
                }
            }

            for (int i = 0; i < bookList.size(); i++) {
                bookList.get(i)[0] = i + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[bookList.size()][4];
        for (int i = 0; i < bookList.size(); i++) {
            data[i] = bookList.get(i);
        }

        if (bookList.isEmpty()) {
            return new Object[][] {
                    { 1, "No Data", 0, "N/A" }
            };
        }

        return data;
    }
}