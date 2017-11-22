package com.keven.ripple.ripplecreator;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keven.ripple.ripple.RippleCreator;

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

        new RippleCreator.Builder(this)
                .setStartColor(R.color.red_color)
                .setEndColor(R.color.green_color)
                .setNormalColor(R.color.blue_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.green_color)
                .setStrokeColor(R.color.brown_color)
                .setStrokeWidth(10)
                .setRadius(30).build().setBindView(textView_1);

        new RippleCreator.Builder(this)
                .setNormalColor(R.color.transparent_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.pink_color)
                .setStrokeColor(R.color.nihuang_color)
                .setStrokeWidth(20)
                .setRadius(30)
                .build().setBindView(textView_2);
//
        new RippleCreator.Builder(this)
                .setNormalColor(R.color.green_color)
                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.red_color)
                .setStrokeColor(R.color.nihuang_color)
                .setStrokeWidth(10)
                .setRadius(30).build().setBindView(textView_3);



        textView_1.setOnClickListener(this);
        textView_2.setOnClickListener(this);
        textView_3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}
