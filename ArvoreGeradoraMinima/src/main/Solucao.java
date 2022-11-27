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

        io.escreverSolucao();
    }
}
