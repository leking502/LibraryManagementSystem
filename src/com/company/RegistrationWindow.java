package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Objects;

public class RegistrationWindow {
    private JPanel panel1;
    private JButton registrationButton;
    private JTextField userName;
    private JTextField userPassword;
    private static JFrame frame;

    public RegistrationWindow() {
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userName.getText();
                String password = userPassword.getText();
                if(account.trim() == "" || password.trim() == "")
                    return;
                Data.AddUserData(account,password,"用户");
                Dispose();
                MainWindow mainWindow = new MainWindow();
                mainWindow.ShowWindow();
            }
        });
    }
    public void ShowWindow() {
        JFrame frame = new JFrame("图书管理系统");
        panel1.setSize(100,100);
        frame.setContentPane(new RegistrationWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(800,400,450,300);
        frame.setResizable(false);
        frame.setVisible(true);
        this.frame = frame;
    }
    private void Dispose(){
        frame.dispose();
    }
}
