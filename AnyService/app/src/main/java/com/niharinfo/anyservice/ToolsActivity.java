package com.niharinfo.anyservice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SlidingDrawer;


import com.astuetz.PagerSlidingTabStrip;
import com.demach.konotor.Konotor;


public class ToolsActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener{

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private Drawable oldBackground = null;
    private int currentColor = 0xFF666666;
    PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
        tabs.setTextColor(getResources().getColor(R.color.white));
        changeColor(currentColor);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tools, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_overflow){
                LayoutInflater inflater = (LayoutInflater)ToolsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.overflow_menu_layout,null,false);
                ImageView imageView =(ImageView)v.findViewById(R.id.imgUser);
                Display display = getWindowManager().getDefaultDisplay();
                int deviceWidth = display.getWidth()-240;
                int deviceHeight = display.getHeight();
                pw = new PopupWindow(v,deviceWidth,deviceHeight,true);
            pw.setBackgroundDrawable(new BitmapDrawable());
            pw.setOutsideTouchable(true);
                pw.showAtLocation(findViewById(R.id.action_overflow), Gravity.TOP | Gravity.RIGHT, 0, 0);
                pw.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
                            pw.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pw.dismiss();
                }
            });
            return true;
        }
        if(id == R.id.action_chat){
            Konotor.getInstance(getApplicationContext())
                    .launchFeedbackScreen(ToolsActivity.this);
        }
        if(id == R.id.action_call){
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7799636636"));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Konotor.getInstance(getApplicationContext())
                .withUserName("Chaitanya")            // optional name by which to display the user
                .withIdentifier("Chaitanya.V")            // optional unique identifier for your reference
                .withUserEmail("vedantamskc@gmail.com")        // optional email address of the user
                .withUserMeta("age", "27")            // optional metadata for your user
                .withLaunchMainActivityOnFinish(true) // to launch your app on hitting the back button on Konotor's inbox interface, in case the app was not running already
                .init("aef2a8fa-7dc3-493d-9233-3469d433768b", "97cbba49-9ff8-4c67-885b-25fc5289519b");
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position){
        pager.setCurrentItem(position);
        tabs.setViewPager(pager);

    }

    private void changeColor(int newColor) {

        tabs.setIndicatorColor(getResources().getColor(R.color.white));
        tabs.setDividerColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Drawable colorDrawable = new ColorDrawable(newColor);
            Drawable bottomDrawable = new ColorDrawable(R.color.white);
            LayerDrawable ld = new LayerDrawable(new Drawable[] { colorDrawable, bottomDrawable });
            if (oldBackground == null) {//%MCEPASTEBIN%
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                } else {

                }
            } else {
                TransitionDrawable td = new TransitionDrawable(new Drawable[] { oldBackground, ld });
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                } else {

                }
                td.startTransition(200);
            }
            oldBackground = ld;
        }
        currentColor = newColor;


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentColor", currentColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentColor = savedInstanceState.getInt("currentColor");
        changeColor(currentColor);
    }

}
