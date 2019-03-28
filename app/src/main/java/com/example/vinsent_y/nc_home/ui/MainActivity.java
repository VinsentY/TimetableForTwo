package com.example.vinsent_y.nc_home.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.vinsent_y.nc_home.R;
import com.example.vinsent_y.nc_home.fragment.LifeFragment;
import com.example.vinsent_y.nc_home.fragment.TimeTableFragment;
import com.example.vinsent_y.nc_home.fragment.UserFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;
    private BottomBar mBottomBar;
    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initView() {
        mBottomBar = findViewById(R.id.bottomBar);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_time_table:
                        replaceFragment(mFragments.get(0));
                        break;
                    case R.id.tab_life:
                        replaceFragment(mFragments.get(1));
                        break;
                    case R.id.tab_user:
                        replaceFragment(mFragments.get(2));
                        break;

                }
            }

            private void replaceFragment(Fragment fragment) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.replace_fragment, fragment);
                transaction.commit();
            }

        });

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new TimeTableFragment());
        mFragments.add(new LifeFragment());
        mFragments.add(new UserFragment());

    }

}
