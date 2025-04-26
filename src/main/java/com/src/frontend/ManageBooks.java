package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
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

import com.src.dao.AuthorDAO;
import com.src.dao.BookDAO;
import com.src.dao.GenreDAO;
import com.src.dao.ShelfDAO;
import com.src.dao.StaffDAO;
import com.src.view.manageBooks.AuthorManageTable;
import com.src.view.manageBooks.BookManageTable;
import com.src.view.manageBooks.ShelfManageTable;

public class ManageBooks extends JFrame {

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bookManageTablePanel;
    private JPanel authorManageTablePanel;
    private JPanel shelfmanageTablePanel;
    private JPanel emDash;

    private JLabel bookID;
    private JLabel bookName;
    private JLabel authorName;
    private JLabel Category;
    private JLabel authorID;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel birthDateLabel;
    private JLabel nationalityLabel;
    private JLabel shelfNumberLabel;
    private JLabel mainGenreLabel;
    private JLabel publishedDateLabel;
    private JLabel managerIDLabel;
    private JLabel shelfIDLabel;

    private JLabel manageBook;

    private JButton backButton;
    private JButton addInfo;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton authorButton;
    private JButton bookButton;
    private JButton shelfButton;

    private JTextField searchField;
    private JTextField bookIDField;
    private JTextField bookNameField;
    private JComboBox<String> authorNameField;
    private JComboBox<String> mainGenreID;
    private JTextField authorIDField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField birthDateField;
    private JTextField nationalityField;
    private JTextField shelfNumberField;
    private JComboBox<String> mainGenreField;
    private JTextField publishedDateField;
    private JComboBox<String> managerIDField;
    private JTextField shelfIDField;

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

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1000, 640));
        mainPanel.setLayout(null);
        add(mainPanel);

        leftPanel = new JPanel();
        leftPanel.setBackground(DarkColor);
        leftPanel.setBounds(0, 0, 300, 640);
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(300, 0, 700, 640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);

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

        addInfo = new JButton("Add Info");
        addInfo.setBounds(150, 0, 150, 30);
        addInfo.setBackground(new Color(47, 120, 152));
        addInfo.setForeground(LightColor);
        addInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
        addInfo.setFocusPainted(false);
        addInfo.setBorder(null);
        leftPanel.add(addInfo);

        addInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = leftPanel.getComponents();
                for (Component component : components) {
                    if (component != backButton && component != addInfo) {
                        leftPanel.remove(component);
                    }
                }

                switch (currentMode) {
                    case "Author":
                        leftPanel.add(createAuthorPanel());
                        break;
                    case "Shelves":
                        leftPanel.add(createShelfJPanel());
                        break;
                    default:
                        leftPanel.add(createBookPanel());
                        break;
                }

                leftPanel.revalidate();
                leftPanel.repaint();
            }
        });

        searchField = new JTextField();
        searchField.setBounds(15, 175, 585, 35);
        searchField.setBackground(LightColor);
        searchField.setForeground(DarkColor);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor, 3),
                new EmptyBorder(5, 10, 5, 10)));
        rightPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(605, 175, 80, 35);
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
                            JOptionPane.showMessageDialog(null, "No books found.", "Info",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (currentMode.equals("Author")) {
                        if (searchText.isEmpty()) {
                            authorManageTable.loadAuthorData(authorTable);
                        } else {
                            AuthorDAO authorDAO = new AuthorDAO();
                            authorManageTable.loadSearchResults(authorTable, authorDAO.searchAuthors(searchText));
                        }
                        if (authorTable.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No authors found.", "Info",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (currentMode.equals("Shelves")) {
                        if (searchText.isEmpty()) {
                            shelfManageTable.loadShelfData(shelfTable);
                        } else {
                            ShelfDAO shelfDAO = new ShelfDAO();
                            shelfManageTable.loadSearchResults(shelfTable, shelfDAO.searchShelves(searchText));
                        }
                        if (shelfTable.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No shelves found.", "Info",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error searching: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        manageBook = new JLabel("Manage Books");
        manageBook.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBook.setForeground(DarkColor);
        manageBook.setBounds(250, 0, 600, 100);
        rightPanel.add(manageBook);

        emDash = new JPanel();
        emDash.setBackground(DarkColor);
        emDash.setBounds(255, 75, 200, 5);
        rightPanel.add(emDash);

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
                rightPanel.remove(authorManageTablePanel);
                rightPanel.remove(shelfmanageTablePanel);
                rightPanel.add(bookManageTablePanel);
                manageBook.setText("Manage Books");
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
                rightPanel.remove(bookManageTablePanel);
                rightPanel.remove(shelfmanageTablePanel);
                rightPanel.add(authorManageTablePanel);
                manageBook.setText("Manage Authors");
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
                rightPanel.remove(authorManageTablePanel);
                rightPanel.remove(bookManageTablePanel);
                rightPanel.add(shelfmanageTablePanel);
                manageBook.setText("Manage Shelves");
                leftPanel.revalidate();
                leftPanel.repaint();
                rightPanel.revalidate();
                rightPanel.repaint();
                shelfManageTable.loadShelfData(shelfTable);
            }
        });

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
        shelfTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        shelfTable.getColumnModel().getColumn(3).setPreferredWidth(140);
        shelfTable.getColumnModel().getColumn(4).setPreferredWidth(120);
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

    private void populateGenreComboBox(JComboBox<String> comboBox) {
        try {
            GenreDAO genreDAO = new GenreDAO();
            List<Map<String, Object>> genres = genreDAO.findAll();
            comboBox.removeAllItems();
            for (Map<String, Object> genre : genres) {
                String genreId = (String) genre.get("MainGenre_id");
                String genreName = (String) genre.get("MainGenre_name");
                comboBox.addItem(genreId + " - " + genreName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading genres: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateAuthorComboBox(JComboBox<String> comboBox) {
        try {
            AuthorDAO authorDAO = new AuthorDAO();
            List<Map<String, Object>> authors = authorDAO.findAll();
            comboBox.removeAllItems();
            for (Map<String, Object> author : authors) {
                String authorId = String.valueOf(author.get("Author_id"));
                String authorName = author.get("First_name") + " "
                        + (author.get("Last_name") != null ? author.get("Last_name") : "");
                comboBox.addItem(authorId + " - " + authorName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading authors: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateManagerComboBox(JComboBox<String> comboBox) {
        try {
            StaffDAO staffDAO = new StaffDAO();
            List<Map<String, Object>> managers = staffDAO.findAll();
            comboBox.removeAllItems();
            for (Map<String, Object> manager : managers) {
                String managerId = String.valueOf(manager.get("Manager_id"));
                String managerName = manager.get("First_name") + " "
                        + (manager.get("Last_name") != null ? manager.get("Last_name") : "");
                comboBox.addItem(managerId + " - " + managerName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading managers: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int parseIdFromComboBox(JComboBox<String> comboBox, String fieldName) {
        String selectedValue = comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : "";
        if (selectedValue.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        try {
            String[] parts = selectedValue.split(" - ");
            return Integer.parseInt(parts[0]);
        } catch (Exception ex) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

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
        shelfIDLabel.setBounds(15, 75, 500, 15);
        panel.add(shelfIDLabel);

        shelfIDField = new JTextField(15);
        shelfIDField.setBounds(15, 100, 260, 45);
        shelfIDField.setBackground(LightColor);
        shelfIDField.setForeground(DarkColor);
        shelfIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(shelfIDField);

        shelfNumberLabel = new JLabel("Enter Shelf Number:");
        shelfNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        shelfNumberLabel.setForeground(Color.WHITE);
        shelfNumberLabel.setBounds(15, 175, 500, 15);
        panel.add(shelfNumberLabel);

        shelfNumberField = new JTextField(15);
        shelfNumberField.setBounds(15, 200, 260, 45);
        shelfNumberField.setBackground(LightColor);
        shelfNumberField.setForeground(DarkColor);
        shelfNumberField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(shelfNumberField);

        mainGenreLabel = new JLabel("Enter Main Genre ID:");
        mainGenreLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        mainGenreLabel.setForeground(Color.WHITE);
        mainGenreLabel.setBounds(15, 275, 500, 15);
        panel.add(mainGenreLabel);

        mainGenreField = new JComboBox<>();
        mainGenreField.setEditable(true);
        mainGenreField.setBounds(15, 300, 260, 45);
        mainGenreField.setBackground(LightColor);
        mainGenreField.setForeground(DarkColor);
        mainGenreField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mainGenreField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        populateGenreComboBox(mainGenreField);
        panel.add(mainGenreField);

        managerIDLabel = new JLabel("Enter Manager ID:");
        managerIDLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        managerIDLabel.setForeground(Color.WHITE);
        managerIDLabel.setBounds(15, 375, 500, 15);
        panel.add(managerIDLabel);

        managerIDField = new JComboBox<>();
        managerIDField.setEditable(true);
        managerIDField.setBounds(15, 400, 260, 45);
        managerIDField.setBackground(LightColor);
        managerIDField.setForeground(DarkColor);
        managerIDField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        managerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        populateManagerComboBox(managerIDField);
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
                    String shelfIDStr = shelfIDField.getText().trim();
                    String shelfNumberStr = shelfNumberField.getText().trim();

                    if (shelfIDStr.isEmpty()) {
                        throw new IllegalArgumentException("Shelf ID is required.");
                    }
                    if (shelfNumberStr.isEmpty()) {
                        throw new IllegalArgumentException("Shelf Number is required.");
                    }

                    int shelfID;
                    int shelfNumber;
                    try {
                        shelfID = Integer.parseInt(shelfIDStr);
                        shelfNumber = Integer.parseInt(shelfNumberStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Shelf ID and Shelf Number must be valid integers.");
                    }

                    int mainGenre = parseIdFromComboBox(mainGenreField, "Main Genre ID");
                    int managerID = parseIdFromComboBox(managerIDField, "Manager ID");

                    ShelfDAO shelfDAO = new ShelfDAO();
                    if (shelfDAO.getById(shelfID) != null) {
                        throw new IllegalArgumentException("Shelf with ID " + shelfID + " already exists.");
                    }

                    StaffDAO staffDAO = new StaffDAO();
                    if (staffDAO.getById(managerID) == null) {
                        throw new IllegalArgumentException("Manager with ID " + managerID + " does not exist.");
                    }

                    Map<String, Object> shelf = new HashMap<>();
                    shelf.put("Shelves_id", shelfID);
                    shelf.put("Shelf_number", shelfNumber);
                    shelf.put("MainGenre_id", mainGenre);
                    shelf.put("Manager_id", managerID);

                    shelfDAO.insert(shelf);

                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding shelf: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                    int shelfID;
                    try {
                        shelfID = Integer.parseInt(shelfIDStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Shelf ID must be a valid integer.");
                    }

                    ShelfDAO shelfDAO = new ShelfDAO();
                    if (shelfDAO.getById(shelfID) == null) {
                        throw new IllegalArgumentException("Shelf with ID " + shelfID + " does not exist.");
                    }

                    shelfDAO.delete(shelfID);
                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf deleted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting shelf: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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

                    if (shelfIDStr.isEmpty()) {
                        throw new IllegalArgumentException("Shelf ID is required.");
                    }

                    if (shelfNumberStr.isEmpty() && mainGenreField.getSelectedItem() == null
                            && managerIDField.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Nothing to update.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    int shelfID;
                    try {
                        shelfID = Integer.parseInt(shelfIDStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Shelf ID must be a valid integer.");
                    }

                    ShelfDAO shelfDAO = new ShelfDAO();
                    Map<String, Object> existingShelf = shelfDAO.getById(shelfID);
                    if (existingShelf == null) {
                        throw new IllegalArgumentException("Shelf with ID " + shelfID + " does not exist.");
                    }

                    Map<String, Object> shelf = new HashMap<>();
                    shelf.put("Shelves_id", shelfID);

                    if (!shelfNumberStr.isEmpty()) {
                        try {
                            shelf.put("Shelf_number", Integer.parseInt(shelfNumberStr));
                        } catch (NumberFormatException ex) {
                            throw new IllegalArgumentException("Shelf Number must be a valid integer.");
                        }
                    } else {
                        shelf.put("Shelf_number", existingShelf.get("Shelf_number"));
                    }

                    if (mainGenreField.getSelectedItem() != null) {
                        int mainGenreId = parseIdFromComboBox(mainGenreField, "Main Genre ID");
                        shelf.put("MainGenre_id", mainGenreId);
                    } else {
                        shelf.put("MainGenre_id", existingShelf.get("MainGenre_id"));
                    }

                    if (managerIDField.getSelectedItem() != null) {
                        int managerId = parseIdFromComboBox(managerIDField, "Manager ID");
                        StaffDAO staffDAO = new StaffDAO();
                        if (staffDAO.getById(managerId) == null) {
                            throw new IllegalArgumentException("Manager with ID " + managerId + " does not exist.");
                        }
                        shelf.put("Manager_id", managerId);
                    } else {
                        shelf.put("Manager_id", existingShelf.get("Manager_id"));
                    }

                    shelfDAO.update(shelf);
                    shelfManageTable.loadShelfData(shelfTable);
                    JOptionPane.showMessageDialog(null, "Shelf updated successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating shelf: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

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
        authorID.setBounds(15, 75, 500, 15);
        panel.add(authorID);

        authorIDField = new JTextField(15);
        authorIDField.setBounds(15, 100, 260, 45);
        authorIDField.setBackground(LightColor);
        authorIDField.setForeground(DarkColor);
        authorIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(authorIDField);

        firstNameLabel = new JLabel("Enter First Name:");
        firstNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setBounds(15, 175, 500, 15);
        panel.add(firstNameLabel);

        firstNameField = new JTextField(15);
        firstNameField.setBounds(15, 200, 260, 45);
        firstNameField.setBackground(LightColor);
        firstNameField.setForeground(DarkColor);
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(firstNameField);

        lastNameLabel = new JLabel("Enter Last Name:");
        lastNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(15, 275, 500, 15);
        panel.add(lastNameLabel);

        lastNameField = new JTextField(15);
        lastNameField.setBounds(15, 300, 260, 45);
        lastNameField.setBackground(LightColor);
        lastNameField.setForeground(DarkColor);
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(lastNameField);

        birthDateLabel = new JLabel("Enter Birth Date (YYYY-MM-DD):");
        birthDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        birthDateLabel.setForeground(Color.WHITE);
        birthDateLabel.setBounds(15, 375, 500, 15);
        panel.add(birthDateLabel);

        birthDateField = new JTextField(15);
        birthDateField.setBounds(15, 400, 260, 45);
        birthDateField.setBackground(LightColor);
        birthDateField.setForeground(DarkColor);
        birthDateField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(birthDateField);

        nationalityLabel = new JLabel("Enter Nationality:");
        nationalityLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        nationalityLabel.setForeground(Color.WHITE);
        nationalityLabel.setBounds(15, 475, 500, 15);
        panel.add(nationalityLabel);

        nationalityField = new JTextField(15);
        nationalityField.setBounds(15, 500, 260, 45);
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
                    String authorIdStr = authorIDField.getText().trim();
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String birthDate = birthDateField.getText().trim();
                    String nationality = nationalityField.getText().trim();

                    if (authorIdStr.isEmpty()) {
                        throw new IllegalArgumentException("Author ID is required.");
                    }
                    if (firstName.isEmpty()) {
                        throw new IllegalArgumentException("First Name is required.");
                    }
                    if (lastName.isEmpty()) {
                        throw new IllegalArgumentException("Last Name is required.");
                    }

                    int authorId;
                    try {
                        authorId = Integer.parseInt(authorIdStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Author ID must be a valid integer.");
                    }

                    AuthorDAO authorDAO = new AuthorDAO();
                    if (authorDAO.getById(authorId) != null) {
                        throw new IllegalArgumentException("Author with ID " + authorId + " already exists.");
                    }

                    Date birthDateValue = null;
                    if (!birthDate.isEmpty()) {
                        try {
                            birthDateValue = Date.valueOf(birthDate);
                            LocalDate birthLocalDate = birthDateValue.toLocalDate();
                            int currentYear = LocalDate.now().getYear();
                            if (birthLocalDate.getYear() < 1900 || birthLocalDate.getYear() > currentYear) {
                                throw new IllegalArgumentException(
                                        "Birth year must be between 1900 and " + currentYear + ".");
                            }
                        } catch (IllegalArgumentException ex) {
                            throw new IllegalArgumentException("Birth Date must be in YYYY-MM-DD format.");
                        }
                    }

                    Map<String, Object> author = new HashMap<>();
                    author.put("Author_id", authorId);
                    author.put("First_name", firstName);
                    author.put("Last_name", lastName);
                    author.put("BirthDate", birthDateValue);
                    author.put("Nationality", nationality.isEmpty() ? null : nationality);
                    author.put("Bio", null);

                    authorDAO.insert(author);

                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding author: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                    int authorId;
                    try {
                        authorId = Integer.parseInt(authorIdStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Author ID must be a valid integer.");
                    }

                    AuthorDAO authorDAO = new AuthorDAO();
                    if (authorDAO.getById(authorId) == null) {
                        throw new IllegalArgumentException("Author with ID " + authorId + " does not exist.");
                    }

                    authorDAO.delete(authorId);
                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author deleted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting author: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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

                    if (authorIdStr.isEmpty()) {
                        throw new IllegalArgumentException("Author ID is required.");
                    }

                    if (firstName.isEmpty() && lastName.isEmpty() && birthDate.isEmpty() && nationality.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nothing to update.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    int authorId;
                    try {
                        authorId = Integer.parseInt(authorIdStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Author ID must be a valid integer.");
                    }

                    AuthorDAO authorDAO = new AuthorDAO();
                    Map<String, Object> existingAuthor = authorDAO.getById(authorId);
                    if (existingAuthor == null) {
                        throw new IllegalArgumentException("Author with ID " + authorId + " does not exist.");
                    }

                    Map<String, Object> author = new HashMap<>();
                    author.put("Author_id", authorId);

                    if (!firstName.isEmpty()) {
                        author.put("First_name", firstName);
                    } else {
                        author.put("First_name", existingAuthor.get("First_name"));
                    }

                    if (!lastName.isEmpty()) {
                        author.put("Last_name", lastName);
                    } else {
                        author.put("Last_name", existingAuthor.get("Last_name"));
                    }

                    if (!birthDate.isEmpty()) {
                        try {
                            Date birthDateValue = Date.valueOf(birthDate);
                            LocalDate birthLocalDate = birthDateValue.toLocalDate();
                            int currentYear = LocalDate.now().getYear();
                            if (birthLocalDate.getYear() < 1900 || birthLocalDate.getYear() > currentYear) {
                                throw new IllegalArgumentException(
                                        "Birth year must be between 1900 and " + currentYear + ".");
                            }
                            author.put("BirthDate", birthDateValue);
                        } catch (IllegalArgumentException ex) {
                            throw new IllegalArgumentException("Birth Date must be in YYYY-MM-DD format.");
                        }
                    } else {
                        author.put("BirthDate", existingAuthor.get("BirthDate"));
                    }

                    if (!nationality.isEmpty()) {
                        author.put("Nationality", nationality);
                    } else {
                        author.put("Nationality", existingAuthor.get("Nationality"));
                    }

                    author.put("Bio", existingAuthor.get("Bio"));

                    authorDAO.update(author);

                    authorManageTable.loadAuthorData(authorTable);
                    JOptionPane.showMessageDialog(null, "Author updated successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating author: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

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

        bookID = new JLabel("Enter Book ISBN:");
        bookID.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookID.setForeground(Color.WHITE);
        bookID.setBounds(15, 75, 500, 15);
        panel.add(bookID);

        bookIDField = new JTextField(15);
        bookIDField.setBounds(15, 100, 260, 45);
        bookIDField.setBackground(LightColor);
        bookIDField.setForeground(DarkColor);
        bookIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(bookIDField);

        bookName = new JLabel("Enter Book Title:");
        bookName.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookName.setForeground(Color.WHITE);
        bookName.setBounds(15, 175, 500, 15);
        panel.add(bookName);

        bookNameField = new JTextField(15);
        bookNameField.setBounds(15, 200, 260, 45);
        bookNameField.setBackground(LightColor);
        bookNameField.setForeground(DarkColor);
        bookNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(bookNameField);

        authorName = new JLabel("Enter Author ID:");
        authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
        authorName.setForeground(Color.WHITE);
        authorName.setBounds(15, 275, 500, 15);
        panel.add(authorName);

        authorNameField = new JComboBox<>();
        authorNameField.setEditable(true);
        authorNameField.setBounds(15, 300, 260, 45);
        authorNameField.setBackground(LightColor);
        authorNameField.setForeground(DarkColor);
        authorNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        authorNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        populateAuthorComboBox(authorNameField);
        panel.add(authorNameField);

        Category = new JLabel("Enter Genre ID:");
        Category.setFont(new Font("Tahoma", Font.BOLD, 15));
        Category.setForeground(Color.WHITE);
        Category.setBounds(15, 375, 500, 15);
        panel.add(Category);

        mainGenreID = new JComboBox<>();
        mainGenreID.setEditable(true);
        mainGenreID.setBounds(15, 400, 260, 45);
        mainGenreID.setBackground(LightColor);
        mainGenreID.setForeground(DarkColor);
        mainGenreID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mainGenreID.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        populateGenreComboBox(mainGenreID);
        panel.add(mainGenreID);

        publishedDateLabel = new JLabel("Enter Published Date (YYYY-MM-DD):");
        publishedDateLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        publishedDateLabel.setForeground(Color.WHITE);
        publishedDateLabel.setBounds(15, 475, 500, 15);
        panel.add(publishedDateLabel);

        publishedDateField = new JTextField(15);
        publishedDateField.setBounds(15, 500, 260, 45);
        publishedDateField.setBackground(LightColor);
        publishedDateField.setForeground(DarkColor);
        publishedDateField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(publishedDateField);

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
                    String isbn = bookIDField.getText().trim();
                    String title = bookNameField.getText().trim();
                    String publishedDate = publishedDateField.getText().trim();
                    String status = "Available";

                    if (isbn.isEmpty()) {
                        throw new IllegalArgumentException("ISBN is required.");
                    }
                    if (title.isEmpty()) {
                        throw new IllegalArgumentException("Book Title is required.");
                    }

                    int authorId = parseIdFromComboBox(authorNameField, "Author ID");
                    int mainGenreId = parseIdFromComboBox(mainGenreID, "Genre ID");

                    BookDAO bookDAO = new BookDAO();
                    if (bookDAO.getById(isbn) != null) {
                        throw new IllegalArgumentException("Book with ISBN " + isbn + " already exists.");
                    }

                    AuthorDAO authorDAO = new AuthorDAO();
                    if (authorDAO.getById(authorId) == null) {
                        throw new IllegalArgumentException("Author with ID " + authorId + " does not exist.");
                    }

                    GenreDAO genreDAO = new GenreDAO();
                    if (genreDAO.getById(mainGenreId) == null) {
                        throw new IllegalArgumentException("Genre with ID " + mainGenreId + " does not exist.");
                    }

                    Date publishedDateValue = null;
                    if (!publishedDate.isEmpty()) {
                        try {
                            publishedDateValue = Date.valueOf(publishedDate);
                            LocalDate pubLocalDate = publishedDateValue.toLocalDate();
                            int currentYear = LocalDate.now().getYear();
                            if (pubLocalDate.getYear() < 1900 || pubLocalDate.getYear() > currentYear) {
                                throw new IllegalArgumentException(
                                        "Published year must be between 1900 and " + currentYear + ".");
                            }
                        } catch (IllegalArgumentException ex) {
                            throw new IllegalArgumentException("Published Date must be in YYYY-MM-DD format.");
                        }
                    }

                    Map<String, Object> book = new HashMap<>();
                    book.put("ISBN", isbn);
                    book.put("Title", title);
                    book.put("Author_id", authorId);
                    book.put("MainGenre_id", mainGenreId);
                    book.put("published_day", publishedDateValue);
                    book.put("Status", status);

                    bookDAO.insert(book);

                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding book: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                    String isbn = bookIDField.getText().trim();
                    if (isbn.isEmpty()) {
                        throw new IllegalArgumentException("ISBN is required.");
                    }

                    BookDAO bookDAO = new BookDAO();
                    if (bookDAO.getById(isbn) == null) {
                        throw new IllegalArgumentException("Book with ISBN " + isbn + " does not exist.");
                    }

                    bookDAO.delete(isbn);
                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting book: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
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
                    String isbn = bookIDField.getText().trim();
                    String title = bookNameField.getText().trim();
                    String publishedDateStr = publishedDateField.getText().trim();

                    if (isbn.isEmpty()) {
                        throw new IllegalArgumentException("ISBN is required.");
                    }

                    if (title.isEmpty() && authorNameField.getSelectedItem() == null
                            && mainGenreID.getSelectedItem() == null
                            && publishedDateStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nothing to update.", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    BookDAO bookDAO = new BookDAO();
                    Map<String, Object> existingBook = bookDAO.getById(isbn);
                    if (existingBook == null) {
                        throw new IllegalArgumentException("Book with ISBN " + isbn + " does not exist.");
                    }

                    Map<String, Object> book = new HashMap<>();
                    book.put("ISBN", isbn);

                    if (!title.isEmpty()) {
                        book.put("Title", title);
                    } else {
                        book.put("Title", existingBook.get("Title"));
                    }

                    if (authorNameField.getSelectedItem() != null) {
                        int authorId = parseIdFromComboBox(authorNameField, "Author ID");
                        AuthorDAO authorDAO = new AuthorDAO();
                        if (authorDAO.getById(authorId) == null) {
                            throw new IllegalArgumentException("Author with ID " + authorId + " does not exist.");
                        }
                        book.put("Author_id", authorId);
                    } else {
                        book.put("Author_id", existingBook.get("Author_id"));
                    }

                    if (mainGenreID.getSelectedItem() != null) {
                        int mainGenreId = parseIdFromComboBox(mainGenreID, "Genre ID");
                        GenreDAO genreDAO = new GenreDAO();
                        if (genreDAO.getById(mainGenreId) == null) {
                            throw new IllegalArgumentException("Genre with ID " + mainGenreId + " does not exist.");
                        }
                        book.put("MainGenre_id", mainGenreId);
                    } else {
                        book.put("MainGenre_id", existingBook.get("MainGenre_id"));
                    }

                    if (!publishedDateStr.isEmpty()) {
                        try {
                            Date publishedDateValue = Date.valueOf(publishedDateStr);
                            LocalDate pubLocalDate = publishedDateValue.toLocalDate();
                            int currentYear = LocalDate.now().getYear();
                            if (pubLocalDate.getYear() < 1900 || pubLocalDate.getYear() > currentYear) {
                                throw new IllegalArgumentException(
                                        "Published year must be between 1900 and " + currentYear + ".");
                            }
                            book.put("published_day", publishedDateValue);
                        } catch (IllegalArgumentException ex) {
                            throw new IllegalArgumentException("Published Date must be in YYYY-MM-DD format.");
                        }
                    } else {
                        book.put("published_day", existingBook.get("published_day"));
                    }

                    book.put("Status", existingBook.get("Status"));

                    bookDAO.update(book);
                    bookManageTable.loadBookData(bookTable);
                    JOptionPane.showMessageDialog(null, "Book updated successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating book: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
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