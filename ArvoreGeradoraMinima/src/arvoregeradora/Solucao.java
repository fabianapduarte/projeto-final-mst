package arvoregeradora;

import estruturas.*;
import java.util.List;
import java.util.Stack;

public abstract class Solucao {
    protected static final int max = Integer.MAX_VALUE;
    
    protected Stack<Aresta> pilha = new Stack<>();
    
    protected Grafo melhorOpcao = new Grafo();
    
    public abstract Grafo gerarSolucao(Grafo grafo);
    
    protected abstract boolean verificarLimite(Grafo grafo, int limite);
    
    protected abstract Grafo obterArvoreGeradora(Grafo grafo, List<Aresta> arestas);
}