package com.example.domka.menhajAlsaleheen.app.Controller.GoToQuestionController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.MyMessage.MyMessage;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Controller.MainController.Start_Activity;
import com.example.domka.menhajAlsaleheen.app.Controller.PartController.PartContentActivity;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;

public class GoToQuestionActivity extends AppCompatActivity
{
    Button btn_bak;
    Button btn_search;
    RadioButton r_part1;
    RadioButton r_part2;
    RadioButton r_part3;
    EditText et_q_no;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_question);
        My_refrence();
        My_ClickListner();
        final MyAppSetting myAppSetting = new MyAppSetting(this);
        Integer font_no = myAppSetting.getFont_no();
        Font font = new Font();
        font.set_font(this, font_no, r_part1, r_part2, r_part3, btn_bak, btn_search, et_q_no);
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
        btn_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                search_fun();
            }
        });
    }

    private void search_fun()
    {
        if (et_q_no.getText().toString().trim().equals(""))
        {
            return;
        }
        Integer part_ID = 1;
        if (r_part1.isChecked())
        {
            part_ID = 1;
        }
        else if (r_part2.isChecked())
        {
            part_ID = 2;
        }
        else
        {
            part_ID = 3;
        }
        Integer page_no;
        PartContent partContent = new PartContent();
        page_no = partContent.getpageInQestion(this, part_ID, Integer.valueOf(et_q_no.getText().toString()));
        if (page_no > 0)
        {
            final Intent i1 = new Intent(this, PartContentActivity.class);
            partContent.setDefultPage(this, part_ID, page_no);
            i1.putExtra("part_id", "" + part_ID);
            i1.putExtra("page_no", "" + page_no);
            i1.putExtra("searched", "" + "مسألة " + et_q_no.getText().toString().trim());
            startActivity(i1);
            overridePendingTransition(R.anim.slideleft_to_right, R.anim.slide_out_bottom);
        }
        else
        {
            MyMessage.ShowMessage(this, "لاتوجد مسألة بهذا الرقم", 1000);
        }
    }

    private void My_refrence()
    {
        btn_bak = (Button) findViewById(R.id.btn_back);
        btn_search = (Button) findViewById(R.id.btn_search);
        r_part1 = (RadioButton) findViewById(R.id.radioButton_part1);
        r_part2 = (RadioButton) findViewById(R.id.radioButton_part2);
        r_part3 = (RadioButton) findViewById(R.id.radioButton_part3);
        et_q_no = (EditText) findViewById(R.id.et_q_no);
    }
}
