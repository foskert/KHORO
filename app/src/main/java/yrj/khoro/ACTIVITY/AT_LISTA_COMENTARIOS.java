package yrj.khoro.ACTIVITY;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
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
    private SearchView mSearchView;
    static   ADP_COMENTARIO adapter;
    static Boolean sincronizado=false;
    ArrayList<CLSS_COMENTARIO> arrayLista = new ArrayList<CLSS_COMENTARIO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CLSS_USUARIO US=new CLSS_USUARIO(getApplicationContext());
        new SY_COMENTARIOS(this, US.getDevice_token_for_pushes(), US.getUsername()).execute();
        setContentView(R.layout.la_lista_anuncio);
        inicializar_componentes();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!sincronizado){
                    inicializar_componentes();
                }
            }
        },1000);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
                    adapter = new ADP_COMENTARIO(this, arrayLista);
                    id_list_comentario.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    sincronizado=true;
                }
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.at_menu_lista_comentarios, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        //mSearchView.setQueryHint("Search...");

        return true;
    }
    public void configureFolderSearchView(){
        mSearchView.setOnQueryTextListener( new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchView.onActionViewCollapsed();;
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            //configureFolderSearchView();
            return true;
        }else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
