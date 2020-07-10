package com.example.myapplication.ui.main.map;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cilent.Client;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;
import com.example.step.OrientSensor;
import com.example.step.StepSensorAcceleration;
import com.example.step.StepSensorBase;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView iv_location;
    private float currentX = 0;
    private float currentY = 0;
    private float newX, newY;
    private MainActivity parent;
    private Button button_dingwei;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int[] AP = new int[6];
    public float px, py;
    private Timer mTimer;   // 启动定时任务的对象
    private final int SAMPLE_RATE = 2000; // 采样周期，以毫秒为单位，两秒一次
    private String str1;
    private WifiManager wm;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    public void InitLocation() {

        TranslateAnimation translateAnimation = new TranslateAnimation(currentX, newX, currentY, newY);
        translateAnimation.setDuration(100);
        iv_location.setAnimation(translateAnimation);
        translateAnimation.start();
    }

    public void init() {                 //数据初始化
        for (int a = 0; a < 6; a++) {
            AP[a] = -100;
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                init();

                int j = 0;
                List<ScanResult> results = wm.getScanResults();  //拿到扫描的结果
                for (ScanResult result : results) {
                    if (result.SSID.equals("1604") && result.level > -100) {
                        AP[j] = result.level;
                        j++;
                    }
                    if (result.SSID.equals("TP-LINK_1704") && result.level > -100) {
                        AP[2] = result.level;
                    }
                    if (result.SSID.equals("1601T") && result.level > -100) {
                        AP[3] = result.level;
                    }
                    if (result.SSID.equals("llalala") && result.level > -100) {
                        AP[4] = result.level;
                    }
                    if (result.SSID.equals("HUAWEI-YJ520Q") && result.level > -100) {
                        AP[5] = result.level;
                    }
                }
                str1 = "3 " + AP[0] + " " + AP[1] + " " + AP[2] + " " + AP[3] + " " + AP[4] + " " + AP[5];
                String str2 = Client.send(str1);
                String[] strings4 = str2.split("/");
                String[] strings5 = strings4[1].split(" ");
                px = (float) (Integer.parseInt(strings5[0]) * 410 / 12.0 / 6.8 * 16.5);
                py = (float) ((20 - Integer.parseInt(strings5[1])) * 410 / 12 / 6.8 * 16.5);

            }
            super.handleMessage(msg);
        }
    };
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = (MainActivity) getActivity();
        newX = px;
        newY = py;
        iv_location = getActivity().findViewById(R.id.imageView_location);
        button_dingwei = getActivity().findViewById(R.id.button);
        button_dingwei.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        wm = (WifiManager) parent.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wm.startScan();                                  //开始扫描AP
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);

                    }
                }, 0, 10000); // 立即执行任务，每隔5000ms执行一次WiFi扫描的任务
            }


        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                int num = 0;
                while (true) {
//                    num++;
//                    newX = currentX + (100 - num) / 10;
//                    newY = currentY - (100 - num) / 10;
                    newX = px;
                    newY = py;
                    TranslateAnimation translateAnimation = new TranslateAnimation(currentX, newX, currentY, newY);
                    translateAnimation.setDuration(100);//动画持续时间
                    iv_location.setAnimation(translateAnimation);
                    translateAnimation.start();
                    currentX = newX;
                    currentY = newY;
                    try {
                        Thread.sleep(100);//线程持续时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }

}