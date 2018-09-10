package com.example.domka.menhajAlsaleheen.app.Controller.MainController;

import android.content.DialogInterface;
import android.content.Intent;
//import android.graphics.Color;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.domka.menhajAlsaleheen.app.Others.AboutDeveloperActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.NoteController.NoticeActivity;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.FavoriteController.FavoriteActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.GoToQuestionController.GoToQuestionActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.CoverFlow.CoverFlowActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.SearchController.SearchActivity;
import com.example.domka.menhajAlsaleheen.app.Controller.ThematicController.ThematicActivity;
import com.example.domka.menhajAlsaleheen.app.Others.AboutAppActivity;
import com.example.domka.menhajAlsaleheen.app.Others.ConnectUsActiviyt;
import com.example.domka.menhajAlsaleheen.app.Others.FontSettingActivity;
import com.example.domka.menhajAlsaleheen.app.Others.MapActivity;
import com.example.domka.menhajAlsaleheen.database.DatabaseAdapter;
import com.example.domka.menhajAlsaleheen.database.DatabaseHelper;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.utils.SpotlightListener;

import java.io.IOException;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
//import com.github.amlcurran.showcaseview.ShowcaseView;
//import com.github.amlcurran.showcaseview.targets.ViewTarget;
//import com.wooplr.spotlight.SpotlightView;
//import com.wooplr.spotlight.utils.SpotlightListener;

public class Start_Activity extends AppCompatActivity
{
    Button btn_setting;
    //----------------------
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    //--------------------------
    LinearLayout li_Show_part;
    LinearLayout li_goto_question;
    LinearLayout li_show_favorite;
    LinearLayout li_show_search;
    LinearLayout li_show_notice;
    LinearLayout li_show_thematic;
    LinearLayout li_content;
    LinearLayout li_menhaj;
    LinearLayout li_low;
    Typeface tf1;
    //----------------------------
    Integer co = 0;
    // drawer
    private Drawer drawer;
      private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //-----------------------------
        tryToCreateDatabase();
        //------------------
        MyRefrence();
        MyCilckListener();
        MyAnimation();
        //-----------------------------
        setFont(2);
        //--------------------
        set_learnnig1();
        set_drawer();

//        PartContent partContent = new PartContent();
//        partContent.setID(2);
//        ThematicDevision thematicDevision = new ThematicDevision();
//        Integer[] IDSArray;
//        IDSArray = thematicDevision.get_parents_IDS(this, 6, "");
//        Integer[] IDSArray2;
//        IDSArray2 = thematicDevision.getChildeID(this, 4);
//
//        String s = "";
//        for (int i = 0; i < IDSArray.length ; i++)
//        {
//            s = s + String.valueOf(IDSArray[i]) + "|";
//        }
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
//
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void set_learnnig1()
    {
        new SpotlightView.Builder(this)
                .introAnimationDuration(100)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(200)
                .headingTvColor(Color.parseColor("#EEEEEE"))
                .headingTvSize(13)
                .headingTvText("كتاب منهاج الصالحين \n فتاوى سماحة اية الله العظمى \n السيد على الحسيني السيستاني(دام ظلة)")
                .subHeadingTvColor(Color.parseColor("#81D4FA"))
                .subHeadingTvSize(22)
                .subHeadingTvText("التالي")
                .maskColor(Color.parseColor("#424242"))
                .target(li_menhaj)
                .lineAnimDuration(100)
                .lineAndArcColor(Color.parseColor("#EEEEEE"))
                .dismissOnTouch(true)
                .setTypeface(tf1)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .setListener(new SpotlightListener()
                {
                    @Override
                    public void onUserClicked(String s)
                    {
                        Ask_fun();

                    }
                })
                .show();
//
    }

    private void Ask_fun()
    {
        new AlertDialog.Builder(this)
                .setMessage("بدء الجولة التعريفيه")
                .setCancelable(false)
                .setPositiveButton("             نعم             ", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        set_learnnig2();
                    }
                })
                .setNegativeButton("          الغاء         ", null)
                .show();

    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setMessage("سوف تخرج من التطبيق ؟")
                .setCancelable(false)
                .setPositiveButton("             نعم             ", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                      CloseAll();
                    }
                })
                .setNegativeButton("          الغاء         ", null)
                .show();
    }

    private void CloseAll()
    {
        ActivityCompat.finishAffinity(this);
        finish();
    }

    private void set_learnnig2()
    {

//        new ShowcaseView.Builder(this)
//                .setTarget(new ViewTarget(li_show_favorite))
//                .setContentTitle("المفضلة")
//                .setContentText("اضغط على هذا الزر اذا اردت مشاهدة المضلة")
//                .build();
        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setFadeDuration(100);
        config.setRenderOverNavigationBar(false);
        config.setDelay(400); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
        sequence.setConfig(config);
        sequence.addSequenceItem(li_Show_part,
                "تصفح اجزاء الكتاب \n يمكنك تصفح ومشاركة صفحات الاجزاء الثلاثة لكتاب منهاج الصالحين الصادر عن ال سماحة السيد السيستاني ( دام ظلة ) ", "التالي");
        sequence.addSequenceItem(li_show_notice,
                "المحفوظات \n يمكنك مشاهدة ملاحظاتك على الصفحات", "التالي").setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener()
        {
            @Override
            public void onDismiss(MaterialShowcaseView materialShowcaseView, int i)
            {
                switch (co)
                {
                    case (0):
                    {
                        YoYo.with(Techniques.Bounce).duration(700).playOn(li_show_notice);
                        co += 1;
                        break;
                    }
                    case (1):
                    {
                        YoYo.with(Techniques.Bounce).duration(700).playOn(li_goto_question);
                        co += 1;
                        break;
                    }
                    case (2):
                    {
                        YoYo.with(Techniques.Bounce).duration(700).playOn(li_show_favorite);
                        co += 1;
                        break;
                    }
                    case (3):
                    {
                        YoYo.with(Techniques.Bounce).duration(700).playOn(li_show_thematic);
                        co += 1;
                        break;
                    }
                }
            }
        });
        sequence.addSequenceItem(li_goto_question,
                "يمكنك ذهاب الى مسألة بواسطة كتابة رقم المسالة", "التالي");
        sequence.addSequenceItem(li_show_favorite,
                "يمكنك مشاهدة ما تم حفظه مسبقاً من الصفحات", "التالي");
        sequence.addSequenceItem(li_show_search,
                "واجهه للبحث في جميع اجزاء الكتاب", "تم");
        YoYo.with(Techniques.Bounce).duration(400).playOn(li_Show_part);
        sequence.start();
    }

    private void tryToCreateDatabase()
    {
        try
        {
            DatabaseAdapter db = new DatabaseAdapter(this);
            db.open();
            db.close();
        }
        catch (Exception e)
        {
            try
            {
                DatabaseHelper databaseHelper = new DatabaseHelper(this);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void MyAnimation()
    {
        YoYo.with(Techniques.BounceInLeft)
                .duration(2500)
                .playOn(li_show_search);
        YoYo.with(Techniques.BounceInLeft)
                .duration(2500)
                .playOn(li_content);
        AnimationSet rollingIn = new AnimationSet(true);
        Animation moving = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1f, Animation.RELATIVE_TO_PARENT, 50, Animation.RELATIVE_TO_PARENT, 50, Animation.RELATIVE_TO_PARENT, 50);
        moving.setDuration(900);
       // rollingIn.addAnimation(moving);
      //  li_content.startAnimation(rollingIn);
       // Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_down_to_up);
       // li_content.startAnimation(animation1);

        YoYo.with(Techniques.RotateIn).duration(700)
            .playOn(li_content);

        YoYo.with(Techniques.RotateInDownLeft).duration(900)
                .repeat(1)
                .playOn(li_show_notice);
        YoYo.with(Techniques.RotateInDownLeft).duration(1000)
                .repeat(1)
                .playOn(li_goto_question);
        YoYo.with(Techniques.RotateInDownLeft).duration(1100)
                .repeat(1)
                .playOn(li_show_favorite);
        YoYo.with(Techniques.RotateInDownLeft)
                .repeat(1)
                .delay(500)
                .duration(600)
                .playOn(li_show_search);
        YoYo.with(Techniques.FadeOutUp)
                .duration(0)
                .repeat(1)
                .playOn(li_show_thematic);
        YoYo.with(Techniques.FlipInY)
                .delay(1500)
                .duration(2600)
                .repeat(1)
                .playOn(li_show_thematic);
        YoYo.with(Techniques.FadeOut)
                .delay(0)
                .duration(0)
                .repeat(1)
                .playOn(li_low );
        YoYo.with(Techniques.BounceInLeft)
                .delay(1500)
                .duration(5600)
                .repeat(1)
                .playOn(li_low );
        YoYo.with(Techniques.Wobble)
                .delay(5500)
                .duration(45600)
                .repeat(5)
                .playOn(li_low );
        YoYo.with(Techniques.Flash)
                .delay(7500)
                .duration(600)
                .repeat(12)
                .playOn(li_Show_part);

    }

    private void MyCilckListener()
    {
        final Intent i0 = new Intent(this, AboutAppActivity.class);
        final Intent i1 = new Intent(this, CoverFlowActivity.class);
        final Intent i2 = new Intent(this, GoToQuestionActivity.class);
        final Intent i3 = new Intent(this, FavoriteActivity.class);
        final Intent i4 = new Intent(this, NoticeActivity.class);
        final Intent i5 = new Intent(this, SearchActivity.class);
        final Intent i6 = new Intent(this, ThematicActivity.class);
        //----------------------------------
        li_menhaj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i0);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawer.openDrawer();
            }
        });
        //------ show part activity ---------
        li_Show_part.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i1);
            }
        });
        //------  --------
        li_goto_question.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i2);
                overridePendingTransition(R.anim.myanimation, R.anim.slide_out_bottom);
            }
        });
        //-------  --------
        li_show_favorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i3);
            }
        });
        //-------  --------
        li_show_notice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                startActivity(i4);
            }
        });
        //-------  --------
        li_show_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i5);
            }
        });
        //-------  --------
        li_show_thematic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(i5);
            }
        });
        //-------  --------
    }

    private void MyRefrence()
    {
        btn_setting = (Button) findViewById(R.id.btn_setting);
        //-------------------------
//        t1 = (TextView) findViewById(R.id.t1);
//        t2 = (TextView) findViewById(R.id.textView3);
//        t3 = (TextView) findViewById(R.id.textView2);
//        t4 = (TextView) findViewById(R.id.textView4);
//        t5 = (TextView) findViewById(R.id.t5);
//        t6 = (TextView) findViewById(R.id.t6);
//        t7 = (TextView) findViewById(R.id.textView8);
        //-----------------------------
        li_Show_part = (LinearLayout) findViewById(R.id.li_Show_part);
        li_goto_question = (LinearLayout) findViewById(R.id.li_goto_question);
        li_show_favorite = (LinearLayout) findViewById(R.id.li_show_favorite);
        li_show_notice = (LinearLayout) findViewById(R.id.li_show_notice);
        li_show_search = (LinearLayout) findViewById(R.id.li_show_search);
        li_show_thematic = (LinearLayout) findViewById(R.id.li_show_thematic);
        li_content = (LinearLayout) findViewById(R.id.li_content);
        li_menhaj = (LinearLayout) findViewById(R.id.li_menhaj);
        li_low = (LinearLayout) findViewById(R.id.li_low);
        //-------------------------------------
        AssetManager mngr = this.getAssets();

        tf1 = Typeface.createFromAsset(mngr, "f" + 2 + ".ttf");
    }

    private void setFont(int fontID)
    {
        //------------------------------------------------
//        Font font = new Font();
//        font.set_font(this, fontID, t1, t2, t3, t4, t5, t6, t7);
//        //-----------------------------------------------
    }

    // drawer
    private void set_drawer()
    {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.h_setany).build();
        drawer = new DrawerBuilder().withActivity(this)
                .addDrawerItems(getDrawerItems())
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggle(false)
                .withOnDrawerItemClickListener(clickListener)
                .withAccountHeader(headerResult)
                .build();
    }

    public IDrawerItem[] getDrawerItems()
    {
        IDrawerItem[] items = new IDrawerItem[7];
        PrimaryDrawerItem item = new PrimaryDrawerItem().withIdentifier(1).withName("عن التطبيق").withIcon(R.drawable.d_book);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(7).withName("اعدادت الخط").withIcon(R.drawable.d_seeting);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("بواسطة \n معهد تراث الانبياء");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("تفضل بزيارة موقعنا").withIcon(R.drawable.d_website);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("تابعنا على فيسبوك").withIcon(R.drawable.facebook_logo);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("تواصل معنا").withIcon(R.drawable.d_conect);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("موقعنا على الخارطة").withIcon(R.drawable.d_location);

        items[0] = item;
        items[1] = item1;
        items[2] = item2;
        items[3] = item3;
        items[4] = item4;
        items[5] = item5;
        items[6] = item6;
        return items;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            if (drawer.isDrawerOpen())
            {
                drawer.closeDrawer();
            }
            else
            {
                drawer.openDrawer();
            }
        }
        return true;
    }

    private Drawer.OnDrawerItemClickListener clickListener = new Drawer.OnDrawerItemClickListener()
    {
        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
        {
            switch ((int) drawerItem.getIdentifier())
            {
                case 1:
                    openAboutActivity();
                    break;
                case 2:

                    break;
                case 3:
                    openWebsite();
                    break;
                case 4:
                    openFacebook();
                    break;
                case 5:
                    openConnectUsActivity();
                    break;
                case 6:
                    openMapActivity();
                    break;
                case 7:

                    openSettingActivity(); //  waite
                    break;
            }
            return true;
        }
    };

    private void openConnectUsActivity()
    {
        Intent i = new Intent(this, ConnectUsActiviyt.class);
        startActivity(i);
    }

    private void openMapActivity()
    {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }

    private void openSettingActivity()
    {
        Intent i = new Intent(this, FontSettingActivity.class);
        startActivity(i);
        drawer.closeDrawer();

    }

    private void openAboutDeveloper()
    {
        Intent i = new Intent(this, AboutDeveloperActivity.class);
        startActivity(i);
    }

    private void openTelegram()
    {
    }

    private void openWebsite()
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://turath-alanbiaa.org/")));
    }

    private void openFacebook()
    {
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/TurathAlanbiaa/"));
            startActivity(intent);
        }
        catch (Exception e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/TurathAlanbiaa/")));
        }
    }

    private void downloadPDF()
    {
    }

    private void openAboutActivity()
    {
        final Intent i = new Intent(this, AboutAppActivity.class);
        startActivity(i);
    }
}
