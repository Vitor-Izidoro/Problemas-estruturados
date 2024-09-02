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
        LinkedList2 lista2 = new LinkedList2();

        // Inserindo elementos de forma ordenada
        lista2.inserirOrdenado(20);
        lista2.inserirOrdenado(10);
        lista2.inserirOrdenado(30);
        lista2.inserirOrdenado(25);

        // Imprimindo os elementos da lista ordenada
        System.out.print("LinkedList2: ");
        lista2.imprimirLista();  // Saída: 10 20 25 30
    }
}