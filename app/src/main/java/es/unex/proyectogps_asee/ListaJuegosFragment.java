package es.unex.proyectogps_asee;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.proyectogps_asee.adapters.Adapter;
import es.unex.proyectogps_asee.databinding.CardCellBinding;
import es.unex.proyectogps_asee.databinding.ListRecyclerviewBinding;
import es.unex.proyectogps_asee.interfaces.JuegoAPI;
import es.unex.proyectogps_asee.modelos.Juego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaJuegosFragment extends Fragment {

    private static List<Juego> juegos = new ArrayList();
    private String url;
    private String genreName;
    private String gameName;
    private RecyclerView recyclerView;
    private ListRecyclerviewBinding binding;

    public ListaJuegosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListRecyclerviewBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            this.url = bundle.getString("url");
            this.genreName = bundle.getString("genreName");
            this.gameName = bundle.getString("gameName");

            if(genreName!=null) {
                busquedaJuegosPorCategoria(this.url,genreName); //Obtiene los juegos de la API y los mete en la lista de juegos
            }
            else if(gameName!=null){
                busquedaJuegosPorNombre(this.url,gameName);
            } else{Toast.makeText(getContext(),"Error al cargar la categoria", Toast.LENGTH_LONG);}
        }

        // Init Recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.rview);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);



        return view;
    }

    public void busquedaJuegosPorCategoria(String url, String genre){
        Map<String, String> mapHeaders = new HashMap<String, String>() {{
            put("Client-ID", JuegoAPI.id_cliente);
            put("Authorization", JuegoAPI.token);
        }};

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.igdb.com/v4/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        JuegoAPI client = retrofit.create(JuegoAPI.class);
        //Log.i("EL GENERO ELEGIDO ES: ",""+genre);
        Call<List<Juego>> call = client.find(mapHeaders, this.genreName);
        //Call<List<Juego>> call = client.find(mapHeaders,genre);

        call.enqueue(new Callback<List<Juego>>() {
            @Override
            public void onResponse(Call<List<Juego>> call, Response<List<Juego>> response) {
                juegos = response.body();
                Adapter adapter = new Adapter(juegos, new Adapter.ItemClickListener() {
                    @Override
                    public void onItemClick(Juego juego) {

                        replaceFragment(new JuegoEnDetalleFragment(juego));
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Juego>> call, Throwable t) {
                Log.i("ERROR DATOS API", "MIERDA, NO FUNCIONA");
            }
        });
    }

    public void busquedaJuegosPorNombre(String url, String gameName){
        Map<String, String> mapHeaders = new HashMap<String, String>() {{
            put("Client-ID", JuegoAPI.id_cliente);
            put("Authorization", JuegoAPI.token);
        }};

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.igdb.com/v4/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        JuegoAPI client = retrofit.create(JuegoAPI.class);
        //Log.i("EL JUWGO ELEGIDO ES: ",""+gameName);
        Call<List<Juego>> call = client.busquedaDirecta(mapHeaders,gameName);

        call.enqueue(new Callback<List<Juego>>() {
            @Override
            public void onResponse(Call<List<Juego>> call, Response<List<Juego>> response) {
                juegos = response.body();
                Adapter adapter = new Adapter(juegos, new Adapter.ItemClickListener() {
                    @Override
                    public void onItemClick(Juego juego) {
                        replaceFragment(new JuegoEnDetalleFragment(juego));
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Juego>> call, Throwable t) {
                Log.i("ERROR DATOS API", "MIERDA, NO FUNCIONA");
            }
        });
    }

    //Method tha replace the frame layout with the fragment requiered
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }
}