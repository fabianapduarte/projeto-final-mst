package main;

import mst.*; //importacao do pacote
import java.util.Scanner;

/**
 *
 * @author Fabiana Pereira e Samuel Costa
 */
public class Solucao {
    public static void main(String[] args) {
        Dados dados = new Dados();
        IOFIle io = new IOFIle(args[0]);
        io.lerDados(dados);
        
        dados.getCasa(0).setFilho(dados.getCasa(1));
        dados.getCasa(0).setFilho(dados.getCasa(2));
            dados.getCasa(2).setFilho(dados.getCasa(3));
                    dados.getCasa(3).setFilho(dados.getCasa(4));
        

        io.escreverSolucao(dados.getCasa(0));
    }
}
