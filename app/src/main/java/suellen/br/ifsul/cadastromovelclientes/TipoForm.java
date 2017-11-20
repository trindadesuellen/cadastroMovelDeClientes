package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TipoForm extends AppCompatActivity {
    String id;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_form);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            id = extras.getString("id");
            admin = extras.getString("admin");
        }
    }

    @Override
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

    public void criarFormulario(View v) {

        Intent intent = new Intent(this, MainCampo.class);
        intent.putExtra("id", id);
        intent.putExtra("admin", admin);
        startActivity(intent);

    }

    public void formSalvos(View v) {

        Intent intent = new Intent(this, MainFormsSalvos.class);
        intent.putExtra("id", id);
        intent.putExtra("admin", admin);
        startActivity(intent);

    }

    public void usuarios(View v) {

        Intent intent = new Intent(this, Usuarios.class);
        intent.putExtra("id", id);
        intent.putExtra("admin", admin);
        startActivity(intent);

    }


}
