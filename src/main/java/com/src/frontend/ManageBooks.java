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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.src.dao.BookDAO;
import com.src.dao.ShelfDAO;
import com.src.dao.AuthorDAO;
import com.src.view.manageBooks.BookManageTable;
import com.src.view.manageBooks.AuthorManageTable;
import com.src.view.manageBooks.ShelfManageTable;

public class ManageBooks extends JFrame {

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bookManageTablePanel;
    private JPanel authorManageTablePanel;
    private JPanel shelfmanageTablePanel;
    private JPanel emDash;

    // Icons and Labels
    private JLabel iconID;
    private JLabel iconBook;
    private JLabel iconAuthor;
    private JLabel iconCategory;
    private JLabel bookID;
    private JLabel bookName;
    private JLabel authorName;
    private JLabel Category;
    private JLabel searchIcon;

    private JLabel authorID;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel birthDateLabel;
    private JLabel nationalityLabel;

    // Content
    private JLabel manageBook;

    // Buttons
    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton authorButton;
    private JButton bookButton;
    private JButton shelfButton;

    // Fields
    private JTextField searchField;
    private JTextField bookIDField;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField mainGenreID;
    private JTextField authorIDField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField birthDateField;
    private JTextField nationalityField;

    private JLabel shelfNumberLabel;
    private JTextField shelfNumberField;
    private JLabel mainGenreLabel;
    private JTextField mainGenreField;
    private JLabel managerIDLabel;
    private JTextField managerIDField;
    private JTextField shelfIDField;
    private JLabel shelfIDLabel;

    private JTable bookTable;
    private JTable authorTable;
    private JTable shelfTable;
    private BookManageTable bookManageTable;
    private AuthorManageTable authorManageTable;
    private ShelfManageTable shelfManageTable;
    private String currentMode = "Book";

    private int tableY = 250;

    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220, 238, 229);

    public ManageBooks() {
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
        bookManageTable = new BookManageTable();
        authorManageTable = new AuthorManageTable();
        shelfManageTable = new ShelfManageTable();

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1000, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setBackground(DarkColor);
        leftPanel.setBounds(0, 0, 300, 640);
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);

        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(300, 0, 700, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(-1, 0, 150, 30);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(LightColor);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        leftPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        // Search Field and Button
        searchField = new JTextField();
        searchField.setBounds(50, 175, 550, 35);
        searchField.setBackground(LightColor);
        searchField.setForeground(DarkColor);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor, 3),
                new EmptyBorder(5, 10, 5, 10)));
        rightPanel.add(searchField);

        ImageIcon search = new ImageIcon(getClass().getResource("/com/res/SearchIcon.png"));
        Image scaledSearchIcon = search.getImage().getScaledInstance(45, 35, Image.SCALE_SMOOTH);
        searchIcon = new JLabel(new ImageIcon(scaledSearchIcon));
        searchIcon.setBounds(0, 175, 45, 35);
        rightPanel.add(searchIcon);

        searchButton = new JButton("Search");
        searchButton.setBounds(610, 175, 60, 35);
        searchButton.setBackground(LightColor);
        searchButton.setForeground(DarkColor);
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new LineBorder(DarkColor, 3));
        rightPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = searchField.getText().trim();
                    if (currentMode.equals("Book")) {
                        if (searchText.isEmpty()) {
                            bookManageTable.loadBookData(bookTable);
                        } else {
                            BookDAO bookDAO = new BookDAO();
                            bookManageTable.loadSearchResults(bookTable, bookDAO.searchBooks(searchText));
                        }
                        if (bookTable.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No books found.");
                        }
                    } else if (currentMode.equals("Author")) {
                        if (searchText.isEmpty()) {
                            authorManageTable.loadAuthorData(authorTable);
                        } else {
                            AuthorDAO authorDAO = new AuthorDAO();
                            authorManageTable.loadSearchResults(authorTable, authorDAO.searchAuthors(searchText));
                        }
                        if (authorTable.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No authors found.");
                        }
                    } 
                    // else if (currentMode.equals("Shelves")) {
                    //     if (searchText.isEmpty()) {
                    //         shelfManageTable.loadShelfData(shelfTable);
                    //     } else {
                    //         ShelfDAO shelfDAO = new ShelfDAO();
                    //         shelfManageTable.loadSearchResults(shelfTable, shelfDAO.searchShelves(searchText));
                    //     }
                    //     if (shelfTable.getRowCount() == 0) {
                    //         JOptionPane.showMessageDialog(null, "No shelves found.");
                    //     }
                    // }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error searching: " + ex.getMessage());
                }
            }
        });

        // Content Panel
        manageBook = new JLabel("Manage Books");
        manageBook.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBook.setForeground(DarkColor);
        manageBook.setBounds(250, 0, 600, 100);
        rightPanel.add(manageBook);

        emDash = new JPanel();
        emDash.setBackground(DarkColor);
        emDash.setBounds(255, 75, 200, 5);
        rightPanel.add(emDash);

        // Mode Buttons
        bookButton = new JButton("Book");
        bookButton.setBounds(50, 100, 200, 55);
        bookButton.setBackground(LightColor);
        bookButton.setForeground(DarkColor);
        bookButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        bookButton.setFocusPainted(false);
        bookButton.setBorder(new LineBorder(DarkColor, 3));
        rightPanel.add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMode = "Book";
                leftPanel.removeAll();
                leftPanel.add(createBookPanel());
                rightPanel.remove(authorManageTablePanel);
                rightPanel.remove(shelfmanageTablePanel);
                rightPanel.add(bookManageTablePanel);
                manageBook.setText(currentMode);
                leftPanel.revalidate();
                leftPanel.repaint();
                rightPanel.revalidate();
                rightPanel.repaint();
                bookManageTable.loadBookData(bookTable);
            }
        });

        authorButton = new JButton("Author");
        authorButton.setBounds(250, 100, 200, 55);
        authorButton.setBackground(LightColor);
        authorButton.setForeground(DarkColor);
        authorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        authorButton.setFocusPainted(false);
        authorButton.setBorder(new LineBorder(DarkColor, 3));
        rightPanel.add(authorButton);

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMode = "Author";
                leftPanel.removeAll();
                leftPanel.add(createAuthorPanel());
                rightPanel.remove(bookManageTablePanel);
                rightPanel.remove(shelfmanageTablePanel);
                rightPanel.add(authorManageTablePanel);
                manageBook.setText(currentMode);
                leftPanel.revalidate();
                leftPanel.repaint();
                rightPanel.revalidate();
                rightPanel.repaint();
                authorManageTable.loadAuthorData(authorTable);
            }
        });

        shelfButton = new JButton("Shelves");
        shelfButton.setBounds(450, 100, 200, 55);
        shelfButton.setBackground(LightColor);
        shelfButton.setForeground(DarkColor);
        shelfButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        shelfButton.setFocusPainted(false);
        shelfButton.setBorder(new LineBorder(DarkColor, 3));
        rightPanel.add(shelfButton);

        shelfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMode = "Shelves";
                leftPanel.removeAll();
                leftPanel.add(createShelfJPanel());
                rightPanel.remove(authorManageTablePanel);
                rightPanel.remove(bookManageTablePanel);
                rightPanel.add(shelfmanageTablePanel);
                manageBook.setText(currentMode);
                leftPanel.revalidate();
                leftPanel.repaint();
                rightPanel.revalidate();
                rightPanel.repaint();
                shelfManageTable.loadShelfData(shelfTable);
            }
        });

        // Book Table Panel
        bookManageTablePanel = new JPanel(new BorderLayout());
        bookManageTablePanel.setBackground(LightColor);
        bookManageTablePanel.setBounds(0, tableY, 700, 390);
        rightPanel.add(bookManageTablePanel);

        String[] columnBookTable = { "Book ISBN", "Title", "Author", "Main Genre", "Published Day", "Status" };
        DefaultTableModel modelBookTable = new DefaultTableModel(columnBookTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookTable = new JTable(modelBookTable);
        bookTable.setBackground(LightColor);
        bookTable.setForeground(DarkColor);
        bookTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        bookTable.setRowHeight(25);
        bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        bookTable.getColumnModel().getColumn(0).setPreferredWidth(90);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(155);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        bookTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        bookTable.getColumnModel().getColumn(5).setPreferredWidth(65);

        bookTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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

        bookManageTable.loadBookData(bookTable);

        JTableHeader headerBookTable = bookTable.getTableHeader();
        headerBookTable.setBackground(new Color(47, 120, 152));
        headerBookTable.setForeground(LightColor);
        headerBookTable.setFont(new Font("Tahoma", Font.BOLD, 12));

        JScrollPane scrollBookTablePanel = new JScrollPane(bookTable);
        scrollBookTablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBookTablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        bookManageTablePanel.add(scrollBookTablePanel, BorderLayout.CENTER);

        // Author Table Panel
        authorManageTablePanel = new JPanel(new BorderLayout());
        authorManageTablePanel.setBackground(LightColor);
        authorManageTablePanel.setBounds(0, tableY, 700, 390);
        rightPanel.add(authorManageTablePanel);

        String[] columnAuthorTable = { "Author ID", "Name", "Birth Date", "Nationality", "Bio" };
        DefaultTableModel modelAuthorTable = new DefaultTableModel(columnAuthorTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        authorTable = new JTable(modelAuthorTable);
        authorTable.setBackground(LightColor);
        authorTable.setForeground(DarkColor);
        authorTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        authorTable.setRowHeight(25);
        authorTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        authorTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        authorTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        authorTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        authorTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        authorTable.getColumnModel().getColumn(4).setPreferredWidth(270);

        authorTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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

        authorManageTable.loadAuthorData(authorTable);

        JTableHeader headerAuthorTable = authorTable.getTableHeader();
        headerAuthorTable.setBackground(new Color(47, 120, 152));
        headerAuthorTable.setForeground(LightColor);
        headerAuthorTable.setFont(new Font("Tahoma", Font.BOLD, 12));

        JScrollPane scrollAuthorTablePanel = new JScrollPane(authorTable);
        scrollAuthorTablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollAuthorTablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        authorManageTablePanel.add(scrollAuthorTablePanel, BorderLayout.CENTER);

        // Shelf Table Panel
        shelfmanageTablePanel = new JPanel(new BorderLayout());
        shelfmanageTablePanel.setBackground(LightColor);
        shelfmanageTablePanel.setBounds(0, tableY, 700, 390);
        rightPanel.add(shelfmanageTablePanel);

        String[] columnShelfTable = { "Shelf ID", "Shelf Number", "Genre", "Manager Name", "Email", "Phone" };
        DefaultTableModel modelShelfTable = new DefaultTableModel(columnShelfTable, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        shelfTable = new JTable(modelShelfTable);
        shelfTable.setBackground(LightColor);
        shelfTable.setForeground(DarkColor);
        shelfTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        shelfTable.setRowHeight(25);
        shelfTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        shelfTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        shelfTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        shelfTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        shelfTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        shelfTable.getColumnModel().getColumn(4).setPreferredWidth(180);
        shelfTable.getColumnModel().getColumn(5).setPreferredWidth(130);

        shelfTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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

        shelfManageTable.loadShelfData(shelfTable);

        JTableHeader headerShelfTable = shelfTable.getTableHeader();
        headerShelfTable.setBackground(new Color(47, 120, 152));
        headerShelfTable.setForeground(LightColor);
        headerShelfTable.setFont(new Font("Tahoma", Font.BOLD, 12));

        JScrollPane scrollShelfTablePanel = new JScrollPane(shelfTable);
        scrollShelfTablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollShelfTablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        shelfmanageTablePanel.add(scrollShelfTablePanel, BorderLayout.CENTER);

        leftPanel.add(createBookPanel());
    }

    // Create Shelf Panel
    private JPanel createShelfJPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DarkColor);
        panel.setBounds(0, 0, 300, 640);
        panel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(-1, 0, 150, 30);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(LightColor);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        shelfIDLabel = new JLabel("Enter Shelf ID:");
        shelfIDLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        shelfIDLabel.setForeground(Color.WHITE);
        shelfIDLabel.setBounds(75, 25, 500, 15);
        panel.add(shelfIDLabel);

        shelfIDField = new JTextField(15);
        shelfIDField.setBounds(75, 50, 200, 45);
        shelfIDField.setBackground(LightColor);
        shelfIDField.setForeground(DarkColor);
        shelfIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(shelfIDField);

        shelfNumberLabel = new JLabel("Enter Shelf Number:");
        shelfNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        shelfNumberLabel.setForeground(Color.WHITE);
        shelfNumberLabel.setBounds(75, 115, 500, 15);
        panel.add(shelfNumberLabel);

        shelfNumberField = new JTextField(15);
        shelfNumberField.setBounds(75, 140, 200, 45);
        shelfNumberField.setBackground(LightColor);
        shelfNumberField.setForeground(DarkColor);
        shelfNumberField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(shelfNumberField);

        mainGenreLabel = new JLabel("Enter Main Genre ID:");
        mainGenreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        mainGenreLabel.setForeground(Color.WHITE);
        mainGenreLabel.setBounds(75, 205, 500, 15);
        panel.add(mainGenreLabel);

        mainGenreField = new JTextField(15);
        mainGenreField.setBounds(75, 230, 200, 45);
        mainGenreField.setBackground(LightColor);
        mainGenreField.setForeground(DarkColor);
        mainGenreField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(mainGenreField);

        managerIDLabel = new JLabel("Enter Manager ID:");
        managerIDLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        managerIDLabel.setForeground(Color.WHITE);
        managerIDLabel.setBounds(75, 295, 500, 15);
        panel.add(managerIDLabel);

        managerIDField = new JTextField(15);
        managerIDField.setBounds(75, 320, 200, 45);
        managerIDField.setBackground(LightColor);
        managerIDField.setForeground(DarkColor);
        managerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(managerIDField);

        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 575, 60, 45);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(LightColor);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String shelfID = shelfIDField.getText().trim();
                    String shelfNumber = shelfNumberField.getText().trim();
                    String mainGenre = mainGenreField.getText().trim();
                    String managerID = managerIDField.getText().trim();

                    if (shelfID.isEmpty() || shelfNumber.isEmpty() || mainGenre.isEmpty() || managerID.isEmpty()) {
                        throw new IllegalArgumentException("Shelf ID, Shelf Number, Main Genre ID, and Manager ID are required.");
                    }

                    Map<String, Object> shelf = new HashMap<>();
                    shelf.put("Shelf_ID", Integer.parseInt(shelfID));
                    shelf.put("Shelf_number", Integer.parseInt(shelfNumber));
                    shelf.put("MainGenre_id", Integer.parseInt(mainGenre));
                    shelf.put("Manager_id", Integer.parseInt(managerID));

                    ShelfDAO shelfDAO = new ShelfDAO();
                    shelfDAO.insert(shelf);

                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding shelf: " + ex.getMessage());
                }
            }
        });

        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 575, 70, 45);
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(LightColor);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String shelfIDStr = shelfIDField.getText().trim();
                    if (shelfIDStr.isEmpty()) {
                        throw new IllegalArgumentException("Shelf ID is required.");
                    }
                    int shelfID = Integer.parseInt(shelfIDStr);
                    ShelfDAO shelfDAO = new ShelfDAO();
                    shelfDAO.delete(shelfID);
                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting shelf: " + ex.getMessage());
                }
            }
        });

        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 575, 70, 45);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(LightColor);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String shelfIDStr = shelfIDField.getText().trim();
                    String shelfNumberStr = shelfNumberField.getText().trim();
                    String mainGenre = mainGenreField.getText().trim();
                    String managerID = managerIDField.getText().trim();

                    if (shelfIDStr.isEmpty() || shelfNumberStr.isEmpty() || mainGenre.isEmpty() || managerID.isEmpty()) {
                        throw new IllegalArgumentException("Shelf ID, Shelf Number, Main Genre ID, and Manager ID are required.");
                    }

                    int shelfID = Integer.parseInt(shelfIDStr);
                    int shelfNumber = Integer.parseInt(shelfNumberStr);
                    Map<String, Object> shelf = new HashMap<>();
                    shelf.put("Shelf_ID", shelfID);
                    shelf.put("Shelf_number", shelfNumber);
                    shelf.put("MainGenre_id", Integer.parseInt(mainGenre));
                    shelf.put("Manager_id", Integer.parseInt(managerID));

                    ShelfDAO shelfDAO = new ShelfDAO();
                    shelfDAO.update(shelf);

                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf updated successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating shelf: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    // Create Author Panel
    private JPanel createAuthorPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DarkColor);
        panel.setBounds(0, 0, 300, 640);
        panel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(-1, 0, 150, 30);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(LightColor);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        authorID = new JLabel("Enter Author ID:");
        authorID.setFont(new Font("Tahoma", Font.BOLD, 15));
        authorID.setForeground(Color.WHITE);
        authorID.setBounds(75, 75, 500, 15);
        panel.add(authorID);

        authorIDField = new JTextField(15);
        authorIDField.setBounds(75, 100, 200, 45);
        authorIDField.setBackground(LightColor);
        authorIDField.setForeground(DarkColor);
        authorIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(authorIDField);

        firstNameLabel = new JLabel("Enter First Name:");
        firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setBounds(75, 175, 500, 15);
        panel.add(firstNameLabel);

        firstNameField = new JTextField(15);
        firstNameField.setBounds(75, 200, 200, 45);
        firstNameField.setBackground(LightColor);
        firstNameField.setForeground(DarkColor);
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(firstNameField);

        lastNameLabel = new JLabel("Enter Last Name:");
        lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(75, 275, 500, 15);
        panel.add(lastNameLabel);

        lastNameField = new JTextField(15);
        lastNameField.setBounds(75, 300, 200, 45);
        lastNameField.setBackground(LightColor);
        lastNameField.setForeground(DarkColor);
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(lastNameField);

        birthDateLabel = new JLabel("Enter Birth Date (YYYY-MM-DD):");
        birthDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        birthDateLabel.setForeground(Color.WHITE);
        birthDateLabel.setBounds(75, 375, 500, 15);
        panel.add(birthDateLabel);

        birthDateField = new JTextField(15);
        birthDateField.setBounds(75, 400, 200, 45);
        birthDateField.setBackground(LightColor);
        birthDateField.setForeground(DarkColor);
        birthDateField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(birthDateField);

        nationalityLabel = new JLabel("Enter Nationality:");
        nationalityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        nationalityLabel.setForeground(Color.WHITE);
        nationalityLabel.setBounds(75, 475, 500, 15);
        panel.add(nationalityLabel);

        nationalityField = new JTextField(15);
        nationalityField.setBounds(75, 500, 200, 45);
        nationalityField.setBackground(LightColor);
        nationalityField.setForeground(DarkColor);
        nationalityField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(nationalityField);

        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 575, 60, 45);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(LightColor);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String birthDate = birthDateField.getText().trim();
                    String nationality = nationalityField.getText().trim();

                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        throw new IllegalArgumentException("First Name and Last Name are required.");
                    }

                    Map<String, Object> author = new HashMap<>();
                    author.put("First_name", firstName);
                    author.put("Last_name", lastName);
                    author.put("BirthDate", birthDate.isEmpty() ? null : Date.valueOf(birthDate));
                    author.put("Nationality", nationality.isEmpty() ? null : nationality);
                    author.put("Bio", null);

                    AuthorDAO authorDAO = new AuthorDAO();
                    authorDAO.insert(author);

                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding author: " + ex.getMessage());
                }
            }
        });

        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 575, 70, 45);
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(LightColor);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String authorIdStr = authorIDField.getText().trim();
                    if (authorIdStr.isEmpty()) {
                        throw new IllegalArgumentException("Author ID is required.");
                    }
                    int authorId = Integer.parseInt(authorIdStr);
                    AuthorDAO authorDAO = new AuthorDAO();
                    authorDAO.delete(authorId);
                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting author: " + ex.getMessage());
                }
            }
        });

        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 575, 70, 45);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(LightColor);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String authorIdStr = authorIDField.getText().trim();
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String birthDate = birthDateField.getText().trim();
                    String nationality = nationalityField.getText().trim();

                    if (authorIdStr.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                        throw new IllegalArgumentException("Author ID, First Name, and Last Name are required.");
                    }

                    int authorId = Integer.parseInt(authorIdStr);
                    Map<String, Object> author = new HashMap<>();
                    author.put("Author_id", authorId);
                    author.put("First_name", firstName);
                    author.put("Last_name", lastName);
                    author.put("BirthDate", birthDate.isEmpty() ? null : Date.valueOf(birthDate));
                    author.put("Nationality", nationality.isEmpty() ? null : nationality);
                    author.put("Bio", null);

                    AuthorDAO authorDAO = new AuthorDAO();
                    authorDAO.update(author);

                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author updated successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating author: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    // Create Book Panel
    private JPanel createBookPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DarkColor);
        panel.setBounds(0, 0, 300, 640);
        panel.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(-1, 0, 150, 30);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(LightColor);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        backButton.setBorder(null);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        bookID = new JLabel("Enter Book ID:");
        bookID.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookID.setForeground(Color.WHITE);
        bookID.setBounds(75, 75, 500, 15);
        panel.add(bookID);

        bookIDField = new JTextField(15);
        bookIDField.setBounds(75, 100, 200, 45);
        bookIDField.setBackground(LightColor);
        bookIDField.setForeground(DarkColor);
        bookIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(bookIDField);

        ImageIcon id = new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png"));
        Image scaledIDIcon = id.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconID = new JLabel(new ImageIcon(scaledIDIcon));
        iconID.setBounds(15, 100, 45, 45);
        panel.add(iconID);

        bookName = new JLabel("Enter Book Name:");
        bookName.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookName.setForeground(Color.WHITE);
        bookName.setBounds(75, 175, 500, 15);
        panel.add(bookName);

        bookNameField = new JTextField(15);
        bookNameField.setBounds(75, 200, 200, 45);
        bookNameField.setBackground(LightColor);
        bookNameField.setForeground(DarkColor);
        bookNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(bookNameField);

        ImageIcon book = new ImageIcon(getClass().getResource("/com/res/BookNameIcon.png"));
        Image scaledBookIcon = book.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconBook = new JLabel(new ImageIcon(scaledBookIcon));
        iconBook.setBounds(15, 200, 45, 45);
        panel.add(iconBook);

        authorName = new JLabel("Enter Author ID:");
        authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
        authorName.setForeground(Color.WHITE);
        authorName.setBounds(75, 275, 500, 15);
        panel.add(authorName);

        authorNameField = new JTextField(15);
        authorNameField.setBounds(75, 300, 200, 45);
        authorNameField.setBackground(LightColor);
        authorNameField.setForeground(DarkColor);
        authorNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(authorNameField);

        ImageIcon author = new ImageIcon(getClass().getResource("/com/res/IconAuthor.png"));
        Image scaledAuthorIcon = author.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconAuthor = new JLabel(new ImageIcon(scaledAuthorIcon));
        iconAuthor.setBounds(15, 300, 45, 45);
        panel.add(iconAuthor);

        Category = new JLabel("Enter Genre ID:");
        Category.setFont(new Font("Tahoma", Font.BOLD, 15));
        Category.setForeground(Color.WHITE);
        Category.setBounds(75, 375, 500, 15);
        panel.add(Category);

        mainGenreID = new JTextField(15);
        mainGenreID.setBounds(75, 400, 200, 45);
        mainGenreID.setBackground(LightColor);
        mainGenreID.setForeground(DarkColor);
        mainGenreID.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(mainGenreID);

        ImageIcon category = new ImageIcon(getClass().getResource("/com/res/IconQuantity.png"));
        Image scaledCategoryIcon = category.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconCategory = new JLabel(new ImageIcon(scaledCategoryIcon));
        iconCategory.setBounds(15, 400, 45, 45);
        panel.add(iconCategory);

        addButton = new RoundedButton("Add");
        addButton.setBounds(15, 500, 60, 45);
        addButton.setBackground(new Color(47, 120, 152));
        addButton.setForeground(LightColor);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        addButton.setFocusPainted(false);
        addButton.setBorder(null);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = bookIDField.getText().trim();
                    String title = bookNameField.getText().trim();
                    int authorId = Integer.parseInt(authorNameField.getText().trim());
                    int mainGenreId = Integer.parseInt(mainGenreID.getText().trim());
                    Date publishedDate = Date.valueOf(LocalDate.now());
                    String status = "Available";

                    Map<String, Object> book = new HashMap<>();
                    book.put("ISBN", isbn);
                    book.put("Title", title);
                    book.put("Author_id", authorId);
                    book.put("MainGenre_id", mainGenreId);
                    book.put("published_day", publishedDate);
                    book.put("Status", status);

                    BookDAO bookDAO = new BookDAO();
                    bookDAO.insert(book);

                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding book: " + ex.getMessage());
                }
            }
        });

        deleteButton = new RoundedButton("Delete");
        deleteButton.setBounds(110, 500, 70, 45);
        deleteButton.setBackground(new Color(47, 120, 152));
        deleteButton.setForeground(LightColor);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(null);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = bookIDField.getText().trim();
                    BookDAO bookDAO = new BookDAO();
                    bookDAO.delete(isbn);
                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting book: " + ex.getMessage());
                }
            }
        });

        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 500, 70, 45);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(LightColor);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String isbn = bookIDField.getText().trim();
                    String title = bookNameField.getText().trim();
                    int authorId = Integer.parseInt(authorNameField.getText().trim());
                    int mainGenreId = Integer.parseInt(mainGenreID.getText().trim());
                    Date publishedDate = Date.valueOf(LocalDate.now());
                    String status = "Available";

                    Map<String, Object> book = new HashMap<>();
                    book.put("ISBN", isbn);
                    book.put("Title", title);
                    book.put("Author_id", authorId);
                    book.put("MainGenre_id", mainGenreId);
                    book.put("published_day", publishedDate);
                    book.put("Status", status);

                    BookDAO bookDAO = new BookDAO();
                    bookDAO.update(book);
                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book updated successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating book: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    // Rounded Button Class
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