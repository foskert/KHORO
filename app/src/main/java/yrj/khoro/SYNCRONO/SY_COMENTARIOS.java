package yrj.khoro.SYNCRONO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import yrj.khoro.CLASS.CLSS_QUERY;

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
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            if ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                JSONArray jsonArray = new JSONArray(inputLine);
                for(int i=0; i<jsonArray.length();i++){
                    JSONObject obj = jsonArray.getJSONObject(i);
                    CLSS_QUERY IT= new CLSS_QUERY(context, "comentarios", null, 1);
                    SQLiteDatabase DB= IT.getWritableDatabase();
                    if(DB!=null){
                        IT.insert_comentarios( DB,
                                  obj.getString("_id").toString(),
                                  obj.getString("user_id").toString(),
                                  obj.getString("username").toString(),
                                  obj.getString("content").toString(),
                                  obj.getString("timestamp").toString(),
                                  obj.getString("commentscount").toString(),
                                  obj.getString("likes").toString(),
                                  obj.getString("deleted").toString(),
                                  "",
                                  obj.getString("liked").toString(),
                                  obj.getString("comments").toString());
                    }
                }
            }
            in.close();
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

