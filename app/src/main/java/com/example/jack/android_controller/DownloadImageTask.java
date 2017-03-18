package com.example.jack.android_controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
   private ImageView bmImage;

   public DownloadImageTask(ImageView bmImage) {
      this.bmImage = bmImage;
   }

   protected Bitmap doInBackground(String... urls) {
      String urldisplay = urls[0];
      Bitmap mIcon11 = null;
      try {
         InputStream in = new java.net.URL(urldisplay).openStream();
         mIcon11 = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return mIcon11;
   }

   protected void onPostExecute(Bitmap result) {
      if(result != null) {
         bmImage.setImageBitmap(result);
      }
   }
}