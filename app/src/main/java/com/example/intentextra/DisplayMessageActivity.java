package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DisplayMessageActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE_KEY = "com.example.intentextra.message";
  public static final String TAG = "Activity2: DisplayMessageActivity";

  public void sendToast(CharSequence Message) {
    Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_message);

    // ActionBar title
    setTitle(TAG);

    TextView textView = findViewById(R.id.textView);
    if (getIntent().hasExtra(EXTRA_MESSAGE_KEY)) {
      String message = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_MESSAGE_KEY);
      sendToast("get message from Activity1: "+message);
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
    button3.setEnabled(false);

  }

}
