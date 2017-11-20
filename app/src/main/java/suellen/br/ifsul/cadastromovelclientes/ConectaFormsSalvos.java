package suellen.br.ifsul.cadastromovelclientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Suuh on 04/07/2015.
 */
 public class ConectaFormsSalvos extends AsyncTask<Void, Void, String> {
    MainFormsSalvos context;
    private String retorno;
    ArrayList<FormsSalvos> formssalvos;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... params) {
        WebClient client = new WebClient("http://192.168.1.26/Slim/serverTCC.php/buscar/formulario/"+context.id);
        retorno = client.conectarWS();
        return retorno;
    }

    protected void onPostExecute(String results) {
        if (results != null) {
            String indice;
            String nome;
            formssalvos = new ArrayList<FormsSalvos>();
            try { //Aqui funciona
                JSONObject obj = new JSONObject(results);
                for (int i = 0; i < obj.length(); i++) {
                    indice = obj.getString("" + i);
                    JSONObject obj2 = new JSONObject(indice);
                    //nome= obj2.getString("nome");
                    formssalvos.add(new FormsSalvos(obj2.getString("nome"), obj2.getString("id")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayAdapter<FormsSalvos> ad = new AdaptadorFormsSalvos(context, R.layout.activity_item_da_lista_formssalvos, formssalvos);
            context.lv.setAdapter(ad);
            context.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intencao = new Intent(context, MainMostraFormSalvo.class);
                    intencao.putExtra("idForm", formssalvos.get(position).cod);
                    intencao.putExtra("id", context.id);
                    context.startActivity(intencao);
                }
            });
        }
    }

}
