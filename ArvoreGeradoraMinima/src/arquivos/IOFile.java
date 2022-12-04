package arquivos;

import arquivos.interfaces.*;
import estruturas.*;
import main.GeradorDeSolucao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class IOFile implements Leitura, Escrita{
    private String fileIn;
    private String caminhoAbsoluto = GeradorDeSolucao.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public IOFile(String file) {
        this.fileIn = file;
    }
    
    private int validarValor(Scanner scan, int linhas){
        String campo = scan.next();
        int num = 0;
        try {
            num = Integer.parseInt(campo);
        } catch (NumberFormatException e) {
            System.out.println("Erro na linha "+linhas);
            System.out.println("Arquivo "+fileIn+".txt contém caracteres incorretos!");
            System.exit(0);
        }
        return num;
    }
    
    // Leitura dos grafo
    // @Override 
    public void lerDados(Grafo grafo){
        String caminhoRelativo = "../../../data/";
        File file = new File(this.caminhoAbsoluto + caminhoRelativo + fileIn + ".txt");

        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Ops, o arquivo "+fileIn+".txt não foi encontrado, verifique a pasta data!");
            System.exit(0);
        }
            
        int lineNumber = 1;
        try {
            String valores = scan.nextLine();
            Scanner scanLinha = new Scanner(valores);
            
            int numeroDeCasas = validarValor(scanLinha, lineNumber);
            grafo.setNumeroDeCasas(numeroDeCasas);
            grafo.setLimiteDeConexoes(validarValor(scanLinha, lineNumber));
            lineNumber++;

            for (int i = 0; i < numeroDeCasas; i++) {
                grafo.addCasa(new Casa(i+1));
            }

            for (int i = 1; i < numeroDeCasas; i++) {
                valores = scan.nextLine();
                scanLinha = new Scanner(valores);
                int k = i;
                int ii;
                int total = numeroDeCasas-i;
                for (ii = 0; ii < total; ii++) {
                    int custo = validarValor(scanLinha, lineNumber);
                    Aresta novaAresta = new Aresta(grafo.getCasa(i-1), grafo.getCasa(k), custo);
                    grafo.addAresta(novaAresta);
                    k++;
                }
                lineNumber++;
            }
            System.out.println(grafo.getArestas());
            
        } catch (NoSuchElementException e) {
            System.out.println("Erro na linha "+lineNumber);
            System.out.println("Arquivo "+fileIn+".txt contém dados incompletos!");
            System.exit(0);
        }
    }
    
    private static String espacador(String fill, int nivel){
        String espaco="";
        if(nivel!=1){
            int ii=0;
            for (int j = 0; j < nivel-1; j++){
                String espacador="   ";
                Character c = fill.charAt(ii);
                if(c.equals('├') || c.equals('|')){
                    espacador = "|  ";
                }
                ii+=3;
                espaco = espaco.concat(espacador);
            }
        }
        return espaco;
    }
    
    private static String print(Casa cX, String fill, String saida, int nivel) {
        if (cX != null) {
            saida = saida.concat(fill +"c"+cX.getChave()+'\n');
            String espaco=espacador(fill, nivel);
            for (int i = 0; i < cX.getQtdFilhos(); i++) {
                if (cX.getFilho(i)!= null) {
                    String simbolo = "├──";
                    if (i==cX.getQtdFilhos()-1) {
                        simbolo = "└──";
                    }
                    
                    fill = espaco+simbolo;
                    saida = saida.concat(print(cX.getFilho(i), fill, "", nivel+1));
                }
            }
        }
        return saida;
    }
    
    // Escrita da solucao
    // @Override
    public void escreverSolucao(Casa cX){
        String caminhoRelativoSaida = "../../../out/solucao.txt";
        String caminhoSaida = this.caminhoAbsoluto + caminhoRelativoSaida;
        
        try {
            FileWriter escrever = new FileWriter(caminhoSaida);
            String saida = "";
            String fill = "";
            saida = print(cX, fill, saida, 1);
            System.out.println(saida);
            escrever.write(saida);
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
