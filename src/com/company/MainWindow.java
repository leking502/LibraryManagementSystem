package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


public class MainWindow {
    private static JFrame frame;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton sButton;
    private JTextField textField1;
    private JTabbedPane tabbedPane2;
    private JTable allBook;
    private JTable borTable;
    private JTextField addBookName;
    private JTextField publicationTime;
    private JButton addButton;
    private JTable userTable;
    private JTextField textFieldUser;
    private JButton sUserButton;
    private JButton delUserButton;
    private JButton borBookButton;
    private JTable tableBookM;
    private JTextField textFieldBookM;
    private JButton sButtonM;
    private JButton delBookButtonM;
    private JTextField textFieldBor;
    private JButton sBorBookButton;
    private JButton reBorButton;
    private JLabel welcomeText;
    private JLabel bookTotal;
    private JLabel adminWelcome;
    private Object tragetBookObject;
    private Object tragetBookObjectM;
    private Object tragetUserObject;
    private Object tragetBorBookObject;

    public void SetTargetNull(){
        tragetBookObject = null;
        tragetBookObjectM = null;
        tragetUserObject = null;
        tragetBorBookObject = null;
    }
    public void UpdataAllTable(){
        BookTableUpdata("",allBook);
        UserTableUpdata("",userTable);
        BookTableUpdata("",tableBookM);
        BorTableUpdata("",borTable);
    }
    public  void UpdataAllBookTable(){
        BookTableUpdata("",tableBookM);
        BookTableUpdata("",allBook);
    }
    public MainWindow() {

        BookTableUpdata("",allBook);
        UserTableUpdata("",userTable);
        BookTableUpdata("",tableBookM);
        BorTableUpdata("",borTable);

        if(Objects.equals(UserData.GetMainUserData().GetMainUserJurisdiction(), "管理员")){
            adminWelcome.setText(UserData.GetMainUserData().GetMainUserName()+"欢迎使用管理员功能");
        }
        welcomeText.setText(UserData.GetMainUserData().GetMainUserName()+"\n欢迎使用图书管理系统");
        bookTotal.setText("藏书总量："+ BookData.GetBookTotal());
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
                Data.InsBookData(addBookName.getText(),publicationTime.getText());
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
                for (Object[] objects : BorrowingData.GetBorrowingTable(tragetUserObject.toString())) {
                    BorrowingData.ReBorBook(objects[0].toString());
                }
                Data.DelUserData(tragetUserObject.toString());
                UpdataAllTable();
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
            public void mousePressed(MouseEvent e) {
                int row = tableBookM.rowAtPoint(e.getPoint());
                tragetBookObjectM = tableBookM.getValueAt(row,0);
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Objects.equals(UserData.GetMainUserData().GetMainUserJurisdiction(), "用户")&& tabbedPane1.getSelectedIndex() == 3){
                    tabbedPane1.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,"你不是管理员");
                }
            }
        });
        borBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetBookObject == null){
                    return;
                }
                if(Objects.equals(BookData.FindBookForNum(tragetBookObject.toString()).GetBorrowingSituation(), "已借出")){
                    JOptionPane.showMessageDialog(null,"该书已被借出");
                    return;
                }
                JOptionPane.showMessageDialog(null,"借了编号为:"+tragetBookObject.toString()+"的图书");
                BorrowingData.BorBook(tragetBookObject.toString(),new java.sql.Date(new Date().getTime()));
                UpdataAllTable();
            }
        });
        borTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = borTable.rowAtPoint(e.getPoint());
                tragetBorBookObject = borTable.getValueAt(row,0);
            }
        });
        sBorBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorTableUpdata(textFieldBor.getText().trim(),borTable);
                textFieldBor.setText("");
            }
        });
        reBorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetBorBookObject == null){
                    return;
                }
                JOptionPane.showMessageDialog(null,"归还了编号为："+tragetBorBookObject.toString()+"的图书");
                BorrowingData.ReBorBook(tragetBorBookObject.toString());
                UpdataAllTable();
            }
        });
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(800,400,750,500);
        frame.setResizable(false);
        frame.setVisible(true);
        this.frame = frame;
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
    void BorTableUpdata(String bookName,JTable table){
        String[] head =new String[] {"书本编号", "书本名称", "借出时间"};
        List<Object[]> tragetBookData = new ArrayList<>();
        Object[][] pBorList = BorrowingData.GetBorrowingTable(UserData.GetMainUserData().GetUserName());
        if(pBorList == null){
            pBorList  = new Object[0][0];
        }
        if(!Objects.equals(bookName, "")){
            for (Object[] objects : pBorList) {
                if (!BookData.FindBookForNum(objects[0].toString()).GetBookName().toLowerCase().contains(bookName.toLowerCase()))
                    continue;
                tragetBookData.add(objects);
            }
            if(tragetBookData.size() == 0){
                JOptionPane.showMessageDialog(null,"找不到符合的书");
                return;
            }
        }
        else {
            tragetBookData.addAll(Arrays.asList(pBorList));
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
