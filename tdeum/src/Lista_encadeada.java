public class Lista_encadeada {
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

    public Lista_encadeada() {
        this.comeco = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public boolean vazia() {
        return comeco == null;
    }

    public void insere_primeiro(int info) {
        No novoNo = new No(info);
        if (vazia()) {
            comeco = fim = novoNo;
        } else {
            novoNo.proximo = comeco;
            comeco = novoNo;
        }
        tamanho++;
    }

    public void insere_depois(No no, int info) {
        if (no == null) return;

        No novoNo = new No(info);
        novoNo.proximo = no.proximo;
        no.proximo = novoNo;

        if (no == fim) {
            fim = novoNo;
        }
        tamanho++;
    }

    public void insere_ultimo(int info) {
        No novoNo = new No(info);
        if (vazia()) {
            comeco = fim = novoNo;
        } else {
            fim.proximo = novoNo;
            fim = novoNo;
        }
        tamanho++;
    }

    public void insere_ordenado(int info) {
        No novoNo = new No(info);
        if (vazia()) {
            comeco = fim = novoNo;
        } else if (info < comeco.dado) {
            insere_primeiro(info);
        } else if (info > fim.dado) {
            insere_ultimo(info);
        } else {
            No atual = comeco;
            while (atual.proximo != null && atual.proximo.dado < info) {
                atual = atual.proximo;
            }
            insere_depois(atual, info);
        }
    }

    public void mostra_lista() {
        No atual = comeco;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public int retira_primeiro() {
        if (vazia()) throw new RuntimeException("Lista vazia");

        int dado = comeco.dado;
        comeco = comeco.proximo;
        if (comeco == null) {
            fim = null;
        }
        tamanho--;
        return dado;
    }

    public int retira_ultimo() {
        if (vazia()) throw new RuntimeException("Lista vazia");

        if (comeco == fim) { // Só tem um elemento
            int dado = comeco.dado;
            comeco = fim = null;
            tamanho--;
            return dado;
        }

        No atual = comeco;
        while (atual.proximo != fim) {
            atual = atual.proximo;
        }
        int dado = fim.dado;
        fim = atual;
        fim.proximo = null;
        tamanho--;
        return dado;
    }

    public int retira_depois(No no) {
        if (no == null || no.proximo == null) throw new RuntimeException("Operação inválida");

        int dado = no.proximo.dado;
        no.proximo = no.proximo.proximo;

        if (no.proximo == null) { // Se o próximo era o fim
            fim = no;
        }

        tamanho--;
        return dado;
    }

    public int ultimo_elemento() {
        if (vazia()) throw new RuntimeException("Lista vazia");
        return fim.dado;
    }
}
