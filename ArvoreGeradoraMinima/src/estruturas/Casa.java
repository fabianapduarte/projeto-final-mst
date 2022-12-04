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
    private int altura;

    public Casa(int chave) {
        this.chave = chave;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Casa getPai() {
        return pai;
    }

    public void setPai(Casa pai) {
        this.pai = pai;
    }
}
