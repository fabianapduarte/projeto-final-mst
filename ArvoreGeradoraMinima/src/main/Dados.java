package main;

import estruturas.Casa;
import estruturas.Aresta;
import java.util.ArrayList;
import java.util.List;

public class Dados {
    private int n;
    private int d;
    private List<Casa> casas = new ArrayList<>();
    private List<Aresta> arestas = new ArrayList<>();

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public Casa getCasa(int num) {
        return casas.get(num);
    }

    public void addCasa(Casa casa){
        this.casas.add(casa);
    }
    
    public void addAresta(Aresta aresta){
        this.arestas.add(aresta);
    }

    public List<Aresta> getArestas() {
        return arestas;
    }
}
