package com.example.newsapp.fragmets;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.R;
import com.example.newsapp.RecyclerBind.DataAdapterBigList;
import com.example.newsapp.data.News;
import com.example.newsapp.databinding.FragmentTechBinding;
import com.example.newsapp.networkModule.NewsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentTechBinding binding;

    public TechFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TechFragment newInstance(String param1, String param2) {
        TechFragment fragment = new TechFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech, container, false);


        binding.recyclerSmallItem.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        Call<News> newsInterface = NewsService.getNews().getTechHeadlines("techcrunch");
        newsInterface.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (!response.isSuccessful()) {
                    Log.i("debug", "biglist" + response.message());
                    return;
                }
                binding.progressBar.setVisibility(View.GONE);
                binding.constraint1.setVisibility(View.VISIBLE);
                News articles = (News) response.body();
                binding.recyclerSmallItem.setAdapter(new DataAdapterBigList(binding.getRoot().getContext(), articles.getArticles()));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("fail", t.getMessage());

            }
        });

        return binding.getRoot();
    }
}