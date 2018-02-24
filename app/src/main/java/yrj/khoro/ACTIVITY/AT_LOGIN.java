package yrj.khoro.ACTIVITY;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import yrj.khoro.R;

/**
 * Created by foskert@gmail.com on 24-02-2018.
 */

public class AT_LOGIN extends Activity {
    private EditText edit_usuario_login;
    private EditText edit_clave_login;
    private Button btn_iniciar_login;
    private CheckBox checkBox_recuerdo_clave_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.la_login);
        inicializar_componentes();
        inicializar_eventos();
    }
    public void inicializar_componentes(){
        edit_usuario_login = (EditText) findViewById(R.id.edit_usuario_login);
        edit_clave_login = (EditText) findViewById(R.id.edit_clave_login);
        btn_iniciar_login = (Button) findViewById(R.id.btn_iniciar_login);
        checkBox_recuerdo_clave_login = (CheckBox) findViewById(R.id.checkBox_recuerdo_clave_login);
    }
    public void inicializar_eventos(){
        btn_iniciar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autentificar()){

                }else{
                    Toast.makeText(getApplicationContext(), "Invalide User or Password  " , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean autentificar(){

        return  false;
    }
}
