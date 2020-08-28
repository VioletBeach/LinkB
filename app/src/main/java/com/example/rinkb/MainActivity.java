package com.example.rinkb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.LinePageIndicator;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private BackPressHandler backPressHandler = new BackPressHandler(this);
    private ViewPager mViewPager;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        LinePageIndicator indicator = (LinePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);


        Button btnStartLinkB = (Button)findViewById(R.id.viewpager_button);

        btnStartLinkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new MainActivityFrag1(), "1");
        adapter.addFragment(new MainActivityFrag2(), "2");
        adapter.addFragment(new MainActivityFrag3(), "3");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        // Default
        backPressHandler.onBackPressed();
    }

}