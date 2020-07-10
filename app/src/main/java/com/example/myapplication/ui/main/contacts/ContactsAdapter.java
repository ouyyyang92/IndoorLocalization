package com.example.myapplication.ui.main.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import org.w3c.dom.Text;

public class ContactsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public ContactsAdapter(LayoutInflater inflater){
//        this.context = context;
        this.inflater = inflater;
//        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView_name;
        public TextView textView_state;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.layout_contacts_item,null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.imageView_icon);
            holder.textView_name = view.findViewById(R.id.textView_username);
            holder.textView_state = view.findViewById(R.id.textView_state);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        //给控件赋值

        return null;
    }
}
