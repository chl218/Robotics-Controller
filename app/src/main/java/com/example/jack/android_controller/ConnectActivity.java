package com.example.jack.android_controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectActivity extends AppCompatActivity {

   private EditText editTextAddr;
   private EditText editTextPort;

   private Button buttonConnect;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_connect);

      editTextAddr = (EditText)findViewById(R.id.editTextAddr);
      editTextPort = (EditText)findViewById(R.id.editTextPort);

      buttonConnect = (Button)findViewById(R.id.buttonConnect);
      buttonConnect.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String addr = getAddress();
            int    port = getPort();

            Intent intent = new Intent(ConnectActivity.this, ControllerActivity.class);
            intent.putExtra("addr", addr);
            intent.putExtra("port", port);
            startActivity(intent);
         }
      });
   }


   private String getAddress() {
      String addr;
      try {
         addr = editTextAddr.getText().toString();
      }
      catch(Exception e) {
         addr = "127.0.0.1";
      }

      return addr;
   }

   private int getPort() {
      int port;
      try {
         port = Integer.parseInt(editTextPort.getText().toString());
      }
      catch (Exception e) {
         port = 22;
      }
      if(port < 0) port = 22;

      return port;
   }
}
