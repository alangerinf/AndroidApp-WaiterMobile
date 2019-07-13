package com.alanger.waiter;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waiter.adapters.RViewAdapterCobrar;
import com.alanger.waiter.model.Mesa;
import com.alanger.waiter.model.PedidosResumen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;


public class CobrarActivity extends AppCompatActivity {

    String TAG = CobrarActivity.class.getSimpleName();
    Mesa mesa;
    TextView tViewNOrden;
    TextView tViewCuenta;
    AppCompatButton btnQR;
    int REQUEST_QR_NPALLET = 2134;
    TextView cobrar_tView_Importe;
    TextView tViewCode;


    List<PedidosResumen> pedidosResumenList;
    RecyclerView recyclerView;
    RViewAdapterCobrar adapter;

    static FloatingActionButton fAButtonQR;
    final public static String PARAM_MESA = "mesa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobrar);


        Bundle b = getIntent().getExtras();
        assert b != null;
        mesa = (Mesa) b.getSerializable(PARAM_MESA);
        assert mesa != null;
        Log.d(TAG, mesa.toString());

        define();
        defineAtribs();

        fAButtonQR.setOnClickListener(v->{
            IntentIntegrator intentIntegrator =new IntentIntegrator(this);
            intentIntegrator
                    .setOrientationLocked(false)
                    .setCaptureActivity(CustomScannerActivity.class)
                    .setRequestCode(REQUEST_QR_NPALLET)
                    .initiateScan();
        });
        //events();
    }

    /*
        private void events() {
            btnQR.setOnClickListener(v->{
                IntentIntegrator intentIntegrator =new IntentIntegrator(this);
                intentIntegrator
                        .setOrientationLocked(false)
                        .setCaptureActivity(CustomScannerActivity.class)
                        .setRequestCode(REQUEST_QR_NPALLET)
                        .initiateScan();
            });
        }
    */
    @SuppressLint("SetTextI18n")
    private void defineAtribs() {
        tViewNOrden.setText("" + mesa.getPosicion());
    }

    private void define() {
        tViewNOrden = findViewById(R.id.cobrar_tViewNOrden);
        tViewCuenta = findViewById(R.id.cobrar_tViewCuenta);
        fAButtonQR = findViewById(R.id.mesa_fAButtonQR);
        tViewCode = findViewById(R.id.tViewCode);
        cobrar_tView_Importe = findViewById(R.id.cobrar_tViewCuenta);
        cargarPedidos();
    }
    private void cargarPedidos(){
        pedidosResumenList = new ArrayList<>();

        pedidosResumenList.add(new PedidosResumen(2,"Arroz con Pollo",12.3f,true));
        pedidosResumenList.add(new PedidosResumen(3,"Cabrito",30.3f,false));
        pedidosResumenList.add(new PedidosResumen(2,"Pepian",22.3f,false));
        pedidosResumenList.add(new PedidosResumen(1,"Lomo Saltado",15.3f,true));
        pedidosResumenList.add(new PedidosResumen(4,"Pollada",11.3f,true));

        adapter = new RViewAdapterCobrar(pedidosResumenList);
        recyclerView = findViewById(R.id.cobrar_rViewPedidos);

        recyclerView.setAdapter(adapter);

        float count =0.0f;
        for(PedidosResumen p :pedidosResumenList ){
            count=count+p.getImporteTotal();
        }
        if(count==0){
            cobrar_tView_Importe.setText("S/. 0.00");
        }else {

            cobrar_tView_Importe.setText("S/. "+count);
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        /*
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("pallet", (Serializable) mesa);
        i.putExtras(bundle);
        setResult(Activity.RESULT_OK,i);
        super.onBackPressed();
        */
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QR_NPALLET) {

            try {
                Bundle recibidos = (data.getExtras());
                if (recibidos != null) {
                    String qr = recibidos.getString("qr");

                    try {
                        double d = Double.parseDouble(qr);
                        Toast.makeText(getApplicationContext(),"Se ingresó el código\nde descuento :S/. "+qr,Toast.LENGTH_LONG).show();
                        double count =0.0f;
                        for(PedidosResumen p :pedidosResumenList ){
                            count=count+p.getImporteTotal();
                        }
                        tViewCode.setText("Se descontarón S/. "+d);
                        count = count-d;
                        if(count<=0){
                            cobrar_tView_Importe.setText("S/. 0.00");
                        }else {

                            cobrar_tView_Importe.setText("S/. "+(float)count);
                        }
                    } catch (NumberFormatException | NullPointerException nfe) {
                        Toast.makeText(getApplicationContext(),"Se ingresó el código invalido",Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
        }
    }
}
