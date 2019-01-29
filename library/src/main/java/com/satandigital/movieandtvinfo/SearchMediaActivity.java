package com.satandigital.movieandtvinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.satandigital.movieandtvinfo.adapters.MediaAdapter;
import com.satandigital.movieandtvinfo.models.MediaObject;
import com.satandigital.movieandtvinfo.models.TmdbRawMediaObject;
import com.satandigital.movieandtvinfo.retrofit.TmdbClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMediaActivity extends AppCompatActivity {

    private final String TAG = SearchMediaActivity.class.getSimpleName();

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private MediaAdapter mediaAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_media);

        mProgressDialog = new ProgressDialog(SearchMediaActivity.this);
        mProgressDialog.setMessage("Please wait...");
        findViews();
        setRecyclerView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mProgressDialog.show();
                Call<TmdbRawMediaObject> call = TmdbClient.getInstance(SearchMediaActivity.this)
                        .getSearchMediaClient()
                        .getSearchMedia(s);
                Callback<TmdbRawMediaObject> callback = new Callback<TmdbRawMediaObject>() {
                    @Override
                    public void onResponse(Call<TmdbRawMediaObject> call, Response<TmdbRawMediaObject> response) {
                        mProgressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Log.i(TAG, "Retrofit Response Successful");
                            mediaAdapter.setData(response.body().getResults());
                        } else {
                            Log.e(TAG, "Retrofit Failure" + response.message());
                            Toast.makeText(SearchMediaActivity.this, "Error fetching media", Toast.LENGTH_SHORT).show();
                            mediaAdapter.setData(new ArrayList<MediaObject>());
                        }
                    }

                    @Override
                    public void onFailure(Call<TmdbRawMediaObject> call, Throwable t) {
                        mProgressDialog.dismiss();
                        Log.e(TAG, "Retrofit Failure" + t.getMessage());
                        Toast.makeText(SearchMediaActivity.this, "Error fetching media", Toast.LENGTH_SHORT).show();
                        mediaAdapter.setData(new ArrayList<MediaObject>());
                    }
                };
                call.enqueue(callback);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(SearchMediaActivity.this, 2));
        mediaAdapter = new MediaAdapter(SearchMediaActivity.this);
        mediaAdapter.setData(new ArrayList<MediaObject>());
        mRecyclerView.setAdapter(mediaAdapter);
    }

    private void findViews() {
        mSearchView = findViewById(R.id.search_view);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    public void selectedMedia(MediaObject mediaObject) {
        Intent data = new Intent();
        data.putExtra("media", mediaObject);
        setResult(RESULT_OK, data);
        finish();
    }
}
