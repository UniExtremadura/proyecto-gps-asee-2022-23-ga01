package es.unex.proyectogps_asee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import es.unex.proyectogps_asee.databinding.ActivityBusquedaJuegosBinding;
import es.unex.proyectogps_asee.databinding.ActivityMainBinding;

public class BusquedaJuegosActivity extends AppCompatActivity {

    private ActivityBusquedaJuegosBinding binding;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBusquedaJuegosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        url = getIntent().getStringExtra("url");

        replaceFragment(new ListaJuegosFragment()); //Initial Fragment by deafault

        binding.bottomNavigationView2.setOnItemSelectedListener( item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    //Method tha replace the frame layout with the fragment requiered
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        //Put the data to pass to the next fragment
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);

        fragmentTransaction.replace(R.id.frame_layout2, fragment);
        fragmentTransaction.commit();
    }
}