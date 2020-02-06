package com.example.plugtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 宿主的上下文引用
     */
    private Activity otherActivity;

    /**
     * 无参构造函数
     */
    public MainActivity() {
        super();
    }

    /**
     * 有参构造函数
     * @param context
     */
    public MainActivity(Activity context) {
        super();
        otherActivity = context;
        Log.e("TestActivity", otherActivity.toString() );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (otherActivity != null) {
            // 使用TestHallActivity的上下文引用创建View并添加到根视图
            Button button = new Button(otherActivity);
            button.setText("我是插件TestActivity");
            button.setOnClickListener(this);

            LinearLayout root = new LinearLayout(otherActivity);

            root.addView(button);

            //使用宿主的上线文就不能使用插件的资源了
            otherActivity.setContentView(root);

        } else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }

    /**
     * 返回当前Activity的描述信息
     * @return
     */
    private String getRoomMessage() {
        return "我是插件TestActivity";
    }

    @Override
    public void onClick(View v) {

        if (otherActivity != null) {
            // Toast.makeText(this, "This is Room A!", Toast.LENGTH_SHORT).show();是不合适的调用
            Toast.makeText(otherActivity, "我是插件TestActivity", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "我是插件TestActivity", Toast.LENGTH_SHORT).show();
        }

    }
}
