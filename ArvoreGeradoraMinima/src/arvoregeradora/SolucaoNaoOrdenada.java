package arvoregeradora;

import estruturas.*;
import java.util.Collections;
import java.util.List;

public class SolucaoNaoOrdenada extends Solucao {
    private static final int menor = -1;
    
    public SolucaoNaoOrdenada() {
        melhorOpcao = null;
    }
    
    @Override
    public Grafo gerarSolucao(Grafo grafoCompleto) {
        System.out.println("Gerando árvores de forma não ordenada...");

        List<Aresta> arestas = grafoCompleto.getArestas();
        Grafo arvoreGeradora = obterArvoreGeradora(grafoCompleto, arestas);

//        if(arvoreGeradora != null){ //a mst encontrada é a primeira e obedece ao limite (não existe melhor que ela)
//            return arvoreGeradora;
//        }

        int numeroDeCasas = grafoCompleto.getNumeroDeCasas();
        int totalDeArvores = (int) Math.pow(numeroDeCasas, numeroDeCasas-2);
        for (int i = 0; i < totalDeArvores; i++) {
            arvoreGeradora = obterArvoreGeradora(grafoCompleto, arestas);
            
            if (arvoreGeradora != null) { // É nula quando nao atende aos criterios (D ou custo)
                // temos a primeira candidata (primeira arvore q respeita D) - nas proximas iteracoes substitui pela de menor custo
                melhorOpcao = arvoreGeradora;
            }
        }

        return melhorOpcao;
    }

    @Override
    protected boolean verificarLimite(Grafo grafo, int conexoes) { 
        int maxCasasConectadas = grafo.getLimiteDeConexoes();
        return maxCasasConectadas >= conexoes;
    }
    
    private boolean validarArvoreGeradora(Grafo arvoreGeradora, int conexoes) {
        boolean validacao = true;

        boolean obedeceLimite = verificarLimite(arvoreGeradora, conexoes); 
        if (!obedeceLimite) {
            validacao = false;
        } else if (melhorOpcao != null) {
            int comparacao = arvoreGeradora.compareTo(melhorOpcao);
            if (comparacao != menor) {
                validacao = false;
            }
        }

        return validacao;
    }

    @Override
    // Obtem a árvore geradora a partir do algoritmo de Kruskal
    protected Grafo obterArvoreGeradora(Grafo grafo, List<Aresta> arestas) {
        ConjuntoDisjunto conjunto = new ConjuntoDisjunto();
        int numeroDeCasas = grafo.getNumeroDeCasas();
        int numeroDeArestas = numeroDeCasas-1;
        
        // Ordena arestas pelo custo
        Collections.sort(arestas);

        // Cria conjuntos disjuntos unitários para cada casa
        for (int i = 0; i < numeroDeCasas; ++i) { 
            Casa casa = grafo.getCasa(i);
            conjunto.gerar(casa);
        }
        
        int numeroArestasAdicionadas = 0;
        int totalDeArestasDisponiveis = grafo.getTotalArestas();
        System.out.println("\n\n########### ARVORE ###########");
        for (int i = 0; i < totalDeArestasDisponiveis; i++) {
            Aresta proximaAresta = grafo.getAresta(i);
 
            Casa raizCasaA = conjunto.buscar(proximaAresta.getCasa("a"));
            Casa raizCasaB = conjunto.buscar(proximaAresta.getCasa("b"));
                
            int conexoes = 0;
            int chaveRaizA = raizCasaA.getChave();
            int chaveRaizB = raizCasaB.getChave();

            // Verifica se forma ciclos e, se não houver, une as casas
            if (chaveRaizA != chaveRaizB) {
                System.out.print(proximaAresta);
                conexoes = conjunto.unir(raizCasaA, raizCasaB);
                grafo.setCustoTotal(grafo.getCustoTotal() + proximaAresta.getCusto());
                numeroArestasAdicionadas++;
            }
            
            // Remove penúltima aresta
            if (i == numeroDeArestas - 1) {
                pilha.add(proximaAresta);
                grafo.removerAresta(i);
                grafo.setCustoTotal(grafo.getCustoTotal() - proximaAresta.getCusto());
                totalDeArestasDisponiveis--;
                i--;
            }

            // Valida custo e quantidade de conexões da árvore/floresta gerada
            boolean isArvoreValida = validarArvoreGeradora(grafo, conexoes);
            if (!isArvoreValida || (i == grafo.getTotalArestas() - 1 && numeroArestasAdicionadas < numeroDeArestas)) {
                System.out.println("\nNão atende aos critérios.");
                return null;
            }

            if (numeroArestasAdicionadas == numeroDeArestas) {
                break;
            }
        }
        
        return grafo;
    }
}