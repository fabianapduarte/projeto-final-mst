package main;

import mst.*; //importacao do pacote
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IOFile implements Leitura, Escrita{
    private String fileIn;
    private String caminhoAbsoluto = Solucao.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public IOFile(String file) {
        this.fileIn = file;
    }
    
    // Leitura dos dados
    // @Override 
    public void lerDados(Dados dados){
        String caminhoRelativo = "../../../data/";
        File file = new File(this.caminhoAbsoluto + caminhoRelativo + fileIn + ".txt");

        Scanner scan;
        if (!file.exists()) {
            System.out.println("Nome de arquivo de entrada errado errado! - " + fileIn);
            System.exit(0);
        }
        try {
            scan = new Scanner(file);
            int n = scan.nextInt();
            dados.setN(n);
            dados.setD(scan.nextInt());
            
            for (int i = 0; i < n; i++) {
                dados.setCasa(new Casa(i+1));
            }
            
            //Escreve numeros das casas
            for (int i = 0; i < n; i++) {
                System.out.println(dados.getCasa(i).getChave());
            }
            
            int matriz[][] = new int[n][n];
            int j = 0;
            int k = 1;
            for (int i = 0; i < n-1; i++) {
                int l = k;
                for (int ii = 0; ii < n-i-1; ii++) {
                    int custo = scan.nextInt();
                    matriz[j][l] = custo;
                    matriz[l][j] = custo;
                    l++;
                }
                j++;
                k++;
            }

            // Escreve matriz na tela
            for (int i = 0; i < n; i++) {
                for (int ii = 0; ii < n; ii++) {
                    int custo = matriz[i][ii];
                    System.out.print(custo);
                    int algarismos=String.valueOf(custo).length();
                    for (int l = 0; l < 4-algarismos; l++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static String espacador(String fill, int nivel){
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
    
    public static String print(Casa cX, String fill, String saida, int nivel) {
        if (cX != null) {
            saida = saida.concat(fill + cX.getChave()+'\n');
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
