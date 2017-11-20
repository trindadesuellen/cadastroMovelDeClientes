package suellen.br.ifsul.cadastromovelclientes;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcelo on 12/12/2015.
 */
public class ConectaCoordenador extends AsyncTask<Void, Void, String> {
    Cadastro context;
    private String retorno;
    ArrayList<String> coordenadores;
    private ArrayList<String> coordenadoresIds;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... params) {
        WebClient client = new WebClient("http://192.168.1.26/Slim/serverTCC.php/buscar/coordenador/");
        retorno = client.conectarWS();
        return retorno;
    }

    protected void onPostExecute(String results) {
        if (results != null) {
            String indice;
            coordenadores = new ArrayList<String>();
            coordenadoresIds = new ArrayList<String>();
            try {
                JSONObject obj = new JSONObject(results);
                for (int i = 0; i < obj.length(); i++) {
                    indice = obj.getString("" + i);
                    JSONObject obj2 = new JSONObject(indice);
                    coordenadores.add(obj2.getString("nome"));
                    coordenadoresIds.add(obj2.getString("id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, coordenadores);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        context.selectCoordenador.setAdapter(dataAdapter);

        context.selectCoordenador.setOnItemSelectedListener(new MyOnItemSelectedListener());


    }
    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            //String selectedItem = parent.getItemAtPosition(pos).toString();


            switch (parent.getId()) {

                case R.id.selectCoordenador://make sure the country was already selected during the onCreate
                    context.coordenadorSelecionado = coordenadoresIds.get(pos);
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }
}
