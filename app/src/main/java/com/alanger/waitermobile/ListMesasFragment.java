package com.alanger.waitermobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.alanger.waitermobile.adapters.RViewAdapterMesa;
import com.alanger.waitermobile.model.Mesa;
import com.alanger.waitermobile.model.SharedPreferencesManager;
import com.alanger.waitermobile.model.User;

import java.util.ArrayList;
import java.util.List;


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

    private static RecyclerView rViewPallets;

    private static int REQUESTCODE_EDITPALLET=1001;

    static LottieAnimationView gear;

    String TAG = ListMesasFragment.class.getSimpleName();


    // TODO: Rename and change types and number of parameters
    public static ListMesasFragment newInstance(String param1, String param2) {
        ListMesasFragment fragment = new ListMesasFragment();
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
        addMesas();
        events();
        startAnimation();
        User user = SharedPreferencesManager.getUser(ctx);
        //progressDialog.show();
       // consultarSensores(user.getToken());
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
    Handler handler = new Handler();
    private void events() {

        gear.setOnClickListener(v -> {
        //    startActivity(new Intent(this, EditBasicBash.class));
        });


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
            int pos = rViewPallets.getChildAdapterPosition(v);
            Pallet item = BATCH.getPalletList().get(pos);
            Intent i = new Intent(this, editPalletActivity.class);
            View viewTemp = v;
            TextView tViewNOrden = v.findViewById(R.id.tViewNOrden);
            ActivityOptions options = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation
                    (this,
                            Pair.create(viewTemp, viewTemp.getTransitionName()),
                            Pair.create((View)tViewNOrden, tViewNOrden.getTransitionName())
                    );
            Bundle bundleExtra = new Bundle();
            bundleExtra.putInt("position",pos);
            bundleExtra.putSerializable("pallet",item);
            bundleExtra.putSerializable("sensorList", (Serializable) sensorListAcutal);
            i.putExtras(bundleExtra);
            startActivityForResult(i,REQUESTCODE_EDITPALLET, options.toBundle());
            //handler.postDelayed(()->{
            //    adapter.setModeVerify(false);
            //    adapter.notifyDataSetChanged();
            //},500);
        */
        });

        rViewPallets.setAdapter(adapter);
    }



    private void define() {
        ctx = getContext();
        progressDialog = new ProgressDialog(ctx);


        gear = getView().findViewById(R.id.gear);
        rViewPallets = getView().findViewById(R.id.rViewPallets);
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
