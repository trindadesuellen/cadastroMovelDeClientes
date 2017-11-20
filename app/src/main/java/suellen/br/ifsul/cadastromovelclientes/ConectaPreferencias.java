package suellen.br.ifsul.cadastromovelclientes;

import android.content.Intent;
import android.os.AsyncTask;
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
 * Created by Marcelo on 06/01/2016.
 */
public class ConectaPreferencias extends AsyncTask<Void, Void, String> {
    Configura context;
    private String retorno;
    String id = "null";


    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];
            n =  in.read(b);
            if (n>0) out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... params) {
        HttpResponse response = null;
        String responseString = null;
        String text = null;
        HttpPost post = new HttpPost("http://192.168.1.26/Slim/serverTCC.php/cadastrar/preferencia/");
        List parametros = new ArrayList();
        parametros.add(new BasicNameValuePair("tipo", context.tipo.toString()));
        parametros.add(new BasicNameValuePair("id_usuario", "1"));

        try {
            post.setEntity(new UrlEncodedFormEntity(parametros));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DefaultHttpClient hc = new DefaultHttpClient();

        try {
            response = hc.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            text = getASCIIContentFromEntity(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    protected void onPostExecute(String results) {
        Intent intencao;
        if (results != null) {
            try {
                JSONObject obj = new JSONObject(results);
                id = obj.getString("id");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (id.equals("0") || id.equals("null")) {
                Toast.makeText(context, "Falha ao cadastrar preferencia, tente novamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Preferencia cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                intencao = new Intent(context, TipoForm.class);
                context.startActivity(intencao);
            }
        }
    }
}
