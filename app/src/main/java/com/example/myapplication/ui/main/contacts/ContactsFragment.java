package com.example.myapplication.ui.main.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.data.DateUtils;
import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.ui.main.home.MyPageActivity;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv_contacts;
    private Button btn_search;
    private EditText ed_search;
    private MainActivity parent;
    private String searchKey;
    private Person friend;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
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
        View contentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        parent = (MainActivity)getActivity();
        FindView();
        SetListeners();
//        lv_contacts.setAdapter(new ContactsAdapter(parent,));
        return contentView;
    }

    private void FindView(){
        lv_contacts = parent.findViewById(R.id.listView_contacts);
        btn_search = parent.findViewById(R.id.button_search);
        ed_search = parent.findViewById(R.id.editText_search);
    }

    private void SetListeners(){
        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取用户名
                Intent intent = new Intent(parent,FriendPageActivity.class);
                friend = (Person) lv_contacts.getAdapter().getItem(i);
                intent.putExtras(GetFriendBundle(true));
                startActivity(intent);
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserExist()){
                    Intent intent = new Intent(parent,FriendPageActivity.class);
                    intent.putExtras(GetFriendBundle(IsMyFriend()));
                    startActivity(intent);
                }else {
                    Toast.makeText(parent,"用户不存在",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private boolean UserExist(){
        searchKey = ed_search.getText().toString();
        return true;
    }

    private boolean IsMyFriend(){
        friend = new Person();
        return true;
    }

    private Bundle GetFriendBundle(boolean isFriend){
        Bundle bundle = new Bundle();

        if( isFriend ){ //不是好友
            bundle.putString("state","add a friend");
        }else {
            bundle.putString("state","my friend's page");
        }

//                bundle.putString("username",friend.getName());
//                bundle.putString("phone",friend.getPhone());
//                bundle.putInt("icon",friend.getHeadImg2());
//                bundle.putInt("gender",friend.getGender());
//                if (friend.getEmail().equals("null")){
//                    bundle.putString("email","*****");
//                }
//                else bundle.putString("email",friend.getEmail());
//
//                if (friend.getAddress().equals("null")){
//                    bundle.putString("address","*****");
//                }
//                else bundle.putString("address",friend.getAddress());
//
//                if (friend.getHobby().equals("null")){
//                    bundle.putString("hobbies","*****");
//                }
//                else bundle.putString("hobbies",friend.getHobby());
//
//                Date date = friend.getBornDate();
//                if (date == null){
//                    bundle.putString("birth","*****");
//                }
//                else bundle.putString("birth", DateUtils.udateToString(friend.getBornDate()));
        return bundle;
    }
}