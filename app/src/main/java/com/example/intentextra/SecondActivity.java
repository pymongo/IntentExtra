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

import org.w3c.dom.Text;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE_KEY = "com.example.intentextra.message";
  public static final String TAG = "Activity2 ";

  SharedPreferences configSP;
  boolean isDarkTheme;

  TextView textView;
  Button button1;
  Button button2;
  Button button3;

  public void sendToast(CharSequence Message) {
    Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    configSP = getSharedPreferences("config", MODE_PRIVATE);
    isDarkTheme = configSP.getBoolean("darkTheme", true);
    if (isDarkTheme) {
      setTheme(R.style.Theme_AppCompat);
    } else {
      setTheme(R.style.Theme_AppCompat_Light);
    }
    setContentView(R.layout.activity_second);
    textView = findViewById(R.id.textView);
    setTitle(TAG);
    if (getIntent().hasExtra(EXTRA_MESSAGE_KEY)) {
      String message = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_MESSAGE_KEY);
      sendToast("get message from Activity1: " + message);
      textView.setText(message);
    }

    button1 = findViewById(R.id.button1);
    button2 = findViewById(R.id.button2);
    button3 = findViewById(R.id.button3);
    button1.setEnabled(false);
    button2.setEnabled(false);
    button3.setEnabled(false);
  }

}
