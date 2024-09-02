public class Main {
    public static void main(String[] args) {
        // Criando e usando a primeira lista
        LinkedList lista = new LinkedList();

        // Adicionando elementos à lista
        lista.adicionar(10);
        lista.adicionar(20);
        lista.adicionar(30);
        lista.adicionar(40);

        // Imprimindo os elementos da lista
        System.out.print("LinkedList: ");
        lista.imprimirLista();  // Saída: 10 20 30 40

        // Criando e usando a segunda lista
        ListaSimplesmenteEncadeada lista2 = new ListaSimplesmenteEncadeada();

        // Inserindo elementos de forma ordenada
        lista2.insere_ordenado(20);
        lista2.insere_ordenado(10);
        lista2.insere_ordenado(30);
        lista2.insere_ordenado(25);
        lista2.insere_depois(lista2.getHead().next, 15);

        // Imprimindo os elementos da lista ordenada
        System.out.print("LinkedList2: ");
        lista2.mostra_lista();  // Saída: 10 20 25 30
    }
}