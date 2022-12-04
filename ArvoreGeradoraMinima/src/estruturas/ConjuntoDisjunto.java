package estruturas;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para a estrutura avançada conjunto disjunto
 * @author Fabiana Pereira e Samuel Costa
 */
public class ConjuntoDisjunto {
    private List<Casa> elementos = new ArrayList<>();

    public void gerar(Casa elemento) {
        elemento.setPai(elemento);
        elementos.add(elemento);
    }

    public Casa buscar(Casa elemento) {
        int indexElemento = elementos.indexOf(elemento);

        if (indexElemento != -1) {
            Casa elementoBuscado = elementos.get(indexElemento);
            if (elementoBuscado.getPai().getChave() == elemento.getChave()) {
                return elementoBuscado;
            }

            return buscar(elemento.getPai());
        }

        return null;
    }

    public void unir(Casa elemento1, Casa elemento2) {
        elemento2.setPai(elemento1);
    }
}
