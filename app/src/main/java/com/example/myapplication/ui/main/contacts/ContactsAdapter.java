package com.example.myapplication.ui.main.contacts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.data.Person;
import com.example.myapplication.R;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ContactsAdapter extends BaseAdapter {
    private List<Person> data;
    private Context context;
    private LayoutInflater inflater;
    private Drawable[] image = new Drawable[6];

    public ContactsAdapter(Context context,List<Person> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView_name;
        public TextView textView_state;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            holder = (ViewHolder) view.getTag();
        }
        //给控件赋值
        LoadImage();
        holder.imageView.setImageDrawable(image[data.get(i).getHeadImg2()]);
        holder.textView_name.setText(data.get(i).getName());
        //显示状态
        String state = "在线";
        holder.textView_state.setText("["+"state"+"]");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void LoadImage(){
        image[0] = context.getDrawable(R.drawable.ic_headimage_1);
        image[1] = context.getDrawable(R.drawable.ic_headimage_2);
        image[2] = context.getDrawable(R.drawable.ic_headimage_3);
        image[3] = context.getDrawable(R.drawable.ic_headimage_4);
        image[4] = context.getDrawable(R.drawable.ic_headimage_5);
        image[5] = context.getDrawable(R.drawable.ic_headimage_6);
    }
}
