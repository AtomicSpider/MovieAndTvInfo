package com.satandigital.movieandtvinfo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TmdbRawMediaObject {

    @Expose
    @SerializedName("page")
    private int page;

    @Expose
    @SerializedName("results")
    private ArrayList<MediaObject> results;

    @Expose
    @SerializedName("total_pages")
    private int total_pages;

    @Expose
    @SerializedName("total_results")
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MediaObject> getResults() {
        return results;
    }

    public void setResults(ArrayList<MediaObject> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}