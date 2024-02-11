package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsapp.fragmets.BusinessFragment;
import com.example.newsapp.fragmets.EntertainmentFragment;
import com.example.newsapp.fragmets.SportsFragment;
import com.example.newsapp.fragmets.TechFragment;
import com.example.newsapp.fragmets.WorldFragment;

public class FragAdapter extends FragmentStateAdapter {
    public FragAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new BusinessFragment();
            case 2:
                return new TechFragment();
            case 3:
                return new SportsFragment();
            case 4:
                return new EntertainmentFragment();
        }

        return new WorldFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

