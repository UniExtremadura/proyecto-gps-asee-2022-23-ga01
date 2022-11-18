package es.unex.proyectogps_asee;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.proyectogps_asee.databinding.CardCellBinding;
import es.unex.proyectogps_asee.databinding.FragmentFirstBinding;
import es.unex.proyectogps_asee.interfaces.JuegoAPI;
import es.unex.proyectogps_asee.modelos.Juego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private CardCellBinding binding;
    private static List<Juego> juegos = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CardCellBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testJuegos("https://api.igdb.com/v4/games/?fields=name,summary,rating,cover.image_id&search=minecraft/");
    }

    public void testJuegos(String url){
        Map<String, String> mapHeaders = new HashMap<String, String>() {{
            put("Client-ID", JuegoAPI.id_cliente);
            put("Authorization", JuegoAPI.token);
        }};

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        JuegoAPI client = retrofit.create(JuegoAPI.class);
        Call<List<Juego>> call = client.find(mapHeaders);

        call.enqueue(new Callback<List<Juego>>() {
            @Override
            public void onResponse(Call<List<Juego>> call, Response<List<Juego>> response) {
                juegos = response.body();
                Log.i("TAMANO DEL ARRAY",""+juegos.size());
            }

            @Override
            public void onFailure(Call<List<Juego>> call, Throwable t) {
                Log.i("ERROR DATOS API", "MIERDA, NO FUNCIONA");
            }
        });
    }
}