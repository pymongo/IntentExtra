package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE_KEY = "com.example.intentextra.message";
  public static final String TAG = "Activity2 ";

  public void sendToast(CharSequence Message) {
    Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // setTheme is Before setContentView
    // 会创建一个名为config.xml的key-value pairs存储文件
    final SharedPreferences configSP = getSharedPreferences("config", MODE_PRIVATE);
    final boolean isDarkTheme = configSP.getBoolean("darkTheme", true);
    // SharedPreferences获取值的方法都有默认值，不需要通过.contains判断key是否存在
    if (isDarkTheme) {
      setTheme(R.style.Theme_AppCompat);
    } else {
      setTheme(R.style.Theme_AppCompat_Light);
    }

    // 设置完主题后，让View的一些文案与当前主题一致，如标题、ToggleButton的状态
    setContentView(R.layout.activity_second);
    // ActionBar title
    if (isDarkTheme) {
      setTitle(TAG + "当前主题：深色主题");
    } else {
      setTitle(TAG + "当前主题：白色主题");
    }

    TextView textView = findViewById(R.id.textView);
    if (getIntent().hasExtra(EXTRA_MESSAGE_KEY)) {
      String message = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_MESSAGE_KEY);
      sendToast("get message from Activity1: " + message);
      textView.setText(message);
    }

    Button button1 = findViewById(R.id.button1);
    Button button2 = findViewById(R.id.button2);
    Button button3 = findViewById(R.id.button3);

    button1.setEnabled(false);
    // 打开微信
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent launchIntent = getPackageManager()
            .getLaunchIntentForPackage("com.tencent.mm");
        if (launchIntent != null) {
          startActivity(launchIntent); // null pointer check in case package name was not found
        }
      }
    });
    // 通过默认浏览器打开链接
    button3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.raspberrypi.org"));
        startActivity(browserIntent);
      }
    });
  }

}
