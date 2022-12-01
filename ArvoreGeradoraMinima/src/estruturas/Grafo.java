package estruturas;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private int numeroDeCasas;
    private int limiteDeConexoes;
    private List<Casa> casas = new ArrayList<>();
    private List<Aresta> arestas = new ArrayList<>();

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
