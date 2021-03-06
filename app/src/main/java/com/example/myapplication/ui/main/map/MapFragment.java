package com.example.myapplication.ui.main.map;

import android.content.Context;
import android.content.Intent;
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
import com.example.data.DaoHang;
import com.example.data.MyData;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.ui.start.LoginActivity;
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
    private ImageView iv_location1;
    private float currentX = 0;
    private float currentY = 0;
    private float newX, newY;
    private float friendCurrentX = 0;
    private float friendCurrentY = 0;
    private float friendNewX, friendNewY;
    private MainActivity parent;
    private Button button_dingwei;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int[] AP = new int[6];
    public float px, py;
    public float friendPx=0,friendPy=0;
    private Timer mTimer;   // 启动定时任务的对象
    private final int SAMPLE_RATE = 2000; // 采样周期，以毫秒为单位，两秒一次
    private String str1;
    private WifiManager wm;
    private Button[] btn_place = new Button[9];

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
                String name = parent.GetMe().getName();
                int id = MyData.getId();
                str1 = "3 " + name +" "+ AP[0] + " " + AP[1] + " " + AP[2] + " " + AP[3] + " " + AP[4] + " " + AP[5]+" "+id;
                String str2 = Client.send(str1);
                String[] strings4 = str2.split("/");
                String[] strings5 = strings4[1].split(" ");
                px = (float) (Integer.parseInt(strings5[0]) * 1000 / 12.0 );
                py = (float) ((20 - Integer.parseInt(strings5[1])) * 1000 / 12.0 );
                if (!strings5[2].equals("-1")&&!strings5[3].equals("-1")) {
                    friendPx = (float) (Integer.parseInt(strings5[2]) * 1000 / 12.0);
                    friendPy = (float) ((20 - Integer.parseInt(strings5[3])) * 1000 / 12.0);
                }

                DaoHang DaoHang = new DaoHang();
                int bianhao = MyData.getBiaohao();
                int a = DaoHang.location(Integer.parseInt(strings5[0]),Integer.parseInt(strings5[1]));
                if (bianhao!=-1 && a!=bianhao){
                    Toast.makeText(parent, DaoHang.dh(a,MyData.getBiaohao()), Toast.LENGTH_SHORT).show();
                }
                else if(a ==bianhao) {
                    MyData.setBiaohao(-1);
                    Toast.makeText(parent, "导航完成，祝您生活愉快!", Toast.LENGTH_SHORT).show();
                }

            }
            super.handleMessage(msg);
        }
    };
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = (MainActivity) getActivity();
        newX = px;
        newY = py;
        friendNewX =friendPx;
        friendNewY =friendPy;
        iv_location = getActivity().findViewById(R.id.imageView_location);
        iv_location1 =getActivity().findViewById(R.id.imageView_location1);
        btn_place[1]=getActivity().findViewById(R.id.btn_kitchen);
        btn_place[2]=getActivity().findViewById(R.id.btn_dining_hall);
        btn_place[3]=getActivity().findViewById(R.id.btn_left_room1);
        btn_place[4]=getActivity().findViewById(R.id.btn_right_room1);
        btn_place[5]=getActivity().findViewById(R.id.btn_living_room);
        btn_place[6]=getActivity().findViewById(R.id.btn_left_room2);
        btn_place[7]=getActivity().findViewById(R.id.btn_right_room2);
        btn_place[8]=getActivity().findViewById(R.id.btn_balcony);
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
                        if (MyData.getDengluzhuangtai()==0){
                            message.what = 0;
                        }
                    }
                }, 0, 7000); // 立即执行任务，每隔5000ms执行一次WiFi扫描的任务
            }


        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                int num = 0;
                int count = 0;
                while (true) {
//                    num++;
//                    newX = currentX + (100 - num) / 10;
//                    newY = currentY - (100 - num) / 10;
                    newX = px;
                    newY = py;
                    friendNewX = friendPx;
                    friendNewY = friendPy;

                    TranslateAnimation translateAnimation = new TranslateAnimation(currentX, newX, currentY, newY);
                    TranslateAnimation translateAnimation1 = new TranslateAnimation(friendCurrentX, friendNewX, friendCurrentY, friendNewY);
//                    TranslateAnimation translateAnimation = new TranslateAnimation(0, 1000, 0, 0);
                    translateAnimation.setDuration(100);//动画持续时间
                    translateAnimation1.setDuration(100);
                    iv_location.setAnimation(translateAnimation);
                    iv_location1.setAnimation(translateAnimation1);
                    translateAnimation.start();
                    translateAnimation1.start();
//                    if (newX == currentX && newY == currentY ){
//                        count++;
//                    }
//                    else count =0;
//                    if (count == 150){
//                        Toast.makeText(parent,)
//                    }
                    currentX = newX;
                    currentY = newY;
                    friendCurrentX = friendNewX;
                    friendCurrentY = friendNewY;
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
        btn_place[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "厨房" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","厨房");
                bundle.putInt("num",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "饭厅" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","饭厅");
                bundle.putInt("num",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "左上卧室" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","左上卧室");
                bundle.putInt("num",3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "右上卧室" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","右上卧室");
                bundle.putInt("num",4);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn_place[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "客厅" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","客厅");
                bundle.putInt("num",5);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "左下卧室" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","左下卧室");
                bundle.putInt("num",6);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "右下卧室" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","右下卧室");
                bundle.putInt("num",7);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_place[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),IntroduceActivity.class);
                Bundle bundle=new Bundle();
                String str1 = "14 "+ "阳台" ;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                String[] strings1 = strings[1].split(" ");
                bundle.putString("introduce",strings1[0]);
                bundle.putString("pingjia",strings1[1]);
                bundle.putString("name","阳台");
                bundle.putInt("num",8);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}