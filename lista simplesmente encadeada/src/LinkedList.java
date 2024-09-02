class LinkedList {
    class No {
        int dado;
        No proximo;

        public No(int dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No cabeca;
    private No cauda;
    private int tamanho;

    public LinkedList() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    // Método para adicionar um elemento ao final da lista
    public void adicionar(int dado) {
        No novoNo = new No(dado);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo;
            cauda = novoNo;
        }
        tamanho++;
    }

    // Método para imprimir todos os elementos da lista
    public void imprimirLista() {
        No atual = cabeca;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}