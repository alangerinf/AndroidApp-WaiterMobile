package com.alanger.waitermobile.login.interactor;


import android.content.Context;
import android.util.Log;

import com.alanger.waitermobile.login.presenter.LoginPresenter;
import com.alanger.waitermobile.login.repository.LoginRepository;
import com.alanger.waitermobile.login.repository.LoginRepositoryImpl;
import com.alanger.waitermobile.model.SharedPreferencesManager;
import com.alanger.waitermobile.model.User;



public class LoginInteractorImpl implements LoginInteractor {

    private Context ctx;
    private LoginPresenter presenter;
    private LoginRepository repository;

    String TAG = LoginInteractorImpl.class.getSimpleName();

    public LoginInteractorImpl(Context ctx, LoginPresenter presenter) {
        this.presenter = presenter;
        this.ctx = ctx;
        this.repository = new LoginRepositoryImpl(this);
    }

    @Override
    public void signIn(String user, String password) {
        repository.signIn(user,password);
    }

    @Override
    public void signSuccess(User userTemp) {
        Log.d(TAG,userTemp.toString());
        if(SharedPreferencesManager.saveUser(ctx,userTemp)){
            presenter.loginSuccess();
        }else {
            presenter.loginError("No se pudo guardar el usuario");
        }


    }

    @Override
    public void signError(String mensajeRespuesta) {
        presenter.loginError(mensajeRespuesta);
    }




}
