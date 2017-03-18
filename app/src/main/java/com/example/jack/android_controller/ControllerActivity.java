package com.example.jack.android_controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Handler;


public class ControllerActivity extends AppCompatActivity {

   public String addr;
   public int    port;

   Handler handler = new Handler();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      Intent intent = getIntent();
      this.addr = intent.getStringExtra("addr");
      this.port = intent.getIntExtra("port", 20);

      setContentView(R.layout.activity_controller);

      handler.post(runnableCode);

   }

   private Runnable runnableCode = new Runnable() {
      @Override
      public void run() {
         String url = addr + "/image.jpg";

         try {
            new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute(url);
         }
         catch(Exception e) {
            e.printStackTrace();
         }

         handler.postDelayed(runnableCode, 60);

      }
   };




   String getAddr() {
      return addr;
   }

   int getPort() {
      return port;
   }
}
