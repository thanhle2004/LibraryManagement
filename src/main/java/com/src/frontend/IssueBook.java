package com.src.frontend;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class IssueBook extends JFrame {
    private JPanel navigationPanel, issuePanel, bookPanel, borrowerPanel, mainPanel;
    private JButton backButton, checkButton, issueButton;
    private JTextField bookIdField, borrowerIdField;
    private JFormattedTextField issueDateField, dueDateField;
    // Fields for Book Details
    private JTextField bookDetailIdField, bookNameField, typeField, authorField;
    // Fields for Student Details
    private JTextField borrowerDetailIdField, borrowerNameField, phoneNumberField, addressField;
   
    public IssueBook() {
        setPreferredSize(new Dimension(1100, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
       
        initComponents();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
   
    private void initComponents() {
        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220,238,229);


        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(1100, 640));
        mainPanel.setLayout(null);
        add(mainPanel);
       
        // Navigation Panel (Left)
        navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(5, 77, 120));
        navigationPanel.setBounds(0, 0, 300, 640);
        navigationPanel.setLayout(null);
        mainPanel.add(navigationPanel);
       
        // Back Button
        backButton = new CustomButton("Back", true);
        backButton.setBounds(10, 10, 280, 45);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        navigationPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });
       
        // Issue Book Panel (Left Form)
        issuePanel = new JPanel();
        issuePanel.setBackground(new Color(5, 77, 120));
        issuePanel.setBounds(0, 60, 300, 580);
        issuePanel.setLayout(null);
        navigationPanel.add(issuePanel);
       
        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setForeground(LightColor);
        bookIdLabel.setBounds(20, 30, 200, 25);
        issuePanel.add(bookIdLabel);
       
        bookIdField = new JTextField();
        bookIdField.setBounds(20, 60, 260, 30);
        issuePanel.add(bookIdField);
       
        JLabel borrowerIdLabel = new JLabel("Enter Borrower ID:");
        borrowerIdLabel.setForeground(LightColor);
        borrowerIdLabel.setBounds(20, 100, 200, 25);
        issuePanel.add(borrowerIdLabel);
       
        borrowerIdField = new JTextField();
        borrowerIdField.setBounds(20, 130, 260, 30);
        issuePanel.add(borrowerIdField);
       
        JLabel issueDateLabel = new JLabel("Issue Date:");
        issueDateLabel.setForeground(LightColor);
        issueDateLabel.setBounds(20, 170, 200, 25);
        issuePanel.add(issueDateLabel);
       
        issueDateField = new JFormattedTextField();
        issueDateField.setBounds(20, 200, 260, 30);
        issuePanel.add(issueDateField);
       
        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setForeground(LightColor);
        dueDateLabel.setBounds(20, 240, 200, 25);
        issuePanel.add(dueDateLabel);
       
        dueDateField = new JFormattedTextField();
        dueDateField.setBounds(20, 270, 260, 30);
        issuePanel.add(dueDateField);


        checkButton = new CustomButton("Check Infomation", false);
        checkButton.setBounds(20, 320, 260, 40);
        checkButton.setBackground(new Color(47, 120, 152));
        checkButton.setForeground(new Color(220, 238, 229));
        checkButton.setFocusPainted(false);
        issuePanel.add(checkButton);
       
        issueButton = new CustomButton("Issue Book", false);
        issueButton.setBounds(20, 370, 260, 40);
        issueButton.setBackground(new Color(47, 120, 152));
        issueButton.setForeground(new Color(220, 238, 229));
        issueButton.setFocusPainted(false);

        checkButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();
            String issueDate = issueDateField.getText().trim();
            String dueDate = dueDateField.getText().trim();


            if (bookId.isEmpty() || borrowerId.isEmpty() || issueDate.isEmpty() || dueDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all information", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //DÀNH CHO GIANG
                //CHECK LOGIN BẰNG DATABASE Ở ĐÂY
                issuePanel.add(issueButton);
                issuePanel.revalidate();
                issuePanel.repaint();
            }
        });
       
        // Book Details Panel (Middle)
        bookPanel = createPanel(new Color(220, 238, 229), 310, 0, 400, 640);
        bookPanel.setLayout(null);
        mainPanel.add(bookPanel);
       
        // Load the Book Icon and set it larger
        ImageIcon bookIcon = new ImageIcon(getClass().getResource("/com/res/IconBook.png"));
        Image scaledBookImage = bookIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        bookIcon = new ImageIcon(scaledBookImage);
       
        // Book Title with Icon: Icon on the left, text on the right
        JLabel bookIconLabel = new JLabel(bookIcon);
        bookIconLabel.setBounds(40, 7, 64, 64);
        bookPanel.add(bookIconLabel);
       
        JLabel bookTitle = new JLabel("Book Details", SwingConstants.LEFT);
        bookTitle.setForeground(DarkColor);
        bookTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        bookTitle.setBounds(110, 20, 300, 32);
        bookPanel.add(bookTitle);
       
        JLabel bookIdDetailLabel = new JLabel("Book ID:");
        bookIdDetailLabel.setForeground(DarkColor);
        bookIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookIdDetailLabel.setBounds(20, 80, 150, 25);
        bookPanel.add(bookIdDetailLabel);
       
        bookDetailIdField = new JTextField();
        bookDetailIdField.setBounds(20, 110, 360, 30);
        bookPanel.add(bookDetailIdField);
       
        JLabel bookNameDetailLabel = new JLabel("Book Name:");
        bookNameDetailLabel.setForeground(DarkColor);
        bookNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookNameDetailLabel.setBounds(20, 160, 150, 25);
        bookPanel.add(bookNameDetailLabel);
       
        bookNameField = new JTextField();
        bookNameField.setBounds(20, 190, 360, 30);
        bookPanel.add(bookNameField);
       
        JLabel bookTypeLabel = new JLabel("Type:");
        bookTypeLabel.setForeground(DarkColor);
        bookTypeLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookTypeLabel.setBounds(20, 240, 150, 25);
        bookPanel.add(bookTypeLabel);
       
        typeField = new JTextField();
        typeField.setBounds(20, 270, 360, 30);
        bookPanel.add(typeField);
       
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setForeground(DarkColor);
        authorLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        authorLabel.setBounds(20, 320, 150, 25);
        bookPanel.add(authorLabel);
       
        authorField = new JTextField();
        authorField.setBounds(20, 350, 360, 30);
        bookPanel.add(authorField);
       
        // Student Details Panel (Right)
        borrowerPanel = createPanel(new Color(220, 238, 229), 720, 0, 380, 640);
        borrowerPanel.setLayout(null);
        mainPanel.add(borrowerPanel);
       
        // Load the Student Icon from StudentDetail.png and set it larger (64x64)
        ImageIcon borrowerIcon = new ImageIcon(getClass().getResource("/com/res/BorrowerDetail.png"));
        Image scaledBorrowerImage = borrowerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        borrowerIcon = new ImageIcon(scaledBorrowerImage);
       
        // Student Title with Icon: Icon on the left, text on the right
        JLabel borrowerIconLabel = new JLabel(borrowerIcon);
        borrowerIconLabel.setBounds(20, 10, 64, 64);
        borrowerPanel.add(borrowerIconLabel);
       
        JLabel borrowerTitle = new JLabel("Borrower Details", SwingConstants.LEFT);
        borrowerTitle.setForeground(DarkColor);
        borrowerTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        borrowerTitle.setBounds(90, 20, 300, 32);
        borrowerPanel.add(borrowerTitle);
       
        JLabel borrowerIdDetailLabel = new JLabel("Borrower ID:");
        borrowerIdDetailLabel.setForeground(DarkColor);
        borrowerIdDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerIdDetailLabel.setBounds(20, 80, 150, 25);
        borrowerPanel.add(borrowerIdDetailLabel);
       
        borrowerDetailIdField = new JTextField();
        borrowerDetailIdField.setBounds(20, 110, 340, 30);
        borrowerPanel.add(borrowerDetailIdField);
       
        JLabel borrowerNameDetailLabel = new JLabel("Borrower Name:");
        borrowerNameDetailLabel.setForeground(DarkColor);
        borrowerNameDetailLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerNameDetailLabel.setBounds(20, 160, 200, 25);
        borrowerPanel.add(borrowerNameDetailLabel);
       
        borrowerNameField = new JTextField();
        borrowerNameField.setBounds(20, 190, 340, 30);
        borrowerPanel.add(borrowerNameField);
       
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setForeground(DarkColor);
        phoneNumberLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        phoneNumberLabel.setBounds(20, 240, 340, 25);
        borrowerPanel.add(phoneNumberLabel);
       
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(20, 270, 340, 30);
        borrowerPanel.add(phoneNumberField);
       
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(DarkColor);
        addressLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        addressLabel.setBounds(20, 320, 150, 25);
        borrowerPanel.add(addressLabel);
       
        addressField = new JTextField();
        addressField.setBounds(20, 350, 340, 30);
        borrowerPanel.add(addressField);
    }
   
    private JPanel createPanel(Color color, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        return panel;
    }
   
    class CustomButton extends JButton {
        private boolean rounded;
        private int radius = 10;
        private Color normalColor = new Color(47, 120, 152);
        private Color hoverColor = new Color(0, 0, 0, 50);
        private Color borderColor = new Color(5, 77, 120);


        public CustomButton(String text, boolean rounded) {
            super(text);
            this.rounded = rounded;
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFont(new Font("Tahoma", Font.BOLD, 15));
            setBackground(normalColor);


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


            g2.setColor(getBackground());


            if (rounded) {
                int arc = radius * 2;
                g2.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);
            } else {
                g2.fillRect(0, 0, width, height);
                g2.setColor(borderColor);
                g2.drawRect(0, 0, width - 1, height - 1);
            }


            g2.dispose();
            super.paintComponent(g);
        }
    }


}

