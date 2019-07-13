package com.alanger.waiter;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.alanger.waiter.model.Mesa;


public class CobrarActivity extends AppCompatActivity {

    String TAG = CobrarActivity.class.getSimpleName();
    Mesa mesa;
    TextView tViewNOrden;
    TextView tViewCuenta;
    AppCompatButton btnQR;
    int REQUEST_QR_NPALLET = 2134;

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

}
