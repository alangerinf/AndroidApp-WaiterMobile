package com.alanger.waiter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waiter.R;
import com.alanger.waiter.model.PedidosResumen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class RViewAdapterCobrar
        extends RecyclerView.Adapter<RViewAdapterCobrar.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    private List<PedidosResumen> pedidosResumenList;

    public RViewAdapterCobrar( List<PedidosResumen> pedidosResumenList) {
        this.pedidosResumenList = pedidosResumenList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cobrar_item,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PedidosResumen pedidosResumen = pedidosResumenList.get(position);
        holder.tViewCantidad.setText(""+pedidosResumen.getUnidades());
        holder.tViewName.setText(""+pedidosResumen.getNombre());
        holder.tViewImporte.setText(""+pedidosResumen.getImporteTotal());

    }



    @Override
    public int getItemCount() {
        return pedidosResumenList.size();
    }

    @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tViewCantidad;
        AppCompatTextView tViewName;
        AppCompatTextView tViewImporte;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tViewCantidad = itemView.findViewById(R.id.tViewCantidad);
            tViewName = itemView.findViewById(R.id.tViewNamePlato);
            tViewImporte = itemView.findViewById(R.id.tViewImporte);
        }
    }
}
