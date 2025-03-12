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
import javax.swing.JList;
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
import com.src.view.manageBooks.BookManageTable;


public class ManageBooks extends JFrame{

    private JPanel mainPanel;
    private JPanel NavigationPanel;
    private JPanel rightPanel;
    private JPanel bookManageTablePanel;
    private JPanel emDash;


    private JLabel iconID;
    private JLabel iconBook;
    private JLabel iconAuthor;
    private JLabel iconQuantity;
    private JLabel bookID;
    private JLabel bookName;
    private JLabel authorName;
    private JLabel Quantity;

    //Content
    private JLabel manageBook;
    private JLabel Owner;
    private JLabel Day;
   
    
    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JTextField bookIDField;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField mainGenreID;

    private JTable manageTable;
    private BookManageTable bookManageTable;
    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220,238,229);


    private JList<String> suggestionList;
    private JScrollPane suggestionScrollPane;


    public ManageBooks(){
        setPreferredSize(new Dimension(1000, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        pack();
        
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
    }



    private void initComponents(){
        bookManageTable = new BookManageTable();
        int heightButton = 45;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

         // Main Panel
         mainPanel = new JPanel();
         mainPanel.setBackground(Color.WHITE);
         mainPanel.setPreferredSize(new Dimension(1050, 640));
         mainPanel.setLayout(null);
         add(mainPanel);


        //Navigation
         NavigationPanel = new JPanel();
         NavigationPanel.setBackground(DarkColor);
         NavigationPanel.setBounds(0,0,300,640);
         NavigationPanel.setLayout(null);
         mainPanel.add(NavigationPanel);

         //Right Panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setBounds(300,0,800,640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);
        
        

         //backButton
         backButton = new RoundedButton("Back");
         backButton.setBounds(-1, 0, 300, heightButton);
         backButton.setBackground(new Color(47, 120, 152));
         backButton.setForeground(new Color(220, 238, 229));
         backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
         backButton.setFocusPainted(false);
         backButton.setBorder(null);
         NavigationPanel.add(backButton);

         backButton.addActionListener(new ActionListener() {
            //Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });
        

        //BookID
        bookID = new JLabel("Enter book ID:");
        bookID.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookID.setForeground(new Color(255, 255, 255));
        bookID.setBounds(75, 150, 500, 15);
        NavigationPanel.add(bookID);

        //BookID Field
        bookIDField = new JTextField(15);
        bookIDField.setBounds(75, 175, 200, 45);
        bookIDField.setBackground(LightColor);
        bookIDField.setForeground(new Color(5, 77, 120));
        bookIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(bookIDField);

        //BookID icon
        ImageIcon id = new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png"));
        Image scaledIDIcon = id.getImage().getScaledInstance(85,65, Image.SCALE_SMOOTH);
        iconID = new JLabel(new ImageIcon(scaledIDIcon));
        iconID.setBounds(15, 175, 45, 45);
        NavigationPanel.add(iconID);



        //Book Name
        bookName = new JLabel("Enter Book Name:");
        bookName.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookName.setForeground(new Color(255, 255, 255));
        bookName.setBounds(75, 250, 500, 15);
        NavigationPanel.add(bookName);

        //Book name field
        bookNameField = new JTextField(15);
        bookNameField.setBounds(75, 275, 200, 45);
        bookNameField.setBackground(LightColor);
        bookNameField.setForeground(new Color(5, 77, 120));
        bookNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(bookNameField);

        //Book name Icon
        ImageIcon book = new ImageIcon(getClass().getResource("/com/res/BookNameIcon.png"));
        Image scaledBookIcon = book.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconBook = new JLabel(new ImageIcon(scaledBookIcon));
        iconBook.setBounds(15, 275, 45, 45);
        NavigationPanel.add(iconBook);

        //Author Name
        authorName = new JLabel("Enter Author Name:");
        authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
        authorName.setForeground(new Color(255, 255, 255));
        authorName.setBounds(75, 350, 500, 15);
        NavigationPanel.add(authorName);


        //Author Name Field
        authorNameField = new JTextField(15);
        authorNameField.setBounds(75, 375, 200, 45);
        authorNameField.setBackground(LightColor);
        authorNameField.setForeground(new Color(5, 77, 120));
        authorNameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(authorNameField);


        //Author Icon
        ImageIcon author = new ImageIcon(getClass().getResource("/com/res/IconAuthor.png"));
        Image scaledAuthorIcon = author.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconAuthor = new JLabel(new ImageIcon(scaledAuthorIcon));
        iconAuthor.setBounds(15, 375, 45, 45);
        NavigationPanel.add(iconAuthor);


        //Quantity
        Quantity = new JLabel("Enter Genre ID:");
        Quantity.setFont(new Font("Tahoma", Font.BOLD, 15));
        Quantity.setForeground(new Color(255, 255, 255));
        Quantity.setBounds(75, 450, 500, 15);
        NavigationPanel.add(Quantity);

        //Quantity Field
        mainGenreID = new JTextField(15);
        mainGenreID.setBounds(75, 475, 200, 45);
        mainGenreID.setBackground(LightColor);
        mainGenreID.setForeground(new Color(5, 77, 120));
        mainGenreID.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(mainGenreID);


        //////////Quantity Icon////////
        ImageIcon quantity = new ImageIcon(getClass().getResource("/com/res/IconQuantity.png"));
        Image scaledQuantityIcon = quantity.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconQuantity = new JLabel(new ImageIcon(scaledQuantityIcon));
        iconQuantity.setBounds(15, 475, 45, 45);
        NavigationPanel.add(iconQuantity);

        //////////Add Button////////
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
        
                    
                    bookManageTable.loadBookData(manageTable);
        
                    JOptionPane.showMessageDialog(null, "Book added successfully!");
                } catch(Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding book: " + ex.getMessage());
                }
            }
        });
         NavigationPanel.add(addButton);
        
        //////////Delete Button////////
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
                   
                    String isbn = bookIDField.getText().trim();
                    BookDAO bookDAO = new BookDAO();
                    bookDAO.delete(isbn);   
                    bookManageTable.loadBookData(manageTable);
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!");
                } catch(Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting book: " + ex.getMessage());
                }
            }
        });
        NavigationPanel.add(deleteButton);

        //////////Update Button////////
        updateButton = new RoundedButton("Update");
        updateButton.setBounds(215, 75, 70, heightButton);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(new Color(220, 238, 229));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        
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
        
                 
                    bookManageTable.loadBookData(manageTable);
        
                    JOptionPane.showMessageDialog(null, "Book updated successfully!");
                } catch(Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating book: " + ex.getMessage());
                }
            }
        });
        NavigationPanel.add(updateButton);


         //Content Panel
         //Manage Book//
       
        manageBook = new JLabel("Manage Books");
        manageBook.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBook.setForeground(DarkColor);
        manageBook.setBounds(225, 0, 600, 100);
        rightPanel.add(manageBook);

        emDash = new JPanel();
        emDash.setBackground(DarkColor);
        emDash.setBounds(235,75,200,5);
        rightPanel.add(emDash);

        //Owner//
        Owner = new JLabel("Owner: Admin");
        Owner.setFont(new Font("Tahoma", Font.BOLD, 27));
        Owner.setForeground(DarkColor);
        Owner.setBounds(40, 110, 250, 30);
        rightPanel.add(Owner);
        
        //Date//
        Day = new JLabel("Date: " + formattedDate);
        Day.setFont(new Font("Tahoma", Font.BOLD, 27));
        Day.setForeground(DarkColor);
        Day.setBounds(400, 110, 500, 30);
        rightPanel.add(Day);
        
        
        //Table
        bookManageTablePanel = new JPanel();
        bookManageTablePanel.setBackground(LightColor);
        bookManageTablePanel.setBounds(100,225,500,150);
        bookManageTablePanel.setLayout(null);
        rightPanel.add(bookManageTablePanel);

        String[] columnManageTable = {"Book ISBN", "Title", "Author", "Main Genre", " Published Day", "Status"};

        Object[][] dataManageTable = {

                {"a", "Name 1", "Author 1", "1"},
                {"b", "Name 2", "Author 2", "2"},
                {"c", "Name 3", "Author 3", "3"},
                {"d", "Name 4", "Author 4", "4"},
                {"e", "Name 5", "Author 5", "5"},
                {"f", "Name 6", "Author 6", "6"},
                {"g", "Name 7", "Author 7", "7"},
                {"h", "Name 8", "Author 8", "8"},
                {"j", "Name 9", "Author 9", "9"},
                {"k", "Name 10", "Author 10", "10"},

        };

        DefaultTableModel modelManageTable = new DefaultTableModel(dataManageTable, columnManageTable);
        manageTable = new JTable(modelManageTable);
        manageTable.setBackground(LightColor);
        manageTable.setForeground(DarkColor);
        bookManageTable.loadBookData(manageTable);


        JTableHeader headerManageTable = manageTable.getTableHeader();
        headerManageTable.setOpaque(false);
        headerManageTable.setBackground(new Color(47, 120, 152));
        headerManageTable.setForeground(LightColor);

        JScrollPane scrollManageTablePanel = new JScrollPane(manageTable);
        scrollManageTablePanel.setBounds(0, 0, 500, 150);
        scrollManageTablePanel.getViewport().setOpaque(false);
        scrollManageTablePanel.setOpaque(false);
        bookManageTablePanel.add(scrollManageTablePanel);
       
        
    }
    
    class Bar extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED); 
            g.drawLine(50, 100, 250, 100); 
        }
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