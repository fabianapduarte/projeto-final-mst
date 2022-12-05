package arvoregeradora;

import estruturas.*;
import java.util.List;
import java.util.Stack;

public abstract class Solucao {
    protected static final int max = Integer.MAX_VALUE;
    
    protected int nivel = 2;
    
    protected Stack<Aresta> pilha = new Stack<>();
    
    protected Grafo melhorOpcao = new Grafo();
    
    public abstract Grafo gerarSolucao(Grafo grafo);
    
    protected abstract boolean verificarLimite(Grafo grafo, int limite);
    
    protected abstract Grafo gerarArvore(Grafo grafo, List<Aresta> arestas, int limite);
}