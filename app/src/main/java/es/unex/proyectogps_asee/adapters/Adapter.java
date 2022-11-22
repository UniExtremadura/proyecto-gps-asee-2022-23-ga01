package es.unex.proyectogps_asee.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unex.proyectogps_asee.R;
import es.unex.proyectogps_asee.modelos.Juego;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Juego> juegos;

    public Adapter(List<Juego> juegos){
        this.juegos = juegos;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_lista_de_juegos,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.tv1.setText(juegos.get(position).getNombre());
        Log.i("ELEMENTO DEL JUEGO", "" + juegos.get(1).getNombre());
        holder.tv2.setText(juegos.get(position).getRating().toString());
    }

    @Override
    public int getItemCount() {
        return juegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }
}
