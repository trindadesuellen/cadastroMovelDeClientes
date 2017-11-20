package suellen.br.ifsul.cadastromovelclientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by Suuh on 04/07/2015.
 */
public class AdaptadorCampos extends ArrayAdapter<Campos> {
    private LayoutInflater inflater;
    private int resourceId;

    public AdaptadorCampos(Context context, int resource, List<Campos> objects){
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView,ViewGroup parent){
        final Campos campos = getItem(position);
        convertView = inflater.inflate(resourceId,parent,false);
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkCampos);
        cb.setText(campos.nome);
        cb.setChecked(campos.checado);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campos.checado = cb.isChecked();
            }
        });

        return convertView;
    }
}
