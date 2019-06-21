/*
 * Copyright (c) 2019. Parrot Faurecia Automotive S.A.S. All rights reserved.
 */

package com.parrot.car.coodinatelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TitleBehavoir extends CoordinatorLayout.Behavior<ImageView> {

    public TitleBehavoir() {
    }

    public TitleBehavoir(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        boolean is = dependency instanceof RecyclerView;
        Log.e("------TitleBehavoir-------","==1=" + is);
        return is;
    }

    int y=0;
    float imgX;
    float imgY;
    float cx;
    float cy;
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        if (y==0){
            AppBarLayout appbar = (AppBarLayout) parent.getChildAt(0);
            CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) appbar.getChildAt(0);
            Toolbar toolbar = (Toolbar) toolbarLayout.getChildAt(0);
            RelativeLayout relativeLayout = (RelativeLayout) toolbar.getChildAt(0);
            ImageView img = (ImageView) relativeLayout.getChildAt(0);
            imgX = img.getX();
            imgY = img.getY();
            y = (int) dependency.getY();
            cx = child.getX() + child.getWidth()/2;
            cy = child.getY() + child.getHeight()/2;
        }


        float tY = Math.abs(dependency.getY()/y)*(Math.abs(cy-imgY)+10);
        float tX = Math.abs(dependency.getY()/y)*(Math.abs(cx-imgX)+85);


        Log.e("------TitleBehavoir-------=" + dependency.getY(),"==2=" + dependency.getTop());
        child.setTranslationY(-tY);
        child.setTranslationX(tX);
        child.setScaleX(1-Math.abs(dependency.getY()/y));
        child.setScaleY(1-Math.abs(dependency.getY()/y));
        return true;
    }
}
