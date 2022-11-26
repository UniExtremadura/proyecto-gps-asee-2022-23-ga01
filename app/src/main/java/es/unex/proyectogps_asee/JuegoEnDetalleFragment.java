package es.unex.proyectogps_asee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import es.unex.proyectogps_asee.databinding.FragmentJuegoEnDetalleBinding;
import es.unex.proyectogps_asee.modelos.Juego;

public class JuegoEnDetalleFragment extends Fragment {

    FragmentJuegoEnDetalleBinding binding;
    Juego juego;

    public JuegoEnDetalleFragment (Juego juego){
        this.juego = juego;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJuegoEnDetalleBinding.inflate(inflater, container, false);
        binding.title.setText(juego.getName());
        binding.description.setText(juego.getDescription());

        if(juego.getRating()!=null){
            binding.tv1.setText("Rating: " + juego.getRating());
            Double aux = juego.getRating()*5/100;
            binding.ratingBar2.setRating(aux.floatValue());
        } else{
            binding.tv1.setText("No Rating");
            binding.ratingBar2.setRating(0);
        }

        View view = binding.getRoot();

        return view;
    }
}