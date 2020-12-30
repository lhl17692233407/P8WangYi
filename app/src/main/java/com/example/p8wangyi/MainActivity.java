 package com.example.p8wangyi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.p8wangyi.ui.home.HomeFragment;
import com.example.p8wangyi.ui.home.ZhouYiActivity;
import com.example.p8wangyi.ui.me.MeFragment;
import com.example.p8wangyi.ui.shop.ShopFragment;
import com.example.p8wangyi.ui.sort.SortFragment;
import com.example.p8wangyi.ui.topic.TopFragment;
import com.example.p8wangyi.utils.SpUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavView;
    private ViewPager mVp;
    private TabLayout mTab;
    private ArrayList<Fragment> fragments;
    private ViewPager mSplaceVp;
    private TextView mSplaceText1;
    private TextView mSplaceText2;
    private TextView mSplaceText3;
    private Timer timer;
    private int time = 10;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (time>=5){
                button.setEnabled(false);
            }
            if (time<=5){
                button.setEnabled(true);
            }
            if (time >= 0) {
                button.setText(time + "s");
            }
            if (time == 0) {
                timerTask.cancel();
                mMainRel.setVisibility(View.GONE);
                //mcon.setVisibility(View.VISIBLE);
            }
            time --;
        }
    };
    private TimerTask timerTask;
    private Button button;
    private RelativeLayout mMainRel;
    private ConstraintLayout mcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        String token = SpUtils.getInstance().getString("token");
        Log.e("liuhaolin",token);
    }

    @SuppressLint("ResourceType")
    private void initView() {
        mTab = findViewById(R.id.main_tab);
        mVp = findViewById(R.id.main_vp);
        mcon = findViewById(R.id.main_con);
        mMainRel = findViewById(R.id.main_rel);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TopFragment());
        fragments.add(new SortFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MeFragment());

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);

        mTab.getTabAt(0).setText("首页").setIcon(R.drawable.home_draw);
        mTab.getTabAt(1).setText("专题").setIcon(R.drawable.special_draw);
        mTab.getTabAt(2).setText("分类").setIcon(R.drawable.category_draw);
        mTab.getTabAt(3).setText("购物车").setIcon(R.drawable.shopping_draw);
        mTab.getTabAt(4).setText("我的").setIcon(R.drawable.my_draw);


        mSplaceVp = findViewById(R.id.splace_vp);
        mSplaceText1 = findViewById(R.id.splace_text1);
        mSplaceText2 = findViewById(R.id.splace_text2);
        mSplaceText3 = findViewById(R.id.splace_text3);

        mSplaceText1.setBackgroundResource(R.color.red);
        initShouYe();
    }

    private void initShouYe() {

        View view1 = View.inflate(this, R.layout.activity_viewpage_buju1, null);
        View view2 = View.inflate(this, R.layout.activity_viewpage_buju2, null);
        View view3 = View.inflate(this, R.layout.activity_viewpage_buju3, null);

        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        button = view3.findViewById(R.id.buju3_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerTask.cancel();
                mMainRel.setVisibility(View.GONE);
            }
        });
        mSplaceVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
                container.removeView(views.get(position));
            }
        });
        final Boolean[] b = {false};
        mSplaceVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mSplaceText1.setBackgroundResource(R.color.red);
                    mSplaceText2.setBackgroundResource(R.color.white);
                    mSplaceText3.setBackgroundResource(R.color.white);
                } else if (position == 1) {
                    mSplaceText1.setBackgroundResource(R.color.white);
                    mSplaceText2.setBackgroundResource(R.color.red);
                    mSplaceText3.setBackgroundResource(R.color.white);
                    if (b[0]){
                        timerTask.cancel();
                        button.setText(10+"s");
                        time = 10;
                    }
                } else if (position == 2) {
                    mSplaceText1.setBackgroundResource(R.color.white);
                    mSplaceText2.setBackgroundResource(R.color.white);
                    mSplaceText3.setBackgroundResource(R.color.red);
                    b[0] = true;
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(time);
                        }
                    };
                    timer.schedule(timerTask, 1000, 1000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            mVp.setCurrentItem(3);
        }
    }
    //        AppBarConfiguration build = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_topic
//                , R.id.navigation_sort, R.id.navigation_shop, R.id.navigation_me).build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, build);
//        NavigationUI.setupWithNavController(mNavView, navController);
//        mNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                int id = menuItem.getItemId();
//                if (id == R.id.navigation_home) {
//                    menuItem.setIcon(R.mipmap.ic_menu_choice_pressed);
//                } else if (id == R.id.navigation_topic) {
//                    menuItem.setIcon(R.mipmap.ic_menu_topic_pressed);
//                } else if (id == R.id.navigation_sort) {
//                    menuItem.setIcon(R.mipmap.ic_menu_sort_pressed);
//                } else if (id == R.id.navigation_shop) {
//                    menuItem.setIcon(R.mipmap.ic_menu_shoping_pressed);
//                } else {
//                    menuItem.setIcon(R.mipmap.ic_menu_me_pressed);
//                }
//                return true;
//            }
//        });

    class VpAdapter extends FragmentStatePagerAdapter {

        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
