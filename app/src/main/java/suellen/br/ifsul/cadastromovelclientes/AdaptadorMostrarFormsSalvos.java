package suellen.br.ifsul.cadastromovelclientes;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Suuh on 06/07/2015.
 */
public class AdaptadorMostrarFormsSalvos extends ArrayAdapter<MostrarFormsSalvos> {

    private LayoutInflater inflater;
    private int resourceId;

    public AdaptadorMostrarFormsSalvos(Context context, int resource, List<MostrarFormsSalvos> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final MostrarFormsSalvos mostrarformssalvos = getItem(position);
        convertView = inflater.inflate(resourceId, parent, false);
        TextView nome = (TextView) convertView.findViewById(R.id.tvMostrarFormsSalvos);
        EditText conteudo = (EditText) convertView.findViewById(R.id.conteudo);

        nome.setText(mostrarformssalvos.nome);
        conteudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mostrarformssalvos.conteudo = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return convertView;
    }
}


