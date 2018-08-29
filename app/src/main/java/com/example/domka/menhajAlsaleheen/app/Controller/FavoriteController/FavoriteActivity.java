package com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter.ListFavoriteAdapter;
import com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter.ListItemFavorite;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;
import com.example.domka.menhajAlsaleheen.database.Table;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity
{
    Button btn_bak;
    public int part_ID;
    public int page_no;
    public String content;
    TextView tv_title;
    TextView tv_content_favorite;
    public Table table;
    public static List<String> my_listItem_favorite;
    ArrayList<ListItemFavorite> dataItemFavorite = new ArrayList<>();
    public static ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        My_refrence();
        My_ClickListner();
        dataItemFavorite = new ArrayList<ListItemFavorite>();
        setListItemDetails(dataItemFavorite);
        LoadMySetting();
    }
    private void LoadMySetting()
    {
        MyAppSetting myAppSetting = new MyAppSetting(this);

        Font font = new Font();
        font.set_font(this, myAppSetting.getFont_no(), tv_content_favorite,tv_title);
        font.set_font_size(this, myAppSetting.getFont_size(), tv_content_favorite);

    }

    private void My_ClickListner()
    {
        final Intent i0 = new Intent(this, Start_Activity.class);
        //------ back button onclick ---------------------
        btn_bak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i0);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                openFavoritepage(position);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private void openFavoritepage(int position)
    {
        final Intent i1 = new Intent(this, FavoriteCardActivity.class);
        page_no = Integer.valueOf(table.get(position, "page_no"));
        part_ID = Integer.valueOf(table.get((position), "part_ID"));
        content = table.get((position), "content");
        PartContent partContent = new PartContent();
        partContent.setDefultPage(this, part_ID, page_no);
        i1.putExtra("part_id", "" + part_ID);
        i1.putExtra("page_no", "" + page_no);
        i1.putExtra("content", "" + content);
        startActivity(i1);
        overridePendingTransition(R.anim.myanimation, R.anim.slide_in_top);
    }

    @Override
    protected void onResume()
    {
        dataItemFavorite = new ArrayList<ListItemFavorite>();
        setListItemDetails(dataItemFavorite);
        super.onResume();
    }

    private void My_refrence()
    {
        btn_bak = (Button) findViewById(R.id.btn_back);
        mListView = (ListView) findViewById(R.id.list_view_favorite);
        tv_content_favorite = (TextView) findViewById(R.id.tv_content_favorite);
        tv_title = (TextView) findViewById(R.id.tv_title);

    }

    private void setListItemDetails(ArrayList<ListItemFavorite> ListItemFavorite)
    {
        ListFavoriteAdapter adapter = new ListFavoriteAdapter(this, dataItemFavorite);
        mListView.setAdapter(adapter);
        PartContent partContent = new PartContent();
        table = partContent.getFavoritePages(this);
        if (table.ROWS > 0)
        {
            for (int i = 0; i < table.ROWS; i++)
            {
                ListItemFavorite.add(new ListItemFavorite(table.get(i, "content"), Integer.valueOf(table.get(i, "page_no")), Integer.valueOf               (table.get(i, "part_ID"))));
            }
        }
    }
}
