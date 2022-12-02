package arvoregeradora;

import estruturas.*;

public class SolucaoNaoOrdenada extends Solucao {

    private static final int menor = -1;
    
    public SolucaoNaoOrdenada(){
        melhorOpcao = null;
    }
    
    @Override
    public Grafo gerarSolucao(Grafo grafoCompleto) {
        System.out.println("Gerando árvores de forma não ordenada...");
        int limite = grafoCompleto.getLimiteDeConexoes();
        
        //Obtem arvore geradora com base no alg de prim
        Grafo arvoreGeradora = gerarArvore(grafoCompleto, limite, 1); //ignora o ultimo parametro

        if(arvoreGeradora != null){ //a mst encontrada é a primeira e obedece ao limite (não existe melhor que ela)
            return arvoreGeradora;
        }

        boolean existeCaminho = true;
        for (int i = 0; i < 100000000; i++) {
            arvoreGeradora = gerarArvore(grafoCompleto, limite, 2); //ignora o ultimo parametro
            
            if(arvoreGeradora != null ){ // É nula quando nao atende aos criterios (D ou custo)
                // temos a primeira candidata (primeira arvore q respeita D) - nas proximas iteracoes substitui pela de menor custo
                melhorOpcao = arvoreGeradora;
            }
            existeCaminho = false; //tá de simulacao - deve parar qndo n tiver mais arvores pra testar
        }

        return melhorOpcao;
    }

    @Override
    protected boolean verificarLimite(Grafo grafo, int limite) { 
        int maxCasasConetadas = grafo.getLimiteDeConexoes();
        return maxCasasConetadas<=limite;
    }

    @Override
    protected Grafo gerarArvore(Grafo grafoCompleto, int limite, int num) {
        Grafo arvoreGeradora = new Grafo();
        
        if (melhorOpcao == null) {
            // implementacao de prim
            if(num==1){
                arvoreGeradora.addAresta(grafoCompleto.getAresta(0));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(1));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(3));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(5));
                arvoreGeradora.setCustoTotal(19);
                arvoreGeradora.setLimiteDeConexoes(3); 
            }else{
                arvoreGeradora.addAresta(grafoCompleto.getAresta(0));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(3));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(5));
                arvoreGeradora.addAresta(grafoCompleto.getAresta(8));  
                arvoreGeradora.setCustoTotal(21);
                arvoreGeradora.setLimiteDeConexoes(2);
            }
            
            
            // checar apos adicionar cada aresta
            boolean obedeceLimite = verificarLimite(arvoreGeradora, limite); 
            if(!obedeceLimite){
                return null;
            }
        }else{
            // implementacao de prim pra gerar variantes
            arvoreGeradora.addAresta(grafoCompleto.getAresta(0));
            arvoreGeradora.addAresta(grafoCompleto.getAresta(3));
            arvoreGeradora.addAresta(grafoCompleto.getAresta(5));
            arvoreGeradora.addAresta(grafoCompleto.getAresta(8));  
            arvoreGeradora.setCustoTotal(21);
            arvoreGeradora.setLimiteDeConexoes(2);
            
            // checar apos adicionar cada aresta
            int comparacao = arvoreGeradora.compareTo(melhorOpcao);
            boolean obedeceLimite = verificarLimite(arvoreGeradora, limite); 
            if(!obedeceLimite || comparacao != menor){
                return null;
            }
        }
        
        return arvoreGeradora;
    }
}