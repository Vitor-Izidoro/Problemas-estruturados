class Node {
    int data; // Dado armazenado no nó
    Node next; // Referência para o próximo nó na lista

    // Construtor que inicializa o nó com o dado fornecido
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Classe que representa uma lista simplesmente encadeada
class ListaSimplesmenteEncadeada {
    private Node head;
    private Node tail;

    // Construtor que inicializa uma lista vazia
    public ListaSimplesmenteEncadeada() {
        this.head = null;
        this.tail = null;
    }

    // Verifica se a lista está vazia
    public boolean vazia() {
        return head == null;
    }

    // Insere um novo nó no início da lista
    public void insere_primeiro(int info) {
        Node newNode = new Node(info); // Cria um novo nó
        if (vazia()) {
            head = tail = newNode; // Se a lista estiver vazia, o novo nó é o primeiro e o último
        } else {
            newNode.next = head;
            head = newNode; // Atualiza a cabeça para o novo nó
        }
    }

    // Insere um novo nó após um nó existente
    public void insere_depois(Node no, int info) {
        if (no == null) {
            throw new IllegalArgumentException("Nó não pode ser nulo"); // Valida o nó fornecido
        }
        Node newNode = new Node(info);
        newNode.next = no.next;
        no.next = newNode;
        if (no == tail) { // Se o nó fornecido era o último, atualiza a cauda
            tail = newNode;
        }
    }

    // Insere um novo nó no final da lista
    public void insere_ultimo(int info) {
        Node newNode = new Node(info); // Cria um novo nó
        if (vazia()) {
            head = tail = newNode; // Se a lista estiver vazia, o novo nó é o primeiro e o último
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Insere um novo nó na posição ordenada na lista
    public void insere_ordenado(int info) {  // cria um novo nó e se a lista estiver vazia, vai inserir no primeiro nó
        Node newNode = new Node(info);
        if (vazia() || head.data >= info) {
            insere_primeiro(info);
        } else { // caso não seja o primeiro nó:
            Node current = head;
            while (current.next != null && current.next.data < info) {
                current = current.next; // Percorre a lista até encontrar a posição correta
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode; // Se o novo nó for o último, atualiza a cauda
            }
        }
    }

    // Exibe todos os elementos da lista
    public void mostra_lista() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " "); // Imprime o dado de cada nó
            current = current.next; // Avança para o próximo nó
        }
        System.out.println();
    }

    // Remove e retorna o primeiro nó da lista
    public int retira_primeiro() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia");
        }
        int data = head.data; // Armazena o dado do primeiro nó
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data; // Retorna o dado removido
    }

    // Remove e retorna o último nó da lista
    public int retira_ultimo() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia");
        }
        if (head == tail) { // Se houver apenas um elemento reutiliza o método para remover o primeiro nó
            return retira_primeiro();
        }
        Node current = head;
        while (current.next != tail) {
            current = current.next; // Percorre a lista até encontrar o penúltimo nó
        }
        int data = tail.data;
        tail = current; // O penúltimo nó se torna o último
        tail.next = null;
        return data; // Retorna o dado removido
    }

    // Remove e retorna o nó que vem após o nó fornecido
    public int retira_depois(Node no) {
        if (no == null || no.next == null) {
            throw new IllegalArgumentException("Nó inválido ou último nó");
        }
        int data = no.next.data; // Armazena o dado do nó a ser removido
        no.next = no.next.next;
        if (no.next == null) {
            tail = no; // Se o nó removido era o último, atualiza a tail
        }
        return data;
    }
    // Remove e retorna o dado do nó na posição especificada
    public int retira_na_posicao(int posicao) {
        if (posicao < 0) {
            throw new IllegalArgumentException("Posição inválida");
        }

        // Caso especial: remover o primeiro nó
        if (posicao == 0) {
            return retira_primeiro();
        }

        Node current = head;
        Node previous = null;

        // Percorre a lista até a posição desejada
        for (int i = 0; i < posicao; i++) {
            if (current == null) {
                throw new IllegalArgumentException("Posição inválida");
            }
            previous = current;
            current = current.next;
        }

        // Se o nó atual for nulo, a posição é inválida
        if (current == null) {
            throw new IllegalArgumentException("Posição inválida");
        }

        // Remover o nó na posição desejada
        int data = current.data; // Armazena o dado do nó a ser removido
        previous.next = current.next; // Conecta o nó anterior ao próximo nó

        // Se o nó removido era o último, atualiza a cauda
        if (current.next == null) {
            tail = previous;
        }

        return data; // Retorna o dado removido
    }


    // Retorna o dado do último nó da lista
    public int ultimo_elemento() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia"); // Lança exceção se a lista estiver vazia
        }
        return tail.data;
    }

    // Retorna a referência para o primeiro nó da lista
    public Node getHead() {
        return head;
    }

    // Retorna uma nova lista que é a interseção ordenada entre esta lista e outra lista fornecida
    public ListaSimplesmenteEncadeada intersecaoOrdenada(ListaSimplesmenteEncadeada outraLista) {
        ListaSimplesmenteEncadeada resultado = new ListaSimplesmenteEncadeada();

        Node currentX = this.getHead();

        while (currentX != null) { // Percorre a lista atual
            Node currentY = outraLista.getHead();

            while (currentY != null) { // Percorre a outra lista
                if (currentX.data == currentY.data) { // Verifica se o elemento está presente em ambas as listas
                    if (!resultado.contem(currentX.data)) { // Evita duplicatas no resultado
                        resultado.insere_ordenado(currentX.data); // Insere o elemento na lista de interseção
                    }
                }
                currentY = currentY.next;
            }
            currentX = currentX.next;
        }

        return resultado;
    }

    // Verifica se um dado específico está presente na lista
    public boolean contem(int info) {
        Node current = this.getHead();
        while (current != null) {
            if (current.data == info) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
