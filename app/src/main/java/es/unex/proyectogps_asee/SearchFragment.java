package es.unex.proyectogps_asee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CardCellBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lanza el activity de busquedas pasandole como parametro la url
                //Al llamar al activity de Busqueda de juegos hay que pasarle siempre la url de los juegos que tiene que cargar
                Intent intent = new Intent(getActivity(),BusquedaJuegosActivity.class);
                intent.putExtra("url","https://api.igdb.com/v4/games/?fields=name,summary,rating,cover.image_id&search=minecraft/" );
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}