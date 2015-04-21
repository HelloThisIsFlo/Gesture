package com.shockn745.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GestureActivity extends Activity {
    
    private static final String LOG_TAG = GestureActivity.class.getSimpleName();

    Button mButton;

    private int _xDelta;
    private int _yDelta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);


        mButton = (Button) findViewById(R.id.button);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();

                int action = event.getActionMasked();

                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        Log.d(LOG_TAG,"Action was DOWN");
                        RelativeLayout.LayoutParams lParams =
                                (RelativeLayout.LayoutParams) mButton.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;

                        return false;
                    case (MotionEvent.ACTION_MOVE) :
                        Log.d(LOG_TAG,"Action was MOVE");
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) mButton.getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = -250;
                        layoutParams.bottomMargin = -250;
                        mButton.setLayoutParams(layoutParams);

                        return false;
                    case (MotionEvent.ACTION_UP) :
                        Log.d(LOG_TAG,"Action was UP");
                        return false;
                    case (MotionEvent.ACTION_CANCEL) :
                        Log.d(LOG_TAG,"Action was CANCEL");
                        return false;
                    case (MotionEvent.ACTION_OUTSIDE) :
                        Log.d(LOG_TAG,"Movement occurred outside bounds " +
                                "of current screen element");
                        return false;
                    default :
                        return GestureActivity.super.onTouchEvent(event);
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GestureActivity.this, "Bouton cliqué", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(LOG_TAG, "Scroll Gesture");
        Log.d(LOG_TAG, "Distance scroll : dx = " + distanceX + " dy = " + distanceY);
        float dX = e2.getX() - e1.getX();
        float dY = e2.getY() - e1.getY();
        Log.d(LOG_TAG, "Distance total : dX =  " + dX + " dY = " + dY);
        Log.d(LOG_TAG, "e1 :  x = " + e1.getX() + " y = " + e1.getY());
        Log.d(LOG_TAG, "e2 :  x = " + e2.getX() + " y = " + e2.getY());

        float buttonX = mButton.getX();
        mButton.setX(buttonX - distanceX);
        Log.d(LOG_TAG, "Button x = " + buttonX);

        return false;
    }


}
