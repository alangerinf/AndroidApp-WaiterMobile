package com.alanger.waiter.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waiter.R;
import com.alanger.waiter.model.PedidosResumen;
import com.alanger.waiter.model.Plato;

import java.util.List;


public class RViewAdapterModoCocina
        extends RecyclerView.Adapter<RViewAdapterModoCocina.ViewHolder>
        {

    private List<PedidosResumen> pedidosResumenList;

    private Context ctx;

    public RViewAdapterModoCocina(Context ctx,List<PedidosResumen> pedidosResumenList) {
        this.pedidosResumenList = pedidosResumenList;
        this.ctx=ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_modo_cocina_item,null,false);

        return new ViewHolder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PedidosResumen pedidosResumen = pedidosResumenList.get(position);
        holder.tViewCantidad.setText(""+pedidosResumen.getUnidades());
        holder.tViewName.setText(""+pedidosResumen.getNombre());

        holder.index.setOnClickListener(v->{
            showPopupSelectCantidadPlato(holder.tViewCantidad,pedidosResumen);
        });

    }

    private void showPopupSelectCantidadPlato(TextView tView, PedidosResumen PedidosResumen){
        Dialog dialogClose;
        dialogClose = new Dialog(ctx);
        dialogClose.setContentView(R.layout.dialog_cantidad_plato);
        Button btnOk = (Button) dialogClose.findViewById(R.id.btnOk);
        ImageView iViewDialogClose = (ImageView) dialogClose.findViewById(R.id.iViewDialogClose);
        TextView tViewRecomendacion = dialogClose.findViewById(R.id.tViewRecomendacion);
        TextView tViewNamePlato = dialogClose.findViewById(R.id.tViewNamePlato);

        tViewNamePlato.setText(PedidosResumen.getNombre());
        tViewRecomendacion.setText("Solo se pueden entregar "+PedidosResumen.getUnidades()+" platos");

        EditText eTextCantidad= dialogClose.findViewById(R.id.eTextCantidad);

        eTextCantidad.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(eTextCantidad.getText().toString().equals("")||(Integer.valueOf(eTextCantidad.getText().toString())==0&&eTextCantidad.getText().toString().length()>1)){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            eTextCantidad.setText("0");
                            eTextCantidad.setSelection(eTextCantidad.getText().toString().length());
                        }
                    });

                }else {

                    if(Integer.valueOf(eTextCantidad.getText().toString())>PedidosResumen.getUnidades()){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                eTextCantidad.setError("Máximo superado");
                            }
                        });

                    }

                }

                if((!eTextCantidad.getText().toString().equals(""))&&String.valueOf(Integer.valueOf(eTextCantidad.getText().toString())).length()!= eTextCantidad.getText().toString().length() ){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            eTextCantidad.setText(String.valueOf(Integer.valueOf(eTextCantidad.getText().toString())));
                            eTextCantidad.setSelection(eTextCantidad.getText().toString().length());
                        }
                    });

                }


            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        iViewDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClose.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eTextCantidad.getText().toString().equals("0")&&Integer.valueOf(eTextCantidad.getText().toString())>PedidosResumen.getUnidades()){
                    Toast.makeText(ctx,"No se entregaron Platos",Toast.LENGTH_LONG).show();
                    tView.setText(eTextCantidad.getText().toString());
                }else {
                    //insertar
                    Toast.makeText(ctx,"Se entregaron "+eTextCantidad.getText().toString()+" Platos",Toast.LENGTH_LONG).show();

                }
                dialogClose.dismiss();

            }
        });

        dialogClose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogClose.show();
    }


    @Override
    public int getItemCount() {
        return pedidosResumenList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tViewCantidad;
        AppCompatTextView tViewName;
        ConstraintLayout index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.ipedido_index );
            tViewCantidad = itemView.findViewById(R.id.tViewCantidad);
            tViewName = itemView.findViewById(R.id.tViewName);
        }
    }
}
