package com.example.myapplication.ui.main.map;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cilent.Client;
import com.example.cilent.setloca;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.ui.main.home.MyPageActivity;
import com.example.suanfa.setloca2;

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
    private float newX,newY;
    private MainActivity parent;
    private Button button_dingwei;
    private setloca wifi;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    public void InitLocation(){

        TranslateAnimation translateAnimation = new TranslateAnimation(currentX,newX,currentY,newY);
                    translateAnimation.setDuration(100);
                    iv_location.setAnimation(translateAnimation);
                    translateAnimation.start();
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent= (MainActivity) getActivity();
        newX=parent.px;
        newY=parent.py;
       iv_location= getActivity().findViewById(R.id.imageView_location);
        button_dingwei = getActivity().findViewById(R.id.button);
        button_dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.Fresh();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                int num = 0;
                while(true){
//                    num++;
//                    newX = currentX + (100 - num) / 10;
//                    newY = currentY - (100 - num) / 10;
                    newX=parent.px;
                    newY=parent.py;
                    TranslateAnimation translateAnimation = new TranslateAnimation(currentX,newX,currentY,newY);
                    translateAnimation.setDuration(100);
                    iv_location.setAnimation(translateAnimation);
                    translateAnimation.start();
                    currentX = newX;
                    currentY = newY;
                    try {
                        Thread.sleep(100);
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