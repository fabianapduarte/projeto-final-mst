package estruturas;

import java.util.Comparator;

public class Aresta implements Comparable<Aresta>{
    private Casa casaA;
    private Casa casaB;
    private int custo;

    public Aresta(Casa casaA, Casa casaB, int custo) {
        this.casaA = casaA;
        this.casaB = casaB;
        this.custo = custo;
    }   
    
    @Override
    public String toString() {
        return "\nAresta{casaA=" + casaA.getChave() + ", casaB=" + casaB.getChave() + ", custo=" + custo + '}';
    }

    public Casa getCasa(String letra) {
        if(letra.equals('a')){
            return casaA;
        }else{
            return casaB;
        }
    }

    public int getCusto() {
        return custo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.custo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aresta other = (Aresta) obj;
        return this.custo == other.custo;
    }

    @Override
    public int compareTo(Aresta outra) {
        return Integer.compare(this.custo, outra.custo);
    }
}
