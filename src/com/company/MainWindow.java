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
    private JTabbedPane tabbedPaneA;
    private JButton sButton;
    private JTextField textField1;
    private JTabbedPane tabbedPaneM;
    private JTable bookTableA;
    private JTable borTableA;
    private JTextField addBookName;
    private JTextField publicationTime;
    private JButton addButton;
    private JTable userTableM;
    private JTextField textFieldUser;
    private JButton sUserButton;
    private JButton delUserButton;
    private JButton borBookButton;
    private JTable bookTableM;
    private JTextField textFieldBookM;
    private JButton sButtonM;
    private JButton delBookButtonM;
    private JTextField textFieldBor;
    private JButton sBorBookButton;
    private JButton reBorButton;
    private JLabel welcomeText;
    private JLabel bookTotal;
    private JLabel adminWelcome;
    private JButton 注销Button;
    private JButton 刷新Button;
    private JButton 刷新Button1;
    private JLabel tBookNumA;
    private JLabel tBookNameA;
    private JLabel tBookDateA;
    private JLabel tRbBookNumA;
    private JLabel tRbBookNameA;
    private JLabel tUserNameM;
    private JLabel tBookNumM;
    private JLabel tBookNameM;
    private JLabel tBookDateM;
    private JButton 更新Button;
    private JButton 更新Button1;
    private Object tragetBookA;
    private Object tragetBookM;
    private Object tragetUserM;
    private Object tragetRbBookA;

    public void SetTargetNull(){
        tragetBookA = null;
        tragetBookM = null;
        tragetUserM = null;
        tragetRbBookA = null;
    }
    void UpdataTraget(){
        if(tragetBookA == null){
            tBookNumA.setText("");
            tBookNameA.setText("");
            tBookDateA.setText("");
        } else {
            BookData bookData = BookData.FindBookForNum(tragetBookA.toString());
            if(bookData == null){
                System.out.println("该书不存在");
                return;
            }
            tBookNumA.setText(" 编号: "+bookData.GetBookNumber());
            tBookNameA.setText(" 书名: "+bookData.GetBookName());
            tBookDateA.setText(" 出版日: "+bookData.GetLendingDate());
        }
        if(tragetRbBookA == null){
            tRbBookNumA.setText("");
            tRbBookNameA.setText("");

        }else {
            BookData bookData = BookData.FindBookForNum(tragetRbBookA.toString());
            if(bookData == null){
                System.out.println("该书不存在");
                return;
            }
            tRbBookNumA.setText(" 编号: "+bookData.GetBookNumber());
            tRbBookNameA.setText(" 书名: "+bookData.GetBookName());
        }
        if(tragetBookM == null){
            tBookNumM.setText("");
            tBookNameM.setText("");

        }else {
            BookData bookData = BookData.FindBookForNum(tragetBookM.toString());
            if(bookData == null){
                System.out.println("该书不存在");
                return;
            }
            tBookNumM.setText(" 编号: "+bookData.GetBookNumber());
            tBookNameM.setText(" 书名: "+bookData.GetBookName());
        }
        if(tragetUserM == null){
            tUserNameM.setText("");
        }else {
            if(UserData.FindUser(tragetUserM.toString()) == null){
                System.out.println("该书不存在");
                return;
            };
            tUserNameM.setText(" 用户名: " + tragetUserM.toString());
        }
    }
    void Updata(){
        Data.UpData();
        UpdataAllTable();
        SetTargetNull();
        UpdataTraget();
    }
    public  void UpdataAllBookTable(){
        BookTableUpdata("", bookTableM);
        BookTableUpdata("", bookTableA);
    }
    public void UpdataAllTable(){
        UserTableUpdata("", userTableM);
        BorTableUpdata("", borTableA);
        UpdataAllBookTable();
    }
    public MainWindow() {

        BookTableUpdata("", bookTableA);
        UserTableUpdata("", userTableM);
        BookTableUpdata("", bookTableM);
        BorTableUpdata("", borTableA);

        if(Objects.equals(UserData.GetMainUserData().GetMainUserJurisdiction(), "管理员")){
            adminWelcome.setText(UserData.GetMainUserData().GetMainUserName()+"欢迎使用管理员功能");
        }
        welcomeText.setText(UserData.GetMainUserData().GetMainUserName()+"\n欢迎使用图书管理系统！");
        bookTotal.setText("藏书总量："+ BookData.GetBookTotal());
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookTableUpdata(textField1.getText().trim(), bookTableA);
                textField1.setText("");
            }
        });
        sUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTableUpdata(textFieldUser.getText().trim(), userTableM);
                textFieldUser.setText("");
            }
        });
        sButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookTableUpdata(textFieldBookM.getText().trim(), bookTableM);
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
                addBookName.setText("");
                publicationTime.setText("");
                UpdataAllTable();
            }
        });
        bookTableA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Data.UpData();

                int row = bookTableA.rowAtPoint(e.getPoint());
                tragetBookA = bookTableA.getValueAt(row,0);

                UpdataTraget();
            }
        });
        userTableM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Data.UpData();

                int row = userTableM.rowAtPoint(e.getPoint());
                tragetUserM = userTableM.getValueAt(row,0);

                UpdataTraget();
            }
        });
        delUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetUserM == null){
                    return;
                }
                if(Objects.equals(tragetUserM.toString(), UserData.GetMainUserData().GetMainUserName())){
                    JOptionPane.showMessageDialog(null,"你不可以删除自己");
                    return;
                }
                JOptionPane.showMessageDialog(null,"删除了账号:"+ tragetUserM.toString());

                Object[][] table = BorrowingData.GetBorrowingTable(tragetUserM.toString());
                if(table != null){
                    for (Object[] objects : table) {
                        BorrowingData.ReBorBook(objects[0].toString());
                    }
                }
                Data.DelUserData(tragetUserM.toString());
                UpdataAllTable();
            }
        });
        delBookButtonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetBookM == null){
                    return;
                }
                JOptionPane.showMessageDialog(null,"删除了编号为:"+ tragetBookM.toString()+"的图书");
                for(UserData userData : UserData.GetUserDataList()){
                    for(Object[] objects : Objects.requireNonNull(BorrowingData.GetBorrowingTable(userData.GetUserName()))){
                        if(objects[0] == tragetBookM){
                            BorrowingData.ReBorBook(objects[0].toString());
                        }
                    }
                }
                Data.DelBookData(tragetBookM.toString());
                UpdataAllTable();
            }
        });
        bookTableM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Data.UpData();

                int row = bookTableM.rowAtPoint(e.getPoint());
                tragetBookM = bookTableM.getValueAt(row,0);

                UpdataTraget();
            }
        });
        tabbedPaneA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
                if(Objects.equals(UserData.GetMainUserData().GetMainUserJurisdiction(), "用户")&& tabbedPaneA.getSelectedIndex() == 3){
                    tabbedPaneA.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,"你不是管理员");
                }
            }
        });
        borBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetBookA == null){
                    return;
                }
                if(Objects.equals(BookData.FindBookForNum(tragetBookA.toString()).GetBorrowingSituation(), "已借出")){
                    JOptionPane.showMessageDialog(null,"该书已被借出");
                    return;
                }
                JOptionPane.showMessageDialog(null,"借了编号为:"+ tragetBookA.toString()+"的图书");
                BorrowingData.BorBook(tragetBookA.toString(),new java.sql.Date(new Date().getTime()));
                Data.UpData();
                UpdataAllTable();
            }
        });
        borTableA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Data.UpData();

                int row = borTableA.rowAtPoint(e.getPoint());
                tragetRbBookA = borTableA.getValueAt(row,0);

                UpdataTraget();
            }
        });
        sBorBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorTableUpdata(textFieldBor.getText().trim(), borTableA);
                textFieldBor.setText("");
            }
        });
        reBorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tragetRbBookA == null){
                    return;
                }
                JOptionPane.showMessageDialog(null,"归还了编号为："+ tragetRbBookA.toString()+"的图书");
                BorrowingData.ReBorBook(tragetRbBookA.toString());
                Data.UpData();
                UpdataAllTable();
            }
        });
        注销Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserData.Cancellation();
                Despose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.ShowWindow();
            }
        });
        tabbedPaneA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
            }
        });
        刷新Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Updata();
            }
        });
        刷新Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Updata();
            }
        });
        tabbedPaneM.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
            }
        });
        更新Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Updata();
            }
        });
        更新Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Updata();
            }
        });
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("图书管理系统");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(500,200,800,500);
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
        Object[][] tabalData = tragetBookData.toArray(new Object[0][]);
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
        Object[][] tabalData = tragetUserData.toArray(new Object[0][]);
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
        Object[][] tabalData = tragetBookData.toArray(new Object[0][]);
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
    static void Despose(){frame.dispose();}
}
