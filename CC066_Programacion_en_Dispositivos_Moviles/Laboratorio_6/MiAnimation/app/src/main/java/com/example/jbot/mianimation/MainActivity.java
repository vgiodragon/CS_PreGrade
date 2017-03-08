package com.example.jbot.mianimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView AnimationTarget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AnimationTarget = (ImageView) this.findViewById(R.id.imagen);
    }

    public void rota (View v){
        Animator animation = AnimatorInflater.loadAnimator(this, R.anim.rotate_around_center_point);
        animation.setTarget(AnimationTarget);
        animation.start();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat
                (AnimationTarget, "rotation", 360f);
        scaleXAnimator.setRepeatCount(0);
        scaleXAnimator.setDuration(1000);
        scaleXAnimator.start();
    }

    public void escala (View v){
        Animator animation = AnimatorInflater.loadAnimator(this, R.anim.escala);
        animation.setTarget(AnimationTarget);
        animation.start();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat
                (AnimationTarget, "scaleX", 0.5f);
        scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleXAnimator.setRepeatCount(1);
        scaleXAnimator.setDuration(1000);
        scaleXAnimator.start();
    }
}
