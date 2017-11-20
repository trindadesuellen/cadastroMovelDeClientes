package suellen.br.ifsul.cadastromovelclientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Main extends Activity {
    EditText email;
    EditText senha;
    String a;
    String b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);

    }

    public void onClickLogin(View v) {
        a = email.getText().toString();
        b = senha.getText().toString();

        if(a.isEmpty()){
            email.setError("Informe o e-mail.");
        }else if(b.isEmpty()) {
            senha.setError("Informe a senha.");
        }else {

            ConectaMain rede = new ConectaMain();
            rede.context = this;
            rede.execute();
        }
    }

    public void onClickCadastro(View v) {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }

}
