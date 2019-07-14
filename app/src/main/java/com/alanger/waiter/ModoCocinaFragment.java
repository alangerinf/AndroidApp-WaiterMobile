package com.alanger.waiter;

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

import com.alanger.waiter.adapters.RViewAdapterModoCocina;
import com.alanger.waiter.model.Pedido;
import com.alanger.waiter.model.PedidosResumen;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModoCocinaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModoCocinaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModoCocinaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ModoCocinaFragment() {
        // Required empty public constructor
    }


    RecyclerView rView;

    // TODO: Rename and change types and number of parameters
    public static ModoCocinaFragment newInstance(String param1, String param2) {
        ModoCocinaFragment fragment = new ModoCocinaFragment();
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


    RViewAdapterModoCocina adapter;
    @Override
    public void onStart() {
        super.onStart();
        rView = getView().findViewById(R.id.rView);
        rView.setVisibility(View.INVISIBLE);
        List<PedidosResumen> pedidosResumenList = new ArrayList<>();

        PedidosResumen temp = new PedidosResumen();
        temp.setUnidades(5);
        temp.setNombre("Arroz con pollo");
        pedidosResumenList.add(temp);
        temp =new PedidosResumen();
        temp.setUnidades(3);
        temp.setNombre("Arroz con pato");
        pedidosResumenList.add(temp);
        temp =new PedidosResumen();
        temp.setUnidades(2);
        temp.setNombre("Cabrito");
        pedidosResumenList.add(temp);
        temp =new PedidosResumen();
        temp.setUnidades(3);
        temp.setNombre("Chicarron de Pesado");
        pedidosResumenList.add(temp);

        adapter = new RViewAdapterModoCocina(getContext(),pedidosResumenList);
        rView.setAdapter(adapter);
        rView.setVisibility(View.VISIBLE);
        startAnimation();
    }
    private void startAnimation() {
        final Animation anim_rightFadeIn2 =
                android.view.animation.AnimationUtils.loadAnimation(getContext(),R.anim.bot_fade_in);
        Handler handler2 = new Handler();
        handler2.post(
                () -> {
                    rView.startAnimation(anim_rightFadeIn2);
                    rView.setVisibility(View.VISIBLE);
                }
        );
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modo_cocina, container, false);
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
