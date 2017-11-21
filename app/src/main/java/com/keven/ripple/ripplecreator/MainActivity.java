package com.keven.ripple.ripplecreator;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.keven.ripple.ripple.JoyrunRipple;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview_main);

        Drawable build = new JoyrunRipple.Builder(this)
                .setEndColor(R.color.red_color)
                .setStartColor(R.color.green_color)
//                .setNormalColor(R.color.blue_color)
//                .setPressColor(R.color.red_color)
                .setRippleColor(R.color.pink_color)
                .setStrokeColor(R.color.blue_color)
                .setStrokeWidth(10)
                .setRadius(30).build();

        imageView.setImageDrawable(build);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
