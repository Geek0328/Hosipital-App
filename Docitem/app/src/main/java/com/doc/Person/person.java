package com.doc.Person;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import android.content.Intent;
import com.example.xlz.docitem.R;

/**
 * Created by xlz on 2017/4/26.
 */

public class person extends Activity {
    private Button button1,button2;
    private TextView textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    private ImageView imageView1;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate( saveInstanceState);
        setContentView(R.layout.personal_info);
       // initComponent();
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        textView8=(TextView)findViewById(R.id.textView8);
        textView9=(TextView)findViewById(R.id.textView9);
        textView10=(TextView)findViewById(R.id.textView10);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        Toast.makeText(person.this, name,
                Toast.LENGTH_SHORT).show();
        textView8.setText(name);
        textView10.setText("123456");
    }

}
