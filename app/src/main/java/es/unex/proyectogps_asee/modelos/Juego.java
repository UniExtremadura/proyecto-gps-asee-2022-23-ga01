package es.unex.proyectogps_asee.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Juego implements Serializable {

    int id;
    String name;
    Double rating;
    String summary;
    Cover cover;

    public Juego(){
    }

    public Juego(int id, String name, Double rating, String summary, @Nullable Cover cover) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.summary = summary;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return summary;
    }

    public void setDescription(String sumary) {
        this.summary = sumary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

}
