package com.src.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.src.view.menu.BookCard;
import com.src.view.menu.BookTable;
import com.src.view.menu.BorrowerCard;
import com.src.view.menu.BorrowerTable;
import com.src.view.menu.IssuedBookCard;


public class HomePage extends JFrame {
    private JPanel MainPanel;

    //Navigation
    private JPanel NavigationPanel;
    private JLabel AvataIcon;
    private JButton HomeButton;
    private JButton ManageBookButton;
    private JButton ManageBorrowerButton;
    private JButton IssueBookButton;
    private JButton ReturnBookButton;
    private JButton ViewRecordButton;
    private JButton ViewIssuedBookButton;
    private JButton LogOutButton;

    //Content
    private JPanel ContentPanel;
    //Header
    private JPanel HeaderPanel;
    private JLabel Title;
    private JLabel Owner;
    private JLabel Date;
    //Stat
    private JPanel StatPanel;
    //Book
    private JPanel BookPanel;
    private JLabel BookPanelTitle;
    private JLabel BookPanelIcon;
    private JLabel BookPanelStat;
    //Borrower
    private JPanel BorrowerPanel;
    private JLabel BorrowerPanelTitle;
    private JLabel BorrowerPanelIcon;
    private JLabel BorrowerPanelStat;
    //Issued
    private JPanel IssuedPanel;
    private JLabel IssuedPanelTitle;
    private JLabel IssuedPanelIcon;
    private JLabel IssuedPanelStat;

    //Table
    private JPanel TablePanel;
    private JPanel BookTablePanel;
    private JLabel BookTablePanelTitle;
    private JTable BookTable;
    private JPanel BorrowerTablePanel;
    private JLabel BorrowerTablePanelTitle;
    private JTable BorrowerTable;

    //Access database
    private BookCard bookCard;
    private BorrowerCard borrowerCard;
    private IssuedBookCard issuedBookCard;
    private BookTable booktable;
    private BorrowerTable borrowerTable;

    public HomePage() {
        setPreferredSize(new Dimension(1000, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents();
    }

    private void initComponents() {
        //Init access database
        bookCard = new BookCard();
        borrowerCard = new BorrowerCard();
        issuedBookCard = new IssuedBookCard();
        booktable = new BookTable();
        borrowerTable = new BorrowerTable();


        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220,238,229);
        int heightButton = 45;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        // Main Panel
        MainPanel = new JPanel();
        MainPanel.setBackground(Color.WHITE);
        MainPanel.setPreferredSize(new Dimension(1050, 640));
        MainPanel.setLayout(null);
        add(MainPanel);

        // Navigation Panel
        NavigationPanel = new JPanel();
        NavigationPanel.setBackground(new Color(5, 77, 120));
        NavigationPanel.setBounds(0,0,200,640);
        NavigationPanel.setLayout(null);
        MainPanel.add(NavigationPanel);

        ImageIcon originalAvataIcon = new ImageIcon(getClass().getResource("/com/res/HomeAvataIcon.png"));
        Image scaledAvataImage = originalAvataIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        AvataIcon = new JLabel(new ImageIcon(scaledAvataImage));
        AvataIcon.setBounds(-1,0,200, 200);
        NavigationPanel.add(AvataIcon);

        HomeButton = new JButton("Home page");
        HomeButton.setBackground(new Color(47, 120, 152));
        HomeButton.setForeground(new Color(220, 238, 229));
        HomeButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        HomeButton.setBounds(0, 200, 200, heightButton);
        HomeButton.setFocusPainted(false);
        HomeButton.setBorder(null);
        NavigationPanel.add(HomeButton);

        ManageBookButton = new NavButton("Manage books");
        ManageBookButton.setBounds(0,200 + heightButton, 200, heightButton);
        ManageBookButton.setFocusPainted(false);
        ManageBookButton.setBorder(null);
        NavigationPanel.add(ManageBookButton);

        ManageBorrowerButton = new NavButton("Manage borrowers");
        ManageBorrowerButton.setBounds(0,200 + heightButton*2, 200, heightButton);
        ManageBorrowerButton.setFocusPainted(false);
        ManageBorrowerButton.setBorder(null);
        NavigationPanel.add(ManageBorrowerButton);

        IssueBookButton = new NavButton("Issue books");
        IssueBookButton.setBounds(0,200 + heightButton*3, 200, heightButton);
        IssueBookButton.setFocusPainted(false);
        IssueBookButton.setBorder(null);
        NavigationPanel.add(IssueBookButton);

        ReturnBookButton = new NavButton("Return books");
        ReturnBookButton.setBounds(0,200 + heightButton*4, 200, heightButton);
        ReturnBookButton.setFocusPainted(false);
        ReturnBookButton.setBorder(null);
        NavigationPanel.add(ReturnBookButton);

        ViewRecordButton = new NavButton("View records");
        ViewRecordButton.setBounds(0,200 + heightButton*5, 200, heightButton);
        ViewRecordButton.setFocusPainted(false);
        ViewRecordButton.setBorder(null);
        NavigationPanel.add(ViewRecordButton);

        ViewIssuedBookButton = new NavButton("View issued books");
        ViewIssuedBookButton.setBounds(0,200 + heightButton*6, 200, heightButton);
        ViewIssuedBookButton.setFocusPainted(false);
        ViewIssuedBookButton.setBorder(null);
        NavigationPanel.add(ViewIssuedBookButton);

        LogOutButton = new NavButton("Log out");
        LogOutButton.setBounds(0,200 + heightButton*7, 200, heightButton);
        LogOutButton.setFocusPainted(false);
        LogOutButton.setBorder(null);
        NavigationPanel.add(LogOutButton);

        LogOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                setVisible(false);
            }
        });

        ContentPanel = new JPanel();
        ContentPanel.setBackground(Color.WHITE);
        ContentPanel.setBounds(200,0,800,640);
        ContentPanel.setLayout(null);
        MainPanel.add(ContentPanel);

        //Content Panel
        //Header
        HeaderPanel = new JPanel();
        HeaderPanel.setBackground(Color.WHITE);
        HeaderPanel.setBounds(0,0,800,150);
        HeaderPanel.setLayout(null);
        ContentPanel.add(HeaderPanel);

        Title = new JLabel("WELCOME TO LIBRARY");
        Title.setFont(new Font("Tahoma", Font.BOLD, 30));
        Title.setForeground(DarkColor);
        Title.setBounds(200, 40, 500, 30);
        HeaderPanel.add(Title);

        Owner = new JLabel("Owner: Admin");
        Owner.setFont(new Font("Tahoma", Font.BOLD, 27));
        Owner.setForeground(DarkColor);
        Owner.setBounds(40, 110, 250, 30);
        HeaderPanel.add(Owner);

        Date = new JLabel("Date: " + formattedDate);
        Date.setFont(new Font("Tahoma", Font.BOLD, 27));
        Date.setForeground(DarkColor);
        Date.setBounds(480, 110, 500, 30);
        HeaderPanel.add(Date);

        //Stat
        StatPanel = new JPanel();
        StatPanel.setBackground(Color.WHITE);
        StatPanel.setBounds(-2,180,800,160);
        StatPanel.setLayout(null);
        ContentPanel.add(StatPanel);

        BookPanel = new JPanel();
        BookPanel.setBackground(LightColor);
        BookPanel.setBounds(15,0,240,150);
        BookPanel.setLayout(null);
        StatPanel.add(BookPanel);

        /////////////////////////////////////////MENU-CARD/////////////////////////////////////////////
        //Card no of book


        BookPanelTitle = new JLabel("No. Of Books");
        BookPanelTitle.setOpaque(true);
        BookPanelTitle.setBackground(DarkColor);
        BookPanelTitle.setBounds(0,0,240,30);
        BookPanelTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        BookPanelTitle.setForeground(LightColor);
        BookPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        BookPanel.add(BookPanelTitle);



        ImageIcon BookIcon = new ImageIcon(getClass().getResource("/com/res/BookIcon.png"));
        Image scaledBookImage = BookIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        BookPanelIcon = new JLabel(new ImageIcon(scaledBookImage));
        BookPanelIcon.setBounds(0,30,120,120);
        BookPanel.add(BookPanelIcon);

        //Get value no of book
       
        BookPanelStat = new JLabel(bookCard.showupDB());
        BookPanelStat.setFont(new Font("Tahoma", Font.BOLD, 60));
        BookPanelStat.setForeground(DarkColor);
        BookPanelStat.setBounds(120, 30, 120, 120);
        BookPanelStat.setHorizontalAlignment(SwingConstants.CENTER);
        BookPanel.add(BookPanelStat);

        ////////////////////////////////////////////////////////////////////////
        //Borrower 
        BorrowerPanel = new JPanel();
        BorrowerPanel.setBackground(LightColor);
        BorrowerPanel.setBounds(275,0,240,150);
        BorrowerPanel.setLayout(null);
        StatPanel.add(BorrowerPanel);

        BorrowerPanelTitle = new JLabel("No. Of Borrowers");
        BorrowerPanelTitle.setOpaque(true);
        BorrowerPanelTitle.setBackground(DarkColor);
        BorrowerPanelTitle.setBounds(0,0,240,30);
        BorrowerPanelTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        BorrowerPanelTitle.setForeground(LightColor);
        BorrowerPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        BorrowerPanel.add(BorrowerPanelTitle);

        ImageIcon BorrowerIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerIcon.png"));
        Image scaledBorrowerImage = BorrowerIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        BorrowerPanelIcon = new JLabel(new ImageIcon(scaledBorrowerImage));
        BorrowerPanelIcon.setBounds(0,30,120,120);
        BorrowerPanel.add(BorrowerPanelIcon);

        BorrowerPanelStat = new JLabel(borrowerCard.showupDB());
        BorrowerPanelStat.setFont(new Font("Tahoma", Font.BOLD, 60));
        BorrowerPanelStat.setForeground(DarkColor);
        BorrowerPanelStat.setBounds(120, 30, 120, 120);
        BorrowerPanelStat.setHorizontalAlignment(SwingConstants.CENTER);
        BorrowerPanel.add(BorrowerPanelStat);

        ///////////////////////////////////////////////////////////////////////////////////
        //Issue book
        IssuedPanel = new JPanel();
        IssuedPanel.setBackground(LightColor);
        IssuedPanel.setBounds(535,0,240,150);
        IssuedPanel.setLayout(null);
        StatPanel.add(IssuedPanel);

        IssuedPanelTitle = new JLabel("No. Of Issued");
        IssuedPanelTitle.setOpaque(true);
        IssuedPanelTitle.setBackground(DarkColor);
        IssuedPanelTitle.setBounds(0,0,240,30);
        IssuedPanelTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        IssuedPanelTitle.setForeground(LightColor);
        IssuedPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        IssuedPanel.add(IssuedPanelTitle);

        ImageIcon IssuedIcon = new ImageIcon(getClass().getResource("/com/res/IssuedIcon.png"));
        Image scaledIssuedImage = IssuedIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        IssuedPanelIcon = new JLabel(new ImageIcon(scaledIssuedImage));
        IssuedPanelIcon.setBounds(0,30,120,120);
        IssuedPanel.add(IssuedPanelIcon);

        IssuedPanelStat = new JLabel(issuedBookCard.showupDB());
        IssuedPanelStat.setFont(new Font("Tahoma", Font.BOLD, 60));
        IssuedPanelStat.setForeground(DarkColor);
        IssuedPanelStat.setBounds(120, 30, 120, 120);
        IssuedPanelStat.setHorizontalAlignment(SwingConstants.CENTER);
        IssuedPanel.add(IssuedPanelStat);
        //////////////////////////////////////////////////////////////////////////////////////////////
    
        TablePanel = new JPanel();
        TablePanel.setBackground(Color.WHITE);
        TablePanel.setBounds(-2,355,800,220);
        TablePanel.setLayout(null);
        ContentPanel.add(TablePanel);

        BookTablePanel = new JPanel();
        BookTablePanel.setBackground(LightColor);
        BookTablePanel.setBounds(15,0,365,210);
        BookTablePanel.setLayout(null);
        TablePanel.add(BookTablePanel);

        BookTablePanelTitle = new JLabel("Books Details");
        BookTablePanelTitle.setOpaque(true);
        BookTablePanelTitle.setBackground(DarkColor);
        BookTablePanelTitle.setBounds(0,0,365,30);
        BookTablePanelTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        BookTablePanelTitle.setForeground(LightColor);
        BookTablePanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        BookTablePanel.add(BookTablePanelTitle);

        String[] columnBookTable = {"ID", "Title", "Author", "Year"};

        Object[][] dataBookTable = {
                {"1", "Book 1", "Author 1", "2020"},
                {"2", "Book 2", "Author 2", "2022"},
                {"3", "Book 3", "Author 3", "2024"},
                {"4", "Book 4", "Author 4", "2020"},
                {"5", "Book 5", "Author 5", "2020"},
                {"6", "Book 6", "Author 6", "2022"},
                {"7", "Book 7", "Author 7", "2024"},
                {"8", "Book 8", "Author 8", "2020"},
                {"9", "Book 9", "Author 9", "2020"},
                {"10", "Book 10", "Author 10", "2022"}

        };

        DefaultTableModel modelBookTable = new DefaultTableModel(dataBookTable, columnBookTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        BookTable = new JTable(modelBookTable);
        
        BookTable = new JTable(modelBookTable);
        BookTable.setBackground(LightColor);
        BookTable.setForeground(DarkColor);
        booktable.loadBookData(BookTable);
        

        JTableHeader headerBookTable = BookTable.getTableHeader();
        headerBookTable.setOpaque(false);
        headerBookTable.setBackground(new Color(47, 120, 152));
        headerBookTable.setForeground(LightColor);

        JScrollPane scrollBookTablePane = new JScrollPane(BookTable);
        scrollBookTablePane.setBounds(0, 30, 365, 180);
        scrollBookTablePane.getViewport().setOpaque(false);
        scrollBookTablePane.setOpaque(false);
        BookTablePanel.add(scrollBookTablePane);

        BorrowerTablePanel = new JPanel();
        BorrowerTablePanel.setBackground(LightColor);
        BorrowerTablePanel.setBounds(410,0,365,210);
        BorrowerTablePanel.setLayout(null);
        TablePanel.add(BorrowerTablePanel);

        BorrowerTablePanelTitle = new JLabel("Borrowers Details");
        BorrowerTablePanelTitle.setOpaque(true);
        BorrowerTablePanelTitle.setBackground(DarkColor);
        BorrowerTablePanelTitle.setBounds(0,0,365,30);
        BorrowerTablePanelTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        BorrowerTablePanelTitle.setForeground(LightColor);
        BorrowerTablePanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        BorrowerTablePanel.add(BorrowerTablePanelTitle);

        String[] columnBorrowerTable = {"ID", "Name", "Email", "Phone Number"};

        Object[][] dataBorrowerTable = {
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

        DefaultTableModel modelBorrowerTable = new DefaultTableModel(dataBorrowerTable, columnBorrowerTable){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        BorrowerTable = new JTable(modelBorrowerTable);
        BorrowerTable.setBackground(LightColor);
        BorrowerTable.setForeground(DarkColor);
        borrowerTable.loadBorrowerData(BorrowerTable);

        JTableHeader headerBorrowerTable = BorrowerTable.getTableHeader();
        headerBorrowerTable.setOpaque(false);
        headerBorrowerTable.setBackground(new Color(47, 120, 152));
        headerBorrowerTable.setForeground(LightColor);

        JScrollPane scrollBorrowerTablePane = new JScrollPane(BorrowerTable);
        scrollBorrowerTablePane.setBounds(0, 30, 365, 180);
        scrollBorrowerTablePane.getViewport().setOpaque(false);
        scrollBorrowerTablePane.setOpaque(false);
        BorrowerTablePanel.add(scrollBorrowerTablePane);
    }

    class NavButton extends JButton {
        private Color normalColor = new Color(5, 77, 120);
        private Color hoverColor = new Color(0, 0, 0, 50);

        private boolean isHovered = false;

        public NavButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setForeground(new Color(220, 238, 229));
            setFont(new Font("Tahoma", Font.BOLD, 15));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(normalColor);
            g2.fillRect(0, 0, getWidth(), getHeight());

            if (isHovered) {
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            super.paintComponent(g);
        }
    }

    

}   