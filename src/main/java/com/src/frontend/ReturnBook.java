package com.src.frontend;


import javax.swing.*;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;


public class ReturnBook extends JFrame {
    public ReturnBook() {
        setPreferredSize(new Dimension(1100, 640));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
       
        initComponents();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
       
    private void initComponents(){
        Color DarkColor = new Color(5, 77, 120);
        Color LightColor = new Color(220,238,229);


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);


         // Main Panel
         JPanel mainPanel = new JPanel();
         mainPanel.setBackground(Color.WHITE);
         mainPanel.setPreferredSize(new Dimension(1100, 640));
         mainPanel.setLayout(null);
         add(mainPanel);


        // Navigation Panel (Left)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(5, 77, 120));
        leftPanel.setBounds(0, 0, 300, 640);
        leftPanel.setLayout(null);
        mainPanel.add(leftPanel);
       
        // Back Button
        JButton backButton = new CustomButton("Back", true);
        backButton.setBounds(10, 10, 280, 45);
        backButton.setBackground(new Color(47, 120, 152));
        backButton.setForeground(new Color(220, 238, 229));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        leftPanel.add(backButton);
        backButton.addActionListener(e -> {
            new HomePage();
            setVisible(false);
        });
       
        // Return Information Panel (Left Form)
        JPanel returnPanel = new JPanel();
        returnPanel.setBackground(new Color(5, 77, 120));
        returnPanel.setBounds(0, 60, 300, 580);
        returnPanel.setLayout(null);
        leftPanel.add(returnPanel);
       
        JLabel dateLabel = new JLabel("Date: " + formattedDate);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        dateLabel.setForeground(DarkColor);
        dateLabel.setBounds(20,30,200,25);
        returnPanel.add(dateLabel);


        JLabel bookIdLabel = new JLabel("Enter Book ID:");
        bookIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        bookIdLabel.setForeground(LightColor);
        bookIdLabel.setBounds(20, 80, 200, 25);
        returnPanel.add(bookIdLabel);
       
        JTextField bookIdField = new JTextField();
        bookIdField.setBounds(20, 120, 260, 30);
        returnPanel.add(bookIdField);
       
        JLabel borrowerIdLabel = new JLabel("Enter Borrower ID:");
        borrowerIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        borrowerIdLabel.setForeground(LightColor);
        borrowerIdLabel.setBounds(20, 160, 200, 25);
        returnPanel.add(borrowerIdLabel);
       
        JTextField borrowerIdField = new JTextField();
        borrowerIdField.setBounds(20, 200, 260, 30);
        returnPanel.add(borrowerIdField);


        JButton checkButton = new CustomButton("Check Infomation", false);
        checkButton.setBounds(20, 250, 260, 40);
        checkButton.setBackground(new Color(47, 120, 152));
        checkButton.setForeground(new Color(220, 238, 229));
        checkButton.setFocusPainted(false);
        returnPanel.add(checkButton);


        JButton confirmButton = new CustomButton("Confirm Return", false);
        confirmButton.setBounds(20, 300, 260, 40);
        confirmButton.setBackground(new Color(47, 120, 152));
        confirmButton.setForeground(new Color(220, 238, 229));
        confirmButton.setFocusPainted(false);
       
        checkButton.addActionListener(e -> {
            String bookId = bookIdField.getText().trim();
            String borrowerId = borrowerIdField.getText().trim();


            if (bookId.isEmpty() || borrowerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Book ID and Student ID", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //DÀNH CHO GIANG
                //CHECK LOGIN BẰNG DATABASE Ở ĐÂY
                returnPanel.add(confirmButton);
                returnPanel.revalidate();
                returnPanel.repaint();
            }
        });
       


        // Issue Information (Right Panel)
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(300, 0, 800, 640);
        rightPanel.setBackground(new Color(220, 235, 220));
        rightPanel.setLayout(null);
        mainPanel.add(rightPanel);


        JLabel rightPanelTitle = new JLabel("Issue Details", SwingConstants.CENTER);
        rightPanelTitle.setForeground(DarkColor);
        rightPanelTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        rightPanelTitle.setBounds(0, 20, 800, 32);
        rightPanel.add(rightPanelTitle);


        JLabel issueIDLabel = new JLabel("Issue ID: ");
        issueIDLabel.setForeground(DarkColor);
        issueIDLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        issueIDLabel.setBounds(20, 80, 300, 25);
        rightPanel.add(issueIDLabel);


        JLabel borrowerNameLabel = new JLabel("Borrower Name: ");
        borrowerNameLabel.setForeground(DarkColor);
        borrowerNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        borrowerNameLabel.setBounds(20, 140, 300, 25);
        rightPanel.add(borrowerNameLabel);


        JLabel bookNameLabel = new JLabel("Book Name: ");
        bookNameLabel.setForeground(DarkColor);
        bookNameLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        bookNameLabel.setBounds(20, 200, 300, 25);
        rightPanel.add(bookNameLabel);


        JLabel issueDateLabel = new JLabel("Issue Date: ");
        issueDateLabel.setForeground(DarkColor);
        issueDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        issueDateLabel.setBounds(20, 260, 300, 25);
        rightPanel.add(issueDateLabel);


        JLabel dueDateLabel = new JLabel("Due Date: ");
        dueDateLabel.setForeground(DarkColor);
        dueDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        dueDateLabel.setBounds(20, 320, 300, 25);
        rightPanel.add(dueDateLabel);


        JLabel returnDateLabel = new JLabel("Return Date: ");
        returnDateLabel.setForeground(DarkColor);
        returnDateLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        returnDateLabel.setBounds(20, 380, 300, 25);
        rightPanel.add(returnDateLabel);


        JLabel daysOverDue = new JLabel("Days Overdue: ");
        daysOverDue.setForeground(DarkColor);
        daysOverDue.setFont(new Font("Verdana", Font.BOLD, 18));
        daysOverDue.setBounds(20, 440, 300, 25);
        rightPanel.add(daysOverDue);


        JLabel fineAmountLabel = new JLabel("Fine Amount: ");
        fineAmountLabel.setForeground(DarkColor);
        fineAmountLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        fineAmountLabel.setBounds(20, 500, 300, 25);
        rightPanel.add(fineAmountLabel);


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



