package com.alanger.waiter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.alanger.waiter.adapters.RViewAdapterPedido;
import com.alanger.waiter.model.Pedido;
import com.alanger.waiter.model.SharedPreferencesManager;
import com.alanger.waiter.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PedidosListosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PedidosListosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidosListosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ProgressDialog progressDialog;
    static Activity activity;
    private static int REQUESTCODE_EDITPALLET=1001;
    private  static List<Pedido> pedidoList;

    Context ctx;
    private static RViewAdapterPedido adapter;

    private static RecyclerView rViewPallets;


    private OnFragmentInteractionListener mListener;

    public PedidosListosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedidosListosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PedidosListosFragment newInstance(String param1, String param2) {
        PedidosListosFragment fragment = new PedidosListosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private void addMesas() {

        for(int i=0;i<10;i++){
            Pedido mesaTemp = new Pedido(i+1,i+1);
            pedidoList.add(mesaTemp);
        }

        adapter = new RViewAdapterPedido(pedidoList);


        rViewPallets.setAdapter(adapter);
    }
    private void events() {



    }

    private void startAnimation() {
        final Animation anim_rightFadeIn2 =
                android.view.animation.AnimationUtils.loadAnimation(getContext(),R.anim.bot_fade_in);
        Handler handler2 = new Handler();
        handler2.post(
                () -> {
                    rViewPallets.startAnimation(anim_rightFadeIn2);
                    rViewPallets.setVisibility(View.VISIBLE);
                }
        );
    }

    @Override
    public void onStart() {
        super.onStart();

        define();
        addMesas();
        events();
        startAnimation();
        User user = SharedPreferencesManager.getUser(ctx);
        //progressDialog.show();
        // consultarSensores(user.getToken());
    }
    private void define() {
        ctx = getContext();
        progressDialog = new ProgressDialog(ctx);

        rViewPallets = getView().findViewById(R.id.rViewPallets);
        pedidoList = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos_listos, container, false);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
