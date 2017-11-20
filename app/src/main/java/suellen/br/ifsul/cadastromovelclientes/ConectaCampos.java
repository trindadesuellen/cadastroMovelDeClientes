package suellen.br.ifsul.cadastromovelclientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Suuh on 04/07/2015.
 */
public class ConectaCampos extends AsyncTask<Void, Void, String>{
    private String retorno;
    String checados="";
    ArrayList<Campos> campos;
    String nome;
    String id;

    MainCampo context;
    Button salvar;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... params) {
        WebClient client = new WebClient("http://192.168.1.26/Slim/serverTCC.php/buscar/campos/");
        retorno = client.conectarWS();
        return retorno;
    }

    protected void onPostExecute(String results) {
        if (results != null) {
            String indice;

            campos = new ArrayList<Campos>();
            try { //Aqui funciona
                JSONObject obj = new JSONObject(results);
                for(int i=0;i<obj.length();i++){
                    indice = obj.getString(""+i);
                    JSONObject obj2 = new JSONObject(indice);
                    campos.add(new Campos(obj2.getString("nome"), obj2.getString("cod")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayAdapter<Campos> ad = new AdaptadorCampos(context, R.layout.activity_item_da_lista_campos, campos);
           context.lv.setAdapter(ad);
           context.salvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (Campos produto : campos) {
                        if (produto.checado) {
                            checados += produto.cod+"-";
                        }
                    }
                    nome = context.etCampos.getText().toString();
                    if(nome.isEmpty())
                    {
                        context.etCampos.setError("Digite o nome do Formulário");
                    }else if(checados.isEmpty()){

                        Toast.makeText(v.getContext(), "Nenhum campo selecionado!", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intencao;
                        intencao = new Intent(context, MainSalvarCampos.class);
                        intencao.putExtra("campos", checados);
                        intencao.putExtra("cod", context.cod);
                        intencao.putExtra("nome", nome);
                        context.startActivity(intencao);
                }
                }

            });


        }
    }
}
