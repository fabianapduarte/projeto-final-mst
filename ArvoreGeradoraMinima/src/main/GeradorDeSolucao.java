package main;

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
        Dados dados = new Dados();
        IOFile io = new IOFile(args[0]);
        io.lerDados(dados);
        
        dados.getCasa(0).setFilho(dados.getCasa(1));
        dados.getCasa(0).setFilho(dados.getCasa(2));
            dados.getCasa(2).setFilho(dados.getCasa(3));
                    dados.getCasa(3).setFilho(dados.getCasa(4));
        

        io.escreverSolucao(dados.getCasa(0));
    }
}
