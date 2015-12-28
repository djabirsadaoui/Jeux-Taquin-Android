package com.example.app;

import android.app.Notification;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import java.util.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.*;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import android.app.AlertDialog.*;
import java.util.List;
import android.content.DialogInterface;

import android.os.Bundle;
//import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
   ArrayList<Integer> tab = new ArrayList<Integer>() ;
    ArrayList<Integer> tab2 = new ArrayList<Integer>() ;
    ArrayList<TextView>  valeurs= new ArrayList<TextView>();
    AlertDialog alert;
    AlertDialog.Builder builder;



    private TextView txt ;
    GestureDetector gd;



   /* public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

        }
        if(newConfig.orientation ==Configuration.ORIENTATION_PORTRAIT){

        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gd =new GestureDetector(getApplicationContext(),this);
        setContentView(R.layout.fragment_main);

       /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }else{


          //  tab = savedInstanceState.getIntegerArrayList("TAB");

        }*/

        startGame();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putIntegerArrayList("TAB",tab);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    //récuperer tous evenement
    public boolean onTouchEvent(MotionEvent event) {
        this.gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;

        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {


                        //droite

                           change(2);




                    } else {

                          change(1);
                    }
                }
                result = true;
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    //bas

                       change(4);

                } else {
                    //haut
                        change(3);
                }
            }
            result = true;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
    protected void startGame(){
        int i;
        Random rn = new Random();
        valeurs.add((TextView)findViewById(R.id.text1));
        valeurs.add((TextView)findViewById(R.id.text2));
        valeurs.add((TextView)findViewById(R.id.text3));
        valeurs.add((TextView)findViewById(R.id.text4));
        valeurs.add((TextView)findViewById(R.id.text5));
        valeurs.add((TextView)findViewById(R.id.text6));
        valeurs.add((TextView)findViewById(R.id.text7));
        valeurs.add((TextView)findViewById(R.id.text8));
        valeurs.add((TextView)findViewById(R.id.text9));


        for( i=1;i<=9;i++){
            tab.add(i);
            tab2.add(i);
        }
       //Collections.shuffle(tab);
       // tab.add(9);
        for( i=0;i<=7;i++){
            Log.i("index=", tab.get(i).toString());
            valeurs.get(i).setText(tab.get(i).toString());
        }

        for( i=0;i<=50;i++){

            change(rn.nextInt(4) + 1);
        }

    }

    protected void change(int event){
        int index;
        TextView val;
        //bas
        if(event==1){//gauche
            if(tab.get(2)!=9||tab.get(5)!=9||tab.get(8)!=9){
                if(tab.get(1)==9||tab.get(4)==9||tab.get(7)==9){
                    index = tab.indexOf(9);
                    valeurs.get(index).setText(tab.get(index+1).toString());
                    tab.set(index,tab.get(index+1));
                    tab.set(index+1,9);
                    valeurs.get(index).setBackgroundColor(Color.YELLOW);
                    valeurs.get(index+1).setBackgroundColor(Color.GRAY);
                    valeurs.get(index+1).setText("");

            }else if(tab.get(0)==9||tab.get(3)==9||tab.get(6)==9){
                    index = tab.indexOf(9);
                    valeurs.get(index).setText(tab.get(index+1).toString());
                    tab.set(index,tab.get(index+1));
                    tab.set(index+1,9);
                    valeurs.get(index).setBackgroundColor(Color.YELLOW);
                    valeurs.get(index+1).setBackgroundColor(Color.GRAY);
                    valeurs.get(index+1).setText("");
                }
            }

        }
        if (event == 2) { //droite
            if (tab.get(0) != 9 && tab.get(3) != 9 && tab.get(6) != 9) {
                index = tab.indexOf(9);
                valeurs.get(index).setText(tab.get(index - 1).toString());
                tab.set(index, tab.get(index - 1));
                tab.set(index - 1, 9);
                valeurs.get(index).setBackgroundColor(Color.YELLOW);
                valeurs.get(index - 1).setBackgroundColor(Color.GRAY);
                valeurs.get(index - 1).setText("");

            }

        }
        if (event == 3) { //haut
            if (tab.get(6) != 9 && tab.get(7) != 9 && tab.get(8) != 9) {
                index = tab.indexOf(9);
                valeurs.get(index).setText(tab.get(index + 3).toString());
                tab.set(index, tab.get(index + 3));
                tab.set(index + 3, 9);
                valeurs.get(index).setBackgroundColor(Color.YELLOW);
                valeurs.get(index + 3).setBackgroundColor(Color.GRAY);
                valeurs.get(index + 3).setText("");
            }

        }
        if (event == 4){

            if ((tab.get(0)!= 9) && (tab.get(1) != 9) && (tab.get(2) != 9)) {


                index = tab.indexOf(9);
                valeurs.get(index).setText(tab.get(index - 3).toString());
                tab.set(index, tab.get(index - 3));
                tab.set(index - 3, 9);
                valeurs.get(index).setBackgroundColor(Color.YELLOW);
                valeurs.get(index - 3).setBackgroundColor(Color.GRAY);
                valeurs.get(index - 3).setText("");
            }
        }

        if(tab.equals(tab2)){
            builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("BRAVO")
                    .setMessage("vous voulez rejouer la partie?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            startGame();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("No", null);
         builder.show();

    }
    }
}
