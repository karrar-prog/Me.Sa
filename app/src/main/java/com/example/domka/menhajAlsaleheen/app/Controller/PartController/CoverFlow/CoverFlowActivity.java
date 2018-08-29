package com.example.domka.menhajAlsaleheen.app.Controller.PartController.CoverFlow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.PartContentActivity;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class CoverFlowActivity extends ActionBarActivity
{
    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private ArrayList<GameEntity> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;
    Button btn_back;
    String s_part = String.valueOf(R.string.title1);
    LayoutInflater inflater ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coverflow);
        mData.add(new GameEntity(R.drawable.p1, R.string.title1));
        mData.add(new GameEntity(R.drawable.p2, R.string.title2));
        mData.add(new GameEntity(R.drawable.p3, R.string.title3));
       mTitle = (TextSwitcher) findViewById(R.id.title);
        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent  intent = new Intent(this,Start_Activity.class);

        btn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            startActivity(intent);

            }
        });
        mTitle.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                inflater = LayoutInflater.from(CoverFlowActivity.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);

        mTitle.setOutAnimation(out);
        mAdapter = new CoverFlowAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);
//        mCoverFlow.setDrawingCacheEnabled(true);
//        mCoverFlow.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        mCoverFlow.setAlignTime(900);
        mCoverFlow.setMaxRotationAngle(150);
        mCoverFlow.setTuningWidgetSize(600);


        final Intent i1 = new Intent(this, PartContentActivity.class);
        i1.putExtra("part_id", "1");
        final Intent i2 = new Intent(this, PartContentActivity.class);
        i2.putExtra("part_id", "2");
        final Intent i3 = new Intent(this, PartContentActivity.class);
        i3.putExtra("part_id", "3");
        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {



               if(s_part.equals("الجزء الاول - العبادات"))
                {
                    startActivity(i1);
                }else  if(s_part.equals("الجزء الثاني - المعاملات"))
               {
                   startActivity(i2);
               }else  if(s_part.equals("الجزء الثالث - المعاملات"))
               {
                   startActivity(i3);
               }

            }
        });
        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener()
        {
            @Override
            public void onScrolledToPosition(int position)
            {
              //  mTitle.setText(getResources().getString(mData.get(position).titleResId));
                s_part = getResources().getString(mData.get(position).titleResId);
            }

            @Override
            public void onScrolling()
            {
               mTitle.setText("");
            }
        });
    }

    @Override
    public void onBackPressed()
    {
//        Intent intent = new Intent(this,Start_Activity.class);
//        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onResume()
    {

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coverflow_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
