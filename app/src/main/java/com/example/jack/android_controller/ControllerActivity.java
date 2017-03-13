package com.example.jack.android_controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ControllerActivity extends AppCompatActivity {

   public String addr;
   public int    port;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_controller);


      Intent intent = getIntent();
      addr = intent.getStringExtra("addr");
      port = intent.getIntExtra("port", 20);

      intent = new Intent(getApplicationContext(), MultitouchView.class);
      intent.putExtra("addr", addr);
      intent.putExtra("port", port);
   }

   String getAddr() {
      return addr;
   }

   int getPort() {
      return port;
   }
}
