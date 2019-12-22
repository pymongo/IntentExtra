package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/* 发现SDK23(安卓6.0)依然有各种遗留问题
 * 如AndroidMainfest.xml: 会有警告App data will Automatically backup and restored on install
 * 解决方案：Applicaton标签新增属性 android:allowBackup="true"
 * 下次我创建项目一定要选9.0的SDK(模拟器是10.0的，我手机9.0的)
 *  */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // ActionBar title
    setTitle("Activity1");
  }
}
