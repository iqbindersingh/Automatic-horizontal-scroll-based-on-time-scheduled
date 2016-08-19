package com.demiscrolllist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener,
          ScrollViewListener {
    Handler handler;
    Timer timer;
    ImageView popUpImage, closeimage;
    Parcelable list_state, list_state_rec;
    TimerTask doAsynchronousTask;

    ScrollViewExt scrview;
    ListView listPaid, listFree;
    // Adap_feedPaid adapPaid;
    List<String> arrPaid_one, arrPaid_two;
    boolean webresponse = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getid();


    }

    private void getid() {
        listPaid = (ListView) findViewById(R.id.listPaid);
        listFree = (ListView) findViewById(R.id.listFree);
        scrview = (ScrollViewExt) findViewById(R.id.scrview);

        handler = new Handler();

        scrview.setScrollViewListener(this);


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (timer != null)
            timer.cancel();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // Search.getText().clear();
        //  Searchlayout.setVisibility(View.GONE);
        listPaid.setVisibility(View.VISIBLE);
        //   MainLayout.setVisibility(View.VISIBLE);
        //   Maincolour.setVisibility(View.VISIBLE);
        //  Searchcount = "0";
        //  webresponse = true;
        timer = new Timer();

        TimerTask doAsynchronousTask = new TimerTask() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                            //  here we use webservice which we want to scroll every 10 sec

                            //   WebservicePaid (false);

                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    }
                });
            }
        };

        timer.schedule(doAsynchronousTask, 0, 15000);


    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            Log.d("Activity",
                    "Touch event " + event.getRawX() + "," + event.getRawY()
                            + " " + x + "," + y + " rect " + w.getLeft() + ","
                            + w.getTop() + "," + w.getRight() + ","
                            + w.getBottom() + " coords " + scrcoords[0] + ","
                            + scrcoords[1]);
            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
                    .getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }
        return ret;
    }

    @Override
    public void onScrollChanged(ScrollViewExt scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub

        View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

        // if diff is zero, then the bottom has been reached
        if (diff == 0) {
            // do stuff

            if (webresponse) {
                //     progressBarPagination.setVisibility(View.VISIBLE);

                //  here implement webdservice which you want to call again and again on scroll time too

                //   webservice(arrfeed.size()+10, false);
            } else {

                //  Progress bar visibilty


                // progressBarPagination.setVisibility(View.GONE);
            }

        }

    }
}


