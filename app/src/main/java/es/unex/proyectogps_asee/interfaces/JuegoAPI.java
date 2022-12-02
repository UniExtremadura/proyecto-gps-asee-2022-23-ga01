package es.unex.proyectogps_asee.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.proyectogps_asee.modelos.Juego;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JuegoAPI {
    public static final String id_cliente = "2ed4mxy3i716dml120q2vhsbcpgizs";
    public static final String client_secret = "hycupr3hfkp53t6m4dq944ro8tnd6q";
    public static String token = "Bearer 19snypduzg5xovba3kzfe483zcs6sa";
    public static String fields = "name;";
    public static String where = "genres.slug=\"music\";";

    //games/?fields=name,summary,rating,cover.image_id&search=minecraft/
    //games/?fields=name,rating&genres.shooter
    @POST("games/")
    public Call<List<Juego>> find(@HeaderMap Map<String, String> mapHeaders, @Body HashMap<String,String> body);
    // @Query("genreName") String genreName

    @POST("games/?fields=name,summary,rating,cover.image_id")
    public Call<List<Juego>> busquedaDirecta(@HeaderMap Map<String, String> mapHeaders, @Query("search") String nameGame);


}