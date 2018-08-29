package com.example.domka.menhajAlsaleheen.app.Controller.PartController;

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
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.Adapter.ListItemAdapter;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.Adapter.listItem;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;
import com.example.domka.menhajAlsaleheen.database.Table;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity
{
    public static ListView listviewDetails;
    public static List<String> my_listitem;
    final ArrayList<listItem> listitem = new ArrayList<>();
    public int part_ID;
    public Table table;


    Button btn_back;

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        MyReference();

        //------------------------------------------
        Intent i = getIntent();
        part_ID = Integer.valueOf(i.getStringExtra("part_ID"));
        //---------------------------------------------
        listviewDetails = (ListView) findViewById(R.id.list_view_item);
        ListItemAdapter adapter = new ListItemAdapter(this, listitem);
        listviewDetails.setAdapter(adapter);
        setListItemDetails(listitem);
        MyOnClickLister();
        Font font = new Font();
        final MyAppSetting myAppSetting = new MyAppSetting(this);
        Integer font_no = myAppSetting.getFont_no();
        font.set_font(this, font_no, tv_title);
    }

    private void MyOnClickLister()
    {
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        listviewDetails.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int page_no = Integer.valueOf(table.get(position, "page_no"));
                String title = String.valueOf(table.get(position, "title"));
                open_page_content(page_no,title);
            }
        });
    }

    private void MyReference()
    {
        btn_back = (Button) findViewById(R.id.btn_back);

        tv_title =(TextView) findViewById(R.id.tv_title);
    }



    private void open_page_content(int page_no,String title)
    {
        PartContent partContent = new PartContent();
        partContent.setDefultPage(this, part_ID, page_no);
        Intent i = new Intent(this, PartContentActivity.class);
        i.putExtra("part_id", ""+ part_ID);
        i.putExtra("searched", title);

       startActivity(i);
        finish();
        //    onBackPressed();
//        Intent i = new Intent(this,PartContentActivity.class);
//        startActivity(i);
    }

    private void setListItemDetails(ArrayList<listItem> listitem)
    {
        com.example.domka.menhajAlsaleheen.app.Model.List.List list = new com.example.domka.menhajAlsaleheen.app.Model.List.List();
        table = list.getAllList(this, part_ID);
        for (int i = 0; i < table.ROWS; i++)
        {
            listitem.add(new listItem(table.get(i, "title"), table.get(i, "page_no")));
        }
    }
}
