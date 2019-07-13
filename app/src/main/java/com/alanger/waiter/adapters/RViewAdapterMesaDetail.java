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


public class RViewAdapterMesaDetail
        extends RecyclerView.Adapter<RViewAdapterMesaDetail.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    private Context ctx;
    private List<PedidosResumen> pedidosResumenList;

    public RViewAdapterMesaDetail(Context ctx,List<PedidosResumen> pedidosResumenList) {
        this.pedidosResumenList = pedidosResumenList;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_mesa_item,null,false);
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

        holder.floatingActionButton.setOnClickListener(v->{
            showDialogMenu();
        });


        if(pedidosResumen.isEntragado()){
            holder.floatingActionButton.setClickable(false);
            holder.floatingActionButton.setFocusable(false);
            holder.floatingActionButton.setVisibility(View.INVISIBLE);
        }

    }

    private void showDialogMenu(){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ctx);
        builderSingle.setIcon(R.mipmap.ic_launcher_round);
        builderSingle.setTitle("Opciones");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1);

        arrayAdapter.add("Editar");
        arrayAdapter.add("Eliminar");




        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

            }
        });
        builderSingle.show();

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

        FloatingActionButton floatingActionButton;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tViewCantidad = itemView.findViewById(R.id.tViewCantidad);
            tViewName = itemView.findViewById(R.id.tViewName);
            tViewImporte = itemView.findViewById(R.id.tViewImporte);
            floatingActionButton = itemView.findViewById(R.id.floatingActionButton);

        }
    }
}
