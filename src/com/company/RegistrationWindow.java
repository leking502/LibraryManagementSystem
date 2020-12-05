package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationWindow {
    private JPanel panel1;
    private JButton registrationButton;
    private JTextField userName;
    private JTextField userPassword;
    private JButton 返回Button;
    private static JFrame frame;

    public RegistrationWindow() {
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = userName.getText();
                String password = userPassword.getText();
                if(account.trim() == "" || password.trim() == "" )
                    return;
                UserData userData = Data.FindUser(account);
                if(userData != null){
                    JOptionPane.showMessageDialog(null,"该账号已存在");
                    return;
                }
                JOptionPane.showMessageDialog(null,"注册成功");
                Data.InsUserData(account,password,"用户");
                Data.SetMainUser(account);
                MainWindow mainWindow = new MainWindow();
                mainWindow.ShowWindow();
                Dispose();
            }
        });
        返回Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.ShowWindow();
                Dispose();
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
