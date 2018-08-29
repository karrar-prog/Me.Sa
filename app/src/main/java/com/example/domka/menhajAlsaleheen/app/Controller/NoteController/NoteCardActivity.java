package com.example.domka.menhajAlsaleheen.app.Controller.NoteController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.domka.menhajAlsaleheen.Font.Font;
import com.example.domka.menhajAlsaleheen.R;
import com.example.domka.menhajAlsaleheen.app.Model.MyAppSetting.MyAppSetting;
import com.example.domka.menhajAlsaleheen.app.Model.PartContent.PartContent;

public class NoteCardActivity extends AppCompatActivity
{
    Button btn_save_note;
    Button btn_cancel;
    Integer part_ID = 0;
    Integer page_no = 0;
    PartContent partContent = new PartContent();
    EditText et_note;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);
        MyReferences();
        get_info();
        MyOnClick();
        LoadMySetting();
    }

    private void get_info()
    {
        Intent i = getIntent();
        part_ID = Integer.valueOf(i.getStringExtra("part_ID"));
        page_no = Integer.valueOf(i.getStringExtra("page_no"));
        String note = partContent.getNoticOfPage(this,part_ID,page_no);
        if(note.equals("no"))
        {
            et_note.setText(" ");
        }else
        {
            et_note.setText(note);
        }


    }
    private void LoadMySetting()
    { MyAppSetting myAppSetting = new MyAppSetting(this);
        Font font = new Font();
        font.set_font(this, myAppSetting.getFont_no(), et_note, et_note,btn_cancel,btn_save_note);
        font.set_font_size(this, myAppSetting.getFont_size() );

    }
    private void MyOnClick()
    {
        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        btn_save_note.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateNote();
            }
        });
    }

    private void updateNote()
    {
        String note =  et_note.getText().toString();
        if(note.trim().equals(""))
        {
            et_note.setText("no");
        }else
        {
            et_note.setText(note);
        }
        partContent.addNote(this,note,part_ID,page_no);
        onBackPressed();

    }

    private void MyReferences()
    {
        btn_cancel = (Button) findViewById(R.id.btn_cancel_note);
        btn_save_note = (Button) findViewById(R.id.btn_save_note);
        et_note =(EditText) findViewById(R.id.et_note);
    }
}
