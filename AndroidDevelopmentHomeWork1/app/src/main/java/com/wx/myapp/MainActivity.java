package com.wx.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String str="";
    private EditText input_User;
    private EditText input_PassWord;
    private Button ConformBtn;
    private Button CancelBtn;
    private CheckBox check_Password;
    private TextView Tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_User=findViewById(R.id.editUser);
        input_PassWord=findViewById(R.id.editPassWord);
        ConformBtn=findViewById(R.id.ConfirmBtn);
        CancelBtn=findViewById(R.id.CancelBtn);
        Tv=findViewById(R.id.Tv);

        check_Password=findViewById(R.id.checkBoxPassword);
        //按下Login in按钮之后的操作
        ConformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName=input_User.getText().toString();
                String strPassWord=input_PassWord.getText().toString();
                if(strUserName.equals("test123")&&strPassWord.equals("test123"))
                {
                    Toast.makeText(MainActivity.this,"Log in Successfully!",Toast.LENGTH_LONG).show();
                    Tv.setText("Welcome");
                }
                else
                {
                    Toast.makeText(MainActivity.this,"User Name or Password is incorrect!",Toast.LENGTH_LONG).show();
                    input_User.setText(str);
                    input_PassWord.setText(str);
                }
            }
        });

        //按下CancelBtn之后的操作
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Log in Canceled.",Toast.LENGTH_SHORT).show();
                input_User.setText(str);
                input_PassWord.setText(str);
                Tv.setText("User Login");
            }
        });

        //密码是否可见
        check_Password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    input_PassWord.setTransformationMethod(null);
                else
                    input_PassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }
}
