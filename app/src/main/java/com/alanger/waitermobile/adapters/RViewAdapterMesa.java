package com.alanger.waitermobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waitermobile.R;
import com.alanger.waitermobile.model.Mesa;

import java.util.List;


public class RViewAdapterMesa
        extends RecyclerView.Adapter<RViewAdapterMesa.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener onClickListener;

    List<Mesa> mesaList;

    public RViewAdapterMesa(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_mesa,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Mesa mesaTemp = mesaList.get(position);
        holder.tViewNOrden.setText(""+mesaTemp.getPos());

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

        AppCompatTextView tView_NumPallet;
        AppCompatTextView tViewNumPallet;
        AppCompatTextView tViewNumSensors;
        AppCompatTextView tViewNOrden;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tView_NumPallet = itemView.findViewById(R.id.imesa_tView_sLibre);
            tViewNumPallet = itemView.findViewById(R.id.imesa_tViewNumPallet);
            tViewNumSensors = itemView.findViewById(R.id.imesa_tViewNumSensors);
            tViewNOrden = itemView.findViewById(R.id.imesa_tViewNOrden);
        }
    }
}
