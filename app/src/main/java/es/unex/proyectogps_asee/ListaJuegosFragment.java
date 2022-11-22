package es.unex.proyectogps_asee;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

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
            Log.i("url",url); //Lanza el fragment y pasa de forma correcta la url en este punto
            initData();
            //testJuegos(this.url); //Obtiene los juegos de la API y los mete en la lista de juegos
        }

        // Init Recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.rview);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);

        //Init adapter
        Adapter adapter = new Adapter(juegos);
        recyclerView.setAdapter(adapter);

        return view;
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
                //juegos = response.body();


                Log.i("TAMANO DEL ARRAY",""+juegos.size()); //En este punto consigue de forma correcta los datos de la API
            }

            @Override
            public void onFailure(Call<List<Juego>> call, Throwable t) {
                Log.i("ERROR DATOS API", "MIERDA, NO FUNCIONA");
            }
        });
    }

    public void initData(){
        juegos = new ArrayList<Juego>();
        Juego j1 = new Juego(1,"mena",2.0,"",null);
        Juego j2 = new Juego(2,"ned",2.0,"",null);
        Juego j3 = new Juego(3,"mefsdfna",2.0,"",null);
        Juego j4 = new Juego(4,"meaaaana",2.0,"",null);
        Juego j5 = new Juego(5,"menvvva",2.0,"",null);
        Juego j6 = new Juego(6,"meaaaana",2.0,"",null);
        juegos.add(j1);
        juegos.add(j2);
        juegos.add(j3);
        juegos.add(j4);
        juegos.add(j5);
        juegos.add(j6);
    }
}