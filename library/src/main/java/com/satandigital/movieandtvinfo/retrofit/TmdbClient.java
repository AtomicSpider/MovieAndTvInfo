package com.satandigital.movieandtvinfo.retrofit;

import android.content.Context;

import com.satandigital.movieandtvinfo.BuildConfig;
import com.satandigital.movieandtvinfo.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbClient {
    private static String BASE_URL = null;
    private TmdbApi.SearchMedia searchMedia;

    private Retrofit mRetrofit;
    private static TmdbClient mTmdbClient = null;

    private TmdbClient() {
        initializeRetrofit();
    }

    public static TmdbClient getInstance(Context context) {
        if (BASE_URL == null) BASE_URL = context.getResources().getString(R.string.TMDB_BASE_URL);

        if (mTmdbClient == null) {
            mTmdbClient = new TmdbClient();
        }
        return mTmdbClient;
    }

    public TmdbApi.SearchMedia getSearchMediaClient() {
        if (searchMedia == null) {
            searchMedia = mRetrofit.create(TmdbApi.SearchMedia.class);
        }
        return searchMedia;
    }

    private void initializeRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                                .build();
                        Request.Builder builder = request.newBuilder()
                                .url(url)
                                .method(request.method(), request.body());
                        request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
