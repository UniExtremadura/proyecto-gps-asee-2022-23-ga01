package es.unex.proyectogps_asee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import es.unex.proyectogps_asee.databinding.ActivityBusquedaJuegosBinding;
import es.unex.proyectogps_asee.databinding.ActivityMainBinding;

public class BusquedaJuegosActivity extends AppCompatActivity {

    private ActivityBusquedaJuegosBinding binding;
    private String url;
    private String genreName;
    private String gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBusquedaJuegosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        url = getIntent().getStringExtra("url");
        genreName = getIntent().getStringExtra("genreName");
        gameName = getIntent().getStringExtra("gameName");
        //Log.i("El juego es: ", gameName);
        replaceFragment(new ListaJuegosFragment(),genreName, gameName); //Initial Fragment by deafault

        binding.bottomNavigationView2.setOnItemSelectedListener( item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    replaceFragment(new HomeFragment(),null,null);
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment(),null,null);
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment(),null, null);
                    break;
            }
            return true;
        });
    }

    //Method tha replace the frame layout with the fragment requiered
    private void replaceFragment(Fragment fragment, @Nullable String genreName, @Nullable String gameName){
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        //Put the data to pass to the next fragment
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("genreName", genreName);
        bundle.putString("gameName", gameName);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);

        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }
}