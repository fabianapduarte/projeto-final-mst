package arvoregeradora;

import estruturas.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
        nivel = numeroDeCasas-1;
        int i = 0;
        while (i < totalDeArvores || invalida == true) {
            if(invalida){
                invalida = false;
                i--;
            }
            System.out.println("\n\n########### ARVORE "+(i+1)+" ##########");
            arvoreGeradora = obterArvoreGeradora(grafoCompleto, arestas);      
            if (arvoreGeradora != null) { // É nula quando nao atende aos criterios (D ou custo)
                // primeira candidata (primeira arvore q respeita D) - nas proximas iteracoes substitui pela de menor custo
                melhorOpcao = new Grafo(arvoreGeradora);
            }
            i++;
        }

        System.out.println(melhorOpcao.getArestas());
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
            int comparacao = melhorOpcao.compareTo(arvoreGeradora);
            if (comparacao == menor || comparacao == 0) {
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
        int numeroArestasAdicionadas = 0;
        int totalDeArestasDisponiveis = grafo.getTotalArestas();
        int validas = 0;
        int deMesmoNivel = 0;
        int ultimoNivel = 0;
        Grafo retorno = null;
        Stack<Aresta> pilha = new Stack<>();
        Collections.sort(arestas);

        // Cria conjuntos disjuntos unitários para cada casa
        for (int i = 0; i < numeroDeCasas; ++i) { 
            Casa casa = grafo.getCasa(i);
            conjunto.gerar(casa);
        }
             
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
//                System.out.print(proximaAresta);
                arestasAdd.add(proximaAresta);
                conexoes = conjunto.unir(casaA, casaB);
                grafo.setCustoTotal(grafo.getCustoTotal() + proximaAresta.getCusto());
                numeroArestasAdicionadas++;
//                if (numeroArestasAdicionadas == numeroDeArestas) {
//                    proximaAresta.setNivel(numeroDeArestas);
//                }
                pilha.add(proximaAresta);
            }else if(chaveRaizA == chaveRaizB){
                proximaAresta.setNivel(-1);
            }
            
            nivel = pilha.size();
//            System.out.println(nivel);
            
            
            if(numeroArestasAdicionadas == numeroDeArestas){
               
                //Desempilha e controla nivel das arestas
                Aresta ultimoAdd = pilha.pop();
                int index = arestas.indexOf(ultimoAdd);
                arestas.get(index).setNivel(nivel);
                
//                 Exibir
//                System.out.println(arestas);
//                System.out.println();
                System.out.println(arestasAdd);
                
                for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                    if (arestas.get(j).getNivel()==0)
                        validas++;
                    if (arestas.get(j).getNivel()==nivel)
                        deMesmoNivel++;
                    if (arestas.get(j).getNivel()==numeroDeArestas)
                        ultimoNivel++;
                }
                
                // Valida custo e quantidade de conexões da árvore/floresta gerada
                boolean isArvoreValida = validarArvoreGeradora(grafo, conexoes); 
                if (!isArvoreValida) {
                    System.out.println("\nNão atende aos critérios.");
                    return null;
                }else{
                    retorno = new Grafo();
                    retorno.addArestas(arestasAdd);
                    retorno.setCustoTotal(grafo.getCustoTotal());
                    retorno.setLimiteDeConexoes(grafo.getLimiteDeConexoes());
                }
                
                if (deMesmoNivel == numeroDeArestas || validas<numeroDeArestas) {
                    for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                        if(grafo.getAresta(j).getNivel()>=nivel || grafo.getAresta(j).getNivel()==-1)
                            grafo.getAresta(j).setNivel(0);
                    }
                    nivel = pilha.size();
                    
                    ultimoAdd = pilha.pop();
                    index = arestas.indexOf(ultimoAdd);
                    arestas.get(index).setNivel(nivel);
                    System.out.println("Subindo o nivel: "+nivel);
//                    System.out.println(arestas);
                }
            }else if (numeroArestasAdicionadas < numeroDeArestas && i == totalDeArestasDisponiveis-1) {
                System.out.println("caso sem arestas suficientes");
                for (int j = 0; j < totalDeArestasDisponiveis; j++) {
                    if(grafo.getAresta(j).getNivel()>nivel || grafo.getAresta(j).getNivel()==-1)
                        grafo.getAresta(j).setNivel(0);
                }
                nivel = pilha.size();
                Aresta ultimoAdd = pilha.pop();
//                System.out.println("este - "+ultimoAdd);
                int index = arestas.indexOf(ultimoAdd);
                arestas.get(index).setNivel(nivel);
                System.out.println("Subindo o nivel: "+nivel);
                invalida = true;
//                System.out.println(arestas);
            }
            
            if (numeroArestasAdicionadas == numeroDeArestas) {
                break;
            }
        }
        
        return retorno;
    }
}