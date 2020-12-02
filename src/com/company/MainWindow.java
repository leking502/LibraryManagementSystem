package com.company;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

public class MainWindow {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton sButton;
    private JTextField textField1;
    private JTabbedPane tabbedPane2;
    private JTable table1;
    private JTable table2;

    public MainWindow() {
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        MainWindow mainWindow = new MainWindow();
        mainWindow.ShowWindow();
    }

}
