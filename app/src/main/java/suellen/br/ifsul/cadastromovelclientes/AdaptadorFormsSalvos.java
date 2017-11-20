package suellen.br.ifsul.cadastromovelclientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Suuh on 04/07/2015.
 */
public class AdaptadorFormsSalvos  extends ArrayAdapter<FormsSalvos> {
    private LayoutInflater inflater;
    private int resourceId;

    public AdaptadorFormsSalvos(Context context, int resource, List<FormsSalvos> objects){
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }

    public View getView(int position, View convertView,ViewGroup parent){
        final FormsSalvos formssalvos = getItem(position);
        convertView = inflater.inflate(resourceId,parent,false);
        TextView nome = (TextView) convertView.findViewById(R.id.tvFormsSalvos);
        nome.setText(formssalvos.nome);
        return convertView;
    }
}
