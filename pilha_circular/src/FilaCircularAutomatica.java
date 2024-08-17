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
            // Se a fila estiver cheia, remove o primeiro elemento automaticamente
            remove();
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

    public static void main(String[] args) {
        FilaCircular fila = new FilaCircular(5);

        fila.insere(10);
        fila.insere(20);
        fila.insere(30);
        fila.insere(40);
        fila.insere(50);

        System.out.println("Primeiro elemento: " + fila.primeiro());
        System.out.println("Último elemento: " + fila.ultimo());

        // Insere novo elemento, removendo o mais antigo automaticamente
        fila.insere(60);

        System.out.println("Primeiro elemento após inserção automática: " + fila.primeiro());
        System.out.println("Último elemento após inserção automática: " + fila.ultimo());

        fila.insere(70);

        System.out.println("Primeiro elemento após nova inserção: " + fila.primeiro());
        System.out.println("Último elemento após nova inserção: " + fila.ultimo());
    }
}
