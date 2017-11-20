package suellen.br.ifsul.cadastromovelclientes;

/**
 * Created by Suuh on 06/07/2015.
 */
public class MostrarFormsSalvos {


    /* @Override
    public String toString() {
        return "MostrarFormsSalvos{" +
                "nome='" + nome + '\'' +
                " , cod='" + cod + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }*/

    public String nome;
    public String conteudo="";
    public String cod;

    MostrarFormsSalvos(String nome, String cod){
        this.nome = nome;
        this.cod = cod;
    }


}
