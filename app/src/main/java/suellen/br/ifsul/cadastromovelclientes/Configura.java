package suellen.br.ifsul.cadastromovelclientes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Configura extends AppCompatActivity {
    CheckBox internetMovel;
    CheckBox wifi;
    Boolean resposta;
    Boolean resposta2;
    Integer tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configura);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        internetMovel = (CheckBox)findViewById(R.id.checkBox);
        wifi = (CheckBox)findViewById(R.id.checkBox2);


    }

    public void defineConfiguracao(View v){

        resposta = internetMovel.isChecked();
        resposta2 = wifi.isChecked();
        if(resposta == true && resposta2 == true) {
            tipo = 3;
        }else if(resposta == true && resposta2 == false){
            tipo = 1;
        }else if(resposta == false && resposta2 == true){
            tipo = 2;
        }

        ConectaPreferencias rede = new ConectaPreferencias();
        rede.context = this;
        rede.execute();


    }

}
