package arvoregeradora;

import estruturas.*;
import java.util.List;

public class SolucaoOrdenada extends Solucao{

    @Override
    public Grafo gerarSolucao(Grafo grafo) {
        System.out.println("Gerando árvores de forma ordenada...");
//        throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }

    @Override
    protected boolean verificarLimite(Grafo grafo, int limite) { 
        int maxCasasConetadas = grafo.getLimiteDeConexoes();
        return maxCasasConetadas<=limite;
    }

    @Override
    protected Grafo gerarArvore(Grafo grafo, List<Aresta> arestas, int limite) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return null;
    }
}
