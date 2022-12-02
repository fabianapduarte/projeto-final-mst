package arvoregeradora;

import estruturas.Grafo;

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
    protected Grafo gerarArvore(Grafo grafo, int limite, int num) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
