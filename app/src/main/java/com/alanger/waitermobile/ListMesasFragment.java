package com.alanger.waitermobile;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alanger.waitermobile.adapters.RViewAdapterMesa;
import com.alanger.waitermobile.app.AppController;
import com.alanger.waitermobile.model.Mesa;
import com.alanger.waitermobile.model.PedidosResumen;
import com.alanger.waitermobile.model.SharedPreferencesManager;
import com.alanger.waitermobile.model.User;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListMesasFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ProgressDialog progressDialog;
    private  static List<Mesa> mesaList;

    Context ctx;
    private static RViewAdapterMesa adapter;

    private static RecyclerView rViewMesas;

    private static int REQUESTCODE_EDITPALLET=1001;


    String TAG = ListMesasFragment.class.getSimpleName();

    static Activity activity;

    public ListMesasFragment(Activity mainActivity) {
        this.activity=mainActivity;
    }


    // TODO: Rename and change types and number of parameters
    public static ListMesasFragment newInstance(String param1, String param2) {
        ListMesasFragment fragment = new ListMesasFragment(activity);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public void onStart() {
        super.onStart();

        define();
        //addMesas();
        User user = SharedPreferencesManager.getUser(ctx);
        consultarMesas(user.getToken());
        events();
    }

    private void startAnimation() {
        final Animation anim_rightFadeIn2 =
                android.view.animation.AnimationUtils.loadAnimation(getContext(),R.anim.bot_fade_in);
        Handler handler2 = new Handler();
        handler2.post(
                () -> {
                    rViewMesas.startAnimation(anim_rightFadeIn2);
                    rViewMesas.setVisibility(View.VISIBLE);
                }
        );
    }
    Handler handler = new Handler();
    private void events() {



    }

    private void consultarMesas(String token){
        ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Buscando Mesas");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.d(TAG,"entro en consulta");

        String url = ConectionConfig.POST_MESAS_PENDIENTES;
        Log.d(TAG,url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            int codigoRespuesta = response.getInt("codigoRespuesta");
                            if(codigoRespuesta==ConectionConfig.HTTP_OK) {

                                JSONObject datos = new JSONObject(String.valueOf(response.getJSONObject("datos")));
                                JSONArray mesas = datos.getJSONArray("mesas");
                                mesaList.clear();

                                for(int i=0;i<mesas.length();i++){
                                    //convertir cada Batch  por GSON
                                    Gson gson = new Gson();
                                    Log.d(TAG,"Mesaaaa : "+mesas.getJSONObject(i).toString());

                                    JSONObject mesaData = mesas.getJSONObject(i);

                                    int idMesa = mesaData.getInt("idMesa");
                                    String nombre = mesaData.getString("nombre");
                                    int posicion = mesaData.getInt("posicion");
                                    int estado = mesaData.getInt("estado");

                                    Mesa mesaTemp = new Mesa(idMesa,posicion);
                                    mesaTemp.setNombre(nombre);
                                    mesaTemp.setEstado(estado);

                                    JSONArray pedidosPendientes = mesaData.getJSONArray("pedidosPendientes");

                                    List<PedidosResumen> pedidosResumenList = new ArrayList<>();
                                    for(int j=0;j<pedidosPendientes.length();j++){

                                        JSONObject  pedidosPendienteDataTemp = pedidosPendientes.getJSONObject(j);
                                        Log.d(TAG,"\n->"+pedidosPendienteDataTemp.toString());
                                        PedidosResumen pedidosResumen = new PedidosResumen(
                                                pedidosPendienteDataTemp.getInt("unidades"),
                                                pedidosPendienteDataTemp.getString("nombre"),
                                                pedidosPendienteDataTemp.getInt("importeTotal")
                                        );
                                        pedidosResumenList.add(pedidosResumen);
                                    }
                                    mesaTemp.setPedidosResumenList(pedidosResumenList);
                                    mesaList.add(mesaTemp);
                                }

                                if(mesaList.size()>0){
                                   // tViewSinBatch.setVisibility(View.INVISIBLE);
                                }

                                adapter = new RViewAdapterMesa(mesaList);

                                adapter = new RViewAdapterMesa(mesaList);
                                adapter.setOnClicListener(v -> {
            /*
            obtenerSensoresRestantes();
            */
                                    int pos = rViewMesas.getChildAdapterPosition(v);
                                    Mesa item = mesaList.get(pos);
                                    Intent i = new Intent(getContext(), MesaActivity.class);
                                    View viewTemp = v;

                                    ActivityOptions options = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation
                                            (activity,
                                                    Pair.create(viewTemp, viewTemp.getTransitionName())
                                            );
                                    Bundle bundleExtra = new Bundle();
                                    bundleExtra.putSerializable(MesaActivity.PARAM_MESA,  item);
                                    i.putExtras(bundleExtra);
                                    startActivityForResult(i,REQUESTCODE_EDITPALLET, options.toBundle());
                                    //handler.postDelayed(()->{
                                    //    adapter.setModeVerify(false);
                                    //    adapter.notifyDataSetChanged();
                                    //},500);

                                });

                                rViewMesas.setAdapter(adapter);

                                startAnimation();
                                progressDialog.dismiss();
                                events();
                            }else {
                                if(codigoRespuesta==ConectionConfig.HTTP_ERROR){
                                    Toast.makeText(ctx,"Área sin Sensores",Toast.LENGTH_LONG).show();
                                    // onBackPressed();
                                    progressDialog.dismiss();
                                }
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx,"json"+e.toString(),Toast.LENGTH_LONG).show();
                            Log.d(TAG,e.toString());
                            e.printStackTrace();
                            progressDialog.dismiss();
                            // onBackPressed();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx,error.toString(),Toast.LENGTH_LONG).show();
                Log.d(TAG,error.toString());
                error.printStackTrace();
                progressDialog.dismiss();
                //     onBackPressed();
            }

        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Authorization",token);
                return headers;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    private void addMesas() {

        for(int i=0;i<10;i++){
            Mesa mesaTemp = new Mesa(i+1,i+1);
            mesaList.add(mesaTemp);
        }

        adapter = new RViewAdapterMesa(mesaList);
        adapter.setOnClicListener(v -> {
            /*
            obtenerSensoresRestantes();
            */
            int pos = rViewMesas.getChildAdapterPosition(v);
            Mesa item = mesaList.get(pos);
            Intent i = new Intent(getContext(), MesaActivity.class);
            View viewTemp = v;

            ActivityOptions options = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation
                    (activity,
                            Pair.create(viewTemp, viewTemp.getTransitionName())
                    );
            Bundle bundleExtra = new Bundle();
            bundleExtra.putSerializable(MesaActivity.PARAM_MESA,  item);
            i.putExtras(bundleExtra);
            startActivityForResult(i,REQUESTCODE_EDITPALLET, options.toBundle());
            //handler.postDelayed(()->{
            //    adapter.setModeVerify(false);
            //    adapter.notifyDataSetChanged();
            //},500);

        });

        rViewMesas.setAdapter(adapter);
    }



    private void define() {
        ctx = getContext();
        progressDialog = new ProgressDialog(ctx);

        rViewMesas = getView().findViewById(R.id.mesa_rViewPallets);
        mesaList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_mesas, container, false);


        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: ActivityUpdate argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
