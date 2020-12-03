package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainWindow {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton sButton;
    private JTextField textField1;
    private JTabbedPane tabbedPane2;
    private JTable allBook;
    private JTable table2;
    private JTextField addBookName;
    private JTextField publicationTime;
    private JButton addButton;

    public MainWindow() {

        String head[]=new String[] {"书本编号", "书本名称", "借阅状态", "出版时间"};
        DefaultTableModel tableModel=new DefaultTableModel(BookData.GetBookTable(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
        allBook.setRowSorter(rowSorter);
        allBook.setModel(tableModel);
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookUpdata(textField1.getText().trim());
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addBookName.getText().trim() == "" || publicationTime.getText().trim() == ""){
                    return;
                }
                Data.AddBookData(addBookName.getText(),publicationTime.getText());
                JOptionPane.showMessageDialog(null,
                        "书名：" + addBookName.getText()+
                        "\n出版日期："+ publicationTime.getText()+
                        "\n添加成功");
            }
        });
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(800,400,450,300);
        frame.setVisible(true);
    }
    void BookUpdata(String bookName){
        String head[]=new String[] {"书本编号", "书本名称", "借阅状态", "出版时间"};
        List<Object[]> tragetBookData = new ArrayList<>();
        if(bookName != ""){
            for(int i = 0 ;  i < BookData.GetBookTable().length;i++){
                if(!BookData.GetBookTable()[i][1].toString().toLowerCase().contains(bookName.toLowerCase()))
                    continue;
                tragetBookData.add(BookData.GetBookTable()[i]);
            }
            if(tragetBookData.size() == 0){
                JOptionPane.showMessageDialog(null,"找不到符合的书");
                return;
            }
        }
        else {
            for(int i = 0 ;  i < BookData.GetBookTable().length;i++){
                tragetBookData.add(BookData.GetBookTable()[i]);
            }
        }
        Object[][] tabalData = tragetBookData.toArray(Object[][]::new);
        DefaultTableModel tableModel=new DefaultTableModel(tabalData,head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
        allBook.setRowSorter(rowSorter);
        allBook.setModel(tableModel);
    }
}
