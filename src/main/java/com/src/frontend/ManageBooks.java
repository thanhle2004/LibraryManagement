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
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bookManageTablePanel;
    private JPanel authorManageTablePanel;
    private JPanel emDash;

   //Icon
    private JLabel iconID;
    private JLabel iconBook;
    private JLabel iconAuthor;
    private JLabel iconCategory;
    private JLabel bookID;
    private JLabel bookName;
    private JLabel authorName;
    private JLabel Quantity;
    private JLabel Category;
    private JLabel searchIcon;

    private JLabel authorID;
    private JLabel authorGender;
    private JLabel authorBirth_Year;

    //Content
    private JLabel manageBook;
   
    //Button
    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton authorButton;
    private JButton bookButton;

    //Field
    private JTextField Field;

    private JTable manageTable;

    private int tableY = 250;

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
    
            //left panel
             leftPanel = new JPanel();
             leftPanel.setBackground(DarkColor);
             leftPanel.setBounds(0,0,300,640);
             leftPanel.setLayout(null);
             mainPanel.add(leftPanel);

    
    
             //Right Panel
            rightPanel = new JPanel();
            rightPanel.setBackground(Color.white);
            rightPanel.setBounds(300,0,800,640);
            rightPanel.setLayout(null);
            mainPanel.add(rightPanel);


               //backButton
            backButton = new JButton("Back");
            backButton.setBounds(-1, 0, 150, 30);
            backButton.setBackground(new Color(47, 120, 152));
            backButton.setForeground(new Color(220, 238, 229));
            backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
            backButton.setFocusPainted(false);
            backButton.setBorder(null);
            leftPanel.add(backButton);
      
            backButton.addActionListener(new ActionListener() {
                  //Notification access successfully
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      new HomePage();
                      setVisible(false);
                  }
            });
              
      
            //BookID
            bookID = new JLabel("Enter Book ID:");
            bookID.setFont(new Font("Tahoma", Font.BOLD, 15));
            bookID.setForeground(new Color(255, 255, 255));
            bookID.setBounds(75, 75, 500, 15);
            leftPanel.add(bookID);
      
            //BookID Field
            leftPanel.add(field(75, 100, 200, 45));
      
            //BookID icon
            ImageIcon id = new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png"));
            Image scaledIDIcon = id.getImage().getScaledInstance(85,65, Image.SCALE_SMOOTH);
            iconID = new JLabel(new ImageIcon(scaledIDIcon));
            iconID.setBounds(15, 100, 45, 45);
            leftPanel.add(iconID);
      
      
      
            //Book Name
            bookName = new JLabel("Enter Book Name:");
            bookName.setFont(new Font("Tahoma", Font.BOLD, 15));
            bookName.setForeground(new Color(255, 255, 255));
            bookName.setBounds(75, 175, 500, 15);
            leftPanel.add(bookName);
      
            //Book name field
            leftPanel.add(field(75, 200, 200, 45));
      
            //Book name Icon
      
            ImageIcon book = new ImageIcon(getClass().getResource("/com/res/BookNameIcon.png"));
            Image scaledBookIcon = book.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconBook = new JLabel(new ImageIcon(scaledBookIcon));
            iconBook.setBounds(15, 200, 45, 45);
            leftPanel.add(iconBook);
      
            //Author Name
            authorName = new JLabel("Enter Author Name:");
            authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorName.setForeground(new Color(255, 255, 255));
            authorName.setBounds(75, 275, 500, 15);
            leftPanel.add(authorName);
      
      
            //Author Name Field
            leftPanel.add(field(75, 300, 200, 45));
      
      
            //Author Icon
            ImageIcon author = new ImageIcon(getClass().getResource("/com/res/IconAuthor.png"));
            Image scaledAuthorIcon = author.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconAuthor = new JLabel(new ImageIcon(scaledAuthorIcon));
            iconAuthor.setBounds(15, 300, 45, 45);
            leftPanel.add(iconAuthor);
      
      
            //Category
            Category = new JLabel("Enter Category:");
            Category.setFont(new Font("Tahoma", Font.BOLD, 15));
            Category.setForeground(new Color(255, 255, 255));
            Category.setBounds(75, 375, 500, 15);
            leftPanel.add(Category);
      
            //Category Field
            leftPanel.add(field(75, 400, 200, 45));
      
      
            //////////Category Icon////////
            ImageIcon category = new ImageIcon(getClass().getResource("/com/res/IconQuantity.png"));
            Image scaledCategoryIcon = category.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconCategory = new JLabel(new ImageIcon(scaledCategoryIcon));
            iconCategory.setBounds(15, 400, 45, 45);
            leftPanel.add(iconCategory);
      
            //////////Add Button////////
            addButton = new RoundedButton("Add");
            addButton.setBounds(15, 500, 60, heightButton);
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
            leftPanel.add(addButton);
              
            //////////Delete Button////////
            deleteButton = new RoundedButton("Delete");
            deleteButton.setBounds(110, 500, 70, heightButton);
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
            leftPanel.add(deleteButton);
      
            //////////Update Button////////
            updateButton = new RoundedButton("Update");
            updateButton.setBounds(215, 500, 70, heightButton);
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
      
            leftPanel.add(updateButton);
            
            
            ////////Searching///////
            Field = new JTextField();
            Field.setBounds(250,100,200,35);
            Field.setBackground(LightColor);
            Field.setForeground(new Color(5, 77, 120));
            Field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(DarkColor, 3),
                    new EmptyBorder(5, 10, 5, 10)
            ));
            rightPanel.add(Field);
    
            //Icon//
            ImageIcon search = new ImageIcon(getClass().getResource("/com/res/SearchIcon.png"));
            Image scaledSearchIcon = search.getImage().getScaledInstance(45, 35, Image.SCALE_SMOOTH);
            searchIcon = new JLabel(new ImageIcon(scaledSearchIcon));
            searchIcon.setBounds(200, 100, 45, 35);
            rightPanel.add(searchIcon);
    
           
    
            ////////Search Button/////////
            searchButton = new JButton("Search");
            searchButton.setBounds(475, 100, 60, 35);
            searchButton.setBackground(LightColor);
            searchButton.setForeground(DarkColor);
            searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
            searchButton.setFocusPainted(false);
            searchButton.setBorder(new LineBorder(DarkColor, 3));
             
            searchButton.addActionListener(new ActionListener() {
                //Notification access successfully
                @Override
                public void actionPerformed(ActionEvent e) {
                    
            
                }
            });
            rightPanel.add(searchButton);
    
    
             //Content Panel
             //Manage Book//
           
            manageBook = new JLabel("Manage Books");
            manageBook.setFont(new Font("Tahoma", Font.BOLD, 30));
            manageBook.setForeground(DarkColor);
            manageBook.setBounds(250, 0, 600, 100);
            rightPanel.add(manageBook);
    
            emDash = new JPanel();
            emDash.setBackground(DarkColor);
            emDash.setBounds(255,75,200,5);
            rightPanel.add(emDash);
    
            
            
            //////////Table//////////////
            bookManageTablePanel = new JPanel();
            bookManageTablePanel.setBackground(LightColor);
            bookManageTablePanel.setBounds(0,tableY,800,640);
            bookManageTablePanel.setLayout(null);
            rightPanel.add(bookManageTablePanel);
    
            String[] columnManageTable = {"Book ID", "Book Name", "Author", "Category"};
    
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
            scrollManageTablePanel.setBounds(0, 0, 700, 640);
            scrollManageTablePanel.getViewport().setOpaque(false);
            scrollManageTablePanel.setOpaque(false);
            bookManageTablePanel.add(scrollManageTablePanel, BorderLayout.SOUTH);
    
    
             /////Author Button///////
             authorButton = new JButton("Author");
             authorButton.setBounds(450, 175, 100, 55);
             authorButton.setBackground(LightColor);
             authorButton.setForeground(DarkColor);
             authorButton.setFont(new Font("Tahoma", Font.BOLD, 10));
             authorButton.setFocusPainted(false);
             authorButton.setBorder(new LineBorder(DarkColor, 3));
             rightPanel.add(authorButton);
             
             authorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   leftPanel.removeAll();
                   leftPanel.repaint();
                   leftPanel.add(createAuthorPanel());
                }
            });
             
            

             ////Book Name Button///
             bookButton = new JButton("Book");
             bookButton.setBounds(175,175,100, 55);
             bookButton.setBackground(LightColor);
             bookButton.setForeground(DarkColor);
             bookButton.setFont(new Font("Tahoma", Font.BOLD, 10));
             bookButton.setFocusPainted(false);
             bookButton.setBorder(new LineBorder(DarkColor, 3));
             rightPanel.add(bookButton);

             bookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    leftPanel.removeAll();
                    leftPanel.repaint();
                    leftPanel.add(createBookPanel());
                }
            });


        }

        ///////////Author Panel////////////
        private JPanel createAuthorPanel(){

            int heightButton = 45;
    
          
    
             // Main Panel
            mainPanel = new JPanel();
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setPreferredSize(new Dimension(1050, 640));
            mainPanel.setLayout(null);
            add(mainPanel);
    
    
            //left panel
            leftPanel = new JPanel();
            leftPanel.setBackground(DarkColor);
            leftPanel.setBounds(0,0,300,640);
            leftPanel.setLayout(null);
            mainPanel.add(leftPanel);


             //backButton
            backButton = new JButton("Back");
            backButton.setBounds(-1, 0, 150, 30);
            backButton.setBackground(new Color(47, 120, 152));
            backButton.setForeground(new Color(220, 238, 229));
            backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
            backButton.setFocusPainted(false);
            backButton.setBorder(null);
            leftPanel.add(backButton);
      
            backButton.addActionListener(new ActionListener() {
                  //Notification access successfully
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      new HomePage();
                      setVisible(false);
                  }
            });



            //////////Add Button////////
            addButton = new RoundedButton("Add");
            addButton.setBounds(15, 500, 60, heightButton);
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
            leftPanel.add(addButton);


            //////////Delete Button////////
            deleteButton = new RoundedButton("Delete");
            deleteButton.setBounds(110, 500, 70, heightButton);
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
           leftPanel.add(deleteButton);
  
           //////////Update Button////////
            updateButton = new RoundedButton("Update");
            updateButton.setBounds(215, 500, 70, heightButton);
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
  
            leftPanel.add(updateButton);


            //Author ID
            authorID = new JLabel("Enter Author ID:");
            authorID.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorID.setForeground(new Color(255, 255, 255));
            authorID.setBounds(75, 75, 500, 15);
            leftPanel.add(authorID);

            //Author ID Field
            leftPanel.add(field(75, 100, 200, 45));


            //Author Name
            authorName = new JLabel("Enter Author Name:");
            authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorName.setForeground(new Color(255, 255, 255));
            authorName.setBounds(75, 175, 500, 15);
            leftPanel.add(authorName);
      
      
            //Author Name Field
            leftPanel.add(field(75, 200, 200, 45));


            //Author Birth_Year
            authorBirth_Year = new JLabel("Enter Author Birth Year:");
            authorBirth_Year.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorBirth_Year.setForeground(new Color(255, 255, 255));
            authorBirth_Year.setBounds(75, 275, 500, 15);
            leftPanel.add(authorBirth_Year);
    
    
            //Author Birth Year Field
            leftPanel.add(field(75, 300, 200, 45));


            //Author Gender
            authorGender = new JLabel("Enter Author Gender:");
            authorGender.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorGender.setForeground(new Color(255, 255, 255));
            authorGender.setBounds(75, 375, 500, 15);
            leftPanel.add(authorGender);
     
             //Author Gender Field
            leftPanel.add(field(75, 400, 200, 45));





             //////////Table//////////////
             authorManageTablePanel = new JPanel();
             authorManageTablePanel.setBackground(LightColor);
             authorManageTablePanel.setBounds(0,tableY,800,640);
             authorManageTablePanel.setLayout(null);
             rightPanel.add(authorManageTablePanel);
     
             String[] columnManageTable = {"Author ID", "Author Name", "Author Birth Year", "Author Gender"};
     
             Object[][] dataManageTable = {
     
                     {"a", "Name 1", "2001", "M"},
                     {"b", "Name 2", "2002", "F"},
                     {"c", "Name 3", "2003", "M"},
                     {"d", "Name 4", "2004", "F"},
                     {"e", "Name 5", "2005", "M"},
                     {"f", "Name 6", "2006", "F"},
                     {"g", "Name 7", "2007", "F"},
                     {"h", "Name 8", "2008", "M"},
                     {"j", "Name 9", "2009", "F"},
                     {"k", "Name 10", "2010", "M"},
     
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
             scrollManageTablePanel.setBounds(0, 0, 700, 640);
             scrollManageTablePanel.getViewport().setOpaque(false);
             scrollManageTablePanel.setOpaque(false);
             authorManageTablePanel.add(scrollManageTablePanel, BorderLayout.SOUTH);


            return leftPanel;
        }


        //////////Book Panel///////////
        private JPanel createBookPanel(){
            int heightButton = 45;
    
          
    
             // Main Panel
             mainPanel = new JPanel();
             mainPanel.setBackground(Color.WHITE);
             mainPanel.setPreferredSize(new Dimension(1050, 640));
             mainPanel.setLayout(null);
             add(mainPanel);
    
    
            //left panel
            leftPanel = new JPanel();
            leftPanel.setBackground(DarkColor);
            leftPanel.setBounds(0,0,300,640);
            leftPanel.setLayout(null);
            mainPanel.add(leftPanel);


            //backButton
            backButton = new JButton("Back");
            backButton.setBounds(-1, 0, 150, 30);
            backButton.setBackground(new Color(47, 120, 152));
            backButton.setForeground(new Color(220, 238, 229));
            backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
            backButton.setFocusPainted(false);
            backButton.setBorder(null);
            leftPanel.add(backButton);
     
            backButton.addActionListener(new ActionListener() {
                 //Notification access successfully
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     new HomePage();
                     setVisible(false);
                 }
            });
             
     
            //BookID
            bookID = new JLabel("Enter Book ID:");
            bookID.setFont(new Font("Tahoma", Font.BOLD, 15));
            bookID.setForeground(new Color(255, 255, 255));
            bookID.setBounds(75, 75, 500, 15);
            leftPanel.add(bookID);
     
             //BookID Field
            leftPanel.add(field(75, 100, 200, 45));
     
            //BookID icon
            ImageIcon id = new ImageIcon(getClass().getResource("/com/res/BookIDIcon.png"));
            Image scaledIDIcon = id.getImage().getScaledInstance(85,65, Image.SCALE_SMOOTH);
            iconID = new JLabel(new ImageIcon(scaledIDIcon));
            iconID.setBounds(15, 100, 45, 45);
            leftPanel.add(iconID);
     
     
     
             //Book Name
            bookName = new JLabel("Enter Book Name:");
            bookName.setFont(new Font("Tahoma", Font.BOLD, 15));
            bookName.setForeground(new Color(255, 255, 255));
            bookName.setBounds(75, 175, 500, 15);
            leftPanel.add(bookName);
     
             //Book name field
            leftPanel.add(field(75, 200, 200, 45));
     
             //Book name Icon
     
            ImageIcon book = new ImageIcon(getClass().getResource("/com/res/BookNameIcon.png"));
            Image scaledBookIcon = book.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconBook = new JLabel(new ImageIcon(scaledBookIcon));
            iconBook.setBounds(15, 200, 45, 45);
            leftPanel.add(iconBook);
     
             //Author Name
            authorName = new JLabel("Enter Author Name:");
            authorName.setFont(new Font("Tahoma", Font.BOLD, 15));
            authorName.setForeground(new Color(255, 255, 255));
            authorName.setBounds(75, 275, 500, 15);
            leftPanel.add(authorName);
     
     
            //Author Name Field
            leftPanel.add(field(75, 300, 200, 45));
     
     
             //Author Icon
            ImageIcon author = new ImageIcon(getClass().getResource("/com/res/IconAuthor.png"));
            Image scaledAuthorIcon = author.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconAuthor = new JLabel(new ImageIcon(scaledAuthorIcon));
            iconAuthor.setBounds(15, 300, 45, 45);
            leftPanel.add(iconAuthor);
     
     
            //Category
            Category = new JLabel("Enter Category:");
            Category.setFont(new Font("Tahoma", Font.BOLD, 15));
            Category.setForeground(new Color(255, 255, 255));
            Category.setBounds(75, 375, 500, 15);
            leftPanel.add(Category);
     
             //Category Field
            leftPanel.add(field(75, 400, 200, 45));
     
     
             //////////Category Icon////////
            ImageIcon category = new ImageIcon(getClass().getResource("/com/res/IconQuantity.png"));
            Image scaledCategoryIcon = category.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconCategory = new JLabel(new ImageIcon(scaledCategoryIcon));
            iconCategory.setBounds(15, 400, 45, 45);
            leftPanel.add(iconCategory);
     
             //////////Add Button////////
            addButton = new RoundedButton("Add");
            addButton.setBounds(15, 500, 60, heightButton);
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
            leftPanel.add(addButton);
             
            //////////Delete Button////////
            deleteButton = new RoundedButton("Delete");
            deleteButton.setBounds(110, 500, 70, heightButton);
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
            leftPanel.add(deleteButton);
     
             //////////Update Button////////
            updateButton = new RoundedButton("Update");
            updateButton.setBounds(215, 500, 70, heightButton);
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
     
            leftPanel.add(updateButton);


            //////////Table//////////////
            bookManageTablePanel = new JPanel();
            bookManageTablePanel.setBackground(LightColor);
            bookManageTablePanel.setBounds(0,tableY,800,640);
            bookManageTablePanel.setLayout(null);
            rightPanel.add(bookManageTablePanel);
    
            String[] columnManageTable = {"Book ID", "Book Name", "Author", "Category"};
    
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
            scrollManageTablePanel.setBounds(0, 0, 700, 640);
            scrollManageTablePanel.getViewport().setOpaque(false);
            scrollManageTablePanel.setOpaque(false);
            bookManageTablePanel.add(scrollManageTablePanel, BorderLayout.SOUTH);
           

            return leftPanel;
        }



        public Component field(int x, int y, int width, int height){
            Field = new JTextField();
            Field.setBounds(x, y, width, height);
            Field.setBackground(LightColor);
            Field.setForeground(new Color(5, 77, 120));
            Field.setBorder(BorderFactory.createCompoundBorder(
                     new LineBorder(new Color(5, 77, 120)),
                     new EmptyBorder(5, 10, 5, 10)
             ));
            return Field;
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
