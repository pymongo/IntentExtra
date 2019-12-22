package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

/* 发现SDK23(安卓6.0)依然有各种遗留问题
 * 如EditText组件抛出警告：缺少autoFillHints属性，当我加上时又提示只有SDK26+才支持这个属性...
 * 如AndroidMainfest.xml: 会有警告App data will Automatically backup and restored on install
 * 解决方案：Application标签新增属性 android:allowBackup="true"
 * 下次我创建项目一定要选9.0的SDK(模拟器是10.0的，我手机9.0的)
 *  */
public class MainActivity extends AppCompatActivity {

  public static final String TAG = "Activity1: MainActivity";
  public static final String EXTRA_MESSAGE_KEY = "com.example.intentextra.message";

  public void sendToast(CharSequence Message) {
    Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // ActionBar title
    setTitle(TAG);

    final EditText editText = findViewById(R.id.editText);
    Button button1 = findViewById(R.id.button1);
    Button button2 = findViewById(R.id.button2);
    Button button3 = findViewById(R.id.button3);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), DisplayMessageActivity.class);
        String message = editText.getText().toString();
        // key-value pairs
        intent.putExtra(EXTRA_MESSAGE_KEY, message);
        startActivity(intent);
      }
    });
  }
}
