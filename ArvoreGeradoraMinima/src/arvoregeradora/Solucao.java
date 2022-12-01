package arvoregeradora;

import estruturas.Grafo;

public abstract class Solucao {
    public static final int max = Integer.MAX_VALUE;
    protected Grafo melhorOpcao = new Grafo();
    
    public abstract void gerarArvores(Grafo grafo);
    
    public abstract boolean verificarLimite(Grafo grafo, int limite);
}