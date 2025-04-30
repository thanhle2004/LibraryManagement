package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.src.dao.ManagerDAO;
import com.src.dao.StaffDAO;
import com.src.view.manageStaff.StaffManageTable;

public class ManageStaff extends JFrame {

    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel rightPanel;
    private JPanel staffTablePanel;
    private JPanel separatorLine;

    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton clearButton;

    private JLabel staffIdLabel;
    private JLabel staffNameLabel;
    private JLabel staffEmailLabel;
    private JLabel staffPhoneLabel;
    private JLabel staffSupervisorLabel;
    private JLabel staffBirthdayLabel;
    private JLabel staffAddressLabel;
    private JLabel manageStaffTitle;

    private JLabel ownerLabel;
    private JLabel dateLabel;

    private JLabel staffIdIcon;
    private JLabel staffNameIcon;
    private JLabel staffEmailIcon;
    private JLabel staffPhoneIcon;
    private JLabel staffSupervisorIcon;
    private JLabel staffBirthdayIcon;
    private JLabel staffAddressIcon;

    private JTextField staffIdField;
    private JTextField staffNameField;
    private JTextField staffEmailField;
    private JTextField staffPhoneField;
    private JTextField staffBirthdayField;
    private JTextField staffAddressField;
    private JTextField searchField;
    private JComboBox<String> staffSupervisorComboBox;

    private JTable manageTable;
    private StaffManageTable staffManageTable;

    Color darkColor = new Color(5, 77, 120);
    Color lightColor = new Color(220, 238, 229);

    public ManageStaff() {
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
        staffManageTable = new StaffManageTable();
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
    
        staffIdLabel = new JLabel("Manager ID:");
        staffIdLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffIdLabel.setForeground(new Color(255, 255, 255));
        staffIdLabel.setBounds(75, 50, 500, 15); 
        navigationPanel.add(staffIdLabel);
    
        staffIdField = new JTextField(15);
        staffIdField.setBounds(75, 75, 200, 30); 
        staffIdField.setBackground(lightColor);
        staffIdField.setForeground(new Color(5, 77, 120));
        staffIdField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffIdField);
    
        ImageIcon originalIdIcon = new ImageIcon(getClass().getResource("/com/res/ID.png"));
        Image scaledIdIcon = originalIdIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        staffIdIcon = new JLabel(new ImageIcon(scaledIdIcon));
        staffIdIcon.setBounds(15, 75, 30, 30); 
        navigationPanel.add(staffIdIcon);
    
        staffNameLabel = new JLabel("Staff Name:");
        staffNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffNameLabel.setForeground(new Color(255, 255, 255));
        staffNameLabel.setBounds(75, 130, 500, 15); 
        navigationPanel.add(staffNameLabel);
    
        staffNameField = new JTextField(15);
        staffNameField.setBounds(75, 150, 200, 30); 
        staffNameField.setBackground(lightColor);
        staffNameField.setForeground(new Color(5, 77, 120));
        staffNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffNameField);
    
        ImageIcon originalNameIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerNameIcon.png"));
        Image scaledNameIcon = originalNameIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        staffNameIcon = new JLabel(new ImageIcon(scaledNameIcon));
        staffNameIcon.setBounds(15, 150, 30, 30); 
        navigationPanel.add(staffNameIcon);
    
        staffEmailLabel = new JLabel("Staff Email:");
        staffEmailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffEmailLabel.setForeground(new Color(255, 255, 255));
        staffEmailLabel.setBounds(75, 200, 500, 15); 
        navigationPanel.add(staffEmailLabel);
    
        staffEmailField = new JTextField(15);
        staffEmailField.setBounds(75, 225, 200, 30); 
        staffEmailField.setBackground(lightColor);
        staffEmailField.setForeground(new Color(5, 77, 120));
        staffEmailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffEmailField);
    
        ImageIcon originalEmailIcon = new ImageIcon(getClass().getResource("/com/res/EmailIcon.jpg"));
        Image scaledEmailIcon = originalEmailIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        staffEmailIcon = new JLabel(new ImageIcon(scaledEmailIcon));
        staffEmailIcon.setBounds(15, 225, 30, 30); 
        navigationPanel.add(staffEmailIcon);
    
        staffPhoneLabel = new JLabel("Staff Phone:");
        staffPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffPhoneLabel.setForeground(new Color(255, 255, 255));
        staffPhoneLabel.setBounds(75, 275, 500, 15); 
        navigationPanel.add(staffPhoneLabel);
    
        staffPhoneField = new JTextField(15);
        staffPhoneField.setBounds(75, 300, 200, 30); 
        staffPhoneField.setBackground(lightColor);
        staffPhoneField.setForeground(new Color(5, 77, 120));
        staffPhoneField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffPhoneField);
    
        ImageIcon originalPhoneIcon = new ImageIcon(getClass().getResource("/com/res/PhoneIcon.png"));
        Image scaledPhoneIcon = originalPhoneIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        staffPhoneIcon = new JLabel(new ImageIcon(scaledPhoneIcon));
        staffPhoneIcon.setBounds(15, 300, 30, 30); 
        navigationPanel.add(staffPhoneIcon);
    
        staffSupervisorLabel = new JLabel("Supervisor:");
        staffSupervisorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffSupervisorLabel.setForeground(new Color(255, 255, 255));
        staffSupervisorLabel.setBounds(75, 350, 500, 15); 
        navigationPanel.add(staffSupervisorLabel);
    
        ManagerDAO managerDAO = new ManagerDAO();
        List<String> supervisorUsernames = managerDAO.getAllUsernames();
        if (supervisorUsernames.isEmpty()) {
            supervisorUsernames.add("No Supervisors");
        }
        staffSupervisorComboBox = new JComboBox<>(supervisorUsernames.toArray(new String[0]));
        staffSupervisorComboBox.setBounds(75, 375, 200, 30);
        staffSupervisorComboBox.setBackground(lightColor);
        staffSupervisorComboBox.setForeground(darkColor);
        staffSupervisorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        staffSupervisorComboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(darkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffSupervisorComboBox);
    
        ImageIcon originalSupervisorIcon = new ImageIcon(getClass().getResource("/com/res/AddressIcon.jpg"));
        Image scaledSupervisorIcon = originalSupervisorIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        staffSupervisorIcon = new JLabel(new ImageIcon(scaledSupervisorIcon));
        staffSupervisorIcon.setBounds(15, 375, 30, 30); 
        navigationPanel.add(staffSupervisorIcon);
    
       
        staffBirthdayLabel = new JLabel("Birthday:");
        staffBirthdayLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffBirthdayLabel.setForeground(new Color(255, 255, 255));
        staffBirthdayLabel.setBounds(75, 425, 500, 15); 
        navigationPanel.add(staffBirthdayLabel);
    
        staffBirthdayField = new JTextField(15);
        staffBirthdayField.setBounds(75, 450, 200, 30); 
        staffBirthdayField.setBackground(lightColor);
        staffBirthdayField.setForeground(new Color(5, 77, 120));
        staffBirthdayField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffBirthdayField);
    
      
        staffAddressLabel = new JLabel("Address:");
        staffAddressLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffAddressLabel.setForeground(new Color(255, 255, 255));
        staffAddressLabel.setBounds(75, 500, 500, 15); 
        navigationPanel.add(staffAddressLabel);
    
        staffAddressField = new JTextField(15);
        staffAddressField.setBounds(75, 525, 200, 30); 
        staffAddressField.setBackground(lightColor);
        staffAddressField.setForeground(new Color(5, 77, 120));
        staffAddressField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffAddressField);
    
        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 559, 60, buttonHeight); 
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(new Color(220, 238, 229));
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);
        navigationPanel.add(addButton);
    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = staffIdField.getText().trim();
                    String fullName = staffNameField.getText().trim();
                    String email = staffEmailField.getText().trim();
                    String phoneNumber = staffPhoneField.getText().trim();
                    String supervisorUsername = staffSupervisorComboBox.getSelectedItem().toString();
                    String birthday = staffBirthdayField.getText().trim();
                    String address = staffAddressField.getText().trim();
    
                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Manager ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    int managerId;
                    try {
                        managerId = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Manager ID! Please enter a valid number.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    StaffDAO staffDAO = new StaffDAO();
                    if (staffDAO.getById(managerId) != null) {
                        JOptionPane.showMessageDialog(null, "Staff with ID " + managerId + " already exists!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    if (fullName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    if (phoneNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Phone number cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    // Validate birthday format (expected: YYYY-MM-DD)
                    if (!birthday.isEmpty() && !birthday.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                        JOptionPane.showMessageDialog(null, "Invalid birthday format! Use YYYY-MM-DD.", "Error",
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
    
                    Map<String, Object> staff = new HashMap<>();
                    staff.put("Manager_id", managerId);
                    staff.put("First_name", firstName);
                    staff.put("Last_name", lastName);
                    staff.put("Email", email);
                    staff.put("Phone_number", phoneNumber);
                    staff.put("supervisor_username", supervisorUsername);
                    staff.put("birthday", birthday.isEmpty() ? null : birthday);
                    staff.put("address", address.isEmpty() ? null : address);
    
                    staffDAO.insert(staff);
    
                    staffManageTable.loadStaffData(manageTable);
    
                    JOptionPane.showMessageDialog(null, "Staff added successfully!");
    
                    staffIdField.setText("");
                    staffNameField.setText("");
                    staffEmailField.setText("");
                    staffPhoneField.setText("");
                    staffSupervisorComboBox.setSelectedIndex(0);
                    staffBirthdayField.setText("");
                    staffAddressField.setText("");
    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 559, 70, buttonHeight); 
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(new Color(220, 238, 229));
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);
        navigationPanel.add(deleteButton);
    
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = staffIdField.getText().trim();
                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Manager ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    int managerId;
                    try {
                        managerId = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Manager ID! Please enter a valid number.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    StaffDAO staffDAO = new StaffDAO();
                    if (staffDAO.getById(managerId) == null) {
                        JOptionPane.showMessageDialog(null, "Staff with ID " + managerId + " does not exist!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    staffDAO.delete(managerId);
                    staffManageTable.loadStaffData(manageTable);
                    JOptionPane.showMessageDialog(null, "Staff deleted successfully!");
    
                    staffIdField.setText("");
                    staffNameField.setText("");
                    staffEmailField.setText("");
                    staffPhoneField.setText("");
                    staffSupervisorComboBox.setSelectedIndex(0);
                    staffBirthdayField.setText("");
                    staffAddressField.setText("");
    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 559, 70, buttonHeight); 
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(new Color(220, 238, 229));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        navigationPanel.add(updateButton);
    
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = staffIdField.getText().trim();
                    String fullName = staffNameField.getText().trim();
                    String email = staffEmailField.getText().trim();
                    String phoneNumber = staffPhoneField.getText().trim();
                    String supervisorUsername = staffSupervisorComboBox.getSelectedItem().toString();
                    String birthday = staffBirthdayField.getText().trim();
                    String address = staffAddressField.getText().trim();
    
                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Manager ID cannot be empty!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    int managerId;
                    try {
                        managerId = Integer.parseInt(idText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Manager ID! Please enter a valid number.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    StaffDAO staffDAO = new StaffDAO();
                    Map<String, Object> existingStaff = staffDAO.getById(managerId);
                    if (existingStaff == null) {
                        JOptionPane.showMessageDialog(null, "Staff with ID " + managerId + " does not exist!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    if (fullName.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && birthday.isEmpty()
                            && address.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nothing to update!", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
    
                    String finalEmail = existingStaff.get("Email").toString();
                    if (!email.isEmpty()) {
                        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                            JOptionPane.showMessageDialog(null, "Invalid email format!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        finalEmail = email;
                    }
    
                    String finalPhoneNumber = existingStaff.get("Phone_number") != null
                            ? existingStaff.get("Phone_number").toString()
                            : "";
                    if (!phoneNumber.isEmpty()) {
                        finalPhoneNumber = phoneNumber;
                    }
    
                    String finalBirthday = existingStaff.get("birthday") != null
                            ? existingStaff.get("birthday").toString()
                            : "";
                    if (!birthday.isEmpty()) {
                        if (!birthday.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                            JOptionPane.showMessageDialog(null, "Invalid birthday format! Use YYYY-MM-DD.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        finalBirthday = birthday;
                    }
    
                    String finalAddress = existingStaff.get("address") != null
                            ? existingStaff.get("address").toString()
                            : "";
                    if (!address.isEmpty()) {
                        finalAddress = address;
                    }
    
                    String firstName = existingStaff.get("First_name").toString();
                    String lastName = existingStaff.get("Last_name") != null ? existingStaff.get("Last_name").toString()
                            : "";
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
    
                    Map<String, Object> staff = new HashMap<>();
                    staff.put("Manager_id", managerId);
                    staff.put("First_name", firstName);
                    staff.put("Last_name", lastName);
                    staff.put("Email", finalEmail);
                    staff.put("Phone_number", finalPhoneNumber);
                    staff.put("supervisor_username", supervisorUsername);
                    staff.put("birthday", finalBirthday.isEmpty() ? null : finalBirthday);
                    staff.put("address", finalAddress.isEmpty() ? null : finalAddress);
    
                    staffDAO.update(staff);
    
                    staffManageTable.loadStaffData(manageTable);
    
                    JOptionPane.showMessageDialog(null, "Staff updated successfully!");
    
                    staffIdField.setText("");
                    staffNameField.setText("");
                    staffEmailField.setText("");
                    staffPhoneField.setText("");
                    staffSupervisorComboBox.setSelectedIndex(0);
                    staffBirthdayField.setText("");
                    staffAddressField.setText("");
    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        manageStaffTitle = new JLabel("Manage Staff");
        manageStaffTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageStaffTitle.setForeground(darkColor);
        manageStaffTitle.setBounds(220, 0, 600, 100);
        rightPanel.add(manageStaffTitle);
    
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
                    StaffDAO staffDAO = new StaffDAO();
                    if (searchTerm.isEmpty()) {
                        staffManageTable.loadStaffData(manageTable);
                    } else {
                        staffManageTable.loadSearchResults(manageTable, staffDAO.searchStaff(searchTerm));
                    }
                    if (manageTable.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "No staff found.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error searching: " + ex.getMessage(), "Error",
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
                    staffManageTable.loadStaffData(manageTable);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error clearing search: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        staffTablePanel = new JPanel();
        staffTablePanel.setBackground(lightColor);
        staffTablePanel.setBounds(0, 250, 800, 350);
        staffTablePanel.setLayout(null);
        rightPanel.add(staffTablePanel);
    
        String[] columnManageTable = { "Manager ID", "First Name", "Last Name", "Email", "Phone Number",
                "Supervisor Username", "Birthday", "Address" };
        DefaultTableModel modelManageTable = new DefaultTableModel(columnManageTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(lightColor);
        manageTable.setForeground(darkColor);
        manageTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        manageTable.setRowHeight(25);
        manageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
        manageTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        manageTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        manageTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        manageTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        manageTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        manageTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        manageTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        manageTable.getColumnModel().getColumn(7).setPreferredWidth(100);
    
        manageTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setVerticalAlignment(JLabel.TOP);
                    label.setToolTipText(value != null ? value.toString() : null);
                }
                return c;
            }
        });
    
        manageTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = manageTable.getSelectedRow();
                if (selectedRow != -1) {
                    staffIdField.setText(manageTable.getValueAt(selectedRow, 0).toString());
                    String firstName = manageTable.getValueAt(selectedRow, 1).toString();
                    String lastName = manageTable.getValueAt(selectedRow, 2) != null
                            ? manageTable.getValueAt(selectedRow, 2).toString()
                            : "";
                    staffNameField.setText(lastName.isEmpty() ? firstName : lastName + " " + firstName);
                    staffEmailField.setText(manageTable.getValueAt(selectedRow, 3) != null
                            ? manageTable.getValueAt(selectedRow, 3).toString()
                            : "");
                    staffPhoneField.setText(manageTable.getValueAt(selectedRow, 4) != null
                            ? manageTable.getValueAt(selectedRow, 4).toString()
                            : "");
                    String supervisor = manageTable.getValueAt(selectedRow, 5) != null
                            ? manageTable.getValueAt(selectedRow, 5).toString()
                            : "";
                    staffSupervisorComboBox.setSelectedItem(supervisor);
                    staffBirthdayField.setText(manageTable.getValueAt(selectedRow, 6) != null
                            ? manageTable.getValueAt(selectedRow, 6).toString()
                            : "");
                    staffAddressField.setText(manageTable.getValueAt(selectedRow, 7) != null
                            ? manageTable.getValueAt(selectedRow, 7).toString()
                            : "");
                }
            }
        });
    
        JScrollPane scrollManageTablePanel = new JScrollPane(manageTable);
        scrollManageTablePanel.setBounds(0, 0, 800, 350);
        scrollManageTablePanel.getViewport().setOpaque(false);
        scrollManageTablePanel.setOpaque(false);
        staffTablePanel.add(scrollManageTablePanel);
    
        staffManageTable.loadStaffData(manageTable);
    
        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setOpaque(false);
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(lightColor);
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