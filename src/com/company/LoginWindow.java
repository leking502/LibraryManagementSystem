package com.company;

import javax.swing.*;
import java.util.Objects;

public class LoginWindow {
    private JButton button;
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton regButton;
    private static JFrame frame;

    public LoginWindow() {
        button.addActionListener(e -> {
            String account = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());
            Data.SetMainUser(account);
            if(UserData.GetMainUserData() == null || !Objects.equals(UserData.GetMainUserData().GetUserPassword(), password)){
                JOptionPane.showMessageDialog(null,"账户或密码错误");
                return;
            }
            Dispose();
            MainWindow mainWindow = new MainWindow();
            mainWindow.ShowWindow();
        });
        regButton.addActionListener(e -> {
            Dispose();
            RegistrationWindow registrationWindow = new RegistrationWindow();
            registrationWindow.ShowWindow();
        });
    }
    public void ShowWindow() {
        JFrame frame = new JFrame("图书管理系统");
        panel1.setSize(100,100);
        frame.setContentPane(new LoginWindow().panel1);
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
