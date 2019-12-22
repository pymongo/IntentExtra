package com.example.intentextra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
  }

}
