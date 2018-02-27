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

import yrj.khoro.CLASS.CLSS_COMENTARIO;
/**
 * Created by yrj on 27-02-2018.
 */

public class SY_COMENTARIOS extends AsyncTask<String, Void, Void> {
    private final Context context;
    private String key;
    private String username;

    public SY_COMENTARIOS( Context context, String key, String username) {
        this.context = context;
        this.key=key;
        this.username=username;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String URLConexion = "http://104.131.2.197/getNewsfeed?sid=" + this.key+"&username="+this.username ;
        String inputLine;

        try {
            URL url = new URL(URLConexion);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("Conexion "+urlConnection.getResponseCode());
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(16000);
            urlConnection.setConnectTimeout(16000);
            urlConnection.connect();

            InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            if ((inputLine = reader.readLine()) != null) {
                final JSONObject obj = new JSONObject(inputLine);
                if(obj.length()>0){
                    for(int i=0; i<obj.length();i++ ){
                        new CLSS_COMENTARIO(this.context,
                                  obj.getString("_id").toString(),
                                  obj.getString("user_id").toString(),
                                  obj.getString("username").toString(),
                                  obj.getString("content").toString(),
                                  obj.getString("timestamp").toString(),
                                  obj.getInt("commentscount"),
                                  obj.getInt("likes"),
                                  obj.getInt("deleted"),
                                  obj.getString("photo").toString(),
                                  obj.getString("liked").toString(),
                                  obj.getString("comments").toString())
                          .guardar();

                    }
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

