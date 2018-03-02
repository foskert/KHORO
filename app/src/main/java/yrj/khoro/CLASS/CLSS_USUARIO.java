package yrj.khoro.CLASS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import yrj.khoro.ACTIVITY.AT_LISTA_COMENTARIOS;
import yrj.khoro.ACTIVITY.AT_LOGIN;

/**
 * Created by foskert@gmail.com on 24-02-2018.
 */

public class CLSS_USUARIO {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private String username;
    private String device_token_for_pushes;
    private int followers;
    private int follings;
    private String session;
    private Context context;
    private static final String KEY_LOGIN = "USERNAME";
    private static final String KEY_TOKEN_ID = "ID_SESION";
    private static final String KEY_TOKEN_NAME = "NAME_SESION";
    private static final String IS_LOGIN = "AUTENFIFICAO";

    public CLSS_USUARIO(Context context, String username, String device_token_for_pushes, int followers, int follings, String session) {
        this.username = username;
        this.device_token_for_pushes = device_token_for_pushes;
        this.followers = followers;
        this.follings = follings;
        this.session = session;
        this.context= context;
        preferencias();
    }
    public CLSS_USUARIO(Context context) { this.context=context;   preferencias(); }
    public String getUsername() {
        return pref.getString(KEY_LOGIN, username);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDevice_token_for_pushes() {
        return pref.getString(KEY_TOKEN_ID, device_token_for_pushes);
    }
    public void setDevice_token_for_pushes(String device_token_for_pushes) {
        this.device_token_for_pushes = device_token_for_pushes;
    }
    public int getFollowers() {
        return followers;
    }
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public int getFollings() {
        return follings;
    }
    public void setFollings(int follings) {
        this.follings = follings;
    }
    public String getSession() {
        return session;
    }
    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        String toS= "CLSS_USUARIO{" +
                  "username='" + username + '\'' +
                  ", device_token_for_pushes='" + device_token_for_pushes + '\'' +
                  ", followers=" + followers +
                  ", follings=" + follings +
                  ", session='" + session + '\'' +
                  '}';
        System.out.println(toS);
        return "";
    }
    public void preferencias(){
        pref = this.context.getSharedPreferences(KEY_LOGIN, PRIVATE_MODE);
        pref = this.context.getSharedPreferences(KEY_TOKEN_ID, PRIVATE_MODE);
        pref = this.context.getSharedPreferences(KEY_TOKEN_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void  crear_sesion(){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_LOGIN, this.username);
        editor.putString(KEY_TOKEN_ID, this.session);
        editor.putString(KEY_TOKEN_NAME, this.device_token_for_pushes);
        editor.commit();
    }
    public HashMap<String, String> getAutentificacion(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_LOGIN, pref.getString(KEY_LOGIN, null));
        user.put(KEY_TOKEN_ID, pref.getString(KEY_TOKEN_ID, null));
        user.put(KEY_TOKEN_NAME, pref.getString(KEY_TOKEN_NAME, null));
        user.put(IS_LOGIN, pref.getString(IS_LOGIN, null));
        return user;
    }
    public boolean Iniciado(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public void revisar_session(String Ventana){
        if(!this.Iniciado() && Ventana.equals("INICIO")){
            Intent i = new Intent(this.context, AT_LOGIN.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(i);
        }else if (this.Iniciado() && Ventana.equals("LOGIN")){
            Intent i = new Intent(this.context, AT_LISTA_COMENTARIOS.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(i);
        }
    }

    public void cerrar_session(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(this.context, AT_LISTA_COMENTARIOS.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(i);
    }
}