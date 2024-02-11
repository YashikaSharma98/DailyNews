package com.example.newsapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.newsapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding binding;
    FragAdapter fragAdapter;
    FragmentManager fragmentManager;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding.toolbar.setTitle("");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawer, R.string.app_name, R.string.app_name);
        binding.drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navigationView.setItemIconTintList(null);

        if (binding.navigationView != null) {
            binding.navigationView.setNavigationItemSelectedListener(this);
        }

        fragmentManager = getSupportFragmentManager();
        fragAdapter = new FragAdapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(fragAdapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home1:
                //close navigation drawer
                binding.drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.bookmarksNews: {
                Intent intent = new Intent(HomeActivity.this, Bookmarks.class);
                startActivity(intent);
                break;
            }

            case R.id.settingsNews:
                break;
            case R.id.about: {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shareNews:
                inviteFriends();
                break;
            case R.id.rateUs:
                rateUs();
                break;
        }
        return false;
    }


    private void rateUs() {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " Unable to find source market app! try again", Toast.LENGTH_LONG).show();

        }
    }

    private void inviteFriends() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey what's up? read latest news. Download it from here... \n https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, "Share a link");
        startActivity(shareIntent);
    }

    private void hideStatusBar() {
        // Fullscreen mode
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.WHITE);


        // If you are using Android API level 30 (Android 11) or higher, you can use the following method
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            // For lower API levels, use the following method
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

}