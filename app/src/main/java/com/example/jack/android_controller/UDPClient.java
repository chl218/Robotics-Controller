package com.example.jack.android_controller;

import android.os.AsyncTask;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class UDPClient extends AsyncTask<Void, Void, Void> {

   private String dstAddress;
   private int dstPort;

   private int accel;
   private int theta;

   UDPClient(String dstAddress, int dstPort, int accel, int theta) {
      this.dstAddress = dstAddress;
      this.dstPort    = dstPort;

      this.accel = accel;
      this.theta = theta;
   }



   @Override
   protected Void doInBackground(Void... arg0) {

      DatagramSocket socket = null;
      try {
         // Create UDP socket
         socket = new DatagramSocket();
         // Create the message
         String msg = "" + accel + " " + theta;

         // Transform message to byte array due to parameter type
         byte[] send = msg.getBytes();

         // Transform String address type to InetAddress type
         InetAddress inetAddress = InetAddress.getByName(dstAddress);
         // Create UDP packet
         DatagramPacket sendPacket = new DatagramPacket(send, send.length, inetAddress, dstPort);
         // Send the packet
         socket.send(sendPacket);

      }
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if (socket != null) {
            try {
               socket.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      return null;
   }

   @Override
   protected void onPostExecute(Void result) {
      super.onPostExecute(result);
   }

}