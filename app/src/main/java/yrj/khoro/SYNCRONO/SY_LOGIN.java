package yrj.khoro.SYNCRONO;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import yrj.khoro.CLASS.CLSS_USUARIO;

/**
 * Created by foskert@gmail.com on 24-02-2018.
 */

public class SY_LOGIN  extends AsyncTask<String, Void, Void> {

    private final Context context;
    private String username;
    private String password;

    public SY_LOGIN(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Void doInBackground(String... params) {
        String URLConexion = "http://104.131.2.197/login?username=" + this.username + "&password=" + this.password;
        String inputLine;
        try {
            URL url = new URL(URLConexion);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(16000);
            urlConnection.setConnectTimeout(16000);
            urlConnection.connect();
            InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            if ((inputLine = reader.readLine()) != null) {
                final JSONObject obj = new JSONObject(inputLine);
                if(obj.length()>0){
                    CLSS_USUARIO User= new CLSS_USUARIO(this.context, obj.getString("username").toString(), obj.getString("device_token_for_pushes").toString(), obj.getInt("followers"), obj.getInt("followings"), obj.getString("session") );
                    User.crear_sesion();
                    User.revisar_session("LOGIN");
                }
            }
            reader.close();
            streamReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
