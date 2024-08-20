public class FilaCircularAutomatica {
    private int[] fila;
    private int inicio;
    private int fim;
    private int tamanho;
    private int capacidade;

    public FilaCircularAutomatica(int capacidade) {
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
            remove();
        }

        // Encontra a posição correta para o novo elemento
        int i = tamanho - 1;
        while (i >= 0 && fila[(inicio + i) % capacidade] > elemento) {
            fila[(inicio + i + 1) % capacidade] = fila[(inicio + i) % capacidade];
            i--;
        }
        fila[(inicio + i + 1) % capacidade] = elemento;

        fim = (fim + 1) % capacidade;
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

    public FilaCircularAutomatica merge(FilaCircularAutomatica outraFila) {
        FilaCircularAutomatica filaResultante = new FilaCircularAutomatica(this.capacidade + outraFila.capacidade);

        int i = 0, j = 0;
        int[] fila1 = this.fila;
        int[] fila2 = outraFila.fila;

        while (i < this.tamanho && j < outraFila.tamanho) {
            int elemento1 = fila1[(this.inicio + i) % this.capacidade];
            int elemento2 = fila2[(outraFila.inicio + j) % outraFila.capacidade];

            if (elemento1 <= elemento2) {
                filaResultante.insere(elemento1);
                i++;
            } else {
                filaResultante.insere(elemento2);
                j++;
            }
        }

        // Inserir os elementos restantes da fila original
        while (i < this.tamanho) {
            filaResultante.insere(fila1[(this.inicio + i) % this.capacidade]);
            i++;
        }

        // Inserir os elementos restantes da outra fila
        while (j < outraFila.tamanho) {
            filaResultante.insere(fila2[(outraFila.inicio + j) % outraFila.capacidade]);
            j++;
        }

        return filaResultante;
    }
}