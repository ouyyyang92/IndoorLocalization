package com.example.myapplication.ui.main.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.cilent.Client;
import com.example.data.Person;
import com.example.myapplication.R;
import com.google.android.gms.common.api.Api;

import java.util.List;

public class RequestAdapter extends BaseAdapter {
    private List<Person> requests;
    private Context context;
    private LayoutInflater inflater;
    private String myName;
    private Drawable[] image;
    private String fromname;

    public RequestAdapter(Context context, List<Person> requests, String myName) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.requests = requests;
        this.myName = myName;
    }

    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public Object getItem(int i) {
        return requests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RequestViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_request_item, null);
            holder = new RequestViewHolder();
            holder.iv_icon = view.findViewById(R.id.imageView_icon);
            holder.tv_username = view.findViewById(R.id.textView_username);
            holder.btn_accept = view.findViewById(R.id.button_accept);
            holder.btn_reject = view.findViewById(R.id.button_reject);
            holder.tv_hint = view.findViewById(R.id.textView_hint);
            view.setTag(holder);
        } else {
            holder = (RequestViewHolder) view.getTag();
        }
        //给控件赋值
        LoadImage();
        holder.tv_hint.setVisibility(View.INVISIBLE);
        holder.iv_icon.setImageDrawable(image[requests.get(i).getHeadImg2()]);
        holder.tv_username.setText(requests.get(i).getName());
        final RequestViewHolder finalHolder = holder;
        fromname = requests.get(i).getName();
        holder.btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = "9" + " " + finalHolder.tv_username.getText().toString() + " " + myName;
                Client.send(str1);
                finalHolder.btn_accept.setVisibility(View.INVISIBLE);
                finalHolder.btn_reject.setVisibility(View.INVISIBLE);
                finalHolder.tv_hint.setVisibility(View.VISIBLE);
            }
        });
        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = "10" + " " + finalHolder.tv_username.getText().toString() + " " + myName;
                Client.send(str1);
                finalHolder.btn_accept.setVisibility(View.INVISIBLE);
                finalHolder.btn_reject.setVisibility(View.INVISIBLE);
                finalHolder.tv_hint.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void LoadImage() {
        image = new Drawable[6];
        image[0] = context.getDrawable(R.drawable.ic_headimage_1);
        image[1] = context.getDrawable(R.drawable.ic_headimage_2);
        image[2] = context.getDrawable(R.drawable.ic_headimage_3);
        image[3] = context.getDrawable(R.drawable.ic_headimage_4);
        image[4] = context.getDrawable(R.drawable.ic_headimage_5);
        image[5] = context.getDrawable(R.drawable.ic_headimage_6);
    }

    private static class RequestViewHolder {
        public ImageView iv_icon;
        public TextView tv_username;
        public Button btn_accept;
        public Button btn_reject;
        public TextView tv_hint;
    }
}
