package com.keven.ripple.ripple;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.keven.ripple.utils.DensityUtil;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

/**
 * Created by keven-liang on 2017/11/13.
 */

public class RippleCreator {


    private Drawable mRippleDrawable;
    private Builder mBuilder;


    public RippleCreator(Builder builder){
        mBuilder = builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if(builder.getClickMode() == Builder.AUTOFIX_MODE){
                initRippleDrawable(builder);
            }else if(builder.getClickMode() == Builder.SELECTOR_MODE){
                initSelectorDrawable(builder);
            }

        }else {
            initSelectorDrawable(builder);
        }
    }

    public void setBindView(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(getBgDrawable());
        }else {
            view.setBackgroundDrawable(getBgDrawable());
        }
    }

    public Drawable getBgDrawable(){
        return mRippleDrawable;
    }

    private void initSelectorDrawable(Builder builder) {
        //设置普通的点按效果
        GradientDrawable normalGradientDrawable = new GradientDrawable();
        initGradientDrawable(normalGradientDrawable, builder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            normalGradientDrawable.setColors(getBgColorList(builder));
        }else {
            normalGradientDrawable.setColor(getColor(builder.getNormalColor()));
        }

        GradientDrawable pressGradientDrawable = new GradientDrawable();
        initGradientDrawable(pressGradientDrawable, builder);
        pressGradientDrawable.setColor(getColor(builder.getPressColor()));


        StateListDrawable stateListDrawable = newSelector( normalGradientDrawable, pressGradientDrawable);

        mRippleDrawable = stateListDrawable;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initRippleDrawable(Builder builder) {
        //设置水波纹
        RippleDrawable rippleDrawable = (RippleDrawable) builder.getmContext().getDrawable(R.drawable.ripple_creator_selector);
        ColorStateList colorStateList = createColorStateList(builder.getPressColor(),builder.getRippleColor());
        rippleDrawable.setColor(colorStateList);

        GradientDrawable gradientDrawable = new GradientDrawable();
        initGradientDrawable(gradientDrawable, builder);
        gradientDrawable.setColors(getBgColorList(builder));

        rippleDrawable.setDrawableByLayerId(R.id.ripple_creator_shape, gradientDrawable);

        mRippleDrawable = rippleDrawable;
    }

    private int getColor(@ColorRes int color){
        if(color >= 0) {
            int tempColor = 0;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tempColor = mBuilder.getmContext().getColor(color);
            } else {
                tempColor = mBuilder.getmContext().getResources().getColor(color);
            }

            return tempColor;
        }
        return Color.TRANSPARENT;
    }

    private void initGradientDrawable(GradientDrawable gradientDrawable, Builder builder){

        gradientDrawable.setStroke(DensityUtil.dip2px(builder.getmContext(),builder.getStrokeWidth()), getColor(builder.getStrokeColor()));
        gradientDrawable.setCornerRadius(DensityUtil.dip2px(builder.getmContext(),builder.getRadius()));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            gradientDrawable.setOrientation(builder.getOrientation());
        }
    }



    /** 设置Selector。 */
    private   StateListDrawable newSelector(Drawable normal, Drawable pressed) {
        StateListDrawable bg = new StateListDrawable();
        // View.PRESSED_ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_pressed }, pressed);
        // View.ENABLED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, normal);
        return bg;
    }

    /**
     * 获取背景颜色
     * @param builder
     * @return
     */
    private int[] getBgColorList(Builder builder){
        int startColor = builder.getStartColor();
        int endColor = builder.getEndColor();
        int normalColor = builder.getNormalColor();

        int[] colorList = new int[2];

        if(startColor != -1 && endColor != -1){
            colorList[0] = getColor(startColor);
            colorList[1] = getColor(endColor);
        }else {
            colorList[0] = getColor(normalColor);
            colorList[1] = getColor(normalColor);

        }
        return colorList;
    }

    /** 对设置不同状态时其文字颜色。 */
    private ColorStateList createColorStateList(@ColorRes int pressed,@ColorRes int rippleColor) {
        int[] colors = new int[] {  getColor(pressed), getColor(rippleColor),Color.TRANSPARENT};//水波纹背景颜色，水波纹颜色，闲置情况下的颜色
        int[][] states = new int[3][];
        states[0] = new int[] { android.R.attr.state_pressed };
        states[1] = new int[] { android.R.attr.state_enabled };
        states[2] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public static class Builder{

        private int pressColor = -1;
        private int radius = 0;
        private int rippleColor = -1;
        private int strokeWidth = 0;
        private int strokeColor = -1;

        //=======================设置背景颜色==================================
        private int normalColor = -1;
        private int startColor = -1;
        private int endColor =-1;
        private GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
        //=======================设置背景颜色==================================

        public static final int SELECTOR_MODE = 2;
        public static final int AUTOFIX_MODE = 3;
        private int clickMode = AUTOFIX_MODE;

        private Context mContext;

        public Builder(@NonNull Context context){
            mContext = context;
        }


        public RippleCreator build(){
            RippleCreator rippleCreator = new RippleCreator(this);
            return rippleCreator;
        }

        public int getRadius() {
            return radius;
        }


        /**
         * 设置圆角半径
         * @param radius  单位：dp
         * @return
         */
        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public int getRippleColor() {
            return rippleColor;
        }

        public Builder setRippleColor(@ColorRes int rippleColor) {
            this.rippleColor = rippleColor;
            return this;
        }

        public int getPressColor() {
            return pressColor;
        }

        public Builder setPressColor(@ColorRes int pressColor) {
            this.pressColor = pressColor;
            return this;
        }

        public int getNormalColor() {
            return normalColor;
        }

        public Builder setNormalColor(@ColorRes int normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        public Context getmContext() {
            return mContext;
        }

        public Builder setmContext(Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public int getStrokeWidth() {
            return strokeWidth;
        }

        /**
         * 设置边框的宽度
         * @param strokeWidth  单位：dp
         * @return
         */
        public Builder setStrokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public int getStrokeColor() {
            return strokeColor;
        }

        public Builder setStrokeColor(@ColorRes int strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        public int getStartColor() {
            return startColor;
        }

        public Builder setStartColor(@ColorRes int startColor) {
            this.startColor = startColor;
            return this;
        }

        public int getEndColor() {
            return endColor;
        }

        public Builder setEndColor(@ColorRes int endColor) {
            this.endColor = endColor;
            return this;
        }

        public GradientDrawable.Orientation getOrientation() {
            return orientation;
        }

        public Builder setOrientation(GradientDrawable.Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public int getClickMode() {
            return clickMode;
        }

        public Builder setSelectorMode(boolean isSelectorMode){
            if(isSelectorMode){
                clickMode = SELECTOR_MODE;
            }else {
                clickMode = AUTOFIX_MODE;
            }
            return this;
        }

    }
}
