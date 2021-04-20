package com.example.mybutton;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static float initialX, initialY;
    static int height, width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    public void buttonClick(View v)
    {
        //change the text that starts as hello world.
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("gordon griesel's app");
        //
        //show a popup message.
        Context context = getApplicationContext();
        CharSequence text = "by Gordon Griesel";
        int duration = Toast.LENGTH_SHORT;
        Toast tst = Toast.makeText(getApplicationContext(), text, duration);
        tst.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //swipe to close app must be nearly vertical.
                float currX = event.getX();
                float currY = event.getY();
                float ydiff = currY - initialY;
                float xdiff = currX - initialX;
                xdiff = (xdiff < 0.0) ? -xdiff : xdiff;
                if (ydiff > (height*0.5) &&
                    xdiff < (width*0.1)) {
                    //System.exit(1);
                    finish();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return super.onTouchEvent(event);
    }

}
