package es.unex.proyectogps_asee.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class Juego implements Parcelable {

    private int id;
    private String name;
    private Double rating;
    private String sumary;
    private Cover cover;

    public Juego(){

    }

    public Juego(int id, String name, Double rating, String sumary, @Nullable Cover cover) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.sumary = sumary;
        this.cover = cover;
    }

    protected Juego(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        sumary = in.readString();
    }

    public static final Creator<Juego> CREATOR = new Creator<Juego>() {
        @Override
        public Juego createFromParcel(Parcel in) {
            return new Juego(in);
        }

        @Override
        public Juego[] newArray(int size) {
            return new Juego[size];
        }
    };

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return sumary;
    }

    public void setDescription(String sumary) {
        this.sumary = sumary;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
        parcel.writeString(sumary);
    }
}
