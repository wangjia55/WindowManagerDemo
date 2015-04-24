package com.jacob.windowmanager;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jacob.windowmanager.lesson1.LessonOneActivity;
import com.jacob.windowmanager.lesson2.LessonTwoActivity;
import com.jacob.windowmanager.lesson3.LessonThreeActivity;
import com.jacob.windowmanager.lesson4.LessonFourActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void lessonOne(View view){
        Intent intent = new Intent(this, LessonOneActivity.class);
        startActivity(intent);
    }
    public void lessonTwo(View view){
        Intent intent = new Intent(this, LessonTwoActivity.class);
        startActivity(intent);
    }

    public void lessonThree(View view){
        Intent intent = new Intent(this, LessonThreeActivity.class);
        startActivity(intent);
    }

    public void lessonFour(View view){
        Intent intent = new Intent(this, LessonFourActivity.class);
        startActivity(intent);
    }

}
