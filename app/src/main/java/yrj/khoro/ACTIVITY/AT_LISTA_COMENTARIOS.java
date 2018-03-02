package yrj.khoro.ACTIVITY;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import yrj.khoro.ADAPTER.ADP_COMENTARIO;
import yrj.khoro.CLASS.CLSS_COMENTARIO;
import yrj.khoro.CLASS.CLSS_QUERY;
import yrj.khoro.CLASS.CLSS_USUARIO;
import yrj.khoro.R;
import yrj.khoro.SYNCRONO.SY_COMENTARIOS;

public class AT_LISTA_COMENTARIOS extends AppCompatActivity {
    private ListView id_list_comentario;
    ADP_COMENTARIO adapter;
    ArrayList<CLSS_COMENTARIO> arrayLista = new ArrayList<CLSS_COMENTARIO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CLSS_USUARIO US=new CLSS_USUARIO(getApplicationContext());
        new SY_COMENTARIOS(this, US.getDevice_token_for_pushes(), US.getUsername()).execute();
        setContentView(R.layout.la_lista_anuncio);
        inicializar_componentes();
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();
    }
    public  void inicializar_componentes() {
        id_list_comentario = (ListView) findViewById(R.id.id_list_comentario);


        CLSS_QUERY CO= new CLSS_QUERY(this, "comentarios", null, 1);
        SQLiteDatabase DB= CO.getWritableDatabase();
        if (DB != null) {
            Cursor cursor=CO.select_comentarios_all(DB);
            if(cursor.getCount()>0){
                if (cursor.moveToFirst()) {
                    arrayLista.clear();
                    do {
                         CLSS_COMENTARIO COM = new CLSS_COMENTARIO(
                                  this,
                                  cursor.getString(0),
                                  cursor.getString(1),
                                  cursor.getString(2),
                                  cursor.getString(3),
                                   cursor.getString(4),
                                  cursor.getInt(5),
                                  cursor.getInt(6),
                                  cursor.getInt(7),
                                   cursor.getString(8),
                                  cursor.getString(9),
                                  cursor.getString(10));
                        arrayLista.add(COM);
                    } while (cursor.moveToNext());
                    System.out.println("p10 tamaño "+arrayLista.size());
                    adapter = new ADP_COMENTARIO(this, arrayLista);
                    System.out.println("iten ya listos "+adapter.getCount());
                    System.out.println("strin del adptador "+adapter.getItem(0).toString());
                    id_list_comentario.setAdapter(adapter);
                }
            }
        }
    }
}
