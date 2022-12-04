package arvoregeradora;

import estruturas.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolucaoNaoOrdenada extends Solucao {

    private static final int menor = -1;
    
    public SolucaoNaoOrdenada(){
        melhorOpcao = null;
    }
    
    @Override
    public Grafo gerarSolucao(Grafo grafoCompleto) {
        System.out.println("Gerando árvores de forma não ordenada...");
        int limite = grafoCompleto.getLimiteDeConexoes();
        List<Aresta> arestas = grafoCompleto.getArestas();
        
        //Obtem arvore geradora com base no alg de kruskal
        Grafo arvoreGeradora = gerarArvore(grafoCompleto, arestas, limite); //ignora o ultimo parametro

        if(arvoreGeradora != null){ //a mst encontrada é a primeira e obedece ao limite (não existe melhor que ela)
            return arvoreGeradora;
        }

        int numeroDeCasas = grafoCompleto.getNumeroDeCasas();
        int totalDeArvores = (int) Math.pow(numeroDeCasas, numeroDeCasas-2);
        for (int i = 0; i < totalDeArvores; i++) {
            arvoreGeradora = gerarArvore(grafoCompleto, arestas, limite); //ignora o ultimo parametro
            
            if(arvoreGeradora != null ){ // É nula quando nao atende aos criterios (D ou custo)
                // temos a primeira candidata (primeira arvore q respeita D) - nas proximas iteracoes substitui pela de menor custo
                melhorOpcao = arvoreGeradora;
            }
        }

        return melhorOpcao;
    }

    @Override
    protected boolean verificarLimite(Grafo grafo, int limite) { 
        int maxCasasConetadas = grafo.getLimiteDeConexoes();
        return maxCasasConetadas<=limite;
    }
    
    
    private boolean verificacao(Grafo arvoreGeradora, int limite){
        boolean retorno = true;
        if (melhorOpcao == null) {
            // checar apos adicionar cada aresta
            boolean obedeceLimite = verificarLimite(arvoreGeradora, limite); 
            if(!obedeceLimite){
                retorno = false;
            }
        }else{
            // checar apos adicionar cada aresta
            int comparacao = arvoreGeradora.compareTo(melhorOpcao);
            boolean obedeceLimite = verificarLimite(arvoreGeradora, limite); 
            if(!obedeceLimite || comparacao != menor){
                retorno = false;
            }
        }
        return retorno;
    }

    @Override
    protected Grafo gerarArvore(Grafo grafo, List<Aresta> arestas, int limite) {
        //Baseado em kruskal
        Grafo arvoreGeradora = new Grafo();
//        List<ConjuntoDisjunto> casas = new ArrayList<>();
        int numeroDeCasas = grafo.getNumeroDeCasas();
        int numeroDeArestas = numeroDeCasas-1;
        
        // Ordenar  
        Collections.sort(arestas);

        for (int i = 0; i < numeroDeCasas; ++i){ 
            Casa casa = grafo.getCasa(i);
//            casas.add(new ConjuntoDisjunto(casa));
            //no construtor ele é seu proprio pai e o rank = 0        
        }
 
        for (int i = 0; i < numeroDeArestas; i++) {
            Aresta proximaAresta = grafo.getAresta(i++);
 
//            Casa casaA = buscar(casas, proximaAresta.getCasa("a"));
//            Casa casaB = buscar(casas, proximaAresta.getCasa("b"));
// 
//            if (casaA != casaB) {
//                arvoreGeradora.addAresta(proximaAresta);
//                unir(casas, casaA, casaB);
//            }
            
            if(i == numeroDeArestas-1){
                pilha.add(proximaAresta);
            }
            
            if(!verificacao(arvoreGeradora, limite)){
                return null;
            }
        }
      
        return arvoreGeradora;
    }
}