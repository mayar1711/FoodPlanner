package com.example.foodplanner.ui.onbording;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.foodplanner.R;
import com.example.foodplanner.ui.authentication.MainActivity;

public class OnBoarding extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView back;
    private TextView next;
    private TextView skip;
    private TextView[] point;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bording);
        back = findViewById(R.id.tv_back);
        next = findViewById(R.id.tv_next);
        skip = findViewById(R.id.tv_skip);
        viewPager =findViewById(R.id.view_pager);
        linearLayout =findViewById(R.id.indicator);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        setIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);
        back.setOnClickListener(v -> {
            if (getItem(0) > 0)
                viewPager.setCurrentItem(getItem(-1), true);
        });

        next.setOnClickListener(v -> {
            if (getItem(0) < 1)
                viewPager.setCurrentItem(getItem(1), true);
            else {
                Intent intent = new Intent(OnBoarding.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        skip.setOnClickListener(v -> {
            Intent intent = new Intent(OnBoarding.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

   private  ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
        @Override
        public void onPageSelected(int position) {
            setIndicator(position);
            if (position > 0)
                back.setVisibility(View.VISIBLE);
            else
                back.setVisibility(View.INVISIBLE);
        }
        @Override
        public void onPageScrollStateChanged(int state) { }
    };

    public void setIndicator(int position) {
        point = new TextView[2];
        linearLayout.removeAllViews();
        for (int i = 0; i < point.length; i++) {
            point[i] = new TextView(this);
            point[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            linearLayout.addView(point[i]);
        }
        point[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
    }
    private int getItem(int value) {
        return viewPager.getCurrentItem() + value;
    }
}
