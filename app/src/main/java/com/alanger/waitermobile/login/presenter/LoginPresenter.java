package com.alanger.waitermobile.login.presenter;

public interface LoginPresenter {
    void signIn(String user, String password);//Interactor
    void loginSuccess();
    void loginError(String error);


}
