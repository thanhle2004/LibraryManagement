package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.src.dao.BookDAO;
import com.src.dao.BorrowerDAO;
import com.src.dao.BorrowingDAO;

public class ReturnBook extends JFrame {
    private JPanel mainPanel, leftPanel, returnPanel, rightPanel;
    private JButton backButton, checkButton, confirmButton;
    private JTextField bookIdField, borrowerIdField;
    private JTextField issueIDField, borrowerNameField, bookNameField, issueDateField, dueDateField, returnDateField,
            daysOverdueField, fineAmountField;

    public ReturnBook() {
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
        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220, 238, 229);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String returnDateFormatted = currentDate.format(returnDateFormatter);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Navigation Panel (Left)
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(5, 77, 120));
        leftPanel.setBounds(0, 0, 300, 640);
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);

        // Back Button
        backButton = new CustomButton("Back", true);
        backButton.setBounds(10, 10, 280, 45);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        leftPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });

        // Return Information Panel (Left Form)
        returnPanel = new JPanel();
        returnPanel.setBackground(new Color(5, 77, 120));
        returnPanel.setBounds(0, 60, 300, 580);
        returnPanel.setLayout(null);
        leftPanel.add(returnPanel);

        JLabel dateLabel = new JLabel("Date: " + formattedDate);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        dateLabel.setForeground(LightColor);
        dateLabel.setBounds(20, 30, 200, 25);
        returnPanel.add(dateLabel);

        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        bookIdLabel.setForeground(LightColor);
        bookIdLabel.setBounds(20, 80, 200, 25);
        returnPanel.add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(20, 120, 260, 30);
        returnPanel.add(bookIdField);

        JLabel borrowerIdLabel = new JLabel("Enter Borrower ID:");
        borrowerIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        borrowerIdLabel.setForeground(LightColor);
        borrowerIdLabel.setBounds(20, 160, 200, 25);
        returnPanel.add(borrowerIdLabel);

        borrowerIdField = new JTextField();
        borrowerIdField.setBounds(20, 200, 260, 30);
        returnPanel.add(borrowerIdField);

        checkButton = new CustomButton("Check Information", false);
        checkButton.setBounds(20, 250, 260, 40);
        checkButton.setBackground(new Color(47, 120, 152));
        checkButton.setForeground(new Color(220, 238, 229));
        checkButton.setFocusPainted(false);
        returnPanel.add(checkButton);

        confirmButton = new CustomButton("Confirm Return", false);
        confirmButton.setBounds(20, 300, 260, 40);
        confirmButton.setBackground(new Color(47, 120, 152));
        confirmButton.setForeground(new Color(220, 238, 229));
        confirmButton.setFocusPainted(false);

        // Issue Information (Right Panel)
        rightPanel = new JPanel();
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setBackground(new Color(220, 235, 220));
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        JLabel rightPanelTitle = new JLabel("Issue Details", SwingConstants.CENTER);
        rightPanelTitle.setForeground(DarkColor);
        rightPanelTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        rightPanelTitle.setBounds(0, 20, 800, 32);
        rightPanel.add(rightPanelTitle);

        JLabel issueIDLabel = new JLabel("Issue ID:");
        issueIDLabel.setForeground(DarkColor);
        issueIDLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        issueIDLabel.setBounds(20, 80, 150, 25);
        rightPanel.add(issueIDLabel);

        issueIDField = new JTextField();
        issueIDField.setBounds(250, 80, 500, 30);
        issueIDField.setEditable(false);
        rightPanel.add(issueIDField);

        JLabel borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setForeground(DarkColor);
        borrowerNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerNameLabel.setBounds(20, 140, 200, 25);
        rightPanel.add(borrowerNameLabel);

        borrowerNameField = new JTextField();
        borrowerNameField.setBounds(250, 140, 500, 30);
        borrowerNameField.setEditable(false);
        rightPanel.add(borrowerNameField);

        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setForeground(DarkColor);
        bookNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookNameLabel.setBounds(20, 200, 150, 25);
        rightPanel.add(bookNameLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(250, 200, 500, 30);
        bookNameField.setEditable(false);
        rightPanel.add(bookNameField);

        JLabel issueDateLabel = new JLabel("Issue Date:");
        issueDateLabel.setForeground(DarkColor);
        issueDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        issueDateLabel.setBounds(20, 260, 150, 25);
        rightPanel.add(issueDateLabel);

        issueDateField = new JTextField();
        issueDateField.setBounds(250, 260, 500, 30);
        issueDateField.setEditable(false);
        rightPanel.add(issueDateField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setForeground(DarkColor);
        dueDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        dueDateLabel.setBounds(20, 320, 150, 25);
        rightPanel.add(dueDateLabel);

        dueDateField = new JTextField();
        dueDateField.setBounds(250, 320, 500, 30);
        dueDateField.setEditable(false);
        rightPanel.add(dueDateField);

        JLabel returnDateLabel = new JLabel("Return Date:");
        returnDateLabel.setForeground(DarkColor);
        returnDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        returnDateLabel.setBounds(20, 380, 150, 25);
        rightPanel.add(returnDateLabel);

        returnDateField = new JTextField(returnDateFormatted);
        returnDateField.setBounds(250, 380, 500, 30);
        returnDateField.setEditable(false);
        rightPanel.add(returnDateField);

        JLabel daysOverDue = new JLabel("Days Overdue:");
        daysOverDue.setForeground(DarkColor);
        daysOverDue.setFont(new Font("Verdana", Font.BOLD, 18));
        daysOverDue.setBounds(20, 440, 150, 25);
        rightPanel.add(daysOverDue);

        daysOverdueField = new JTextField();
        daysOverdueField.setBounds(250, 440, 500, 30);
        daysOverdueField.setEditable(false);
        rightPanel.add(daysOverdueField);

        JLabel fineAmountLabel = new JLabel("Fine Amount:");
        fineAmountLabel.setForeground(DarkColor);
        fineAmountLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        fineAmountLabel.setBounds(20, 500, 150, 25);
        rightPanel.add(fineAmountLabel);

        fineAmountField = new JTextField();
        fineAmountField.setBounds(250, 500, 500, 30);
        fineAmountField.setEditable(false);
        rightPanel.add(fineAmountField);

        checkButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();

            if (bookId.isEmpty() || borrowerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Book ID and Borrower ID", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            BorrowingDAO borrowingDAO = new BorrowingDAO();
            Map<String, Object> borrowing = null;
            for (Map<String, Object> record : borrowingDAO.findAll()) {
                if (bookId.equals(record.get("ISBN")) &&
                        Integer.parseInt(borrowerId) == (Integer) record.get("Borrower_id") &&
                        (record.get("Status").equals("Borrowed") || record.get("Status").equals("Overdue"))) {
                    borrowing = record;
                    break;
                }
            }

            if (borrowing == null) {
                JOptionPane.showMessageDialog(this, "No active borrowing record found for this Book ID and Borrower ID",
                        "Record Not Found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BookDAO bookDAO = new BookDAO();
            Map<String, Object> book = bookDAO.findById(bookId);
            if (book == null) {
                JOptionPane.showMessageDialog(this, "Book ID does not exist", "Book Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            BorrowerDAO borrowerDAO = new BorrowerDAO();
            Map<String, Object> borrower = borrowerDAO.findById(borrowerId);
            if (borrower == null) {
                JOptionPane.showMessageDialog(this, "Borrower ID does not exist", "Borrower Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate dueDate = ((Date) borrowing.get("DueDate")).toLocalDate();
            LocalDate returnDate = LocalDate.now();
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            double fine = daysLate > 0 ? daysLate * 1.0 : 0.0; // 1$ per day late

            issueIDField.setText(String.valueOf(borrowing.get("Borrowing_id")));
            String firstName = (String) borrower.get("First_name");
            String lastName = (String) borrower.get("Last_name");
            String fullName = firstName + (lastName != null ? " " + lastName : "");
            borrowerNameField.setText(fullName);
            bookNameField.setText((String) book.get("Title"));
            issueDateField.setText(borrowing.get("BorrowerDate").toString());
            dueDateField.setText(borrowing.get("DueDate").toString());
            daysOverdueField.setText(String.valueOf(daysLate > 0 ? daysLate : 0));
            fineAmountField.setText(String.format("%.2f $", fine));

            returnPanel.add(confirmButton);
            returnPanel.revalidate();
            returnPanel.repaint();
        });

        confirmButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();

            BorrowingDAO borrowingDAO = new BorrowingDAO();
            Map<String, Object> borrowing = null;
            for (Map<String, Object> record : borrowingDAO.findAll()) {
                if (bookId.equals(record.get("ISBN")) &&
                        Integer.parseInt(borrowerId) == (Integer) record.get("Borrower_id") &&
                        (record.get("Status").equals("Borrowed") || record.get("Status").equals("Overdue"))) {
                    borrowing = record;
                    break;
                }
            }

            if (borrowing == null) {
                JOptionPane.showMessageDialog(this, "No active borrowing record found", "Record Not Found",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            LocalDate dueDate = ((Date) borrowing.get("DueDate")).toLocalDate();
            LocalDate returnDate = LocalDate.now();
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            double fine = daysLate > 0 ? daysLate * 1.0 : 0.0;

          
            borrowing.put("ReturnDay", Date.valueOf(returnDate));
            borrowing.put("Fine_AMOUNT", new java.math.BigDecimal(fine));
            borrowing.put("Status", "Returned");

            // BookDAO bookDAO = new BookDAO();
            // try {
            // // Update borrowing record
            // borrowingDAO.update(borrowing);

            // // Update book status to "Available"
            // Map<String, Object> book = bookDAO.findById(bookId);
            // if (book != null) {
            // book.put("Status", daysLate > 0 ? "Overdue" : "Available");
            // bookDAO.update(book);
            // }

            // JOptionPane.showMessageDialog(this, "Book returned successfully! Fine: $" +
            // String.format("%.2f", fine), "Success", JOptionPane.INFORMATION_MESSAGE);
            // clearFields();
            // returnPanel.remove(confirmButton);
            // returnPanel.revalidate();
            // returnPanel.repaint();
            // } catch (Exception ex) {
            // JOptionPane.showMessageDialog(this, "Error returning book: " +
            // ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            // }

            try {
                borrowingDAO.update(borrowing);
                JOptionPane.showMessageDialog(this, "Book returned successfully! Fine: $" + String.format("%.2f", fine),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                returnPanel.remove(confirmButton);
                returnPanel.revalidate();
                returnPanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error returning book: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearFields() {
        bookIdField.setText("");
        borrowerIdField.setText("");
        issueIDField.setText("");
        borrowerNameField.setText("");
        bookNameField.setText("");
        issueDateField.setText("");
        dueDateField.setText("");
        returnDateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        daysOverdueField.setText("");
        fineAmountField.setText("");
    }

    class CustomButton extends JButton {
        private boolean rounded;
        private int radius = 10;
        private Color normalColor = new Color(47, 120, 152);
        private Color hoverColor = new Color(0, 0, 0, 50);
        private Color borderColor = new Color(5, 77, 120);

        public CustomButton(String text, boolean rounded) {
            super(text);
            this.rounded = rounded;
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFont(new Font("Tahoma", Font.BOLD, 15));
            setBackground(normalColor);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(hoverColor);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(normalColor);
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

            g2.setColor(getBackground());

            if (rounded) {
                int arc = radius * 2;
                g2.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);
            } else {
                g2.fillRect(0, 0, width, height);
                g2.setColor(borderColor);
                g2.drawRect(0, 0, width - 1, height - 1);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }
}