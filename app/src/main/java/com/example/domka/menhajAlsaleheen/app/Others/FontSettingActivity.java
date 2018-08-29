package com.example.domka.menhajAlsaleheen.app.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;

public class FontSettingActivity extends AppCompatActivity
{
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    Button btn_save;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_setting);
        r1 = (RadioButton) findViewById(R.id.radioButton_part1);
        r2 = (RadioButton) findViewById(R.id.radioButton_part2);
        r3 = (RadioButton) findViewById(R.id.radioButton_part3);
        btn_cancel = (Button) findViewById(R.id.btn_cancel_font);
        btn_save = (Button) findViewById(R.id.btn_save_font);
        Font font = new Font();
        font.set_font(this, 1, r1);
        font.set_font(this, 2, r2);
        font.set_font(this, 3, r3);
        final MyAppSetting myAppSetting = new MyAppSetting(this);
        Integer font_no = myAppSetting.getFont_no();
        font.set_font_size(this, font_no, r1, r2, r3);
        font.set_font(this, font_no, btn_cancel, btn_save);
        if (font_no == 1)
        {
            r1.setChecked(true);
        }
        else if (font_no == 2)
        {
            r2.setChecked(true);
        }
        else if (font_no == 3)
        {
            r3.setChecked(true);
        }
        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (r1.isChecked())
                {
                    myAppSetting.setFont_no(1);
                }
                else if (r2.isChecked())
                {
                    myAppSetting.setFont_no(2);
                }
                else if (r3.isChecked())
                {
                    myAppSetting.setFont_no(3);
                }
                finish();
            }
        });
    }
}
