package com.company;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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
    private JTable userTable;
    private JTextField textFieldUser;
    private JButton sUserButton;
    private JButton delUserButton;
    private JButton 借阅选定图书Button;
    private JTable tableBookM;
    private JTextField textFieldBookM;
    private JButton sButtonM;
    private JButton delBookButtonM;
    private Object tragetBookObject;
    private Object tragetBookObjectM;
    private Object tragetUserObject;

    public void UpdataAllTable(){
        BookTableUpdata("",allBook);
        UserTableUpdata("",userTable);
        BookTableUpdata("",tableBookM);
    }
    public  void UpdataAllBookTable(){
        BookTableUpdata("",tableBookM);
        BookTableUpdata("",allBook);
    }
    public MainWindow() {

        BookTableUpdata("",allBook);
        UserTableUpdata("",userTable);
        BookTableUpdata("",tableBookM);

        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookTableUpdata(textField1.getText().trim(),allBook);
                textField1.setText("");
            }
        });
        sUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTableUpdata(textFieldUser.getText().trim(),userTable);
                textFieldUser.setText("");
            }
        });
        sButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookTableUpdata(textFieldBookM.getText().trim(),tableBookM);
                textFieldBookM.setText("");
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
                UpdataAllTable();
            }
        });
        allBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = allBook.rowAtPoint(e.getPoint());
                tragetBookObject = allBook.getValueAt(row,0);
            }
        });
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = userTable.rowAtPoint(e.getPoint());
                tragetUserObject = userTable.getValueAt(row,0);

            }
        });
        delUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetUserObject == null){
                    return;
                }
                if(Objects.equals(tragetUserObject.toString(), UserData.GetMainUserData().GetMainUserName())){
                    JOptionPane.showMessageDialog(null,"你不可以删除自己");
                    return;
                }
                JOptionPane.showMessageDialog(null,"删除了账号:"+tragetUserObject.toString());
                Data.DelUserData(tragetUserObject.toString());
                UserTableUpdata("",userTable);
            }
        });
        delBookButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetBookObjectM == null){
                    return;
                }
                JOptionPane.showMessageDialog(null,"删除了编号为:"+tragetBookObjectM.toString()+"的图书");
                Data.DelBookData(tragetBookObjectM.toString());
                UpdataAllBookTable();
            }
        });
        tableBookM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = allBook.rowAtPoint(e.getPoint());
                tragetBookObjectM = allBook.getValueAt(row,0);
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
    void BookTableUpdata(String bookName,JTable table){
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
        table.setRowSorter(rowSorter);
        table.setModel(tableModel);
    }
    void UserTableUpdata(String userName,JTable table){
        String[] head =new String[] {"用户姓名", "用户密码", "权限组", "借阅证情况"};
        List<Object[]> tragetUserData = new ArrayList<>();
        if(userName != ""){
            for(int i = 0 ;  i < UserData.GetUserTable().length;i++){
                if(!UserData.GetUserTable()[i][0].toString().toLowerCase().contains(userName.toLowerCase()))
                    continue;
                tragetUserData.add(UserData.GetUserTable()[i]);
            }
            if(tragetUserData.size() == 0){
                JOptionPane.showMessageDialog(null,"找不到符合用户");
                return;
            }
        }
        else {
            for(int i = 0 ;  i < UserData.GetUserTable().length;i++){
                tragetUserData.add(UserData.GetUserTable()[i]);
            }
        }
        Object[][] tabalData = tragetUserData.toArray(Object[][]::new);
        DefaultTableModel tableModel=new DefaultTableModel(tabalData,head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
        table.setRowSorter(rowSorter);
        table.setModel(tableModel);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
