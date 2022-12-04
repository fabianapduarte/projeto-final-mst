package main;

import estruturas.*;
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
//        try {
//            System.setOut(new PrintStream(System.out, true, "UTF8"));
//        } catch (UnsupportedEncodingException ex) {}
        Grafo grafo = new Grafo();
        IOFile io = new IOFile(args[0]);
        io.lerDados(grafo);
                    
        Solucao solucao;
        if (args.length == 1 || args[1].equals("1")) {
            solucao = new SolucaoNaoOrdenada();
        } else {
            solucao = new SolucaoOrdenada();
        }   
        
        Grafo mst = solucao.gerarSolucao(grafo);
        Casa cX = mst.inverterRelacao(mst.getCasas());
        
        io.escreverSolucao(cX);
    }
}
