package com.example.lrc_flag_system;

import android.os.Parcel;
import android.os.Parcelable;

public class TutorModel implements Parcelable {
    private String id;
    private String name;

    // constructor
    public TutorModel(String id, String name, String role, String subject){
        this.id = id;
        this.name = name;
    }

    public TutorModel(Parcel in) {

    }

    public static final Creator<TutorModel> CREATOR = new Creator<TutorModel>() {
        @Override
        public TutorModel createFromParcel(Parcel in) {
            return new TutorModel(in);
        }

        @Override
        public TutorModel[] newArray(int size) {
            return new TutorModel[size];
        }
    };

    //toString
    @Override
    public String toString() {
        return "TutorModel{" +
                "ID='" + id + "'" +
                "name='" + name + '\'' +
                '}';
    }


    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }
}
