package yrj.khoro.ACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import yrj.khoro.R;

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
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }
    public  void inicializar_componentes(){
        id_list_comentario =  (ListView) findViewById(R.id.id_list_comentario);

    }

}
