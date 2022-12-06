package estruturas;

public class Aresta implements Comparable<Aresta>{
    private Casa casaA;
    private Casa casaB;
    private int custo;
    private int nivel;

    public Aresta(Casa casaA, Casa casaB, int custo) {
        this.casaA = casaA;
        this.casaB = casaB;
        this.custo = custo;
        this.nivel = 0;
    }   

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    @Override
    public String toString() {
        return "\nAresta{casaA=" + casaA.getChave() + ", casaB=" + casaB.getChave() + ", custo=" + custo + ", nivel=" + nivel +'}';
    }

    public Casa getCasa(String letra) {
        if(letra.equals("a")){
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
    public int compareTo(Aresta outra) {
        return Integer.compare(this.custo, outra.custo);
    }
}
