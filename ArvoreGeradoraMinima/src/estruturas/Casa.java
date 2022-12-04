package estruturas;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para o nó da árvore
 * @author Fabiana Pereira e Samuel Costa
 */
public class Casa {
    private int chave;
    private List<Casa> filhos = new ArrayList<>();
    private Casa pai;
    private int conexoes;

    public Casa(int chave) {
        this.chave = chave;
        this.conexoes = 0;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public Casa getFilho(int num) {
        return filhos.get(num);
    }

    public void setFilho(Casa filho) {
        filhos.add(filho);
    }

    public int getQtdFilhos() {
        return this.filhos.size();
    }

    public int getConexoes() {
        return conexoes;
    }

    public void setConexoes(int valor) {
        this.conexoes = valor;
    }

    public Casa getPai() {
        return pai;
    }

    public void setPai(Casa pai) {
        this.pai = pai;
    }
}
