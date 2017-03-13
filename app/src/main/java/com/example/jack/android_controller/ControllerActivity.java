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

      Intent intent = getIntent();
      this.addr = intent.getStringExtra("addr");
      this.port = intent.getIntExtra("port", 20);

      setContentView(R.layout.activity_controller);
   }

   String getAddr() {
      return addr;
   }

   int getPort() {
      return port;
   }
}
