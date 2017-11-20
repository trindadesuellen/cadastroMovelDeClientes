package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainSalvarCampos extends AppCompatActivity {
    String camposs;
    String cod;
    String etCampos;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_salvar_campos);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            camposs = extras.getString("campos","NAO ACHEI");
            cod = extras.getString("cod","NAO ACHEI");
            etCampos = extras.getString("nome", "NAO ACHEI");
           // bt = (Button) findViewById(R.id.bt1);
            //bt.setText(camposs+cod+etCampos);
            ConectaSalvarCampos rede = new ConectaSalvarCampos();
            rede.context = this;
            rede.execute();

        }

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
}
