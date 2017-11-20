package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainCadastrarCampos extends AppCompatActivity {

    private Spinner spn1;
    EditText etNome;
    String nomeC;
    private List<String> nomes = new ArrayList<String>();
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastrar_campos);
        etNome = (EditText)findViewById(R.id.etNome);

        nomes.add("Caracteres");
        nomes.add("Data");
        nomes.add("Números");


        //Identifica o Spinner no layout
        spn1 = (Spinner) findViewById(R.id.spinner1);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                nome = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                //Toast.makeText(MainCadastrarCampos.this, "Nome Selecionado: " + nome, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    public void cadastrarCampo(View v){
        nomeC = etNome.getText().toString();
        if(nomeC.isEmpty()){
            etNome.setError("Informe o nome do campo.");
        }else{
            ConectaCadastroCampo rede = new ConectaCadastroCampo();
            rede.context = this;
            rede.execute();
        }
        
    }

}
