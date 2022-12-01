package arvoregeradora;

import estruturas.Grafo;
import java.util.Collections;

public class SolucaoNaoOrdenada extends Solucao {

    public SolucaoNaoOrdenada(){
        melhorOpcao = null;
    }
    
    @Override
    public void gerarArvores(Grafo grafo) {
        System.out.println("Gerando árvores de forma não ordenada...");
        int limite = grafo.getLimiteDeConexoes();
        Grafo arvoreGeradoraMinima = null;
//        obter uma arvore gerado minima removendo ciclos
//            futuramente preencher uma heap com todas as arvores geradoras
//        por ora preencher um arrayList com todas as arvores geradoras
//        ordenar por custo
        boolean obedeceLimite = verificarLimite(grafo, limite);
        
        if(melhorOpcao==null){
            melhorOpcao = arvoreGeradoraMinima;
        }else{
            if(obedeceLimite && melhorOpcao.compareTo(grafo)==-1){
                melhorOpcao = arvoreGeradoraMinima;
            }
        } 
    }

    @Override
    public boolean verificarLimite(Grafo grafo, int limite) {
        int custoTotal = grafo.getCustoTotal();
        return custoTotal<=limite;
    }
}
