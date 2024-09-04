package Lista;

public class No {
    public String Aresta;
    public int Custo;
    public No prox;
    public No ant;

    public No() {
    }

    public No(String aresta, int custo, No prox,No ant) {
        this.Aresta = aresta;
        this.Custo = custo;
        this.ant = ant;
        this.prox = prox;
    }

    public String getAresta() {
        return Aresta;
    }

    public void setAresta(String aresta) {
        Aresta = aresta;
    }

    public int getCusto() {
        return Custo;
    }

    public void setCusto(int custo) {
        Custo = custo;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
}