package com.src.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ManageBooks extends JFrame{
    private JPanel mainPanel;
    private JPanel NavigationPanel;
    private JPanel rightPanel;
    private JPanel bookManageTablePanel;


    private JLabel iconID;
    private JLabel iconBook;
    private JLabel iconAuthor;
    private JLabel iconQuantity;
    private JLabel bookID;
    private JLabel bookName;
    private JLabel authorName;
    private JLabel Quantity;
    private JLabel manageBook;
    // private JLabel Bar;
    
    

    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JTextField bookIDField;
    private JTextField bookNameField;
    private JTextField authorNameField;
    private JTextField quantityField;

    private JTable manageTable;

    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220,238,229);


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

        int heightButton = 45;

         // Main Panel
         mainPanel = new JPanel();
         mainPanel.setBackground(Color.WHITE);
         mainPanel.setPreferredSize(new Dimension(1050, 640));
         mainPanel.setLayout(null);
         add(mainPanel);


        //Navigation
         NavigationPanel = new JPanel();
         NavigationPanel.setBackground(new Color(5, 77, 120));
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
         backButton.setBounds(0, 0, 300, heightButton);
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
        Image scaledIDIcon = id.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
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

        ImageIcon book = new ImageIcon(getClass().getResource("/com/res/IconBook.png"));
        Image scaledBookIcon = book.getImage().getScaledInstance(85, 65, Image.SCALE_SMOOTH);
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
        Quantity = new JLabel("Enter Quantity:");
        Quantity.setFont(new Font("Tahoma", Font.BOLD, 15));
        Quantity.setForeground(new Color(255, 255, 255));
        Quantity.setBounds(75, 450, 500, 15);
        NavigationPanel.add(Quantity);

        //Quantity Field
        quantityField = new JTextField(15);
        quantityField.setBounds(75, 475, 200, 45);
        quantityField.setBackground(LightColor);
        quantityField.setForeground(new Color(5, 77, 120));
        quantityField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(quantityField);


        //Quantity Icon
        ImageIcon quantity = new ImageIcon(getClass().getResource("/com/res/IconQuantity.png"));
        Image scaledQuantityIcon = quantity.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconQuantity = new JLabel(new ImageIcon(scaledQuantityIcon));
        iconQuantity.setBounds(15, 475, 45, 45);
        NavigationPanel.add(iconQuantity);

        //Add Button
         addButton = new RoundedButton("Add");
         addButton.setBounds(5, 75, 60, heightButton);
         addButton.setBackground(new Color(47, 120, 152));
         addButton.setForeground(new Color(220, 238, 229));
         addButton.setFont(new Font("Tahoma", Font.BOLD, 15));
         addButton.setFocusPainted(false);
         addButton.setBorder(null);
         
         addButton.addActionListener(new ActionListener() {
            //Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                
        
            }
        });
         NavigationPanel.add(addButton);
        
        //Delete Button
         deleteButton = new RoundedButton("Delete");
         deleteButton.setBounds(100, 75, 70, heightButton);
         deleteButton.setBackground(new Color(47, 120, 152));
         deleteButton.setForeground(new Color(220, 238, 229));
         deleteButton.setFont(new Font("Tahoma", Font.BOLD, 15));
         deleteButton.setFocusPainted(false);
         deleteButton.setBorder(null);
        
         deleteButton.addActionListener(new ActionListener() {
            //Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                
        
            }
        });
        NavigationPanel.add(deleteButton);

        //Update Button
        updateButton = new RoundedButton("Update");
        updateButton.setBounds(200, 75, 70, heightButton);
        updateButton.setBackground(new Color(47, 120, 152));
        updateButton.setForeground(new Color(220, 238, 229));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(null);
        
        updateButton.addActionListener(new ActionListener() {
           //Notification access successfully
           @Override
           public void actionPerformed(ActionEvent e) {
               
       
           }
        });

         NavigationPanel.add(updateButton);


         //Manage book content
       
        manageBook = new JLabel("Manage Books");
        manageBook.setFont(new Font("Tahoma", Font.BOLD, 30));
        manageBook.setForeground(DarkColor);
        manageBook.setBounds(250, 0, 600, 100);
        rightPanel.add(manageBook);
        
        //Table
        bookManageTablePanel = new JPanel();
        bookManageTablePanel.setBackground(LightColor);
        bookManageTablePanel.setBounds(100,150,500,150);
        bookManageTablePanel.setLayout(null);
        rightPanel.add(bookManageTablePanel);

        String[] columnManageTable = {"Book ID", "Name", "Author", "Quantity"};

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
