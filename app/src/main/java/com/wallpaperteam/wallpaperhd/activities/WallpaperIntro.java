package com.wallpaperteam.wallpaperhd.activities;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.wallpaperteam.wallpaperhd.R;

/**
 * Created by sev_user on 6/21/2016.
 */
public class WallpaperIntro extends AppIntro {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro1_title), getString(R.string.intro1_desc), R.drawable.help1, Color.parseColor("#b93221")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro2_title), getString(R.string.intro2_desc), R.drawable.help2, Color.parseColor("#b93221")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro3_title), getString(R.string.intro3_desc), R.drawable.help3, Color.parseColor("#b93221")));

        showStatusBar(false);
        setFadeAnimation();
    }

    @Override
    public void onSkipPressed() {
        finish();
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}
