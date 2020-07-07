package com.example.cilent;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class setloca extends AppCompatActivity {
    int[] AP = new int[4];
    int wifi1, wifi2, wifi3, wifi4;
    public int i = 0;
    public int Xcoord = -1;//X轴坐标
    public int Ycoord = -1;//X轴坐标
//    private WifiManager wm;
//    private List<ScanResult>   results;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        wm.startScan();
//        results = wm.getScanResults();
//        System.out.println(results.get(0).SSID);
//        getAP();
    }
    public void init() {                 //数据初始化
        for (int a = 0; a < 4; a++) {
            AP[a] = -200;
        }

        wifi1 = -200;
        wifi2 = -200;
        wifi3 = -200;
        wifi4 = -200;
    }
    private void scan() {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wm.startScan();                                  //开始扫描AP

        List<ScanResult> results = wm.getScanResults();  //拿到扫描的结果
        for (ScanResult result : results) {
            if (result.SSID.equals("1604") && result.level > -100) {
                wifi1 = result.level;
            }
            if (result.SSID.equals("1604") && result.level > -100) {
                wifi2 = result.level;
            }
            if (result.SSID.equals("TP_LINK_1704") && result.level > -100) {
                wifi3 = result.level;
            }
            if (result.SSID.equals("1601T") && result.level > -100) {
                wifi4 = result.level;
            }
        }
    }
    public int[] getAP()
    {
        init();
        scan();

        AP[0] = wifi1;
        AP[1] = wifi2;
        AP[2] = wifi3;
        AP[3] = wifi4;


        System.out.println(AP[0]);
        System.out.println(AP[1]);
        System.out.println(AP[2]);
        System.out.println(AP[3]);
        return AP;
    }

    private void Twaiting(){

    }

}
