package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

/* 发现SDK23(安卓6.0)依然有各种遗留问题
 * 如EditText组件抛出警告：缺少autoFillHints属性，当我加上时又提示只有SDK26+才支持这个属性...
 * 如AndroidMainfest.xml: 会有警告App data will Automatically backup and restored on install
 * 解决方案：Application标签新增属性 android:allowBackup="true"
 * 下次我创建项目一定要选9.0的SDK(模拟器是10.0的，我手机9.0的)
 *  */
public class MainActivity extends AppCompatActivity {

  public static final String TAG = "Activity1 ";
  public static final String EXTRA_MESSAGE_KEY = "com.example.intentextra.message";

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
    setContentView(R.layout.activity_main);
    // View初始化完后，让主题的toggleButton状态与isDarkTheme一致
    ToggleButton toggleTheme = findViewById(R.id.button4);
    toggleTheme.setChecked(isDarkTheme);
    // ActionBar title
    if (isDarkTheme) {
      setTitle(TAG + "当前主题：深色主题");
    } else {
      setTitle(TAG + "当前主题：白色主题");
    }

    final EditText editText = findViewById(R.id.editText);
    Button button1 = findViewById(R.id.button1);
    Button button2 = findViewById(R.id.button2);
    Button button3 = findViewById(R.id.button3);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        String message = editText.getText().toString();
        // key-value pairs
        intent.putExtra(EXTRA_MESSAGE_KEY, message);
        startActivity(intent);
      }
    });
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
    // toggle深色主题
    toggleTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          // setTheme(R.style.Theme_AppCompat);
          // step.1 修改配置
          configSP.edit().putBoolean("darkTheme", true).apply();
          // step.2 如果配置项有变化Reload Activity使主题修改生效
          if (!isDarkTheme) {
            finish();
            startActivity(getIntent());
          }
        } else {
          // setTheme(R.style.Theme_AppCompat_Light);
          // step.1 修改配置
          configSP.edit().putBoolean("darkTheme", false).apply();
          // step.2 如果配置项有变化Reload Activity使主题修改生效
          if (isDarkTheme) {
            finish();
            startActivity(getIntent());
          }
        }
      }
    });

  }
}
