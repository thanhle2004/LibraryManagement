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
    private JPanel NavigationPanel;
    private JPanel rightPanel;
    private JPanel borrowerTablePanel;
    private JPanel emDash;

    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JLabel borrowerID;
    private JLabel borrowerName;
    private JLabel borrowerAge;
    private JLabel borrowerGender;
    private JLabel manageBorrower;

    // Content
    private JLabel Owner;
    private JLabel Date;

    // Icon
    private JLabel borrowerIdIcon;
    private JLabel borrowerNameIcon;
    private JLabel borrowerAgeIcon;
    private JLabel borrowerGenderIcon;

    private JTextField borrowerIDField;
    private JTextField borrowerNameField;
    private JTextField borrowerBornYearField;
    private JTextField borrowerEmailField;

    private JTable manageTable;
    private BorrowerManageTable borrowerManageTable;

   

    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220, 238, 229);

    public ManageBorrowers() {
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
        borrowerManageTable = new BorrowerManageTable();
        int heightButton = 45;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        ////////// Main Panel////////
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1050, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        ////////// Navigation////////
        NavigationPanel = new JPanel();
        NavigationPanel.setBackground(DarkColor);
        NavigationPanel.setBounds(0, 0, 300, 640);
        NavigationPanel.setLayout(null);
        mainPanel.add(NavigationPanel);

        ////////// Right Panel////////
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        ////////// backButton////////
        backButton = new RoundedButton("Back");
        backButton.setBounds(-1, 0, 300, heightButton);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        NavigationPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            // Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        ///////// Add Button///////
        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 75, 60, heightButton);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(new Color(220, 238, 229));
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fullName = borrowerNameField.getText().trim();
                    String bornYearText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = "null"; 
                    String phoneNumber = "null"; 
        
                    
                    if (fullName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                
                    int bornYear;
                    try {
                        bornYear = Integer.parseInt(bornYearText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid birth year! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                   
                    int currentYear = LocalDate.now().getYear();
                    if (bornYear < 1900 || bornYear > currentYear) {
                        JOptionPane.showMessageDialog(null, "Invalid birth year! Must be between 1900 and " + currentYear, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                  
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                    // Xử lý tên: Tách thành first name và last name
                    String firstName = "";
                    String lastName = "";
                    String[] nameParts = fullName.split("\\s+");
        
                    if (nameParts.length == 1) {
                        firstName = nameParts[0];
                    } else {
                        firstName = nameParts[nameParts.length - 1];
                        lastName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                    }
        
                    // Chuẩn bị dữ liệu để thêm vào database
                    Map<String, Object> borrower = new HashMap<>();
                    borrower.put("First_name", firstName);
                    borrower.put("Last_name", lastName);
                    borrower.put("born_year", bornYear);
                    borrower.put("Email", email);
                    borrower.put("Address", address);
                    borrower.put("Phone Number", phoneNumber);
        
                    // Gọi DAO để thêm vào database
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    borrowerDAO.insert(borrower);
        
                    // Cập nhật lại bảng hiển thị dữ liệu
                    borrowerManageTable.loadBorrowerData(manageTable);
        
                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(null, "Borrower added successfully!");
        
                    // Xóa dữ liệu trong các ô nhập liệu
                    borrowerNameField.setText("");
                    borrowerBornYearField.setText("");
                    borrowerEmailField.setText("");
        
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding borrower: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        NavigationPanel.add(addButton);

        /////// Delete Button//////////
        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 75, 70, heightButton);
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
        NavigationPanel.add(deleteButton);

        ///// Update Button///////
        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 75, 70, heightButton);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(new Color(220, 238, 229));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);

        updateButton.addActionListener(new ActionListener() {
            // Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = borrowerIDField.getText().trim();
                    String fullName = borrowerNameField.getText().trim();
                    String bornYearText = borrowerBornYearField.getText().trim();
                    String email = borrowerEmailField.getText().trim();
                    String address = "null"; 
                    String phoneNumber = "null"; 
        
                    
                    if (fullName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                
                    int bornYear;
                    try {
                        bornYear = Integer.parseInt(bornYearText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid birth year! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                   
                    int currentYear = LocalDate.now().getYear();
                    if (bornYear < 1900 || bornYear > currentYear) {
                        JOptionPane.showMessageDialog(null, "Invalid birth year! Must be between 1900 and " + currentYear, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                  
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
        
                    // Xử lý tên: Tách thành first name và last name
                    String firstName = "";
                    String lastName = "";
                    String[] nameParts = fullName.split("\\s+");
        
                    if (nameParts.length == 1) {
                        firstName = nameParts[0];
                    } else {
                        firstName = nameParts[nameParts.length - 1];
                        lastName = String.join(" ", Arrays.copyOf(nameParts, nameParts.length - 1));
                    }
        
                    // Chuẩn bị dữ liệu để thêm vào database
                    Map<String, Object> borrower = new HashMap<>();
                    borrower.put("Borrower_id", id);
                    borrower.put("First_name", firstName);
                    borrower.put("Last_name", lastName);
                    borrower.put("born_year", bornYear);
                    borrower.put("Email", email);
                    borrower.put("Address", address);
                    borrower.put("Phone Number", phoneNumber);
        
                    // Gọi DAO để thêm vào database
                    BorrowerDAO borrowerDAO = new BorrowerDAO();
                    borrowerDAO.update(borrower);
        
                    // Cập nhật lại bảng hiển thị dữ liệu
                    borrowerManageTable.loadBorrowerData(manageTable);
        
                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(null, "Borrower update successfully!");
        
                    // Xóa dữ liệu trong các ô nhập liệu
                    borrowerNameField.setText("");
                    borrowerBornYearField.setText("");
                    borrowerEmailField.setText("");
        
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error update borrower: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        NavigationPanel.add(updateButton);

        ///// Borrower ID///////
        borrowerID = new JLabel("Borrower ID:");
        borrowerID.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerID.setForeground(new Color(255, 255, 255));
        borrowerID.setBounds(75, 150, 500, 15);
        NavigationPanel.add(borrowerID);

        //// Borrower ID Field/////
        borrowerIDField = new JTextField(15);
        borrowerIDField.setBounds(75, 175, 200, 45);
        borrowerIDField.setBackground(LightColor);
        borrowerIDField.setForeground(new Color(5, 77, 120));
        borrowerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        NavigationPanel.add(borrowerIDField);

        //// Borrower ID Icon///
        ImageIcon originalBorrowerIcon = new ImageIcon(getClass().getResource("/com/res/ID.png"));
        Image scaledBorrowerIcon = originalBorrowerIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerIdIcon = new JLabel(new ImageIcon(scaledBorrowerIcon));
        borrowerIdIcon.setBounds(15, 175, 45, 45);
        NavigationPanel.add(borrowerIdIcon);

        /// Borrower Name////

        borrowerName = new JLabel("Borrower Name:");
        borrowerName.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerName.setForeground(new Color(255, 255, 255));
        borrowerName.setBounds(75, 250, 500, 15);
        NavigationPanel.add(borrowerName);

        /// Borrower Name Field///
        borrowerNameField = new JTextField(15);
        borrowerNameField.setBounds(75, 275, 200, 45);
        borrowerNameField.setBackground(LightColor);
        borrowerNameField.setForeground(new Color(5, 77, 120));
        borrowerNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        NavigationPanel.add(borrowerNameField);

        /// Borrower Name Icon////
        ImageIcon originalNameIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerNameIcon.png"));
        Image scaledNameIcon = originalNameIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerNameIcon = new JLabel(new ImageIcon(scaledNameIcon));
        borrowerNameIcon.setBounds(15, 275, 45, 45);
        NavigationPanel.add(borrowerNameIcon);

        // Borrower Age//
        borrowerAge = new JLabel("Borrower Born Year:");
        borrowerAge.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerAge.setForeground(new Color(255, 255, 255));
        borrowerAge.setBounds(75, 350, 500, 15);
        NavigationPanel.add(borrowerAge);

        borrowerBornYearField = new JTextField(15);
        borrowerBornYearField.setBounds(75, 375, 200, 45);
        borrowerBornYearField.setBackground(LightColor);
        borrowerBornYearField.setForeground(new Color(5, 77, 120));
        borrowerBornYearField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        NavigationPanel.add(borrowerBornYearField);

        // Age Icon//
        ImageIcon originaAgeIcon = new ImageIcon(getClass().getResource("/com/res/AgeIcon.png"));
        Image scaledAgeIcon = originaAgeIcon.getImage().getScaledInstance(45, 55, Image.SCALE_SMOOTH);
        borrowerAgeIcon = new JLabel(new ImageIcon(scaledAgeIcon));
        borrowerAgeIcon.setBounds(15, 375, 45, 55);
        NavigationPanel.add(borrowerAgeIcon);

        // Borrower Gender//
        borrowerGender = new JLabel("Borrower Email:");
        borrowerGender.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerGender.setForeground(new Color(255, 255, 255));
        borrowerGender.setBounds(75, 450, 500, 15);
        NavigationPanel.add(borrowerGender);

        borrowerEmailField = new JTextField(15);
        borrowerEmailField.setBounds(75, 475, 200, 45);
        borrowerEmailField.setBackground(LightColor);
        borrowerEmailField.setForeground(new Color(5, 77, 120));
        borrowerEmailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)));
        NavigationPanel.add(borrowerEmailField);

        // Gender Icon//
        ImageIcon originaGenderIcon = new ImageIcon(getClass().getResource("/com/res/GenderIcon.png"));
        Image scaledGenderIcon = originaGenderIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        borrowerGenderIcon = new JLabel(new ImageIcon(scaledGenderIcon));
        borrowerGenderIcon.setBounds(15, 475, 45, 45);
        NavigationPanel.add(borrowerGenderIcon);

        // Borrower book content//
        manageBorrower = new JLabel("Manage Borrower");
        manageBorrower.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBorrower.setForeground(DarkColor);
        manageBorrower.setBounds(220, 0, 600, 100);
        rightPanel.add(manageBorrower);

        emDash = new JPanel();
        emDash.setBackground(DarkColor);
        emDash.setBounds(230, 75, 250, 5);
        rightPanel.add(emDash);

        // Owner//
        Owner = new JLabel("Owner: Admin");
        Owner.setFont(new Font("Tahoma", Font.BOLD, 27));
        Owner.setForeground(DarkColor);
        Owner.setBounds(40, 110, 250, 30);
        rightPanel.add(Owner);

        // Date//
        Date = new JLabel("Date: " + formattedDate);
        Date.setFont(new Font("Tahoma", Font.BOLD, 27));
        Date.setForeground(DarkColor);
        Date.setBounds(400, 110, 500, 30);
        rightPanel.add(Date);

        // Table
        borrowerTablePanel = new JPanel();
        borrowerTablePanel.setBackground(LightColor);
        borrowerTablePanel.setBounds(100, 225, 500, 150);
        borrowerTablePanel.setLayout(null);
        rightPanel.add(borrowerTablePanel);

        String[] columnManageTable = { "ID", "Name", "Born Year", "Email", "Address", "Phone Number" };

        Object[][] dataManageTable = {

                { "1", "Borrower 1", "18", "Male" },
                { "2", "Borrower 2", "19", "Female" },
                { "3", "Borrower 3", "20", "Male" },
                { "4", "Borrower 4", "18", "Male" },
                { "5", "Borrower 5", "19", "Female" },
                { "6", "Borrower 6", "20", "Male" },
                { "7", "Borrower 7", "18", "Male" },
                { "8", "Borrower 8", "19", "Female" },
                { "9", "Borrower 9", "20", "Male" },
                { "10", "Borrower 10", "18", "Female" },

        };

        DefaultTableModel modelManageTable = new DefaultTableModel(dataManageTable, columnManageTable);
        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(LightColor);
        manageTable.setForeground(DarkColor);
        borrowerManageTable.loadBorrowerData(manageTable);

        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setOpaque(false);
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(LightColor);

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