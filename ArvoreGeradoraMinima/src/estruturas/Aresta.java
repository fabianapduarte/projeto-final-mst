package estruturas;

import java.util.Collection;
import java.util.Comparator;
        
import java.util.Comparator;


public class Aresta implements Comparable<Aresta>, Comparator<Aresta>{
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
        return "\nAresta{" + "casaA=" + casaA.getChave() + ", casaB=" + casaB.getChave() + ", custo=" + custo + '}';
    }

    public Casa getCasaA() {
        return casaA;
    }

    public Casa getCasaB() {
        return casaB;
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
    public int compareTo(Aresta o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compare(Aresta o1, Aresta o2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
