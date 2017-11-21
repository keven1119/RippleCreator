package com.keven.ripple.ripplecreator;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keven.ripple.ripple.JoyrunRipple;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_1;
    TextView textView_2;
    TextView textView_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_1 = (TextView) findViewById(R.id.text_1);
        textView_2 = (TextView) findViewById(R.id.text_2);
        textView_3 = (TextView) findViewById(R.id.text_3);

        Drawable build_1 = new JoyrunRipple.Builder(this)
                .setEndColor(R.color.red_color)
                .setStartColor(R.color.green_color)
                .setNormalColor(R.color.blue_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.pink_color)
                .setStrokeColor(R.color.blue_color)
                .setStrokeWidth(10)
                .setRadius(30).build();

        Drawable build_2 = new JoyrunRipple.Builder(this)
                .setEndColor(R.color.red_color)
                .setStartColor(R.color.green_color)
                .setNormalColor(R.color.blue_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.pink_color)
                .setStrokeColor(R.color.blue_color)
                .setStrokeWidth(10)
                .setRadius(30).setSelectorMode(true).build();

        Drawable build_3 = new JoyrunRipple.Builder(this)
                .setEndColor(R.color.red_color)
                .setStartColor(R.color.green_color)
                .setNormalColor(R.color.blue_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.pink_color)
                .setStrokeColor(R.color.blue_color)
                .setStrokeWidth(10)
                .setRadius(30).setRippleMode(true).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView_1.setBackground(build_1);
            textView_2.setBackground(build_2);
            textView_3.setBackground(build_3);
        }else{
            textView_1.setBackgroundDrawable(build_1);
            textView_2.setBackgroundDrawable(build_2);
            textView_3.setBackgroundDrawable(build_3);
        }
        textView_1.setOnClickListener(this);
        textView_2.setOnClickListener(this);
        textView_3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}
