package com.company;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.sql.*;
import java.util.Arrays;

public class Main {


    public static void main(String[] args)
    {

        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        //LoginWindow loginWindow = new LoginWindow();
        //loginWindow.ShowWindow();
        Data.LoadData();


        UserData.CreateUserDate("admin","123","1","-1");
        MainWindow mainWindow = new MainWindow();
        mainWindow.ShowWindow();

    }
}
