class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class ListaSimplesmenteEncadeada {
    private Node head;
    private Node tail;

    public ListaSimplesmenteEncadeada() {
        this.head = null;
        this.tail = null;
    }

    public boolean vazia() {
        return head == null;
    }

    public void insere_primeiro(int info) {
        Node newNode = new Node(info);
        if (vazia()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insere_depois(Node no, int info) {
        if (no == null) {
            throw new IllegalArgumentException("Nó não pode ser nulo");
        }
        Node newNode = new Node(info);
        newNode.next = no.next;
        no.next = newNode;
        if (no == tail) {
            tail = newNode;
        }
    }

    public void insere_ultimo(int info) {
        Node newNode = new Node(info);
        if (vazia()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void insere_ordenado(int info) {
        Node newNode = new Node(info);
        if (vazia() || head.data >= info) {
            insere_primeiro(info);
        } else {
            Node current = head;
            while (current.next != null && current.next.data < info) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public void mostra_lista() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int retira_primeiro() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia");
        }
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public int retira_ultimo() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia");
        }
        if (head == tail) { // Apenas um elemento
            return retira_primeiro();
        }
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        int data = tail.data;
        tail = current;
        tail.next = null;
        return data;
    }

    public int retira_depois(Node no) {
        if (no == null || no.next == null) {
            throw new IllegalArgumentException("Nó inválido ou último nó");
        }
        int data = no.next.data;
        no.next = no.next.next;
        if (no.next == null) {
            tail = no;
        }
        return data;
    }

    public int ultimo_elemento() {
        if (vazia()) {
            throw new RuntimeException("A lista está vazia");
        }
        return tail.data;
    }
    public Node getHead() {
        return head;
    }
    public ListaSimplesmenteEncadeada intersecaoOrdenada(ListaSimplesmenteEncadeada outraLista) {
        ListaSimplesmenteEncadeada resultado = new ListaSimplesmenteEncadeada();

        Node currentX = this.getHead();

        while (currentX != null) {
            Node currentY = outraLista.getHead();

            while (currentY != null) {
                if (currentX.data == currentY.data) {
                    if (!resultado.contem(currentX.data)) {  // Evita duplicatas no resultado
                        resultado.insere_ordenado(currentX.data);
                    }
                }
                currentY = currentY.next;
            }
            currentX = currentX.next;
        }

        return resultado;
    }

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
