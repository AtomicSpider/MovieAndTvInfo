package com.satandigital.movieandtvinfo.retrofit;

import com.satandigital.movieandtvinfo.models.TmdbRawMediaObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TmdbApi {

    public interface SearchMedia {
        @GET("search/multi")
        Call<TmdbRawMediaObject> getSearchMedia(@Query("query") String query);
    }
}
