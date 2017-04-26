package com.doc.Commom;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Paint;


import com.doc.Person.person;
import com.doc.DB.LoginDataBaseAdapter;
import com.example.xlz.docitem.R;

/**
 * Created by xlz on 2017/4/20.
 */

public class login extends Activity {

    AutoCompleteTextView cardNumAuto;
    TextView textView;
    EditText edit;
    Button logBT;
    CheckBox savePasswordCB;
    SharedPreferences sp;
    public String cardNumStr,passwordStr;
    LoginDataBaseAdapter loginDataBaseAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        cardNumAuto=(AutoCompleteTextView)findViewById(R.id.cardNumAuto);
        textView=(TextView)findViewById(R.id.textView);
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        edit=(EditText)findViewById(R.id.edit);
        logBT=(Button)findViewById(R.id.logBT);

        sp=this.getSharedPreferences("passwordFile",MODE_PRIVATE);
        savePasswordCB=(CheckBox)findViewById(R.id.savePasswordCB);
        savePasswordCB.setChecked(true);  //默认为记住密码
        cardNumAuto.setThreshold(1);      //输入第一个字母就开始自动提示

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        cardNumAuto.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                String[] allUserName = new String[sp.getAll().size()];// sp.getAll().size()返回的是有多少个键值对
                allUserName = sp.getAll().keySet().toArray(new String[0]);
                // sp.getAll()返回一张hash map
                // keySet()得到的是a set of the keys.
                // hash map是由key-value组成的

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        login.this,android.R.layout.simple_dropdown_item_1line,allUserName);

                cardNumAuto.setAdapter(adapter);// 设置数据适配器

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                edit.setText(sp.getString(cardNumAuto.getText().toString(), ""));// 自动输入密码
            }
        });

        // 登陆
        logBT.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent=new Intent();
                cardNumStr = cardNumAuto.getText().toString();
                passwordStr = edit.getText().toString();
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(cardNumStr);

                /*if (!(passwordStr.equals(storedPassword))) {
                    Toast.makeText(login.this, "密码错误，请重新输入",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (savePasswordCB.isChecked()) {// 登陆成功才保存密码
                        sp.edit().putString(cardNumStr, passwordStr).commit();
                    }
                    Toast.makeText(login.this, "登陆成功，正在获取用户数据……",
                            Toast.LENGTH_SHORT).show();
                    // 跳转到另一个Activity
                    // do something

                }*/
                if (!(passwordStr.equals("123456"))) {
                    Toast.makeText(login.this, "密码错误，请重新输入",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (savePasswordCB.isChecked()) {// 登陆成功才保存密码
                        sp.edit().putString(cardNumStr, passwordStr).commit();
                    }
                    Toast.makeText(login.this, "登陆成功，正在获取用户数据……",
                            Toast.LENGTH_SHORT).show();

                    intent.setClass(login.this,person.class);
                    intent.putExtra("name",cardNumStr);
                    startActivity(intent);
                }

            }
        });


        // 注册
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                intent.setClass(login.this,register.class);
                startActivity(intent);
            }
        });
    }
    public void setID(String cardNumStr){
        this.cardNumStr=cardNumStr;
    }
    public String getID(){
        return cardNumStr;
    }
}
