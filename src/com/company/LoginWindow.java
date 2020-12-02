package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginWindow {
    private JButton button;
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public LoginWindow() {
        button.addActionListener(new OnButtonClick());
    }
    class OnButtonClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String account = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());

        }
    }
    public void ShowWindow() {
        JFrame frame = new JFrame("图书管理系统");
        System.out.println(button);
        panel1.setSize(100,100);
        frame.setContentPane(new LoginWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(800,400,450,300);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
