package com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.MyMessage.MyMessage;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.CoverFlow.CoverFlowActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.PartContentActivity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;
import com.example.domka.menhajAlsaleheen.database.Table;

public class FavoriteCardActivity extends AppCompatActivity
{
    Button btn_back;
    public static Table table;
    public TextView tv_page_content;
    TextView tv_current_page;
    Integer part_ID;
    Integer page_no;
    Button li_unsave;
    Button li_open;
    Button li_share;
    LinearLayout li_for_scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_card);
        MyRefrence();
        MyCilckListener();
        MyAnimation();
        setContent();
        LoadMySetting();
    }

    private void setContent()
    {
        Intent intent = getIntent();
        part_ID = Integer.valueOf(intent.getStringExtra("part_id"));
        page_no = Integer.valueOf(intent.getStringExtra("page_no"));
        tv_current_page.setText(String.valueOf(page_no));
        PartContent partContent = new PartContent();
        tv_page_content.setText(partContent.get_page_content(this, part_ID, page_no).replace("\n", " ").replace("مسألة", "\n مسألة").replace(":", " : \n"));
    }

    private void LoadMySetting()
    { MyAppSetting myAppSetting = new MyAppSetting(this);
        Font font = new Font();
        font.set_font(this, myAppSetting.getFont_no(), tv_page_content,tv_page_content);
        font.set_font_size(this, myAppSetting.getFont_size(), tv_page_content);
        setFont(myAppSetting.getFont_no());

        if (myAppSetting.getNight_read().equals("yes"))
        {
            set_night_read(false);
        }
        else
        {
            set_night_read(true);
        }
    }

    @Override
    protected void onResume()
    {
        LoadMySetting();
        super.onResume();
    }

    private void MyRefrence()
    {
        btn_back = (Button) findViewById(R.id.btn_back_favorit_card);
        tv_page_content = (TextView) findViewById(R.id.tv_page_content_card);
        tv_current_page = (TextView) findViewById(R.id.tv_current_page);
        li_open = (Button) findViewById(R.id.btn_open_favorit_card);
        li_unsave = (Button) findViewById(R.id.btn_s_unset_favorite_favorit_card);
        li_share = (Button) findViewById(R.id.btn_s_share_favorit_card);
        li_for_scroll = (LinearLayout) findViewById(R.id.li_for_scroll);
    }

    private void setFont(int fontID)
    {
        //------------------------------------------------
        Font font = new Font();
        font.set_font(this, fontID, tv_page_content);
        //-----------------------------------------------
    }

    private void MyAnimation()
    {
//        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//        btn_back.startAnimation(animation1);
    }

    private void MyCilckListener()
    {
        final Intent i0 = new Intent(this, CoverFlowActivity.class);
        //----------------------------------
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        li_open.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openPageIn();
            }
        });
        li_share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String shareBody = "" + tv_page_content.getText() + "   ----- بواسطة تطبيق منهاج الصالحين - الجزء" + part_ID + "  " + "رقم الصفحة" + tv_current_page.getText();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "مشاركة صفحة كتاب");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "مشاركة هذه الصفحة على"));
            }
        });
        li_unsave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                unSaveFun();
            }
        });
    }

    private void set_night_read(boolean night)
    {
        if (night)
        {
            li_for_scroll.setBackgroundColor(getResources().getColor(R.color.my_black_coloer));
            tv_page_content.setTextColor(getResources().getColor(R.color.my_white_coloer));
//            tv_current_page.setTextColor(getResources().getColor(R.color.my_white_coloer));
        }
        else
        {
            li_for_scroll.setBackgroundColor(getResources().getColor(R.color.my_white_coloer));
            tv_page_content.setTextColor(getResources().getColor(R.color.my_black_coloer));
//            tv_current_page.setTextColor(getResources().getColor(R.color.my_black_coloer));
        }
    }

    private void unSaveFun()
    {
        new AlertDialog.Builder(this)
                .setMessage("ازالة من المحفوظات ؟")
                .setCancelable(false)
                .setPositiveButton("               نعم               ", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                  unsave();
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                onBackPressed();
                            }
                        }, 1000);

                    }
                })
                .setNegativeButton("              الغاء             ", null)
                .show();

    }

    private void unsave()
    {
        final PartContent partContent = new PartContent();
        partContent.unsetFavorite(this, part_ID, page_no);
        MyMessage.ShowMessage(this, "تم الغاء الحفظ", 1000);
    }

    private void openPageIn()
    {

        final Intent i1 = new Intent(this, PartContentActivity.class);
        PartContent partContent = new PartContent();
        partContent.setDefultPage(this, part_ID, page_no);
        i1.putExtra("part_id", "" + part_ID);
        i1.putExtra("page_no", "" + page_no);
        startActivity(i1);
        overridePendingTransition(R.anim.slideleft_to_right, R.anim.slide_out_bottom);
    }
}
