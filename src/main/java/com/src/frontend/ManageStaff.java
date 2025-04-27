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
    private JPanel emDash;

    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JLabel staffIdLabel;
    private JLabel staffNameLabel;
    private JLabel staffEmailLabel;
    private JLabel staffPhoneLabel;
    private JLabel staffSupervisorLabel;

    private JLabel ownerLabel;
    private JLabel dateLabel;

    private JLabel staffIdIcon;
    private JLabel staffNameIcon;
    private JLabel staffEmailIcon;
    private JLabel staffPhoneIcon;
    private JLabel staffSupervisorIcon;

    private JTextField staffIdField;
    private JTextField staffNameField;
    private JTextField staffEmailField;
    private JTextField staffPhoneField;
    private JComboBox<String> staffSupervisorComboBox;

    private JTable manageTable;
    private StaffManageTable staffManageTable;

    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220, 238, 229);

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
        int heightButton = 45;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(DarkColor);
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        backButton = new RoundedButton("Back");
        backButton.setBounds(-1, 0, 300, heightButton);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(LightColor);
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

        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 590, 60, heightButton);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(LightColor);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = staffIdField.getText().trim();
                    String fullName = staffNameField.getText().trim();
                    String email = staffEmailField.getText().trim();
                    String phoneNumber = staffPhoneField.getText().trim();
                    String supervisorUsername = staffSupervisorComboBox.getSelectedItem().toString();

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

                    staffDAO.insert(staff);

                    staffManageTable.loadStaffData(manageTable);

                    JOptionPane.showMessageDialog(null, "Staff added successfully!");

                    staffIdField.setText("");
                    staffNameField.setText("");
                    staffEmailField.setText("");
                    staffPhoneField.setText("");
                    staffSupervisorComboBox.setSelectedIndex(0);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        navigationPanel.add(addButton);

        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 590, 70, heightButton);
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(LightColor);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);

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

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        navigationPanel.add(deleteButton);

        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 590, 70, heightButton);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(LightColor);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idText = staffIdField.getText().trim();
                    String fullName = staffNameField.getText().trim();
                    String email = staffEmailField.getText().trim();
                    String phoneNumber = staffPhoneField.getText().trim();
                    String supervisorUsername = staffSupervisorComboBox.getSelectedItem().toString();

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

                    if (fullName.isEmpty() && email.isEmpty() && phoneNumber.isEmpty()) {
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

                    staffDAO.update(staff);

                    staffManageTable.loadStaffData(manageTable);

                    JOptionPane.showMessageDialog(null, "Staff updated successfully!");

                    staffIdField.setText("");
                    staffNameField.setText("");
                    staffEmailField.setText("");
                    staffPhoneField.setText("");
                    staffSupervisorComboBox.setSelectedIndex(0);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating staff: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        navigationPanel.add(updateButton);

        staffIdLabel = new JLabel("Manager ID:");
        staffIdLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffIdLabel.setForeground(Color.WHITE);
        staffIdLabel.setBounds(75, 100, 500, 15);
        navigationPanel.add(staffIdLabel);

        staffIdField = new JTextField(15);
        staffIdField.setBounds(75, 125, 200, 45);
        staffIdField.setBackground(LightColor);
        staffIdField.setForeground(DarkColor);
        staffIdField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffIdField);

        ImageIcon originalIdIcon = new ImageIcon(getClass().getResource("/com/res/ID.png"));
        Image scaledIdIcon = originalIdIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        staffIdIcon = new JLabel(new ImageIcon(scaledIdIcon));
        staffIdIcon.setBounds(15, 125, 45, 45);
        navigationPanel.add(staffIdIcon);

        staffNameLabel = new JLabel("Staff Name:");
        staffNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffNameLabel.setForeground(Color.WHITE);
        staffNameLabel.setBounds(75, 170, 500, 15);
        navigationPanel.add(staffNameLabel);

        staffNameField = new JTextField(15);
        staffNameField.setBounds(75, 195, 200, 45);
        staffNameField.setBackground(LightColor);
        staffNameField.setForeground(DarkColor);
        staffNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffNameField);

        ImageIcon originalNameIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerNameIcon.png"));
        Image scaledNameIcon = originalNameIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        staffNameIcon = new JLabel(new ImageIcon(scaledNameIcon));
        staffNameIcon.setBounds(15, 195, 45, 45);
        navigationPanel.add(staffNameIcon);

        staffEmailLabel = new JLabel("Staff Email:");
        staffEmailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffEmailLabel.setForeground(Color.WHITE);
        staffEmailLabel.setBounds(75, 240, 500, 15);
        navigationPanel.add(staffEmailLabel);

        staffEmailField = new JTextField(15);
        staffEmailField.setBounds(75, 265, 200, 45);
        staffEmailField.setBackground(LightColor);
        staffEmailField.setForeground(DarkColor);
        staffEmailField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffEmailField);

        ImageIcon originalEmailIcon = new ImageIcon(getClass().getResource("/com/res/GenderIcon.png"));
        Image scaledEmailIcon = originalEmailIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        staffEmailIcon = new JLabel(new ImageIcon(scaledEmailIcon));
        staffEmailIcon.setBounds(15, 265, 45, 45);
        navigationPanel.add(staffEmailIcon);

        staffPhoneLabel = new JLabel("Staff Phone:");
        staffPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffPhoneLabel.setForeground(Color.WHITE);
        staffPhoneLabel.setBounds(75, 310, 500, 15);
        navigationPanel.add(staffPhoneLabel);

        staffPhoneField = new JTextField(15);
        staffPhoneField.setBounds(75, 335, 200, 45);
        staffPhoneField.setBackground(LightColor);
        staffPhoneField.setForeground(DarkColor);
        staffPhoneField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffPhoneField);

        ImageIcon originalPhoneIcon = new ImageIcon(getClass().getResource("/com/res/GenderIcon.png"));
        Image scaledPhoneIcon = originalPhoneIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        staffPhoneIcon = new JLabel(new ImageIcon(scaledPhoneIcon));
        staffPhoneIcon.setBounds(15, 335, 45, 45);
        navigationPanel.add(staffPhoneIcon);

        staffSupervisorLabel = new JLabel("Supervisor:");
        staffSupervisorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        staffSupervisorLabel.setForeground(Color.WHITE);
        staffSupervisorLabel.setBounds(75, 380, 500, 15);
        navigationPanel.add(staffSupervisorLabel);

        ManagerDAO managerDAO = new ManagerDAO();
        List<String> supervisorUsernames = managerDAO.getAllUsernames();
        if (supervisorUsernames.isEmpty()) {
            supervisorUsernames.add("No Supervisors");
        }
        staffSupervisorComboBox = new JComboBox<>(supervisorUsernames.toArray(new String[0]));
        staffSupervisorComboBox.setBounds(75, 405, 200, 45);
        staffSupervisorComboBox.setBackground(LightColor);
        staffSupervisorComboBox.setForeground(DarkColor);
        staffSupervisorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        staffSupervisorComboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        navigationPanel.add(staffSupervisorComboBox);

        ImageIcon originalSupervisorIcon = new ImageIcon(getClass().getResource("/com/res/GenderIcon.png"));
        Image scaledSupervisorIcon = originalSupervisorIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        staffSupervisorIcon = new JLabel(new ImageIcon(scaledSupervisorIcon));
        staffSupervisorIcon.setBounds(15, 405, 45, 45);
        navigationPanel.add(staffSupervisorIcon);

        JLabel manageStaffLabel = new JLabel("Manage Staff");
        manageStaffLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageStaffLabel.setForeground(DarkColor);
        manageStaffLabel.setBounds(220, 0, 600, 100);
        rightPanel.add(manageStaffLabel);

        emDash = new JPanel();
        emDash.setBackground(DarkColor);
        emDash.setBounds(230, 75, 250, 5);
        rightPanel.add(emDash);

        ownerLabel = new JLabel("Owner: Admin");
        ownerLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        ownerLabel.setForeground(DarkColor);
        ownerLabel.setBounds(40, 110, 250, 30);
        rightPanel.add(ownerLabel);

        dateLabel = new JLabel("Date: " + formattedDate);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
        dateLabel.setForeground(DarkColor);
        dateLabel.setBounds(400, 110, 500, 30);
        rightPanel.add(dateLabel);

        staffTablePanel = new JPanel(new BorderLayout());
        staffTablePanel.setBackground(LightColor);
        staffTablePanel.setBounds(0, 225, 800, 415);
        rightPanel.add(staffTablePanel);

        String[] columnManageTable = { "Manager ID", "First Name", "Last Name", "Email", "Phone Number",
                "Supervisor Username" };
        DefaultTableModel modelManageTable = new DefaultTableModel(columnManageTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(LightColor);
        manageTable.setForeground(DarkColor);
        manageTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        manageTable.setRowHeight(25);
        manageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        manageTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        manageTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        manageTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        manageTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        manageTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        manageTable.getColumnModel().getColumn(5).setPreferredWidth(120);

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
                }
            }
        });

        staffManageTable.loadStaffData(manageTable);

        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(LightColor);
        headerManageTable.setFont(new Font("Tahoma", Font.BOLD, 12));

        JScrollPane scrollManageTablePanel = new JScrollPane(manageTable);
        scrollManageTablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollManageTablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        staffTablePanel.add(scrollManageTablePanel, BorderLayout.CENTER);
    }

    class RoundedButton extends JButton {
        private int radius = 10;
        private Color normalColor = new Color(47, 120, 152);
        private Color borderColor = DarkColor;
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