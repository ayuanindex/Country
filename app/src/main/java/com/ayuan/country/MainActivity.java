package com.ayuan.country;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_chanese;
    private Button btn_english;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_chanese = (Button) findViewById(R.id.btn_chanese);
        btn_english = (Button) findViewById(R.id.btn_english);
        tv_text = (TextView) findViewById(R.id.tv_text);

        btn_chanese.setOnClickListener(this);
        btn_english.setOnClickListener(this);
    }

    private void switchLanguage(String language) {
        //设置应用语言类型
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("zh_simple")) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language.equals("en")) {
            config.locale = Locale.US;
        } else {
            config.locale = Locale.getDefault();
        }
        //更新语言后，destroy当前页面，重新绘制
        resources.updateConfiguration(config, dm);
        Intent it = new Intent(MainActivity.this, MainActivity.class);
        //清空任务栈确保当前打开activit为前台任务栈栈顶
        it.setFlags(/*Intent.FLAG_ACTIVITY_NEW_TASK |*/ Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(it);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chanese:
                switchLanguage("zh_simple");
                break;
            case R.id.btn_english:
                switchLanguage("en");
                break;
        }
    }
}
