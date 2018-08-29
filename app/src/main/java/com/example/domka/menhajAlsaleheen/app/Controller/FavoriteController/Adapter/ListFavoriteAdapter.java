package com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter;

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

import static com.example.domka.menhajAlsaleheen.R.id.tv_content_favorite;

/**
 * Created by domka on 2017-05-26.
 */
public class ListFavoriteAdapter extends BaseAdapter
{
    private Context context;
    ArrayList<ListItemFavorite> dataListFavorite = new ArrayList<>();
    LayoutInflater mInflater;

    public ListFavoriteAdapter(Context context, ArrayList<ListItemFavorite> dataListFavorite)
    {
        this.context = context;
        this.dataListFavorite = dataListFavorite;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return dataListFavorite.size();
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
    public View getView(int position, View listview, ViewGroup parent)
    {
        View v = mInflater.inflate(R.layout.favorite_item, parent, false);
        TextView content = (TextView) v.findViewById(tv_content_favorite);
        TextView part_ID = (TextView) v.findViewById(R.id.tv_page_no);
        TextView page_no = (TextView) v.findViewById(R.id.tv_part_id);
        TextView part_ID2 = (TextView) v.findViewById(R.id.tv_page_no2);
        TextView page_no2 = (TextView) v.findViewById(R.id.tv_part_id2);
        Font font = new Font();
        MyAppSetting myAppSetting = new MyAppSetting(context);
        font.set_font(context, myAppSetting.getFont_no(), content,part_ID,part_ID2,page_no,page_no2);

        font.set_font_size(context, myAppSetting.getFont_size(), content);
        content.setText(dataListFavorite.get(position).content);
        page_no.setText(String.valueOf(dataListFavorite.get(position).page_no));
        part_ID.setText(String.valueOf(dataListFavorite.get(position).part_ID));
        return v;
    }
}
