package yrj.khoro.ACTIVITY;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
        System.out.println("KEY de USUARIO: "+US.getUsername());
        new SY_COMENTARIOS(getApplicationContext(), US.getDevice_token_for_pushes(), US.getUsername()  ).execute();
    }

}
