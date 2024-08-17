public class FilaCircular {
    private int[] fila;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircular(int capacidade) {
        this.capacidade = capacidade;
        fila = new int[capacidade];
        inicio = 0;
        fim = -1;
        tamanho = 0;
    }

    public boolean cheia() {
        return tamanho == capacidade;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public void insere(int elemento) {
        if (cheia()) {
            throw new RuntimeException("Fila cheia");
        }
        fim = (fim + 1) % capacidade;
        fila[fim] = elemento;
        tamanho++;
    }

    public int remove() {
        if (vazia()) {
            throw new RuntimeException("Fila vazia, não é possível remover elementos.");
        }
        int elementoRemovido = fila[inicio];
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return elementoRemovido;
    }

    public int primeiro() {
        if (vazia()) {
            throw new RuntimeException("Fila vazia, não há elementos para exibir.");
        }
        return fila[inicio];
    }

    public int ultimo() {
        if (vazia()) {
            throw new RuntimeException("Fila vazia, não há elementos para exibir.");
        }
        return fila[fim];
    }
}
