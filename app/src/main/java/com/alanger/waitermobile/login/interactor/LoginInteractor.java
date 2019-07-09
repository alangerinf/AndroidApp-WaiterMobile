package com.alanger.waitermobile.login.interactor;

import com.alanger.waitermobile.model.User;


public interface LoginInteractor {

    void signIn(String user, String password);

    void signSuccess(User userTemp);

    void signError(String mensajeRespuesta);
}
