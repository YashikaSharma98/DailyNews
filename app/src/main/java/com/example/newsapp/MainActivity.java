package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.newsapp.databinding.ActivityHomeBinding;
import com.example.newsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bindingmain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the status bar
        hideStatusBarMainActivity();

        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        bindingmain = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bindingmain.getRoot());
        bindingmain.imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void hideStatusBarMainActivity() {
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

 class  ViewAdapter extends FragmentStateAdapter {


     public ViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
         super(fragmentManager, lifecycle);
     }

     @NonNull
     @Override
     public Fragment createFragment(int position) {
         return null;
     }

     @Override
     public int getItemCount() {
         return 0;
     }
 }