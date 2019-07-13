package com.alanger.waiter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waiter.R;
import com.alanger.waiter.model.Mesa;
import com.alanger.waiter.model.Pedido;

import java.util.List;


public class RViewAdapterPedido
        extends RecyclerView.Adapter<RViewAdapterPedido.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    List<Pedido> pedidoList;

    public RViewAdapterPedido(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pedidos_listos_item,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Mesa mesaTemp = pedidoList.get(position);
        holder.tViewNOrden.setText(""+mesaTemp.getPosicion());
        holder.tViewNumPallet.setText(""+mesaTemp.getNombre());

        holder.button.setOnClickListener(v->{
            pedidoList.remove(mesaTemp);
            this.notifyDataSetChanged();
        });

    }



    @Override
    public int getItemCount() {
        return pedidoList.size();
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

        AppCompatTextView tViewNumPallet;
        AppCompatTextView tViewNOrden;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tViewNumPallet = itemView.findViewById(R.id.iplisto_tViewName);
            button =  itemView.findViewById(R.id.button);
            tViewNOrden = itemView.findViewById(R.id.iplisto_tViewNOrden);
        }
    }
}
