package com.example.myapplication.ui.main.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cilent.Client;
import com.example.data.DateUtils;
import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.ui.start.LoginActivity;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Button btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_my_page;
    private Button btn_friends_request;
    private RelativeLayout rl_information;
    private Button btn_information;
    private MainActivity parent;
    private View view;
    private Button btn_exit;
    private ImageView iv_icon;
    private TextView tv_name;
    private Drawable[] image = new Drawable[6];

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = (MainActivity) getActivity();
        assert parent != null;
        LoadImage();
        iv_icon = parent.findViewById(R.id.imageView_icon);
        iv_icon.setImageDrawable(image[parent.GetMe().getHeadImg2()]);
        tv_name = parent.findViewById(R.id.tv_name);
        tv_name.setText(parent.GetMe().getName());
        rl_information= parent.findViewById(R.id.relativeLayout_my_page);
        rl_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), MyPageActivity.class);
                Bundle bundle = new Bundle();
                Person me = parent.GetMe();
                bundle.putString("username",me.getName());
                bundle.putString("phone",me.getPhone());
                bundle.putInt("icon",me.getHeadImg2());
                bundle.putInt("gender",me.getGender());
                if (me.getEmail().equals("null")){
                    bundle.putString("email","*****");
                }
                else bundle.putString("email",me.getEmail());

                if (me.getAddress().equals("null")){
                    bundle.putString("address","*****");
                }
                else bundle.putString("address",me.getAddress());

                if (me.getHobby().equals("null")){
                    bundle.putString("hobbies","*****");
                }
                else bundle.putString("hobbies",me.getHobby());

                Date date = me.getBornDate();
                if (date == null) {
                    bundle.putString("birth", "*****");
                } else bundle.putString("birth", DateUtils.udateToString(me.getBornDate()));

                intent.putExtras(bundle);
                getActivity().startActivity(intent);

            }
        });
        btn_friends_request = parent.findViewById(R.id.btn_friends_request);
        btn_friends_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent, RequestActivity.class);
                intent.putExtra("username", parent.GetMe().getName());
                startActivity(intent);
            }
        });
        btn_exit = parent.findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = parent.GetMe().getName();
                String string = "13 " + name;
                Client.send(string);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void LoadImage(){
        image[0] = parent.getDrawable(R.drawable.ic_headimage_1);
        image[1] = parent.getDrawable(R.drawable.ic_headimage_2);
        image[2] = parent.getDrawable(R.drawable.ic_headimage_3);
        image[3] = parent.getDrawable(R.drawable.ic_headimage_4);
        image[4] = parent.getDrawable(R.drawable.ic_headimage_5);
        image[5] = parent.getDrawable(R.drawable.ic_headimage_6);
    }

}