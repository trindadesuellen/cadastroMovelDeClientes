package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class Cadastro extends Activity {
    EditText email;
    EditText senha;
    EditText nome;
    Spinner selectCoordenador;
    String nomeC;
    String emailC;
    String senhaC;
    public String coordenadorSelecionado = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        email = (EditText)findViewById(R.id.etEmail);
        senha = (EditText)findViewById(R.id.etSenha);
        nome = (EditText)findViewById(R.id.etNome);
        selectCoordenador = (Spinner) findViewById(R.id.selectCoordenador);

        ConectaCoordenador rede = new ConectaCoordenador();
        rede.context = this;
        rede.execute();
    }

    public void cadastrarUsuario(View v){
        emailC = email.getText().toString();
        senhaC = senha.getText().toString();
        nomeC = nome.getText().toString();


        if(nomeC.isEmpty()){
            nome.setError("Informe o nome.");
        }else if(senhaC.isEmpty()) {
            senha.setError("Informe a senha.");
        }else if(emailC.isEmpty()) {
            email.setError("Informe o e-mail.");
        }else{
            ConectaCadastro rede = new ConectaCadastro();
            rede.context = this;
            rede.execute();
        }

    }



}
