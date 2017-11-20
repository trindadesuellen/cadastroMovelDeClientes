package suellen.br.ifsul.cadastromovelclientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainCampo extends AppCompatActivity {

    ListView lv;
    EditText etCampos;
    String cod;
    String nome;
    Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_campo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Intent intencao = new Intent(getApplicationContext() , MainCadastrarCampos.class);
                startActivity(intencao);
            }
        });

        Bundle extras = getIntent().getExtras();
        cod = extras.getString("id");
        lv = (ListView)findViewById(R.id.lista);
        etCampos = (EditText)findViewById(R.id.etCampos);
        salvar = (Button) findViewById(R.id.btSalvarForm);


        ConectaCampos rede = new ConectaCampos();
        rede.context = this;
        rede.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teste_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configuracoes) {
            Intent intent = new Intent(this, Configura.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addCampos(View v){
        Intent intencao;
        intencao = new Intent(this, MainCadastrarCampos.class);
        startActivity(intencao);

    }

}
