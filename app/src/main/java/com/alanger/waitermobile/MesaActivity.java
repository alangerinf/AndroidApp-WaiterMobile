package com.alanger.waitermobile;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alanger.waitermobile.model.Mesa;


public class MesaActivity extends AppCompatActivity {

    String TAG  = MesaActivity.class.getSimpleName();
    Mesa mesa;
    TextView tViewNOrden;
    TextView tViewCuenta;
    TextView tView_Importe;
    ConstraintLayout clContent;
    AppCompatButton btnCobrar;
    int REQUEST_QR_NPALLET=2134;

    final public static String PARAM_MESA = "mesa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);


        Bundle b = getIntent().getExtras();
        assert b != null;
        mesa = (Mesa) b.getSerializable(PARAM_MESA);
        assert mesa != null;
        Log.d(TAG, mesa.toString());

        define();
        defineAtribs();


        events();
    }

    private void events() {
        btnCobrar.setOnClickListener(v->{
            Mesa item = mesa;
            Intent i = new Intent(this, CobrarActivity.class);

            ActivityOptions options = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation
                    (this,
                            Pair.create(btnCobrar, btnCobrar.getTransitionName()),
                            Pair.create(tViewCuenta, tViewCuenta.getTransitionName()),
                            Pair.create(tView_Importe, tView_Importe.getTransitionName()),
                            Pair.create(clContent, clContent.getTransitionName())
                    );
            Bundle bundleExtra = new Bundle();
            bundleExtra.putSerializable(MesaActivity.PARAM_MESA,  item);
            i.putExtras(bundleExtra);
            startActivity(i, options.toBundle());
            v.setClickable(false);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnCobrar.setClickable(true);
    }

    @SuppressLint("SetTextI18n")
    private void defineAtribs() {
        tViewNOrden.setText(""+ mesa.getPos());
    }

    private void define() {
        btnCobrar = findViewById(R.id.mesa_btnCobrar);
        tViewNOrden = findViewById(R.id.mesa_tViewNOrden);
        tViewCuenta = findViewById(R.id.mesa_tViewCuenta);
        tView_Importe = findViewById(R.id.mesa_tView_Importe);
        clContent = findViewById(R.id.mesa_clContentTotal);
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
        //finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QR_NPALLET) {

            try {
                Bundle recibidos = (data.getExtras());
                if (recibidos != null) {
                    String qr = recibidos.getString("qr");
                }
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
        }
    }
}
