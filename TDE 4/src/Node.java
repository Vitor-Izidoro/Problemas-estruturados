class Node {
    int key; // Contagem de palavras
    Node left, right;
    int height;

    public Node(int key) {
        this.key = key;
        this.height = 0; // Novo nó é inicialmente adicionado na folha
    }
}