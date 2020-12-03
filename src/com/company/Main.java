package com.company;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

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
        Data.LoadData();

        LoginWindow loginWindow = new LoginWindow();
        loginWindow.ShowWindow();



        //UserData.LoadMainUserData("admin","123","1","-1");
        //MainWindow mainWindow = new MainWindow();
        //mainWindow.ShowWindow();

    }
}
