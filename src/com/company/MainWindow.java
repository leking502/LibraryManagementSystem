package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private JTextField bookNameTe;
    private JTextField callNumberTe;
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
    private JButton cancelButtonA;
    private JButton upDataBookA;
    private JButton upDataBorA;
    private JLabel tBookNumA;
    private JLabel tBookNameA;
    private JLabel tBookDateA;
    private JLabel tRbBookNumA;
    private JLabel tRbBookNameA;
    private JLabel tUserNameM;
    private JLabel tBookNumM;
    private JLabel tBookNameM;
    private JLabel tBookDateM;
    private JButton upDataUserM;
    private JButton upDataBookM;
    private JTextField collectionPlaceTe;
    private JTextField responsiblePersonTe;
    private JTextField pressTe;
    private JTextField lendingDateTe;
    private JTextField ISBNTe;
    private JRadioButton callNumRadioButton;
    private JRadioButton collPlaRadioButton;
    private JRadioButton bookNameRadioButton;
    private JRadioButton rPeRadioButton;
    private JRadioButton proRadioButton;
    private JRadioButton dateRadioButton;
    private JRadioButton ISBNRadioButton;
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
            BookData bookData = Data.FindBookForNum(tragetBookA.toString());
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
            BookData bookData = Data.FindBookForNum(tragetRbBookA.toString());
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
            BookData bookData = Data.FindBookForNum(tragetBookM.toString());
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
            if(Data.FindUser(tragetUserM.toString()) == null){
                System.out.println("该用户不存在");
                return;
            }
            tUserNameM.setText(" 用户名: " + tragetUserM.toString());
        }
    }
    void Updata(){
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

        bookNameRadioButton.setSelected(true);
        BookTableUpdata("", bookTableA);
        UserTableUpdata("", userTableM);
        BookTableUpdata("", bookTableM);
        BorTableUpdata("", borTableA);

        if(Objects.equals(UserData.GetMainUserJurisdiction(), "管理员")){
            adminWelcome.setText(UserData.GetMainUserName()+"欢迎使用管理员功能");
        }
        welcomeText.setText(UserData.GetMainUserName()+"\n欢迎使用图书管理系统！");
        bookTotal.setText("藏书总量："+ BookData.bookTotal);
        sButton.addActionListener(e -> {
            BookTableUpdata(textField1.getText().trim(), bookTableA);
            textField1.setText("");
        });
        sUserButton.addActionListener(e -> {
            UserTableUpdata(textFieldUser.getText().trim(), userTableM);
            textFieldUser.setText("");
        });
        sButtonM.addActionListener(e -> {
            BookTableUpdata(textFieldBookM.getText().trim(), bookTableM);
            textFieldBookM.setText("");
        });
        addButton.addActionListener(e -> {
            if(callNumberTe.getText().trim().equals("") ||
                    collectionPlaceTe.getText().trim().equals("") ||
                    bookNameTe.getText().trim().equals("") ||
                    responsiblePersonTe.getText().trim().equals("") ||
                    pressTe.getText().trim().equals("") ||
                    lendingDateTe.getText().trim().equals("") ||
                    ISBNTe.getText().trim().equals("")){
                return;
            }
            Data.InsBookData(callNumberTe.getText(),
                    collectionPlaceTe.getText(),
                    bookNameTe.getText(),
                    responsiblePersonTe.getText(),
                    pressTe.getText(),
                    lendingDateTe.getText(),
                    ISBNTe.getText());
            JOptionPane.showMessageDialog(null,
                    "书名：" + bookNameTe.getText()+
                    "\n添加成功");
            callNumberTe.setText("");
            collectionPlaceTe.setText("");
            bookNameTe.setText("");
            responsiblePersonTe.setText("");
            pressTe.setText("");
            lendingDateTe.setText("");
            ISBNTe.setText("");
            UpdataAllTable();
        });
        bookTableA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int row = bookTableA.rowAtPoint(e.getPoint());
                tragetBookA = bookTableA.getValueAt(row,0);

                UpdataTraget();
            }
        });
        userTableM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int row = userTableM.rowAtPoint(e.getPoint());
                tragetUserM = userTableM.getValueAt(row,0);

                UpdataTraget();
            }
        });
        delUserButton.addActionListener(e -> {
            if(tragetUserM == null){
                return;
            }
            if(Objects.equals(tragetUserM.toString(), UserData.GetMainUserName())){
                JOptionPane.showMessageDialog(null,"你不可以删除自己");
                return;
            }
            if(Data.FindUser(tragetUserM.toString()) == null){
                JOptionPane.showMessageDialog(null,"该用户不存在");
                return;
            }
            JOptionPane.showMessageDialog(null,"删除了账号:"+ tragetUserM.toString());

            Object[][] table = BorrowingData.GetUserBorTable(tragetUserM.toString());
            if(table != null){
                for (Object[] objects : table) {
                    BorrowingData.ReBorBook(objects[0].toString());
                }
            }
            Data.DelUserData(tragetUserM.toString());
            UpdataAllTable();
        });
        delBookButtonM.addActionListener(e -> {
            if(tragetBookM == null){
                return;
            }
            if(Data.FindBookForNum(tragetBookM.toString()) == null){
                JOptionPane.showMessageDialog(null,"该书不存在");
                return;
            }
            JOptionPane.showMessageDialog(null,"删除了编号为:"+ tragetBookM.toString()+"的图书");
            for(Object[] userData : Data.GetUserDataTable("",null)){
                for(Object[] objects : Objects.requireNonNull(BorrowingData.GetUserBorTable(userData[1].toString()))){
                    if(objects[0] == tragetBookM){
                        BorrowingData.ReBorBook(objects[0].toString());
                    }
                }
            }
            Data.DelBookData(tragetBookM.toString());
            UpdataAllTable();
        });
        bookTableM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int row = bookTableM.rowAtPoint(e.getPoint());
                tragetBookM = bookTableM.getValueAt(row,0);

                UpdataTraget();
            }
        });
        tabbedPaneA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
                if(Objects.equals(UserData.GetMainUserJurisdiction(), "用户")&& tabbedPaneA.getSelectedIndex() == 3){
                    tabbedPaneA.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,"你不是管理员");
                }
            }
        });
        borBookButton.addActionListener(e -> {
            if(tragetBookA == null){
                return;
            }
            if(Objects.equals(Objects.requireNonNull(Data.FindBookForNum(tragetBookA.toString())).GetBorrowingSituation(), "已借出")){
                JOptionPane.showMessageDialog(null,"该书已被借出");
                return;
            }
            JOptionPane.showMessageDialog(null,"借了编号为:"+ tragetBookA.toString()+"的图书");
            BorrowingData.BorBook(tragetBookA.toString(),new java.sql.Date(new Date().getTime()));

            UpdataAllTable();
        });
        borTableA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {



                int row = borTableA.rowAtPoint(e.getPoint());
                tragetRbBookA = borTableA.getValueAt(row,0);

                UpdataTraget();
            }
        });
        sBorBookButton.addActionListener(e -> {
            BorTableUpdata(textFieldBor.getText().trim(), borTableA);
            textFieldBor.setText("");
        });
        reBorButton.addActionListener(e -> {
            if(tragetRbBookA == null){
                return;
            }
            JOptionPane.showMessageDialog(null,"归还了编号为："+ tragetRbBookA.toString()+"的图书");
            BorrowingData.ReBorBook(tragetRbBookA.toString());

            UpdataAllTable();
        });
        cancelButtonA.addActionListener(e -> {
            UserData.Cancellation();
            Despose();
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.ShowWindow();
        });
        tabbedPaneA.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
            }
        });
        upDataBookA.addActionListener(e -> Updata());
        upDataBorA.addActionListener(e -> Updata());
        tabbedPaneM.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Updata();
            }
        });
        upDataUserM.addActionListener(e -> Updata());
        upDataBookM.addActionListener(e -> Updata());
    }

    public void ShowWindow() {
        JFrame frame = new JFrame("图书管理系统");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setBounds(500,200,800,500);
        frame.setVisible(true);
        MainWindow.frame = frame;
    }
    void BookTableUpdata(String field,JTable table){
        String[] head =new String[] {"书本编号", "索书号","馆藏地","书本名称","责任者","出版社","出版年","ISBN", "借阅状态"};
        Object[][] tabalData;
        if(field == ""){
            tabalData = Data.GetBookDataTable("",null);
        }else {
            if(callNumRadioButton.isSelected()){
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.CallNumber);
            }else if(collPlaRadioButton.isSelected()){
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.CollectionPlace);
            } else if (bookNameRadioButton.isSelected()) {
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.BookName);
            } else if (rPeRadioButton.isSelected()) {
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.ResponsiblePerson);
            } else if (proRadioButton.isSelected()) {
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.Press);
            } else if (dateRadioButton.isSelected()) {
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.LendingDate);
            } else if (ISBNRadioButton.isSelected()) {
                tabalData = Data.GetBookDataTable(field,Data.BookDataType.ISBN);
            }else {
                tabalData = tabalData = Data.GetBookDataTable(field,Data.BookDataType.BookName);
            }
        }
        DefaultTableModel tableModel=new DefaultTableModel(tabalData,head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        RowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setModel(tableModel);
    }
    void UserTableUpdata(String field,JTable table){
        String[] head =new String[] {"用户姓名", "用户密码", "权限组", "借阅证情况"};
        Object[][] nTable;
        if(Objects.equals(field, "")){
            nTable = Data.GetUserDataTable("",null);
        }
        else {
            nTable = Data.GetUserDataTable(field,Data.UserDataType.UserName);
        }
        Object[][] tabalData = nTable;
        DefaultTableModel tableModel=new DefaultTableModel(tabalData,head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        RowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setModel(tableModel);
    }
    void BorTableUpdata(String field,JTable table){
        String[] head =new String[] {"书本编号", "书本名称", "借出时间"};
        List<Object[]> tragetBookData = new ArrayList<>();
        Object[][] pBorList = BorrowingData.GetUserBorTable(UserData.GetMainUserName());
        if(pBorList == null){
            pBorList  = new Object[0][0];
        }
        if(!Objects.equals(field, "")){
            for (Object[] objects : pBorList) {
                if (!Objects.requireNonNull(Data.FindBookForNum(objects[0].toString())).GetBookName().toLowerCase().contains(field.toLowerCase()))
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
        RowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setModel(tableModel);
    }
    static void Despose(){frame.dispose();}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
