package chapter.android.aweme.ss.com.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int num =getIntent().getIntExtra("position",1000);
        TextView textView=findViewById(R.id.textView);
        textView.setText("点击的是："+(num+1));
    }
}
