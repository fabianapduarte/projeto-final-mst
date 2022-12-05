package estruturas;

import java.util.ArrayList;
import java.util.List;

public class Grafo implements Comparable<Grafo>{
    private int numeroDeCasas;
    private int limiteDeConexoes;
    private int custoTotal;
    private List<Casa> casas = new ArrayList<>();
    private List<Aresta> arestas = new ArrayList<>();

    public Grafo() {
        this.numeroDeCasas = 0;
        this.limiteDeConexoes = 0;
        this.custoTotal = 0;
    }

    public Grafo(Grafo arvore) {
        this.numeroDeCasas = arvore.numeroDeCasas;
        this.limiteDeConexoes = arvore.limiteDeConexoes;
        this.custoTotal = arvore.custoTotal;
        this.casas = arvore.casas;
        this.arestas = arvore.arestas;
    }

    public int getNumeroDeCasas() {
        return numeroDeCasas;
    }

    public void setNumeroDeCasas(int numeroDeCasas) {
        this.numeroDeCasas = numeroDeCasas;
    }

    public int getLimiteDeConexoes() {
        return limiteDeConexoes;
    }

    public void setLimiteDeConexoes(int limiteDeConexoes) {
        this.limiteDeConexoes = limiteDeConexoes;
    }

    public Casa getCasa(int num) {
        return casas.get(num);
    }
    
    public List<Casa> getCasas() {
        return casas;
    }

    public void addCasa(Casa casa){
        this.casas.add(casa);
    }
    
    public void addAresta(Aresta aresta){
        this.arestas.add(aresta);
    }
    
    public Aresta getAresta(int num){
        return arestas.get(num);
    }
    
    public Aresta removerAresta(int num){
        return arestas.remove(num);
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public int getTotalArestas() {
        return arestas.size();
    }
    
    public int getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(int custoTotal) {
        this.custoTotal = custoTotal;
    }

    @Override
    public int compareTo(Grafo grafoComparado) {
        return Integer.compare(this.custoTotal, grafoComparado.custoTotal);
    }
    
    public Casa inverterRelacao(List<Casa> lista){
        Casa cX = null;
        for (int i = 0; i < lista.size(); i++) {
            Casa casa = lista.get(i);
            Casa pai = lista.get(i).getPai();
//            System.out.println("Pai: "+pai.getChave()+" - Filho: "+casa.getChave());
            if(pai == casa){
                cX = casa;
            }else{
                pai.setFilho(casa);
            }
        }
        return cX;
    }
}
