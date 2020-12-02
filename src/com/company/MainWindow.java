package com.company;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainWindow {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton sButton;
    private JTextField textField1;
    private JTabbedPane tabbedPane2;
    private JTable allBook;
    private JTable table2;

    public MainWindow() {
        String head[]=new String[] {"书本编号", "书本名称", "借阅状态", "借出时间"};
        DefaultTableModel tableModel=new DefaultTableModel(BookData.GetBookTable(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        allBook.setModel(tableModel);
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(800,400,450,300);
        frame.setVisible(true);
    }
    void test(){
        new MainWindow();
    }

}
