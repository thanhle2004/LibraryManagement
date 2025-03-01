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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.src.auth.Login;



public class LoginPage extends JFrame {
    private JPanel Panel1;
    private JPanel LeftPanel;
    private JPanel RightPanel;
    private JLabel Background;
    private JPanel TopRightPanel;
    private JPanel BottomRightPanel;
    private JLabel AvataIcon;
    private JLabel IconUser;
    private JTextField usernameField;
    private JLabel IconPassword;
    private JPasswordField passwordField;
    private JButton loginButton;


    public LoginPage() {
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
        // Main Panel
        Panel1 = new JPanel();
        Panel1.setBackground(Color.WHITE);
        Panel1.setPreferredSize(new Dimension(1050, 640));
        Panel1.setLayout(null);
        add(Panel1);

        // Left Panel (for Background Image)
        LeftPanel = new JPanel();
        LeftPanel.setBackground(Color.WHITE);
        LeftPanel.setBounds(0, 0, 710, 640);
        LeftPanel.setLayout(null);

        Background = new JLabel();
        Background.setIcon(new ImageIcon(getClass().getResource("/com/res/LeftPanel.png")));
        Background.setBounds(0, 0, 710, 640);
        LeftPanel.add(Background);
        Panel1.add(LeftPanel);

        // Right Panel (for login form)
        RightPanel = new JPanel();
        RightPanel.setBackground(Color.WHITE);
        RightPanel.setBounds(710, 0, 290, 640);
        RightPanel.setLayout(null);
        Panel1.add(RightPanel);

        // Top Panel (Username & Icon)
        TopRightPanel = new JPanel();
        TopRightPanel.setBounds(-5, -5, 290, 320);
        RightPanel.add(TopRightPanel);

        AvataIcon = new JLabel();
        AvataIcon.setIcon(new ImageIcon(getClass().getResource("/com/res/TopRightPanel.png")));
        AvataIcon.setBounds(0, 0, 290, 320);
        TopRightPanel.add(AvataIcon);

        // Bottom Panel (Login Form)
        BottomRightPanel = new JPanel();
        BottomRightPanel.setBackground(Color.WHITE);
        BottomRightPanel.setBounds(0, 320, 290, 320);
        BottomRightPanel.setLayout(null);

        // Username Field
        JPanel userPanel = new JPanel(null);
        userPanel.setBounds(19, 10, 240, 45);

        usernameField = new JTextField(15);
        usernameField.setBounds(45, 0, 195, 45);
        usernameField.setBackground(new Color(220, 238, 229));
        usernameField.setForeground(new Color(5, 77, 120));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));

        ImageIcon originalIconUser = new ImageIcon(getClass().getResource("/com/res/IconUser.png"));
        Image scaledImageUser = originalIconUser.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        IconUser = new JLabel(new ImageIcon(scaledImageUser));
        IconUser.setBounds(0, 0, 45, 45);
        IconUser.setBorder(new LineBorder(new Color(5, 77, 120)));

        userPanel.add(usernameField);
        userPanel.add(IconUser);
        BottomRightPanel.add(userPanel);

        // Password Field
        JPanel passwordPanel = new JPanel(null);
        passwordPanel.setBounds(19, 65, 240, 45);

        ImageIcon originalIconPassword = new ImageIcon(getClass().getResource("/com/res/IconPassword.png"));
        Image scaledImagePassword = originalIconPassword.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        IconPassword = new JLabel(new ImageIcon(scaledImagePassword));
        IconPassword.setBounds(0, 0, 45, 45);
        IconPassword.setBorder(new LineBorder(new Color(5, 77, 120)));

        passwordField = new JPasswordField(15);
        passwordField.setBounds(45, 0, 195, 45);
        passwordField.setBackground(new Color(220, 238, 229));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(5, 77, 120)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        passwordPanel.add(IconPassword);
        passwordPanel.add(passwordField);

        BottomRightPanel.add(passwordPanel);

        // Login Button
        loginButton = new RoundedButton("LOGIN");
        loginButton.setBounds(158, 120, 100, 35);
        loginButton.setBackground(new Color(220, 238, 229));
        loginButton.setForeground(new Color(5, 77, 120));
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));

        loginButton.addActionListener(new ActionListener() {
            //Notification access successfully
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage();
                setVisible(false);
            }
        });

        BottomRightPanel.add(loginButton);
        RightPanel.add(BottomRightPanel);
    }

    class RoundedButton extends JButton {
        private int radius = 10;
        private Color normalColor = new Color(220, 238, 229);
        private Color hoverColor = new Color(0, 0, 0, 50);
        private Color borderColor = new Color(5, 77, 120);

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
            int arc = radius * 2;

            Color currentColor = getBackground();
            g2.setColor(currentColor);
            g2.fillRoundRect(1, 1, width - 2, height - 2, arc, arc);
            g2.setColor(borderColor);
            g2.drawRoundRect(1, 1, width - 3, height - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }
    
}