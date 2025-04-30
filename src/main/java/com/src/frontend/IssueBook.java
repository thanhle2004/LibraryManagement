package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.src.auth.DatabaseAccessManager;
import com.src.dao.BookDAO;
import com.src.dao.BorrowerDAO;
import com.src.dao.BorrowingDAO;

public class IssueBook extends JFrame {
    private JPanel navigationPanel, issuePanel, bookPanel, borrowerPanel, mainPanel;
    private JButton backButton, checkButton, issueButton, renewButton;
    private JTextField bookIdField, borrowerIdField;
    private JFormattedTextField issueDateField, dueDateField, newDueDateField;
    private JTextField bookDetailIdField, bookNameField, typeField, authorField;
    private JTextField borrowerDetailIdField, borrowerNameField, phoneNumberField, addressField;
    private Date parsedIssueDate;  

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
        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220, 238, 229);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(5, 77, 120));
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);

        backButton = new CustomButton("Back", true);
        backButton.setBounds(10, 10, 280, 45);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        navigationPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });

        issuePanel = new JPanel();
        issuePanel.setBackground(new Color(5, 77, 120));
        issuePanel.setBounds(0, 60, 300, 580);
        issuePanel.setLayout(null);
        navigationPanel.add(issuePanel);

        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setForeground(LightColor);
        bookIdLabel.setBounds(20, 30, 200, 25);
        issuePanel.add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(20, 60, 260, 30);
        issuePanel.add(bookIdField);

        JLabel borrowerIdLabel = new JLabel("Enter Borrower ID:");
        borrowerIdLabel.setForeground(LightColor);
        borrowerIdLabel.setBounds(20, 100, 200, 25);
        issuePanel.add(borrowerIdLabel);

        borrowerIdField = new JTextField();
        borrowerIdField.setBounds(20, 130, 260, 30);
        issuePanel.add(borrowerIdField);

        JLabel issueDateLabel = new JLabel("Issue Date:");
        issueDateLabel.setForeground(LightColor);
        issueDateLabel.setBounds(20, 170, 200, 25);
        issuePanel.add(issueDateLabel);

        issueDateField = new JFormattedTextField();
        issueDateField.setBounds(20, 200, 260, 30);
        issuePanel.add(issueDateField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setForeground(LightColor);
        dueDateLabel.setBounds(20, 240, 200, 25);
        issuePanel.add(dueDateLabel);

        dueDateField = new JFormattedTextField();
        dueDateField.setBounds(20, 270, 260, 30);
        issuePanel.add(dueDateField);

        checkButton = new CustomButton("Check Information", false);
        checkButton.setBounds(20, 320, 260, 40);
        checkButton.setBackground(new Color(47, 120, 152));
        checkButton.setForeground(new Color(220, 238, 229));
        checkButton.setFocusPainted(false);
        issuePanel.add(checkButton);

        issueButton = new CustomButton("Issue Book", false);
        issueButton.setBounds(20, 370, 260, 40);
        issueButton.setBackground(new Color(47, 120, 152));
        issueButton.setForeground(new Color(220, 238, 229));
        issueButton.setFocusPainted(false);

        
        JLabel newDueDateLabel = new JLabel("New Due Date:");
        newDueDateLabel.setForeground(LightColor);
        newDueDateLabel.setBounds(20, 420, 200, 25);

        newDueDateField = new JFormattedTextField();
        newDueDateField.setBounds(20, 450, 260, 30);

        renewButton = new CustomButton("Renew", false);
        renewButton.setBounds(20, 490, 260, 40);
        renewButton.setBackground(new Color(47, 120, 152));
        renewButton.setForeground(new Color(220, 238, 229));
        renewButton.setFocusPainted(false);

        checkButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();
            String issueDate = issueDateField.getText().trim();
            String dueDate = dueDateField.getText().trim();

            if (bookId.isEmpty() || borrowerId.isEmpty() || issueDate.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all information", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            Date parsedDueDate = null;

            try {
                parsedIssueDate = dateFormat.parse(issueDate); 
                parsedDueDate = dateFormat.parse(dueDate);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD", "Date Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (parsedDueDate.before(parsedIssueDate)) {
                JOptionPane.showMessageDialog(this, "Due Date must be after Issue Date", "Date Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

         
            String checkBorrowingQuery = "SELECT Borrowing_id, Renewed FROM Borrowing WHERE ISBN = ? AND Borrower_id = ? AND ReturnDay IS NULL";
            boolean isBorrowing = false;
            boolean hasRenewed = false;
            int borrowingId = -1;

            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(checkBorrowingQuery)) {
                stmt.setString(1, bookId);
                stmt.setInt(2, Integer.parseInt(borrowerId));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isBorrowing = true;
                    hasRenewed = rs.getBoolean("Renewed");
                    borrowingId = rs.getInt("Borrowing_id");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error checking borrowing status: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        
            if (isBorrowing) {
                if (!hasRenewed) {
                  
                    int response = JOptionPane.showConfirmDialog(this,
                            "This borrower is currently borrowing this book. Do you want to renew it?",
                            "Renewal Option", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        issuePanel.add(newDueDateLabel);
                        issuePanel.add(newDueDateField);
                        issuePanel.add(renewButton);
                        issuePanel.revalidate();
                        issuePanel.repaint();

                        final int finalBorrowingId = borrowingId;
                        renewButton.addActionListener(renewEvent -> {
                            String newDueDateText = newDueDateField.getText().trim();
                            if (newDueDateText.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Please enter the new due date", "Input Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            Date parsedNewDueDate;
                            try {
                                parsedNewDueDate = dateFormat.parse(newDueDateText);
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(this,
                                        "Invalid date format for new due date. Please use YYYY-MM-DD", "Date Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            if (parsedNewDueDate.before(parsedIssueDate)) {
                                JOptionPane.showMessageDialog(this, "New Due Date must be after Issue Date",
                                        "Date Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            String updateQuery = "UPDATE Borrowing SET DueDate = ?, Renewed = true WHERE Borrowing_id = ?";
                            try (Connection conn = DatabaseAccessManager.getConnection();
                                    PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                                stmt.setDate(1, new java.sql.Date(parsedNewDueDate.getTime()));
                                stmt.setInt(2, finalBorrowingId);
                                int rowsAffected = stmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(this, "Book borrowing renewed successfully!",
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                                    clearFields();
                                    issuePanel.remove(newDueDateLabel);
                                    issuePanel.remove(newDueDateField);
                                    issuePanel.remove(renewButton);
                                    issuePanel.revalidate();
                                    issuePanel.repaint();
                                } else {
                                    JOptionPane.showMessageDialog(this, "Failed to renew borrowing", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Error renewing book: " + ex.getMessage(),
                                        "Database Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(this, "Book is not available for borrowing", "Book Status Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    return; 
                } else {
                    JOptionPane.showMessageDialog(this, "This book has already been renewed by the borrower",
                            "Renewal Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            BookDAO bookDAO = new BookDAO();
            Map<String, Object> book = bookDAO.findById(bookId);

            if (book == null) {
                JOptionPane.showMessageDialog(this, "Book ID does not exist", "Book Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String bookStatus = (String) book.get("Status");
            if (!bookStatus.equals("Available")) {
                JOptionPane.showMessageDialog(this, "Book is not available for borrowing", "Book Status Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "SELECT b.ISBN, b.Title, CONCAT(a.First_name, ' ', a.Last_name) AS Author, g.MainGenre_name AS MainGenre "
                    +
                    "FROM book b " +
                    "INNER JOIN Author a ON b.Author_id = a.Author_id " +
                    "INNER JOIN Genre g ON b.MainGenre_id = g.MainGenre_id " +
                    "WHERE b.ISBN = ?";

            try (Connection conn = DatabaseAccessManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, bookId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    bookDetailIdField.setText(rs.getString("ISBN"));
                    bookNameField.setText(rs.getString("Title"));
                    typeField.setText(rs.getString("MainGenre"));
                    authorField.setText(rs.getString("Author"));
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error fetching book details: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            BorrowerDAO borrowerDAO = new BorrowerDAO();
            Map<String, Object> borrower = borrowerDAO.findById(borrowerId);

            if (borrower == null) {
                JOptionPane.showMessageDialog(this, "Borrower ID does not exist", "Borrower Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            borrowerDetailIdField.setText(String.valueOf(borrower.get("Borrower_id")));
            borrowerNameField.setText(borrower.get("First_name") + " " + borrower.get("Last_name"));
            phoneNumberField.setText((String) borrower.get("Phone_number"));
            addressField.setText((String) borrower.get("Address"));

            issuePanel.add(issueButton);
            issuePanel.revalidate();
            issuePanel.repaint();
        });

        issueButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();
            String issueDate = issueDateField.getText().trim();
            String dueDate = dueDateField.getText().trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            java.sql.Date sqlIssueDate = null;
            java.sql.Date sqlDueDate = null;
            try {
                sqlIssueDate = new java.sql.Date(dateFormat.parse(issueDate).getTime());
                sqlDueDate = new java.sql.Date(dateFormat.parse(dueDate).getTime());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD", "Date Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date currentDate = new Date();
            String borrowingStatus = sqlDueDate.before(currentDate) ? "Overdue" : "Borrowed";

            Random random = new Random();
            int borrowingId = random.nextInt(1000000);

            Map<String, Object> borrowing = new HashMap<>();
            borrowing.put("Borrowing_id", borrowingId);
            borrowing.put("ISBN", bookId);
            borrowing.put("Borrower_id", Integer.parseInt(borrowerId));
            borrowing.put("BorrowerDate", sqlIssueDate);
            borrowing.put("DueDate", sqlDueDate);
            borrowing.put("ReturnDay", null);
            borrowing.put("Fine_AMOUNT", java.math.BigDecimal.ZERO);
            borrowing.put("Status", borrowingStatus);
            borrowing.put("Renewed", false);

            BorrowingDAO borrowingDAO = new BorrowingDAO();
            BookDAO bookDAO = new BookDAO();

            try {
                borrowingDAO.insert(borrowing);
                Map<String, Object> book = bookDAO.findById(bookId);
                if (book != null) {
                    book.put("Status", borrowingStatus);
                    bookDAO.update(book);
                }

                JOptionPane.showMessageDialog(this, "Book issued successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                issuePanel.remove(issueButton);
                issuePanel.revalidate();
                issuePanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error issuing book: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        bookPanel = createPanel(new Color(220, 238, 229), 310, 0, 400, 640);
        bookPanel.setLayout(null);
        mainPanel.add(bookPanel);

        ImageIcon bookIcon = new ImageIcon(getClass().getResource("/com/res/IconBook.png"));
        Image scaledBookImage = bookIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        bookIcon = new ImageIcon(scaledBookImage);

        JLabel bookIconLabel = new JLabel(bookIcon);
        bookIconLabel.setBounds(40, 7, 64, 64);
        bookPanel.add(bookIconLabel);

        JLabel bookTitle = new JLabel("Book Details", SwingConstants.LEFT);
        bookTitle.setForeground(DarkColor);
        bookTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        bookTitle.setBounds(110, 20, 300, 32);
        bookPanel.add(bookTitle);

        JLabel bookIdDetailLabel = new JLabel("Book ID:");
        bookIdDetailLabel.setForeground(DarkColor);
        bookIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookIdDetailLabel.setBounds(20, 80, 150, 25);
        bookPanel.add(bookIdDetailLabel);

        bookDetailIdField = new JTextField();
        bookDetailIdField.setBounds(20, 110, 360, 30);
        bookDetailIdField.setEditable(false);
        bookPanel.add(bookDetailIdField);

        JLabel bookNameDetailLabel = new JLabel("Book Name:");
        bookNameDetailLabel.setForeground(DarkColor);
        bookNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookNameDetailLabel.setBounds(20, 160, 150, 25);
        bookPanel.add(bookNameDetailLabel);

        bookNameField = new JTextField();
        bookNameField.setBounds(20, 190, 360, 30);
        bookNameField.setEditable(false);
        bookPanel.add(bookNameField);

        JLabel bookTypeLabel = new JLabel("Type:");
        bookTypeLabel.setForeground(DarkColor);
        bookTypeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookTypeLabel.setBounds(20, 240, 150, 25);
        bookPanel.add(bookTypeLabel);

        typeField = new JTextField();
        typeField.setBounds(20, 270, 360, 30);
        typeField.setEditable(false);
        bookPanel.add(typeField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setForeground(DarkColor);
        authorLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        authorLabel.setBounds(20, 320, 150, 25);
        bookPanel.add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(20, 350, 360, 30);
        authorField.setEditable(false);
        bookPanel.add(authorField);

        borrowerPanel = createPanel(new Color(220, 238, 229), 720, 0, 380, 640);
        borrowerPanel.setLayout(null);
        mainPanel.add(borrowerPanel);

        ImageIcon borrowerIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerDetail.png"));
        Image scaledBorrowerImage = borrowerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        borrowerIcon = new ImageIcon(scaledBorrowerImage);

        JLabel borrowerIconLabel = new JLabel(borrowerIcon);
        borrowerIconLabel.setBounds(20, 10, 64, 64);
        borrowerPanel.add(borrowerIconLabel);

        JLabel borrowerTitle = new JLabel("Borrower Details", SwingConstants.LEFT);
        borrowerTitle.setForeground(DarkColor);
        borrowerTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        borrowerTitle.setBounds(90, 20, 300, 32);
        borrowerPanel.add(borrowerTitle);

        JLabel borrowerIdDetailLabel = new JLabel("Borrower ID:");
        borrowerIdDetailLabel.setForeground(DarkColor);
        borrowerIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerIdDetailLabel.setBounds(20, 80, 150, 25);
        borrowerPanel.add(borrowerIdDetailLabel);

        borrowerDetailIdField = new JTextField();
        borrowerDetailIdField.setBounds(20, 110, 340, 30);
        borrowerDetailIdField.setEditable(false);
        borrowerPanel.add(borrowerDetailIdField);

        JLabel borrowerNameDetailLabel = new JLabel("Borrower Name:");
        borrowerNameDetailLabel.setForeground(DarkColor);
        borrowerNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerNameDetailLabel.setBounds(20, 160, 200, 25);
        borrowerPanel.add(borrowerNameDetailLabel);

        borrowerNameField = new JTextField();
        borrowerNameField.setBounds(20, 190, 340, 30);
        borrowerNameField.setEditable(false);
        borrowerPanel.add(borrowerNameField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setForeground(DarkColor);
        phoneNumberLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        phoneNumberLabel.setBounds(20, 240, 340, 25);
        borrowerPanel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(20, 270, 340, 30);
        phoneNumberField.setEditable(false);
        borrowerPanel.add(phoneNumberField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(DarkColor);
        addressLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        addressLabel.setBounds(20, 320, 150, 25);
        borrowerPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(20, 350, 340, 30);
        addressField.setEditable(false);
        borrowerPanel.add(addressField);
    }

    private void clearFields() {
        bookIdField.setText("");
        borrowerIdField.setText("");
        issueDateField.setText("");
        dueDateField.setText("");
        bookDetailIdField.setText("");
        bookNameField.setText("");
        typeField.setText("");
        authorField.setText("");
        borrowerDetailIdField.setText("");
        borrowerNameField.setText("");
        phoneNumberField.setText("");
        addressField.setText("");
        parsedIssueDate = null; 
    }

    private JPanel createPanel(Color color, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        return panel;
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