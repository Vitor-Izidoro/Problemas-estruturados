class LinkedList2 {
    class No {
        int dado;
        No proximo;

        public No(int dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    private No comeco;
    private No fim;
    private int tamanho;

    public LinkedList2() {
        this.comeco = null;
        this.fim = null;
        this.tamanho = 0;
    }

    // Método para inserir um elemento de forma ordenada
    public void inserirOrdenado(int dado) {
        No novoNo = new No(dado);

        // a) Lista está vazia
        if (comeco == null) {
            comeco = novoNo;
            fim = novoNo;
        }
        // b) Elemento é menor que o primeiro
        else if (dado < comeco.dado) {
            novoNo.proximo = comeco;
            comeco = novoNo;
        }
        // c) Elemento é maior que o último
        else if (dado > fim.dado) {
            fim.proximo = novoNo;
            fim = novoNo;
        }
        // d) Elemento é intermediário
        else {
            No atual = comeco;
            while (atual.proximo != null && atual.proximo.dado < dado) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
        }

        tamanho++;
    }

    // Método para imprimir todos os elementos da lista
    public void imprimirLista() {
        No atual = comeco;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
