package com.example.domka.menhajAlsaleheen.app.Controller.SearchController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter.ListFavoriteAdapter;
import com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.Adapter.ListItemFavorite;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.PartContentActivity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;
import com.example.domka.menhajAlsaleheen.database.Table;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity
{
    Button btn_bak;
    android.support.v7.widget.SearchView search_editText;

    public int part_ID;
    public int page_no;
    public String content;
    TextView tv_content_favorite;
    public Table table;
    public static List<String> my_listItem_favorite;
    ArrayList<ListItemFavorite> dataItemFavorite = new ArrayList<>();
    public static ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        My_refrence();
        My_ClickListner();
        My_Animation();
        //---------------------------

        dataItemFavorite = new ArrayList<ListItemFavorite>();
        setListItemDetails(dataItemFavorite);
        setFont();

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private void openFavoritepage(int position)
    {





        final Intent i1 = new Intent(this, PartContentActivity.class);
        page_no = Integer.valueOf(table.get(position, "page_no"));
        part_ID = Integer.valueOf(table.get((position), "part_ID"));
        content = table.get((position), "content");
        PartContent partContent = new PartContent();
        partContent.setDefultPage(this, part_ID, page_no);
        i1.putExtra("part_id", "" + part_ID);
        i1.putExtra("page_no", "" + page_no);
        i1.putExtra("content", "" + content);
        i1.putExtra("searched", "" + search_editText.getQuery().toString());

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

    private void setListItemDetails(ArrayList<ListItemFavorite> ListItemFavorite)
    {

        ListFavoriteAdapter adapter = new ListFavoriteAdapter(this, dataItemFavorite);
        mListView.setAdapter(adapter);
        PartContent partContent = new PartContent();
        if(search_editText.getQuery().toString().trim().equals(""))
        {
          return;
        }
        table = partContent.getSearchPage(this,search_editText.getQuery().toString().trim());
        if (table.ROWS > 0)
        {
            for (int i = 0; i < table.ROWS; i++)
            {
                ListItemFavorite.add(new ListItemFavorite(table.get(i, "content"), Integer.valueOf(table.get(i, "page_no")), Integer.valueOf(table.get(i, "part_ID"))));
            }
        }
    }

    private void My_Animation()
    {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        btn_bak.startAnimation(animation1);
         animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_to_left);
        search_editText.startAnimation(animation1);

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
                finish();
            }
        });
        //------ -------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                openFavoritepage(position);

            }
        });
        search_editText.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                search_fun();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
    }

    private void search_fun()
    {


        dataItemFavorite = new ArrayList<ListItemFavorite>();
        setListItemDetails(dataItemFavorite);
    }

    private void My_refrence()
    {
        btn_bak = (Button) findViewById(R.id.btn_back);
        search_editText = ((android.support.v7.widget.SearchView) findViewById(R.id.search_view_content));
        mListView = (ListView) findViewById(R.id.list_view_favorite);
        tv_content_favorite = (TextView) findViewById(R.id.tv_content_favorite);
    }
    private void setFont()
    {
        Font font = new Font();
        final MyAppSetting myAppSetting = new MyAppSetting(this);
        Integer font_no = myAppSetting.getFont_no();
        font.set_font(this, font_no, search_editText,tv_content_favorite);
    }
}
