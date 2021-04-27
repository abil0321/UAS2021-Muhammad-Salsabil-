package com.example.muhammadsalsabiluas2021.AllActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.muhammadsalsabiluas2021.R;

public class HomePage extends AppCompatActivity {
    protected static final float MAX_TEXT_SCALE_DELTA = 0.3f;

    private ViewPager mPager;
//    private NavigationAdapter mPagerAdapter;
//    private SlidingTabLayout mSlidingTabLayout;
    private int mFlexibleSpaceHeight;
    private int mTabHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }
}