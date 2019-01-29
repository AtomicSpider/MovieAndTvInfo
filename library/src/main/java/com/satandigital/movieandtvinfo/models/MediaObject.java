package com.satandigital.movieandtvinfo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaObject implements Parcelable {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("media_type")
    private String media_type;

    @Expose
    @SerializedName("original_name")
    private String original_name;

    @Expose
    @SerializedName("poster_path")
    private String poster_path;

    @Expose
    @SerializedName("genre_ids")
    private int[] genre_ids;

    @Expose
    @SerializedName("overview")
    private String overview;

    public MediaObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.media_type);
        dest.writeString(this.original_name);
        dest.writeString(this.poster_path);
        dest.writeIntArray(this.genre_ids);
        dest.writeString(this.overview);
    }

    protected MediaObject(Parcel in) {
        this.id = in.readInt();
        this.media_type = in.readString();
        this.original_name = in.readString();
        this.poster_path = in.readString();
        this.genre_ids = in.createIntArray();
        this.overview = in.readString();
    }

    public static final Creator<MediaObject> CREATOR = new Creator<MediaObject>() {
        @Override
        public MediaObject createFromParcel(Parcel source) {
            return new MediaObject(source);
        }

        @Override
        public MediaObject[] newArray(int size) {
            return new MediaObject[size];
        }
    };
}
