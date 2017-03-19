package com.example.jack.android_controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;


public class MultitouchView extends View {


   private static final int SIZE = 100;

   private SparseArray<Point> mActivePointers;
   private Paint mPaint;
   private Paint textPaint;

   private int pt0XOrigin;
   private int pt1YOrigin;
   int[] msg = {0, 0};

   private String addr;
   private int    port;

   public MultitouchView(Context context, AttributeSet attrs) {
      super(context, attrs);

      addr = ((ControllerActivity)getContext()).getAddr();
      port = ((ControllerActivity)getContext()).getPort();

      initView();
   }

   private void initView() {
      pt0XOrigin = 0;
      pt1YOrigin = 0;

      mActivePointers = new SparseArray<>();

      mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mPaint.setColor(Color.BLUE);
      mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

      textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      textPaint.setTextSize(20);
   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {



         UDPClient myClient;

         // get pointer index from the event object
         int pointerIndex = event.getActionIndex();
         // get pointer ID
         int pointerId = event.getPointerId(pointerIndex);
         // get masked (not specific to a pointer) action
         int maskedAction = event.getActionMasked();

         switch (maskedAction) {
            // We have a new pointer. Lets add it to the list of pointers
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
               Point p = new Point();
               p.x = (int)event.getX(pointerIndex);
               p.y = (int)event.getY(pointerIndex);

               mActivePointers.put(pointerId, p);
               if(pointerId == 0) {
                  pt0XOrigin = p.x;
               }
               else if(pointerId == 1) {
                  pt1YOrigin = p.y;
               }
               break;
            // a pointer was moved
            case MotionEvent.ACTION_MOVE:
               int size = event.getPointerCount();
               // draw all pointers
               for (int i = 0; i < size; i++) {
                  Point point = mActivePointers.get(event.getPointerId(i));
                  if (point != null) {
                     point.x = (int)event.getX(i);
                     point.y = (int)event.getY(i);

                     if(event.getPointerId(i) == 0) {
                        msg[0] = point.x - pt0XOrigin;
                     }
                     else if(event.getPointerId(i) == 1){
                        msg[1] = point.y - pt1YOrigin;
                     }
                  }
               }
               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();
               break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
               mActivePointers.remove(pointerId);
               if(pointerId == 0) {
                  msg[0] = 0;
               }
               else if(pointerId == 1){
                  msg[1] = 0;
               }
               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();

               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();

               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();

               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();

               myClient = new UDPClient(addr, port, msg[0], msg[1]);
               myClient.execute();
               break;
         } // end-switch

         invalidate();
         Log.d("DEBUG", "" +  pt0XOrigin + " " + msg[0] + " " + pt1YOrigin + " " + msg[1]);

      return true;
   }


   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);

      int size = mActivePointers.size();
      for (int i = 0; i < size; i++) {
         Point point = mActivePointers.valueAt(i);
         if (point != null) {
            mPaint.setColor(Color.BLACK);
            canvas.drawCircle(point.x, point.y, SIZE, mPaint);
         }
      }
      canvas.drawText("Total pointers: " + mActivePointers.size(), 10, 40 , textPaint);
   }

}
