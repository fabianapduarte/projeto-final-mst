package main;

import estruturas.Grafo;
import arvoregeradora.Solucao;
import arvoregeradora.SolucaoOrdenada;
import arvoregeradora.SolucaoNaoOrdenada;
import arquivos.IOFile;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Fabiana Pereira e Samuel Costa
 */
public class GeradorDeSolucao {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (UnsupportedEncodingException ex) {}
        Grafo grafo = new Grafo();
        IOFile io = new IOFile(args[0]);
        io.lerDados(grafo);
        
        grafo.getCasa(0).setFilho(grafo.getCasa(1));
        grafo.getCasa(0).setFilho(grafo.getCasa(2));
            grafo.getCasa(2).setFilho(grafo.getCasa(3));
                    grafo.getCasa(3).setFilho(grafo.getCasa(4));
                    
        Solucao solucao;
        if (args.length == 1 || args[1].equals("1")) {
            solucao = new SolucaoNaoOrdenada();
        } else {
            solucao = new SolucaoOrdenada();
        }
        solucao.gerarArvore();
        io.escreverSolucao(grafo.getCasa(0));
    }
}
