package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;


public class MainMostraFormSalvo extends AppCompatActivity {
    private static final String ENDERECO_TESTE = "8.8.8.8";
    String id;
    ListView lv;
    String cod;
    ConectaMostrarFormSalvo rede;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mostra_form_salvo);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("idForm");
        cod = extras.getString("id");
        lv = (ListView) findViewById(R.id.lista);
        lv.setItemsCanFocus(true);
        rede = new ConectaMostrarFormSalvo();
        rede.context = this;
        rede.execute();
        if(isWifi(MainMostraFormSalvo.this)){
            //sincronizar
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

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 " + ENDERECO_TESTE);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0) && isConnected;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isWifi(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public void cadastrar(View v) {
        for (MostrarFormsSalvos form : rede.mostrarformssalvos) {

            //new ConectaSalvarCampos().execute();
            Toast.makeText(getApplicationContext(), form.toString(), Toast.LENGTH_LONG).show();

        }
    }

    public void cadastrarOk(View v) {
        Toast.makeText(getApplicationContext(), "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        Intent intencao;
        intencao = new Intent(v.getContext(), MainFormsSalvos.class);
        intencao.putExtra("id", cod);
        startActivity(intencao);


    }


}
