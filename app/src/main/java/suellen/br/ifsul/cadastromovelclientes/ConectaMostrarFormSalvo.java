package suellen.br.ifsul.cadastromovelclientes;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Suuh on 06/07/2015.
 */
public class ConectaMostrarFormSalvo extends AsyncTask<Void, Void, String> {
    MainMostraFormSalvo context;
    private String retorno;
    ArrayList<MostrarFormsSalvos> mostrarformssalvos;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... params) {
        WebClient client = new WebClient("http://192.168.1.26/Slim/serverTCC.php/buscar/formulario/salvo/" + context.id);
        retorno = client.conectarWS();
        return retorno;
    }

    protected void onPostExecute(String results) {
        if (results != null) {
            String indice;
            String nome;
            mostrarformssalvos = new ArrayList<MostrarFormsSalvos>();
            try { //Aqui funciona
                JSONObject obj = new JSONObject(results);
                for (int i = 0; i < obj.length(); i++) {
                    indice = obj.getString("" + i);
                    JSONObject obj2 = new JSONObject(indice);
                    mostrarformssalvos.add(new MostrarFormsSalvos(obj2.getString("nome"), obj2.getString("cod")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayAdapter<MostrarFormsSalvos> ad = new AdaptadorMostrarFormsSalvos(context, R.layout.activity_item_da_lista_mostrarformssalvos, mostrarformssalvos);
            context.lv.setAdapter(ad);

        }
    }

}
