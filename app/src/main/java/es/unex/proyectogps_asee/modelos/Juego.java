package es.unex.proyectogps_asee.modelos;

public class Juego {

    private int id;
    private String name;
    private Double rating;
    private String sumary;
    private Cover cover;

    public Juego(){

    }

    public Juego(int id, String name, Double rating, String sumary, Cover cover) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.sumary = sumary;
        this.cover = cover;
    }

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
}
