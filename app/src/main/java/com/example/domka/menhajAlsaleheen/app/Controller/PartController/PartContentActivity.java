package com.example.domka.menhajAlsaleheen.app.Controller.PartController;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
//import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.MyMessage.MyMessage;
import com.example.domka.menhajAlsaleheen.app.Controller.NoteController.NoteCardActivity;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.Adapter.listItem;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.CoverFlow.CoverFlowActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.SearchController.SearchActivity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;
import com.example.domka.menhajAlsaleheen.app.Others.FontSettingActivity;
import com.example.domka.menhajAlsaleheen.database.Table;

import java.util.ArrayList;
import java.util.List;

public class PartContentActivity extends AppCompatActivity
{
    private float x1, x2;
    static final int MIN_DISTANCE = 150;
    TextView tv_page_content2;
    //------------------------
    public ProgressDialog pd;
    //--------------
    int foot_note_open = 0;
    // --------------------
    Button btn_back;
    Button btn_setting;
    Button btn_increment;
    Button btn_decremnet;
    Button btn_s_search;
    public static Table table;
    public int defult_page_no = 1;
    public TextView tv_page_content;
    int last_page;
    public String night_read = "no";
    LinearLayout cardView;
    CardView cardView2;
    //------------------
    EditText et_search;
    TextView tv_current_page;
    int current_page;
    String text_format = "no";
    //-----------------------------
    Button btn_next;
    Button btn_previous;
    Button btn_s_night_read;
    Button btn_s_change_font;
    Button btn_s_set_favorite;
    Button btn_s_add_note_page_content;
    Button btn_s_share_page_content;
    Button btn_s_text_format;
    Button btn_list;
    Button btn_footnote;
    //---------------------------------
    Integer open_setting = 0;
    //-------------------------------
    Integer part_ID;
    //---------------------------------
    LinearLayout setting_linear;
    LinearLayout for_scroll;
    LinearLayout li_setting2;
    int progress1 = 0;
    LinearLayout li_font_size;
    LinearLayout li_font_type;
    LinearLayout li_share;
    LinearLayout li_night;
    LinearLayout li_save;
    LinearLayout li_settingFont;
    LinearLayout li_for_search;
    LinearLayout li_footnote;
    ScrollView s1;
    SearchView search_view_content;
    //------------------------
    SeekBar seekBar;
    String searched;
    //----- for adapter -----
    public static List<String> my_listItem;
    final ArrayList<listItem> dataItem = new ArrayList<>();

    //--------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_content);
        //-------  waitting to grt defult page function  -------------
        MyRefrence();
        MyCilckListener();
        MyAnimation();
        //--------------------
        //--------------------
        //----------------
        part_ID = MyGetPart_ID();
        //--------------------
        MyGetAllPage();
        //--------------------
        LoadMySetting();


    }

    private void LoadMySetting()
    {
        MyAppSetting myAppSetting = new MyAppSetting(this);
        setFont(myAppSetting.getFont_no());
        set_night_read(myAppSetting.getNight_read().equals("no"));
        font_size(myAppSetting.getFont_size());
        text_format_fun();
    }

    private void font_size(Integer font_size)
    {
        Font font = new Font();
        font.set_font_size(this, font_size, tv_page_content, tv_page_content2);
    }
    //-------------------------------------------------
    //--------------- Other Method ------------------
    //-------------------------------------------------

    @Override
    protected void onResume()
    {
        LoadMySetting();
        super.onResume();
    }

    private Integer MyGetPart_ID()
    {
        Intent intent = getIntent();
        part_ID = Integer.valueOf(intent.getStringExtra("part_id"));

        return part_ID;
    }

    private void MyGetPage(int current_page, String type)
    {
        if (current_page <= last_page)
        {
            set_btn_s_save_color(current_page);
            //-------------------------------
            String page_content = "";
            String footnote = "";
            try
            {
                page_content = table.get(current_page, "content");
                footnote = table.get(current_page, "footnote");
                tv_current_page.setText(table.get(current_page, "page_no").replace("\n", " "));
            }
            catch (Exception e)
            {
                //
            }
            String content = page_content;
            if (text_format.equals("yes"))
            {
                content = page_content.replace("\n", " ").replace(" مسألة", "\n \n مسألة").replace(":", " : \n");
            }


            tv_page_content.setText(content);
            tv_page_content2.setText(footnote);
            if (footnote.trim().equals(""))
            {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_to_left);
                btn_footnote.startAnimation(animation1);
                btn_footnote.setVisibility(View.GONE);
            }
            else
            {
                btn_footnote.setVisibility(View.VISIBLE);
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_to_left);
                btn_footnote.startAnimation(animation1);
            }
            ///////
            if (type.equals("next"))
            {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideleft_to_right);
                cardView.startAnimation(animation1);
            }
            else if (type.equals("0"))
            {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                cardView.startAnimation(animation1);
            }
            else if (type.equals("format"))
            {
            }
            else
            {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideleft_to_right);


                cardView.startAnimation(animation1);


                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sliderite_to_left);


                cardView.startAnimation(animation2);
            }
            try
            {
                Intent intent = getIntent();

                searched = (intent.getStringExtra("searched"));
                if(searched==null){

                    ColorSearchedText(" مسألة");
                }
                else {
                    ColorSearchedText(searched);
                }

            }catch (Exception e)
            {
                ColorSearchedText(" مسألة");
            }


        }
    }

    private void MyGetAllPage()
    {
        //------------------------------------------------------
        PartContent partContent = new PartContent();
        table = partContent.getAllpart_content(this, part_ID);
        last_page = table.ROWS - 1;
        seekBar.setMax(last_page);
        //------------------------------
        current_page = partContent.getDefultPage(this, part_ID) - 1;
        seekBar.setProgress(current_page);
        //----------------------------
        MyGetPage(current_page, "next");

    }

    private void set_night_read(boolean night)
    {
        if (night)
        {
            cardView.setBackground(getResources().getDrawable(R.drawable.h_black));
            tv_page_content.setTextColor(getResources().getColor(R.color.my_white_coloer));
            btn_s_night_read.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
            tv_current_page.setTextColor(getResources().getColor(R.color.my_white_coloer));
        }
        else
        {
            cardView.setBackgroundColor(getResources().getColor(R.color.my_white_coloer));
            tv_page_content.setTextColor(getResources().getColor(R.color.my_black_coloer));
            btn_s_night_read.setBackgroundResource(R.drawable.ic_visibility_white_24dp);
            tv_current_page.setTextColor(getResources().getColor(R.color.my_black_coloer));
        }
    }

    private void initializeVariables()
    {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tv_page_content = (TextView) findViewById(R.id.tv_page_content);
    }
    //----------------------------------
    //--------------- Basic Methods ------------------
    //----------------------------------

    private void MyRefrence()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_increment = (Button) findViewById(R.id.btn_s_increment);
        btn_decremnet = (Button) findViewById(R.id.btn_s_decrement);
        tv_page_content = (TextView) findViewById(R.id.tv_page_content);
        cardView = (LinearLayout) findViewById(R.id.card_view);
        // cardView2 = (CardView) findViewById(R.id.card_view2);
        tv_current_page = (TextView) findViewById(R.id.tv_current_page);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_previous = (Button) findViewById(R.id.btn_previous);
        setting_linear = (LinearLayout) findViewById(R.id.li_setting);
        li_setting2 = (LinearLayout) findViewById(R.id.li_setting2);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        for_scroll = (LinearLayout) findViewById(R.id.li_for_scroll);

        li_for_search = (LinearLayout) findViewById(R.id.li_for_search);
        li_settingFont =(LinearLayout) findViewById(R.id.li_setting_font_size);
        //----------------------------------------------------------------
        btn_s_night_read = (Button) findViewById(R.id.btn_s_night_read);
        btn_s_set_favorite = (Button) findViewById(R.id.btn_s_set_favorite);
        btn_s_share_page_content = (Button) findViewById(R.id.btn_s_share_page_content);
        btn_s_text_format = (Button) findViewById(R.id.btn_s_text_format);
        btn_s_add_note_page_content = (Button) findViewById(R.id.btn_s_add_note_page_content);
        btn_s_search = (Button) findViewById(R.id.btn_s_search);
        btn_s_change_font =(Button) findViewById(R.id.btn_s_font_type);
        //------------------------------------------------
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //-------------------------------------------------
        btn_list = (Button) findViewById(R.id.btn_list);
        search_view_content = (SearchView) findViewById(R.id.search_view_content);
        s1 =(ScrollView) findViewById(R.id.s1);
        tv_page_content2 = (TextView) findViewById(R.id.tv_page_content2);
        btn_footnote = (Button) findViewById(R.id.btn_footnote);
        li_footnote = (LinearLayout) findViewById(R.id.li_foot_note);

        // li_font_size = (LinearLayout) findViewById(R.id.li_font_size);
        // li_font_type = (LinearLayout) findViewById(R.id.li_font_type);
        //li_share = (LinearLayout) findViewById(R.id.li_share);
        //  li_save = (LinearLayout) findViewById(R.id.li_save);
        //   li_night = (LinearLayout) findViewById(R.id.li_night);
        // li_text_format = (LinearLayout) findViewById(R.id.li_text_format);
    }

    @Override
    public void onBackPressed()
    {
        if (foot_note_open == 1)
        {
            Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom);
            li_footnote.startAnimation(animation1);
            li_footnote.setVisibility(View.GONE);
            foot_note_open = 0;
        }
        else
        {
            PartContent partContent = new PartContent();
            partContent.setDefultPage(this, part_ID, current_page + 1);
            finish();
        }
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
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        btn_back.startAnimation(animation1);
    }

    private void MyCilckListener()
    {
        btn_footnote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (foot_note_open == 0)
                {
                    li_footnote.setVisibility(View.VISIBLE);
                    foot_note_open = 1;
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
                    li_footnote.startAnimation(animation1);
                }
                else
                {
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom);
                    li_footnote.startAnimation(animation1);
                    li_footnote.setVisibility(View.GONE);
                    foot_note_open = 0;
                }
            }
        });
        final Intent i0 = new Intent(this, CoverFlowActivity.class);
        //----------------------------------
        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               finish();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (current_page < last_page)
                {
                    current_page += 1;
                    MyGetPage(current_page, "next");
                }
                else
                {
                    MyGetPage(last_page, "next");
                    current_page = last_page;
                }
            }
        });
        //------------------------------
        btn_previous.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (current_page > 0)
                {
                    current_page -= 1;
                    MyGetPage(current_page, "previous");
                }
                else
                {
                    MyGetPage(0, "previous");
                    current_page = 0;
                }
            }
        });
        //------------------------------
        btn_setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (open_setting == 0)
                {
                    setSettingVisible(true);
                    //  cardView2.setVisibility(View.VISIBLE);
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_to_left);
                    setting_linear.startAnimation(animation1);
                    //-----------------------
                    animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
                    btn_setting.startAnimation(animation1);
                    open_setting = 1;
                }
                else
                {
                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise2);
                    btn_setting.startAnimation(animation1);
                    setSettingVisible(false);
                    //   cardView2.setVisibility(View.GONE);
                    open_setting = 0;
                }
            }
        });
        //-------------------------------------------------------------
        final MyAppSetting myAppSetting = new MyAppSetting(this);
        btn_s_night_read.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                night_read = myAppSetting.getNight_read();
                if (night_read.equals("yes"))
                {
                    showMessage("قراءة ليلية");
                    set_night_read(true);
                    myAppSetting.setNight_read("no");
                }
                else
                {
                    showMessage("قرائة نهارية");
                    set_night_read(false);
                    myAppSetting.setNight_read("yes");
                }
            }
        });
        //-----------------------------------------------------------
        //-----------------------------------------------------------
        s1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

              //  Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise2);
               // btn_setting.startAnimation(animation1);
                setSettingVisible(false);

                open_setting = 0;
                return false;
            }
        });

        cardView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {



                System.out.println(event.getX());
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (deltaX > 0)
                        {
                            if (current_page > last_page)
                            {
                                current_page = last_page - 1;
                                return true;
                            }
                            if (Math.abs(deltaX) > MIN_DISTANCE)
                            {
                                if (current_page < last_page)
                                {
                                    current_page += 1;
                                    MyGetPage(current_page, "next");
                                }
                                else
                                {
                                    MyGetPage(last_page, "next");
                                    current_page = last_page;
                                }
                            }
                        }
                        else
                        {
                            if (current_page < 0)
                            {
                                current_page = 0;
                                return true;
                            }
                            if (Math.abs(deltaX) > MIN_DISTANCE)
                            {
                                if (current_page != 0)
                                {
                                    current_page -= 1;
                                    MyGetPage(current_page, "previous");
                                }
                                else
                                {
                                    MyGetPage(0, "previous");
                                    current_page = 0;
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });

        //-----------------------
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                // DO SOMETHING
                progress1 = progress;
                tv_current_page.setText(String.valueOf(progress1 + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                current_page = progress1;
                MyGetPage(current_page, "0");
            }
        });
        btn_s_share_page_content.setOnClickListener(new View.OnClickListener()
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
        //---------------------------
        final Intent i = new Intent(PartContentActivity.this, ListActivity.class);
        btn_list.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        i.putExtra("part_ID", "" + part_ID);
                        startActivity(i);
                        finish();
                    }
                });
        //------------------------------
        btn_s_set_favorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                save_fun();
            }
        });
        final Intent i_font = new Intent(this, FontSettingActivity.class);
        btn_s_change_font.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i_font);
            }
        });
        btn_s_text_format.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (text_format.equals("yes"))
                {
                    myAppSetting.setText_format("no");
                }
                else
                {
                    myAppSetting.setText_format("yes");
                }
                text_format_fun();
            }
        });
        search_view_content.setOnQueryTextListener(new SearchView.OnQueryTextListener()
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
        search_view_content.setOnSearchClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                if (open_setting == 0)
//                {
//
//                }
//                else
//                {
//                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise2);
//                    btn_setting.startAnimation(animation1);
//                    setSettingVisible(false);
//                    //   cardView2.setVisibility(View.GONE);
//                    open_setting = 0;
//                }

            }
        });
        search_view_content.setOnCloseListener(new SearchView.OnCloseListener()
        {
            @Override
            public boolean onClose()
            {
             ColorSearchedText(" مسألة");
                return false;
            }
        });
        final Intent i_note = new Intent(this, NoteCardActivity.class);
        btn_s_add_note_page_content.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i_note.putExtra("part_ID", "" + part_ID);
                i_note.putExtra("page_no", "" + tv_current_page.getText());
                startActivity(i_note);
                overridePendingTransition(R.anim.myanimation, R.anim.slide_in_top);
                //show notecard
            }
        });
        btn_increment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               increment_fun();
            }
        });
        btn_decremnet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                decrement_fun();

            }
        });
        final Intent i_search = new Intent(this, SearchActivity.class);
        btn_s_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i_search);
            }
        });
    }

    private void decrement_fun()
    {
        MyAppSetting myAppSetting = new MyAppSetting(this);
        if(myAppSetting.getFont_size() >10){
            myAppSetting.setFont_size_decrement();
            myAppSetting = new MyAppSetting(this);
            font_size(myAppSetting.getFont_size());

        }

    }

    private void increment_fun()
    {
        MyAppSetting myAppSetting = new MyAppSetting(this);
        if(myAppSetting.getFont_size() < 50)
        {
            myAppSetting.setFont_size_increment();
            myAppSetting = new MyAppSetting(this);
            font_size(myAppSetting.getFont_size());
        }

    }

    private void search_fun()
    {
        Table table;
        PartContent partContent = new PartContent();
        table = partContent.getSearchPage(this, part_ID, search_view_content.getQuery().toString());
        if (table.ROWS > 0)
        {
            current_page = Integer.valueOf(table.get(0, "page_no"));
            MyGetPage(current_page - 1, "next");
            ColorSearchedText(search_view_content.getQuery().toString());

        }
        else
        {
            showMessage("لاتوجد نتائج");
        }
    }

    private void ColorSearchedText(String searched)
    {



        String mySearchedString = searched + " ";
        StringBuilder textBuilder = new StringBuilder(tv_page_content.getText().toString());
        StringBuilder searchedTextBuilder = new StringBuilder((mySearchedString));
        SpannableString spannableString = new SpannableString(tv_page_content.getText().toString());

        int counter = 0;
        int index = 0;

        for (int i = 0;i < textBuilder.length() - mySearchedString.length();i++)
        {
            counter = 0;
            if (Character.toLowerCase(textBuilder.charAt(i)) == Character.toLowerCase(searchedTextBuilder.charAt(index)))
            {
                counter++;
                index++;
                for (int j = 1,z = i + 1;j < mySearchedString.length() - 1;j++,z++)
                {
                    if (Character.toLowerCase(textBuilder .charAt(z)) == Character.toLowerCase(searchedTextBuilder .charAt(index)))
                    {
                        counter++;
                        index++;
                    }
                    else
                    {
                        index++;
                        if (index % mySearchedString.length() == 0)
                        {
                            index = 0;
                        }
                        break;
                    }
                }
                if (counter == mySearchedString.length() - 1) // A match
                {

                    if(searched.equals(" مسألة"))
                    {
                        spannableString.setSpan(new ForegroundColorSpan(Color.RED), i,
                                i + mySearchedString.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Do the change you want(In this case changing the fore ground color to red)

                        index = 0;
                        continue;
                    }
                    else
                    {
                        final MyAppSetting myAppSetting = new MyAppSetting(this);
                        if(myAppSetting.getNight_read().equals("yes"))
                        {
                            spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), i,
                                    i + mySearchedString.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Do the change you want(In this case changing the fore ground color to red)
                            spannableString.setSpan(new BackgroundColorSpan(Color.rgb(250, 81, 70)), i,
                                    i + mySearchedString.length() -1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Do the change you want(In this case changing the fore ground color to red)

                            index = 0;
                            continue;
                        }
                        else
                        {
                            spannableString.setSpan(new ForegroundColorSpan(Color.RED), i,
                                    i + mySearchedString.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Do the change you want(In this case changing the fore ground color to red)
                            spannableString.setSpan(new BackgroundColorSpan(Color.rgb(240,240,240)), i,
                                    i + mySearchedString.length() -1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // Do the change you want(In this case changing the fore ground color to red)

                            index = 0;
                            continue;
                        }

                    }

                }
                else
                {
                    index = 0;
                    continue;
                }
            }
        }
        tv_page_content.setText(spannableString);
    }

    private void setSettingVisible(boolean b)
    {
        if (b)
        {
            setting_linear.setVisibility(View.VISIBLE);
            li_setting2.setVisibility(View.VISIBLE);
            btn_s_night_read.setVisibility(View.VISIBLE);
            setting_linear.setVisibility(View.VISIBLE);
            li_settingFont.setVisibility(View.VISIBLE);
            btn_s_share_page_content.setVisibility(View.VISIBLE);
            search_view_content.setVisibility(View.VISIBLE);
            li_for_search.setVisibility(View.VISIBLE);
        }
        else
        {
            setting_linear.setVisibility(View.GONE);
            li_setting2.setVisibility(View.GONE);
            btn_s_night_read.setVisibility(View.GONE);
            li_settingFont.setVisibility(View.GONE);
            setting_linear.setBackground(null);
            btn_s_share_page_content.setVisibility(View.GONE);
            search_view_content.setVisibility(View.GONE);
            li_for_search.setVisibility(View.GONE);
        }
    }

    private void showMessage(String s)
    {
        MyMessage.ShowMessage(this, s, 800);
    }

    private void text_format_fun()
    {
        MyAppSetting myAppSetting = new MyAppSetting(this);
        text_format = myAppSetting.getText_format();
        if (text_format.equals("yes"))
        {
            btn_s_text_format.setBackgroundResource(R.drawable.ic_playlist_add_check_white_24dp);
        }
        else
        {
            btn_s_text_format.setBackgroundResource(R.drawable.ic_playlist_add_check_black_24dp);
        }
        MyGetPage(current_page, "format");
    }

    private void save_fun()
    {
        final PartContent partContent = new PartContent();
        if (partContent.getIfFavorite(this, part_ID, current_page + 1))
        {
            ;
            partContent.setFavorite(this, part_ID, current_page + 1);
            btn_s_set_favorite.setBackgroundResource(R.drawable.ic_bookmark_border_white_24dp);
        }
        else
        {
            partContent.unsetFavorite(this, part_ID, current_page + 1);
            btn_s_set_favorite.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
        }
        set_btn_s_save_color(current_page);
    }

    private void set_btn_s_save_color(int current_page)
    {
        final PartContent partContent = new PartContent();
        if (partContent.getIfFavorite(this, part_ID, current_page + 1))
        {
            btn_s_set_favorite.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
        }
        else
        {
            btn_s_set_favorite.setBackgroundResource(R.drawable.ic_bookmark_border_white_24dp);
        }
    }
}
