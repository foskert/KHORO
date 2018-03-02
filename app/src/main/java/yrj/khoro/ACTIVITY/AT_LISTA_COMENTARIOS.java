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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.la_lista_comentarios);
        inicializar_componentes();
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();
    }
    public  void inicializar_componentes() {
        id_list_comentario = (ListView) findViewById(R.id.id_list_comentario);
        CLSS_USUARIO US=new CLSS_USUARIO(getApplicationContext());
        new SY_COMENTARIOS(this, US.getDevice_token_for_pushes(), US.getUsername()  ).execute();
        System.out.println("p1");
        ADP_COMENTARIO adapter;
        System.out.println("p2");
        ArrayList<CLSS_COMENTARIO> arrayLista = new ArrayList<CLSS_COMENTARIO>();
        System.out.println("p3");
        CLSS_QUERY CO= new CLSS_QUERY(this, "comentarios", null, 1);
        System.out.println("p4");
        SQLiteDatabase DB= CO.getWritableDatabase();
        System.out.println("p5");
        if (DB != null) {
            System.out.println("p6");
            Cursor cursor=CO.select_comentarios_all(DB);
            System.out.println("p7");
            if(cursor.getCount()>0){
                System.out.println("p8");
                if (cursor.moveToFirst()) {
                    System.out.println("p9");
                    do {
                        System.out.println("lista para imprimir "+cursor.getString(0));
                        CLSS_COMENTARIO COM = new CLSS_COMENTARIO(getApplicationContext(), cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)),cursor.getString(8), cursor.getString(9), cursor.getString(10));
                        arrayLista.add(COM);
                    } while (cursor.moveToNext());
                    adapter = new ADP_COMENTARIO(this, arrayLista);
                    id_list_comentario.setAdapter(adapter);
                }
            }
        }
    }
}
