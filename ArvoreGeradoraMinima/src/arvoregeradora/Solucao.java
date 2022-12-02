package arvoregeradora;

import estruturas.Grafo;

public abstract class Solucao {
    protected static final int max = Integer.MAX_VALUE;
    protected Grafo melhorOpcao = new Grafo();
    
    public abstract Grafo gerarSolucao(Grafo grafo);
    
    protected abstract boolean verificarLimite(Grafo grafo, int limite);
    
    protected abstract Grafo gerarArvore(Grafo grafo, int limite, int num);
}