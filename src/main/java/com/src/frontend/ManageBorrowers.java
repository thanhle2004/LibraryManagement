package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.src.dao.BorrowerDAO;
import com.src.dao.BorrowingDAO;
import com.src.view.manageBorrower.BorrowerManageTable;

public class ManageBorrowers extends JFrame {

    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel rightPanel;
    private JPanel borrowerTablePanel;
    private JPanel separatorLine;

    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton clearButton;

    private JLabel borrowerIDLabel;
    private JLabel borrowerNameLabel;
    private JLabel borrowerBornYearLabel;
    private JLabel borrowerEmailLabel;
    private JLabel borrowerAddressLabel;
    private JLabel borrowerPhoneLabel;
    private JLabel manageBorrowerTitle;

    private JLabel ownerLabel;
    private JLabel dateLabel;

    private JTextField borrowerIDField;
    private JTextField borrowerNameField;
    private JTextField borrowerBornYearField;
    private JTextField borrowerEmailField;
    private JTextField borrowerAddressField;
    private JTextField borrowerPhoneField;
    private JTextField searchField;
    private JTable manageTable;
    private BorrowerManageTable borrowerManageTable;

    Color darkColor = new Color(5, 77, 120);
    Color lightColor = new Color(220, 238, 229);

    public ManageBorrowers() {
        setPreferredSize(new Dimension(1100, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        pack();

        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        borrowerManageTable = new BorrowerManageTable();
        int buttonHeight = 45;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(darkColor);
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        backButton = new RoundedButton("Back");
        backButton.setBounds(-1, 0, 300, buttonHeight);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        navigationPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        borrowerIDLabel = new JLabel("Borrower ID:");
        borrowerIDLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerIDLabel.setForeground(new Color(255, 255, 255));
        borrowerIDLabel.setBounds(15, 75, 500, 15);
        navigationPanel.add(borrowerIDLabel);

        borrowerIDField = new JTextField(15);
        borrowerIDField.setBounds(15, 100, 260, 30);
        borrowerIDField.setBackground(lightColor);
        borrowerIDField.setForeground(new Color(5, 77, 120));
        borrowerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerIDField);

        borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerNameLabel.setForeground(new Color(255, 255, 255));
        borrowerNameLabel.setBounds(15, 155, 500, 15);
        navigationPanel.add(borrowerNameLabel);

        borrowerNameField = new JTextField(15);
        borrowerNameField.setBounds(15, 175, 260, 30);
        borrowerNameField.setBackground(lightColor);
        borrowerNameField.setForeground(new Color(5, 77, 120));
        borrowerNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerNameField);

        borrowerBornYearLabel = new JLabel("Birthday:");
        borrowerBornYearLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerBornYearLabel.setForeground(new Color(255, 255, 255));
        borrowerBornYearLabel.setBounds(15, 225, 500, 15);
        navigationPanel.add(borrowerBornYearLabel);

        borrowerBornYearField = new JTextField(15);
        borrowerBornYearField.setBounds(15, 250, 260, 30);
        borrowerBornYearField.setBackground(lightColor);
        borrowerBornYearField.setForeground(new Color(5, 77, 120));
        borrowerBornYearField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerBornYearField);

        borrowerEmailLabel = new JLabel("Borrower Email:");
        borrowerEmailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerEmailLabel.setForeground(new Color(255, 255, 255));
        borrowerEmailLabel.setBounds(15, 300, 500, 15);
        navigationPanel.add(borrowerEmailLabel);

        borrowerEmailField = new JTextField(15);
        borrowerEmailField.setBounds(15, 325, 260, 30);
        borrowerEmailField.setBackground(lightColor);
        borrowerEmailField.setForeground(new Color(5, 77, 120));
        borrowerEmailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerEmailField);

        borrowerAddressLabel = new JLabel("Borrower Address:");
        borrowerAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerAddressLabel.setForeground(new Color(255, 255, 255));
        borrowerAddressLabel.setBounds(15, 375, 500, 15);
        navigationPanel.add(borrowerAddressLabel);

        borrowerAddressField = new JTextField(15);
        borrowerAddressField.setBounds(15, 400, 260, 30);
        borrowerAddressField.setBackground(lightColor);
        borrowerAddressField.setForeground(new Color(5, 77, 120));
        borrowerAddressField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerAddressField);

        borrowerPhoneLabel = new JLabel("Phone Number:");
        borrowerPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerPhoneLabel.setForeground(new Color(255, 255, 255));
        borrowerPhoneLabel.setBounds(15, 450, 500, 15);
        navigationPanel.add(borrowerPhoneLabel);

        borrowerPhoneField = new JTextField(15);
        borrowerPhoneField.setBounds(15, 475, 260, 30);
        borrowerPhoneField.setBackground(lightColor);
        borrowerPhoneField.setForeground(new Color(5, 77, 120));
        borrowerPhoneField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerPhoneField);

        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 525, 60, buttonHeight);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(new Color(220, 238, 229));
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String borrowerId = borrowerIDField.getText().trim();
                    String fullName = borrowerNameField.getText().trim();
                    String birthdayText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = borrowerAddressField.getText().trim();
                    String phoneNumber = borrowerPhoneField.getText().trim();

                    if (borrowerId.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Borrower ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    if (borrowerDAO.getById(borrowerId) != null) {
                        throw new IllegalArgumentException("Borrrower with ISBN " + borrowerId + " already exists.");
                    }
                    if (fullName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!birthdayText.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                        JOptionPane.showMessageDialog(null, "Invalid birthday format! Use YYYY-MM-DD.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LocalDate birthday;
                    try {
                        birthday = LocalDate.parse(birthdayText);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid birthday! Please enter a valid date.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LocalDate currentDate = LocalDate.now();
                    if (birthday.isBefore(LocalDate.of(1900, 1, 1)) || birthday.isAfter(currentDate)) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid birthday! It must be between 1900-01-01 and " + currentDate, "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!phoneNumber.isEmpty() && !phoneNumber.matches("^\\d{10}$")) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number! It must be 10 digits.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Address cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (phoneNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Phone number cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String firstName = "";
                    String lastName = "";
                    String[] nameParts = fullName.split("\\s+");

                    if (nameParts.length == 1) {
                        firstName = nameParts[0];
                    } else {
                        firstName = nameParts[nameParts.length - 1];
                        lastName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                    }

                    Map<String, Object> borrower = new HashMap<>();
                    borrower.put("Borrower_id", borrowerId);
                    borrower.put("First_name", firstName);
                    borrower.put("Last_name", lastName);
                    borrower.put("birthday", birthdayText);
                    borrower.put("Email", email);
                    borrower.put("Address", address.isEmpty() ? null : address);
                    borrower.put("Phone_number", phoneNumber.isEmpty() ? null : phoneNumber);
                    borrowerDAO.insert(borrower);

                    borrowerManageTable.loadBorrowerData(manageTable);

                    JOptionPane.showMessageDialog(null, "Borrower added successfully!");

                    borrowerNameField.setText("");
                    borrowerBornYearField.setText("");
                    borrowerEmailField.setText("");
                    borrowerAddressField.setText("");
                    borrowerPhoneField.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding borrower: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        navigationPanel.add(addButton);

        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 525, 70, buttonHeight);
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(new Color(220, 238, 229));
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = borrowerIDField.getText().trim();
                    BorrowingDAO borrowingDAO = new BorrowingDAO();

                    if (borrowingDAO.isBorrowerBorrowedinRecord(id)) {
                        throw new IllegalArgumentException(
                                "This borrower is currently in record. Cannot delete.");
                    }
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    borrowerDAO.delete(id);
                    borrowerManageTable.loadBorrowerData(manageTable);
                    JOptionPane.showMessageDialog(null, "Borrower deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting borrower: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        navigationPanel.add(deleteButton);

        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 525, 70, buttonHeight);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(new Color(220, 238, 229));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = borrowerIDField.getText().trim();
                    String fullName = borrowerNameField.getText().trim();
                    String birthdayText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = borrowerAddressField.getText().trim();
                    String phoneNumber = borrowerPhoneField.getText().trim();

                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Borrower ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (fullName.isEmpty() && birthdayText.isEmpty() && email.isEmpty() && address.isEmpty()
                            && phoneNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nothing to update!", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    Map<String, Object> existingBorrower = borrowerDAO.getById(id);
                    if (existingBorrower == null) {
                        throw new IllegalArgumentException("Borrower with ID " + id + " does not exist.");
                    }

                    String firstName = (String) existingBorrower.get("First_name");
                    String lastName = (String) existingBorrower.get("Last_name");
                    if (!fullName.isEmpty()) {
                        String[] nameParts = fullName.split("\\s+");
                        if (nameParts.length == 1) {
                            firstName = nameParts[0];
                            lastName = "";
                        } else {
                            firstName = nameParts[nameParts.length - 1];
                            lastName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                        }
                    }

                    String updatedBirthday = existingBorrower.get("birthday") != null
                            ? existingBorrower.get("birthday").toString()
                            : null;
                    if (!birthdayText.isEmpty()) {
                        if (!birthdayText.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                            JOptionPane.showMessageDialog(null, "Invalid birthday format! Use YYYY-MM-DD.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        LocalDate birthday;
                        try {
                            birthday = LocalDate.parse(birthdayText);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid birthday! Please enter a valid date.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        LocalDate currentDate = LocalDate.now();
                        if (birthday.isBefore(LocalDate.of(1900, 1, 1)) || birthday.isAfter(currentDate)) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid birthday! It must be between 1900-01-01 and " + currentDate, "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        updatedBirthday = birthdayText;
                    }

                    String updatedEmail = (String) existingBorrower.get("Email");
                    if (!email.isEmpty()) {
                        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                            JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        updatedEmail = email;
                    }

                    String updatedAddress = existingBorrower.get("Address") != null
                            ? existingBorrower.get("Address").toString()
                            : null;
                    if (!address.isEmpty()) {
                        updatedAddress = address;
                    }

                    String updatedPhoneNumber = existingBorrower.get("Phone_number") != null
                            ? existingBorrower.get("Phone_number").toString()
                            : null;
                    if (!phoneNumber.isEmpty()) {
                        if (!phoneNumber.matches("^\\d{10}$")) {
                            JOptionPane.showMessageDialog(null, "Invalid phone number! It must be 10 digits.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        updatedPhoneNumber = phoneNumber;
                    }

                    Map<String, Object> borrower = new HashMap<>();
                    borrower.put("Borrower_id", id);
                    borrower.put("First_name", firstName);
                    borrower.put("Last_name", lastName);
                    borrower.put("birthday", updatedBirthday);
                    borrower.put("Email", updatedEmail);
                    borrower.put("Address", updatedAddress);
                    borrower.put("Phone_number", updatedPhoneNumber);

                    borrowerDAO.update(borrower);

                    borrowerManageTable.loadBorrowerData(manageTable);

                    JOptionPane.showMessageDialog(null, "Borrower updated successfully!");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating borrower: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        navigationPanel.add(updateButton);

        manageBorrowerTitle = new JLabel("Manage Borrowers");
        manageBorrowerTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBorrowerTitle.setForeground(darkColor);
        manageBorrowerTitle.setBounds(220, 0, 600, 100);
        rightPanel.add(manageBorrowerTitle);

        separatorLine = new JPanel();
        separatorLine.setBackground(darkColor);
        separatorLine.setBounds(230, 75, 250, 5);
        rightPanel.add(separatorLine);

        ownerLabel = new JLabel("Owner: Admin");
        ownerLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        ownerLabel.setForeground(darkColor);
        ownerLabel.setBounds(40, 110, 250, 30);
        rightPanel.add(ownerLabel);

        dateLabel = new JLabel("Date: " + formattedDate);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        dateLabel.setForeground(darkColor);
        dateLabel.setBounds(400, 110, 500, 30);
        rightPanel.add(dateLabel);

        searchField = new JTextField(15);
        searchField.setBounds(40, 160, 500, 30);
        searchField.setBackground(lightColor);
        searchField.setForeground(new Color(5, 77, 120));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        rightPanel.add(searchField);

        searchButton = new RoundedButton("Search");
        searchButton.setBounds(550, 160, 80, 30);
        searchButton.setBackground(new Color(47, 120, 152));
        searchButton.setForeground(new Color(220, 238, 229));
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(null);
        rightPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().trim();
                try {
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    List<Map<String, Object>> borrowers;
                    if (searchTerm.isEmpty()) {
                        borrowerManageTable.loadBorrowerData(manageTable);
                    } else {
                        borrowers = borrowerDAO.searchBorrowers(searchTerm);
                        borrowerManageTable.loadSearchResults(manageTable, borrowers);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error searching borrowers: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton = new RoundedButton("Clear");
        clearButton.setBounds(645, 160, 60, 30);
        clearButton.setBackground(new Color(47, 120, 152));
        clearButton.setForeground(new Color(220, 238, 229));
        clearButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        clearButton.setFocusPainted(false);
        clearButton.setBorder(null);
        rightPanel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                try {
                    borrowerManageTable.loadBorrowerData(manageTable);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error clearing search: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        borrowerTablePanel = new JPanel();
        borrowerTablePanel.setBackground(lightColor);
        borrowerTablePanel.setBounds(0, 250, 800, 150);
        borrowerTablePanel.setLayout(null);
        rightPanel.add(borrowerTablePanel);

        String[] columnManageTable = { "ID", "Name", "Birthday", "Address", "Email", "Phone Number" };

        DefaultTableModel modelManageTable = new DefaultTableModel(columnManageTable, 0);
        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(lightColor);
        manageTable.setForeground(darkColor);
        borrowerManageTable.loadBorrowerData(manageTable);

        manageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        manageTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        manageTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        manageTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        manageTable.getColumnModel().getColumn(3).setPreferredWidth(230);
        manageTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        manageTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        manageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = manageTable.getSelectedRow();
                if (selectedRow != -1) {

                    String id = manageTable.getValueAt(selectedRow, 0).toString();
                    String name = manageTable.getValueAt(selectedRow, 1).toString();
                    String birthday = manageTable.getValueAt(selectedRow, 2).toString();
                    String address = manageTable.getValueAt(selectedRow, 3) != null
                            ? manageTable.getValueAt(selectedRow, 3).toString()
                            : "";
                    String email = manageTable.getValueAt(selectedRow, 4).toString();
                    String phoneNumber = manageTable.getValueAt(selectedRow, 5) != null
                            ? manageTable.getValueAt(selectedRow, 5).toString()
                            : "";

                    borrowerIDField.setText(id);
                    borrowerNameField.setText(name);
                    borrowerBornYearField.setText(birthday);
                    borrowerEmailField.setText(email);
                    borrowerAddressField.setText(address);
                    borrowerPhoneField.setText(phoneNumber);
                }
            }
        });

        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setOpaque(false);
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(lightColor);

        JScrollPane scrollManageTablePanel = new JScrollPane(manageTable);
        scrollManageTablePanel.setBounds(0, 0, 800, 150);
        scrollManageTablePanel.getViewport().setOpaque(false);
        scrollManageTablePanel.setOpaque(false);
        borrowerTablePanel.add(scrollManageTablePanel);
    }

    class RoundedButton extends JButton {
        private int radius = 5;
        private Color normalColor = new Color(47, 120, 152);
        private Color borderColor = new Color(5, 77, 120);
        private Color hoverColor = new Color(80, 140, 180);
        private Color pressedColor = new Color(30, 90, 120);
        private boolean isPressed = false;

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!isPressed) {
                        setBackground(hoverColor);
                    }
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!isPressed) {
                        setBackground(normalColor);
                    }
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    isPressed = true;
                    setBackground(pressedColor);
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isPressed = false;
                    if (getMousePosition() != null) {
                        setBackground(hoverColor);
                    } else {
                        setBackground(normalColor);
                    }
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
            int arc = radius * 2;

            Color currentColor = getBackground();
            g2.setColor(currentColor);
            g2.fillRoundRect(1, 1, width - 3, height - 2, arc, arc);
            g2.setColor(borderColor);
            g2.drawRoundRect(1, 1, width - 3, height - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}