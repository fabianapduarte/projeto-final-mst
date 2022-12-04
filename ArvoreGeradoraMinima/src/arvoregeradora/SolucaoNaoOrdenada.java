package arvoregeradora;

import estruturas.*;
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

//        if(arvoreGeradora != null){ //a mst encontrada é a primeira e obedece ao limite (não existe melhor que ela)
//            return arvoreGeradora;
//        }

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
//        Grafo arvoreGeradora = new Grafo();
        ConjuntoDisjunto conjunto = new ConjuntoDisjunto();
        int numeroDeCasas = grafo.getNumeroDeCasas();
        int numeroDeArestas = numeroDeCasas-1;
        
        // Ordenar  
        Collections.sort(arestas);

        for (int i = 0; i < numeroDeCasas; ++i){ 
            Casa casa = grafo.getCasa(i);
            conjunto.gerar(casa);
            //ele é seu proprio pai       
        }
        
        int arestasAdd = 0;
        System.out.println("\n\n########### ARVORE ###########");
        int totalDeArestasDisponiveis = grafo.getArestas().size();
        for (int i = 0; i < totalDeArestasDisponiveis; i++) {
            
            Aresta proximaAresta = grafo.getAresta(i);
 
            Casa casaA = conjunto.buscar(proximaAresta.getCasa("a"));
            Casa casaB = conjunto.buscar(proximaAresta.getCasa("b"));
                
            int conexoes = 0;
            int chaveA = casaA.getChave();
            int chaveB = casaB.getChave();
            if (chaveA != chaveB) {
//                arvoreGeradora.addAresta(proximaAresta);
                System.out.print(proximaAresta);
                conexoes = conjunto.unir(casaA, casaB);
                arestasAdd++;
            }
            
            if(i == numeroDeArestas-1){
                pilha.add(proximaAresta);
                // remover aresta do grafo
                grafo.removeAresta(i);
                totalDeArestasDisponiveis--;
                i--;
            }
            
            if(i == grafo.getArestas().size()-1 && arestasAdd<numeroDeArestas){
                // ultima iteracao e nao temos o numero de arestas
                // REFATORAR movendo pra dentro de verificacao
                return null;
            }
            if(!true){
                // Nao atende aos criterios
                // System.out.println("Nao atende aos criterios");
                return null;
            }else if(arestasAdd == numeroDeArestas){
                break;
            } 
        }
        
        return grafo;
    }
}