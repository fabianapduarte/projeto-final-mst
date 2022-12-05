package arvoregeradora;

import estruturas.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolucaoNaoOrdenada extends Solucao {
    private static final int menor = -1;
    private boolean invalida = false;
    public SolucaoNaoOrdenada() {
        melhorOpcao = null;
    }
    
    @Override
    public Grafo gerarSolucao(Grafo grafoCompleto) {
        System.out.println("Gerando árvores de forma não ordenada...");

        List<Aresta> arestas = grafoCompleto.getArestas();
        Grafo arvoreGeradora;

        int numeroDeCasas = grafoCompleto.getNumeroDeCasas();
        int totalDeArvores = (int) Math.pow(numeroDeCasas, numeroDeCasas-2);
        for (int i = 0; i < totalDeArvores; i++) {
            if(invalida){
                i--;
                invalida = false;
            }
            System.out.println("\n\n########### ARVORE "+(i+1)+" ##########");
            arvoreGeradora = obterArvoreGeradora(grafoCompleto, arestas);      
            if (arvoreGeradora != null) { // É nula quando nao atende aos criterios (D ou custo)
                // temos a primeira candidata (primeira arvore q respeita D) - nas proximas iteracoes substitui pela de menor custo
                melhorOpcao = new Grafo(arvoreGeradora);
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
            if (arvoreGeradora.getCustoTotal() > melhorOpcao.getCustoTotal()) {
                validacao = false;
            }
        }

        return validacao;
    }

    @Override
    // Obtem a árvore geradora a partir do algoritmo de Kruskal
    protected Grafo obterArvoreGeradora(Grafo grafo, List<Aresta> arestas) {
        List<Aresta> arestasAdd = new ArrayList<>();
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
        grafo.setCustoTotal(0);
        for (int i = 0; i < totalDeArestasDisponiveis; i++) {
            Aresta proximaAresta = grafo.getAresta(i);
 
            Casa casaA = proximaAresta.getCasa("a");
            Casa casaB = proximaAresta.getCasa("b");
                
            int conexoes = 0;
            int chaveRaizA = conjunto.buscar(casaA).getChave();
            int chaveRaizB = conjunto.buscar(casaB).getChave();

            // Verifica se forma ciclos e, se não houver, une as casas
            if (chaveRaizA != chaveRaizB && proximaAresta.getNivel() == 0) {
                arestasAdd.add(proximaAresta);
                System.out.print(proximaAresta);
                conexoes = conjunto.unir(casaA, casaB);
                grafo.setCustoTotal(grafo.getCustoTotal() + proximaAresta.getCusto());
                numeroArestasAdicionadas++;
            }else if(chaveRaizA == chaveRaizB){
                proximaAresta.setNivel(-1);
            }
            
            if (numeroArestasAdicionadas == numeroDeArestas) {
                proximaAresta.setNivel(numeroDeArestas);
            }

            int validas = 0;
            int doNivel = 0;
            int ultimoNivel = 0;
//            System.out.println();
            for (int j = 0; j < totalDeArestasDisponiveis; j++) {
//                System.out.println(grafo.getAresta(j).getNivel());
                if (grafo.getAresta(j).getNivel()==0) {
                    validas++;
                }
                if (grafo.getAresta(j).getNivel()==numeroDeArestas-nivel) {
                    doNivel++;
                }
                if (grafo.getAresta(j).getNivel()==numeroDeArestas) {
                    ultimoNivel++;
                }
            }
            
            
            if(numeroArestasAdicionadas == numeroDeArestas){
                System.out.println("Nivel: "+nivel);
                System.out.println(arestasAdd);
                for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                    System.out.println(grafo.getAresta(j).getNivel());
                }
                
                
                System.out.println("\nValidas "+validas);
                if (doNivel == numeroDeArestas) {
                    nivel++;
                    
                    System.out.println("mudou de nivel");
                    for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                        grafo.getAresta(j).setNivel(0);
                    }
                    arestasAdd.get(numeroDeArestas-nivel-1).setNivel(numeroDeArestas-nivel);
                    
                } else{
                    System.out.println("continua");
                    if (ultimoNivel == numeroDeArestas) {
                        for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                            if (grafo.getAresta(j).getNivel()==numeroDeArestas) {
                                grafo.getAresta(j).setNivel(0);
                            }
                        }

                        arestasAdd.get(numeroDeArestas-nivel-1).setNivel(numeroDeArestas-nivel);
                    }
                }
//                
            }

            // Valida custo e quantidade de conexões da árvore/floresta gerada
            boolean isArvoreValida = validarArvoreGeradora(grafo, conexoes);
            if (!isArvoreValida) {
                System.out.println("\nNão atende aos critérios.");
                return null;
            }

            // avisa ocorrencia de aresta invalida para refazer a arvore com outra combinacao
            if (i == grafo.getTotalArestas() - 1 && numeroArestasAdicionadas != numeroDeArestas) {
                invalida = true;
                for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                    if (grafo.getAresta(j).getNivel()==numeroDeArestas || grafo.getAresta(j).getNivel()==-1) {
                        grafo.getAresta(j).setNivel(0);
                    }
                }
                arestasAdd.get(numeroDeArestas-nivel-1).setNivel(numeroDeArestas-nivel);
            }
                        
            if (numeroArestasAdicionadas == numeroDeArestas) {
                break;
            }
        }
        
        return grafo;
    }
}