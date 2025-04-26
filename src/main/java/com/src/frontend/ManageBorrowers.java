package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

    private JLabel borrowerIDLabel;
    private JLabel borrowerNameLabel;
    private JLabel borrowerBornYearLabel;
    private JLabel borrowerEmailLabel;
    private JLabel borrowerAddressLabel;
    private JLabel borrowerPhoneLabel;
    private JLabel manageBorrowerTitle;

    // Content
    private JLabel ownerLabel;
    private JLabel dateLabel;

    // Icons
    private JLabel borrowerIDIcon;
    private JLabel borrowerNameIcon;
    private JLabel borrowerBornYearIcon;
    private JLabel borrowerEmailIcon;
    private JLabel borrowerAddressIcon;
    private JLabel borrowerPhoneIcon;

    private JTextField borrowerIDField;
    private JTextField borrowerNameField;
    private JTextField borrowerBornYearField;
    private JTextField borrowerEmailField;
    private JTextField borrowerAddressField;
    private JTextField borrowerPhoneField;

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

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Navigation Panel
        navigationPanel = new JPanel();
        navigationPanel.setBackground(darkColor);
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);

        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        // Back Button
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

        // Borrower ID Label
        borrowerIDLabel = new JLabel("Borrower ID:");
        borrowerIDLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerIDLabel.setForeground(new Color(255, 255, 255));
        borrowerIDLabel.setBounds(75, 75, 500, 15);
        navigationPanel.add(borrowerIDLabel);

        // Borrower ID Field
        borrowerIDField = new JTextField(15);
        borrowerIDField.setBounds(75, 100, 200, 45);
        borrowerIDField.setBackground(lightColor);
        borrowerIDField.setForeground(new Color(5, 77, 120));
        borrowerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerIDField);

        // Borrower ID Icon
        ImageIcon originalBorrowerIcon = new ImageIcon(getClass().getResource("/com/res/ID.png"));
        Image scaledBorrowerIcon = originalBorrowerIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerIDIcon = new JLabel(new ImageIcon(scaledBorrowerIcon));
        borrowerIDIcon.setBounds(15, 100, 45, 45);
        navigationPanel.add(borrowerIDIcon);

        // Borrower Name Label
        borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerNameLabel.setForeground(new Color(255, 255, 255));
        borrowerNameLabel.setBounds(75, 155, 500, 15);
        navigationPanel.add(borrowerNameLabel);

        // Borrower Name Field
        borrowerNameField = new JTextField(15);
        borrowerNameField.setBounds(75, 180, 200, 45);
        borrowerNameField.setBackground(lightColor);
        borrowerNameField.setForeground(new Color(5, 77, 120));
        borrowerNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerNameField);

        // Borrower Name Icon
        ImageIcon originalNameIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerNameIcon.png"));
        Image scaledNameIcon = originalNameIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerNameIcon = new JLabel(new ImageIcon(scaledNameIcon));
        borrowerNameIcon.setBounds(15, 180, 45, 45);
        navigationPanel.add(borrowerNameIcon);

        // Borrower Born Year Label
        borrowerBornYearLabel = new JLabel("Borrower Born Year:");
        borrowerBornYearLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerBornYearLabel.setForeground(new Color(255, 255, 255));
        borrowerBornYearLabel.setBounds(75, 235, 500, 15);
        navigationPanel.add(borrowerBornYearLabel);

        // Borrower Born Year Field
        borrowerBornYearField = new JTextField(15);
        borrowerBornYearField.setBounds(75, 260, 200, 45);
        borrowerBornYearField.setBackground(lightColor);
        borrowerBornYearField.setForeground(new Color(5, 77, 120));
        borrowerBornYearField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerBornYearField);

        // Born Year Icon
        ImageIcon originalAgeIcon = new ImageIcon(getClass().getResource("/com/res/AgeIcon.png"));
        Image scaledAgeIcon = originalAgeIcon.getImage().getScaledInstance(45, 55, Image.SCALE_SMOOTH);
        borrowerBornYearIcon = new JLabel(new ImageIcon(scaledAgeIcon));
        borrowerBornYearIcon.setBounds(15, 260, 45, 55);
        navigationPanel.add(borrowerBornYearIcon);

        // Borrower Email Label
        borrowerEmailLabel = new JLabel("Borrower Email:");
        borrowerEmailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerEmailLabel.setForeground(new Color(255, 255, 255));
        borrowerEmailLabel.setBounds(75, 315, 500, 15);
        navigationPanel.add(borrowerEmailLabel);

        // Borrower Email Field
        borrowerEmailField = new JTextField(15);
        borrowerEmailField.setBounds(75, 340, 200, 45);
        borrowerEmailField.setBackground(lightColor);
        borrowerEmailField.setForeground(new Color(5, 77, 120));
        borrowerEmailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerEmailField);

        // Email Icon
        ImageIcon originalEmailIcon = new ImageIcon(getClass().getResource("/com/res/EmailIcon.jpg"));
        Image scaledEmailIcon = originalEmailIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerEmailIcon = new JLabel(new ImageIcon(scaledEmailIcon));
        borrowerEmailIcon.setBounds(15, 340, 45, 45);
        navigationPanel.add(borrowerEmailIcon);

        // Borrower Address Label
        borrowerAddressLabel = new JLabel("Borrower Address:");
        borrowerAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerAddressLabel.setForeground(new Color(255, 255, 255));
        borrowerAddressLabel.setBounds(75, 395, 500, 15);
        navigationPanel.add(borrowerAddressLabel);

        // Borrower Address Field
        borrowerAddressField = new JTextField(15);
        borrowerAddressField.setBounds(75, 420, 200, 45);
        borrowerAddressField.setBackground(lightColor);
        borrowerAddressField.setForeground(new Color(5, 77, 120));
        borrowerAddressField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerAddressField);

        // Address Icon
        ImageIcon originalAddressIcon = new ImageIcon(getClass().getResource("/com/res/AddressIcon.png"));
        Image scaledAddressIcon = originalAddressIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerAddressIcon = new JLabel(new ImageIcon(scaledAddressIcon));
        borrowerAddressIcon.setBounds(15, 420, 45, 45);
        navigationPanel.add(borrowerAddressIcon);

        // Borrower Phone Number Label
        borrowerPhoneLabel = new JLabel("Borrower Phone:");
        borrowerPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerPhoneLabel.setForeground(new Color(255, 255, 255));
        borrowerPhoneLabel.setBounds(75, 475, 500, 15);
        navigationPanel.add(borrowerPhoneLabel);

        // Borrower Phone Number Field
        borrowerPhoneField = new JTextField(15);
        borrowerPhoneField.setBounds(75, 500, 200, 45);
        borrowerPhoneField.setBackground(lightColor);
        borrowerPhoneField.setForeground(new Color(5, 77, 120));
        borrowerPhoneField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(borrowerPhoneField);

        // Phone Icon
        ImageIcon originalPhoneIcon = new ImageIcon(getClass().getResource("/com/res/PhoneIcon.png"));
        Image scaledPhoneIcon = originalPhoneIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerPhoneIcon = new JLabel(new ImageIcon(scaledPhoneIcon));
        borrowerPhoneIcon.setBounds(15, 500, 45, 45);
        navigationPanel.add(borrowerPhoneIcon);

        // Add Button
        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 555, 60, buttonHeight);
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
                    String bornYearText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = borrowerAddressField.getText().trim();
                    String phoneNumber = borrowerPhoneField.getText().trim();

                    if (borrowerId.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Borrower ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (fullName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (bornYearText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Born year cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int bornYear;
                    try {
                        bornYear = Integer.parseInt(bornYearText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid birth year! Please enter a valid year (e.g., 1990).", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int currentYear = LocalDate.now().getYear();
                    if (bornYear < 1900 || bornYear > currentYear) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid birth year! Must be between 1900 and " + currentYear, "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!phoneNumber.isEmpty() && !phoneNumber.matches("^\\d{10}$")) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number! Must be 10 digits.", "Error",
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
                    borrower.put("born_year", bornYear);
                    borrower.put("Email", email);
                    borrower.put("Address", address.isEmpty() ? null : address);
                    borrower.put("Phone_number", phoneNumber.isEmpty() ? null : phoneNumber);

                    BorrowerDAO borrowerDAO = new BorrowerDAO();
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

        // Delete Button
        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 555, 70, buttonHeight);
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
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    borrowerDAO.delete(id);
                    borrowerManageTable.loadBorrowerData(manageTable);
                    JOptionPane.showMessageDialog(null, "Borrower deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting borrower: " + ex.getMessage());
                }
            }
        });
        navigationPanel.add(deleteButton);

        // Update Button
        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 555, 70, buttonHeight);
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
                    String bornYearText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = borrowerAddressField.getText().trim();
                    String phoneNumber = borrowerPhoneField.getText().trim();

                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Borrower ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (fullName.isEmpty() && bornYearText.isEmpty() && email.isEmpty() && address.isEmpty()
                            && phoneNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nothing to update!", "Info",
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

                    int bornYear = (Integer) existingBorrower.get("born_year");
                    if (!bornYearText.isEmpty()) {
                        try {
                            bornYear = Integer.parseInt(bornYearText);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid birth year! Please enter a valid year (e.g., 1990).", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int currentYear = LocalDate.now().getYear();
                        if (bornYear < 1900 || bornYear > currentYear) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid birth year! Must be between 1900 and " + currentYear, "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
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

                    String updatedAddress = (String) existingBorrower.get("Address");
                    if (!address.isEmpty()) {
                        updatedAddress = address;
                    }

                    String updatedPhoneNumber = (String) existingBorrower.get("Phone Number");
                    if (!phoneNumber.isEmpty()) {
                        if (!phoneNumber.matches("^\\d{10}$")) {
                            JOptionPane.showMessageDialog(null, "Invalid phone number! Must be 10 digits.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        updatedPhoneNumber = phoneNumber;
                    }

                    Map<String, Object> borrower = new HashMap<>();
                    borrower.put("Borrower_id", id);
                    borrower.put("First_name", firstName);
                    borrower.put("Last_name", lastName);
                    borrower.put("born_year", bornYear);
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

        // Manage Borrower Title
        manageBorrowerTitle = new JLabel("Manage Borrower");
        manageBorrowerTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBorrowerTitle.setForeground(darkColor);
        manageBorrowerTitle.setBounds(220, 0, 600, 100);
        rightPanel.add(manageBorrowerTitle);

        // Separator Line
        separatorLine = new JPanel();
        separatorLine.setBackground(darkColor);
        separatorLine.setBounds(230, 75, 250, 5);
        rightPanel.add(separatorLine);

        // Owner Label
        ownerLabel = new JLabel("Owner: Admin");
        ownerLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        ownerLabel.setForeground(darkColor);
        ownerLabel.setBounds(40, 110, 250, 30);
        rightPanel.add(ownerLabel);

        // Date Label
        dateLabel = new JLabel("Date: " + formattedDate);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        dateLabel.setForeground(darkColor);
        dateLabel.setBounds(400, 110, 500, 30);
        rightPanel.add(dateLabel);

        // Borrower Table Panel
        borrowerTablePanel = new JPanel();
        borrowerTablePanel.setBackground(lightColor);
        borrowerTablePanel.setBounds(0, 225, 800, 150);
        borrowerTablePanel.setLayout(null);
        rightPanel.add(borrowerTablePanel);

        String[] columnManageTable = { "ID", "Name", "Born Year", "Address", "Email", "Phone Number" };

        Object[][] dataManageTable = {
                { "1", "Borrower 1", 1990, "123 Street", "borrower1@example.com", "1234567890" },
                { "2", "Borrower 2", 1991, "456 Avenue", "borrower2@example.com", "0987654321" },
                { "3", "Borrower 3", 1992, "789 Boulevard", "borrower3@example.com", "1234509876" },
                { "4", "Borrower 4", 1993, "321 Road", "borrower4@example.com", "6543210987" },
                { "5", "Borrower 5", 1994, "654 Lane", "borrower5@example.com", "7894561230" },
                { "6", "Borrower 6", 1995, "987 Drive", "borrower6@example.com", "3216549870" },
                { "7", "Borrower 7", 1996, "147 Circle", "borrower7@example.com", "4567891230" },
                { "8", "Borrower 8", 1997, "258 Square", "borrower8@example.com", "9876543210" },
                { "9", "Borrower 9", 1998, "369 Place", "borrower9@example.com", "1237894560" },
                { "10", "Borrower 10", 1999, "741 Court", "borrower10@example.com", "6549871230" },
        };

        DefaultTableModel modelManageTable = new DefaultTableModel(dataManageTable, columnManageTable);
        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(lightColor);
        manageTable.setForeground(darkColor);
        borrowerManageTable.loadBorrowerData(manageTable);

        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setOpaque(false);
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(lightColor);

        JScrollPane scrollManageTablePanel = new JScrollPane(manageTable);
        scrollManageTablePanel.setBounds(0, 0, 500, 150);
        scrollManageTablePanel.getViewport().setOpaque(false);
        scrollManageTablePanel.setOpaque(false);
        borrowerTablePanel.add(scrollManageTablePanel);
    }

    class RoundedButton extends JButton {
        private int radius = 10;
        private Color normalColor = new Color(47, 120, 152);
        private Color borderColor = new Color(5, 77, 120);
        private Color hoverColor = new Color(0, 0, 0, 50);

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);

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
            int arc = radius * 5;

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