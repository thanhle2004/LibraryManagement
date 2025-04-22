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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class ManageBorrower extends JFrame{
    
    

    private JPanel mainPanel;
    private JPanel NavigationPanel;
    private JPanel rightPanel;
    private JPanel borrowerTablePanel;
    private JPanel emDash;
    
    //Button
    private JButton backButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;





    private JLabel borrowerID;
    private JLabel borrowerName;
    private JLabel borrowerAge;
    private JLabel borrowerGender;
    private JLabel manageBorrower;

  
    //Icon
    private JLabel borrowerIdIcon;
    private JLabel borrowerNameIcon;
    private JLabel borrowerAgeIcon;
    private JLabel borrowerGenderIcon;
    private JLabel searchIcon;
    
    //Field
    private JTextField borrowerIDField;
    private JTextField borrowerNameField;
    private JTextField borrowerAgeField;
    private JTextField borrowerGenderField;
    private JTextField searchField;
    

    private JTable manageTable;

    Color DarkColor = new Color(5, 77, 120);
    Color LightColor = new Color(220,238,229);


    public ManageBorrower(){
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

       

        ////////// Main Panel////////
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1050, 640));
        mainPanel.setLayout(null);
        add(mainPanel);


       //////////Navigation////////
        NavigationPanel = new JPanel();
        NavigationPanel.setBackground(DarkColor);
        NavigationPanel.setBounds(0,0,300,640);
        NavigationPanel.setLayout(null);
        mainPanel.add(NavigationPanel);
        
        //////////Right Panel////////
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);
        rightPanel.setBounds(300,0,800,640);
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);


         //////////backButton////////
         backButton = new JButton("Back");
         backButton.setBounds(-1, 0, 150, 30);
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


        /////////Add Button///////
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
        NavigationPanel.add(addButton);


        ///////Delete Button//////////
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
       NavigationPanel.add(deleteButton);


       /////Update Button///////
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

        NavigationPanel.add(updateButton);


        /////Borrower ID///////
        borrowerID = new JLabel("Borrower ID:");
        borrowerID.setFont(new Font("Tahoma", Font.BOLD, 15));
        borrowerID.setForeground(new Color(255, 255, 255));
        borrowerID.setBounds(75, 75, 500, 15);
        NavigationPanel.add(borrowerID);

        ////Borrower ID Field/////
        borrowerIDField = new JTextField(15);
        borrowerIDField.setBounds(75, 100, 200, 45);
        borrowerIDField.setBackground(LightColor);
        borrowerIDField.setForeground(new Color(5, 77, 120));
        borrowerIDField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        NavigationPanel.add(borrowerIDField);

       ////Borrower ID Icon///
       ImageIcon originalBorrowerIcon = new ImageIcon(getClass().getResource("/com/res/ID.png"));
       Image scaledBorrowerIcon = originalBorrowerIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
       borrowerIdIcon = new JLabel(new ImageIcon(scaledBorrowerIcon));
       borrowerIdIcon.setBounds(15, 100, 45, 45);
       NavigationPanel.add(borrowerIdIcon);
       
       ///Borrower Name////
       
       borrowerName = new JLabel("Borrower Name:");
       borrowerName.setFont(new Font("Tahoma", Font.BOLD, 15));
       borrowerName.setForeground(new Color(255, 255, 255));
       borrowerName.setBounds(75, 175, 500, 15);
       NavigationPanel.add(borrowerName);

       ///Borrower Name Field///
       borrowerNameField = new JTextField(15);
       borrowerNameField.setBounds(75, 200, 200, 45);
       borrowerNameField.setBackground(LightColor);
       borrowerNameField.setForeground(new Color(5, 77, 120));
       borrowerNameField.setBorder(BorderFactory.createCompoundBorder(
               new LineBorder(new Color(5, 77, 120)),
               new EmptyBorder(5, 10, 5, 10)
       ));
       NavigationPanel.add(borrowerNameField);  

       ///Borrower Name Icon////
       ImageIcon originalNameIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerNameIcon.png"));
       Image scaledNameIcon = originalNameIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
       borrowerNameIcon = new JLabel(new ImageIcon(scaledNameIcon));
       borrowerNameIcon.setBounds(15, 200, 45, 45);
       NavigationPanel.add(borrowerNameIcon);

       //Borrower Age//
       borrowerAge = new JLabel("Borrower Age:");
       borrowerAge.setFont(new Font("Tahoma", Font.BOLD, 15) );
       borrowerAge.setForeground(new Color(255, 255, 255));
       borrowerAge.setBounds(75, 275, 500, 15);
       NavigationPanel.add(borrowerAge);

       borrowerAgeField = new JTextField(15);
       borrowerAgeField.setBounds(75, 300, 200, 45);
       borrowerAgeField.setBackground(LightColor);
       borrowerAgeField.setForeground(new Color(5, 77, 120));
       borrowerAgeField.setBorder(BorderFactory.createCompoundBorder(
               new LineBorder(new Color(5, 77, 120)),
               new EmptyBorder(5, 10, 5, 10)
       ));
       NavigationPanel.add(borrowerAgeField);

       //Age Icon//
       ImageIcon originaAgeIcon = new ImageIcon(getClass().getResource("/com/res/AgeIcon.png"));
       Image scaledAgeIcon = originaAgeIcon.getImage().getScaledInstance(45, 55, Image.SCALE_SMOOTH);
       borrowerAgeIcon = new JLabel(new ImageIcon(scaledAgeIcon));
       borrowerAgeIcon.setBounds(15, 300, 45, 55);
       NavigationPanel.add(borrowerAgeIcon);


       //Borrower Gender//
       borrowerGender = new JLabel("Borrower Gender:");
       borrowerGender.setFont(new Font("Tahoma", Font.BOLD, 15) );
       borrowerGender.setForeground(new Color(255, 255, 255));
       borrowerGender.setBounds(75, 375, 500, 15);
       NavigationPanel.add(borrowerGender);

       borrowerGenderField = new JTextField(15);
       borrowerGenderField.setBounds(75, 400, 200, 45);
       borrowerGenderField.setBackground(LightColor);
       borrowerGenderField.setForeground(new Color(5, 77, 120));
       borrowerGenderField.setBorder(BorderFactory.createCompoundBorder(
               new LineBorder(new Color(5, 77, 120)),
               new EmptyBorder(5, 10, 5, 10)
       ));
       NavigationPanel.add(borrowerGenderField);


       //Gender Icon//
       ImageIcon originaGenderIcon = new ImageIcon(getClass().getResource("/com/res/GenderIcon.png"));
       Image scaledGenderIcon = originaGenderIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
       borrowerGenderIcon = new JLabel(new ImageIcon(scaledGenderIcon));
       borrowerGenderIcon.setBounds(15, 400, 45, 45);
       NavigationPanel.add(borrowerGenderIcon);


       //Borrower book content//
       manageBorrower = new JLabel("Manage Borrower");
       manageBorrower.setFont(new Font("Tahoma", Font.BOLD, 30));
       manageBorrower.setForeground(DarkColor);
       manageBorrower.setBounds(220, 0, 600, 100);
       rightPanel.add(manageBorrower); 

       emDash = new JPanel();
       emDash.setBackground(DarkColor);
       emDash.setBounds(230,75,250,5);
       rightPanel.add(emDash); 




        ////////Searching///////
        searchField = new JTextField();
        searchField.setBounds(250,100,200,35);
        searchField.setBackground(LightColor);
        searchField.setForeground(new Color(5, 77, 120));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(DarkColor, 3),
                new EmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(searchField);

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
        

       //Table
       borrowerTablePanel = new JPanel();
       borrowerTablePanel.setBackground(LightColor);
       borrowerTablePanel.setBounds(0,300,800,640);
       borrowerTablePanel.setLayout(null);
       rightPanel.add(borrowerTablePanel);

       String[] columnManageTable = {"ID", "Name", "Age", "Gender"};

       Object[][] dataManageTable = {
 
         {"1", "Borrower 1", "18", "Male"},
         {"2", "Borrower 2", "19", "Female"},
         {"3", "Borrower 3", "20", "Male"},
         {"4", "Borrower 4", "18", "Male"},
         {"5", "Borrower 5", "19", "Female"},
         {"6", "Borrower 6", "20", "Male"},
         {"7", "Borrower 7", "18", "Male"},
         {"8", "Borrower 8", "19", "Female"},
         {"9", "Borrower 9", "20", "Male"},
         {"10", "Borrower 10", "18", "Female"},

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
