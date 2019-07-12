package com.alanger.waitermobile.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waitermobile.R;
import com.alanger.waitermobile.model.Mesa;
import com.alanger.waitermobile.model.PedidosResumen;

import java.util.List;


public class RViewAdapterMesaDetail
        extends RecyclerView.Adapter<RViewAdapterMesaDetail.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    List<Mesa> mesaList;

    public RViewAdapterMesaDetail(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mesas_item,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Mesa mesaTemp = mesaList.get(position);
        holder.tViewNOrden.setText(""+mesaTemp.getPosicion());
        if(mesaTemp.getPedidosResumenList().size()==0){
            holder.tView_Pendientes.setText("Sin Pendientes");
            holder.tViewListPendientes.setVisibility(View.GONE);
        }else {
            String pedidos="";
            for(int i=0;i<mesaTemp.getPedidosResumenList().size();i++){
                PedidosResumen p = mesaTemp.getPedidosResumenList().get(i);
                pedidos=pedidos+p.getUnidades()+" "+p.getNombre();
                if(mesaTemp.getPedidosResumenList().size()-1!=i){
                    pedidos=pedidos+"\n";
                }
            }
            holder.tViewListPendientes.setText(pedidos);
        }


        String colorEnable="#D81B60";
        String colorDisable="#bdbdbd";
        switch (mesaTemp.getEstado()){
            case 0:
                holder.tView_sLibre.setTextColor(Color.parseColor(colorEnable));
                holder.tView_sEspera.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sAtendido.setTextColor(Color.parseColor(colorDisable));
                break;
            case 1:
                holder.tView_sLibre.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sEspera.setTextColor(Color.parseColor(colorEnable));
                holder.tView_sAtendido.setTextColor(Color.parseColor(colorDisable));
                break;
            case 2:
                holder.tView_sLibre.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sEspera.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sAtendido.setTextColor(Color.parseColor(colorEnable));
                break;
            case 3:
                holder.tView_sLibre.setTextColor(Color.parseColor(colorEnable));
                holder.tView_sEspera.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sAtendido.setTextColor(Color.parseColor(colorDisable));
                holder.tView_sLibre.setText("Reserva");
                break;
        }




    }

    public void setOnClicListener(View.OnClickListener listener){
        this.onClickListener=listener;

    }

    @Override
    public int getItemCount() {
        return mesaList.size();
    }



    @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
    }

    public void verificar() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tView_sLibre;
        AppCompatTextView tView_sEspera;
        AppCompatTextView tView_sAtendido;

        AppCompatTextView tView_Pendientes;
        AppCompatTextView tViewListPendientes;
        AppCompatTextView tViewNOrden;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tView_sLibre = itemView.findViewById(R.id.imesa_tView_sLibre);
            tView_Pendientes = itemView.findViewById(R.id.imesa_tView_Pendientes);
            tViewListPendientes = itemView.findViewById(R.id.imesa_tViewListPendientes);
            tViewNOrden = itemView.findViewById(R.id.imesa_tViewNOrden);

            tView_sLibre = itemView.findViewById(R.id.imesa_tView_sLibre);
            tView_sEspera = itemView.findViewById(R.id.imesa_tView_sEspera);
            tView_sAtendido = itemView.findViewById(R.id.imesa_tView_sAtendido);

        }
    }
}
