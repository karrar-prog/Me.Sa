package com.example.domka.menhajAlsaleheen.app.Controller.PartController.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.ListActivity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;

import java.util.ArrayList;

/**
 * Created by domka on 2017-05-14.
 */
public class ListItemAdapter extends BaseAdapter
{
    Context context;
    ArrayList<listItem> dataList = new ArrayList<>();

    LayoutInflater mInflater;

    public ListItemAdapter(Context context,ArrayList<listItem> dataList)
    {
        this.context =context;
        this.dataList = dataList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return dataList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ListActivity.my_listitem.get(position);

    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v= mInflater.inflate(R.layout.list_item_item,parent,false);
        TextView titile = (TextView)v.findViewById(R.id.title);
        TextView page_no = (TextView)v.findViewById(R.id.page_no);


        Font font = new Font();
        MyAppSetting myAppSetting = new MyAppSetting(context);
        font.set_font(context, myAppSetting.getFont_no(),titile,page_no );

        font.set_font_size(context, myAppSetting.getFont_size(), titile);

        titile.setText(dataList.get(position).title);
        page_no.setText(dataList.get(position).page_no);

        return v;
    }
}
