package arvoregeradora;

import estruturas.*;
import java.util.List;

public class SolucaoOrdenada extends Solucao{

    @Override
    public Grafo gerarSolucao(Grafo grafo) {
        System.out.println("Gerando árvores de forma ordenada...");
        return null;
    }

    @Override
    protected boolean verificarLimite(Grafo grafo, int limite) { 
        int maxCasasConetadas = grafo.getLimiteDeConexoes();
        return maxCasasConetadas<=limite;
    }

    @Override
    protected Grafo obterArvoreGeradora(Grafo grafo, List<Aresta> arestas) {
        return null;
    }
}
