package com.alanger.waiter;

public class ConectionConfig {


    private static final String ROOT= "https://waiter-mobile-api.herokuapp.com/";
    public static final String POST_LOGIN=ROOT+"autenticacion/login";
    public static final String GET_GETBASICS = ROOT+"zonas";
    public static final String POST_GETSENSOR = ROOT + "sensores";
    public static final String POST_MESAS_PENDIENTES = ROOT+"mesas/pedidos_pendientes";
    public static final String POST_PLATOS = ROOT+"platos";
    public static final String POST_BATCH_UPLOAD = ROOT+"periodos/agregar";

    public static final int HTTP_OK = 0;
    public static final int HTTP_ERROR = 1;//ERROR FUNCIONAL SIN DATA

}
