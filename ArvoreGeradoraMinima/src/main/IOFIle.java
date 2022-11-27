package main;

import mst.*; //importacao do pacote
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IOFIle implements Leitura, Escrita{
    private String fileIn;
    private String caminhoAbsoluto = Solucao.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public IOFIle(String file) {
        this.fileIn = file;
    }
    
    // Leitura dos dados
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
                    System.out.print(matriz[i][ii]+" ");
                }
                System.out.println();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Escrita da solucao
    public void escreverSolucao(){
        String caminhoRelativoSaida = "../../../out/solucao.txt";
        String caminhoSaida = this.caminhoAbsoluto + caminhoRelativoSaida;
        
        try {
            FileWriter escrever = new FileWriter(caminhoSaida);
            String saida = "42";
            System.out.println(saida);
            escrever.write(saida);
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
