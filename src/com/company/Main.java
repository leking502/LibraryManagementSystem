package com.company;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;


public class Main {


    public static void main(String[] args)
    {
        Data.LoadSql();
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.ShowWindow();

        //UserData.LoadMainUserData("admin","admin","管理员","正常");
        //MainWindow mainWindow = new MainWindow();
        //mainWindow.ShowWindow();

    }
}
