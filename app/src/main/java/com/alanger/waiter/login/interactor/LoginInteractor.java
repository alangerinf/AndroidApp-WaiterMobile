package com.alanger.waiter.login.interactor;

import com.alanger.waiter.model.User;


public interface LoginInteractor {

    void signIn(String user, String password);

    void signSuccess(User userTemp);

    void signError(String mensajeRespuesta);
}
