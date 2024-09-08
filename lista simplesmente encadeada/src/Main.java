public class Main {
    public static void main(String[] args) {
        // Criando e usando a primeira lista
        ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();

        // Adicionando elementos à lista
        lista.insere_primeiro(10);
        lista.insere_ultimo(20);
        lista.insere_ultimo(30);
        lista.insere_ultimo(40);
        lista.insere_ultimo(60);
        // Imprimindo os elementos da lista
        //lista.retira_depois(lista.getHead().next);
        lista.retira_na_posicao(0);
        System.out.print("LinkedList: ");
        lista.mostra_lista();  // Saída: 10 20 30 40

        // Criando e usando a segunda lista
        ListaSimplesmenteEncadeada lista2 = new ListaSimplesmenteEncadeada();

        // Inserindo elementos de forma ordenada
        lista2.insere_ordenado(20);
        lista2.insere_ordenado(10);
        lista2.insere_ordenado(30);
        lista2.insere_ordenado(88);
        lista2.insere_depois(lista2.getHead().next, 15);

        lista2.retira_primeiro();
        lista2.retira_ultimo();
        lista2.insere_primeiro(3);
        // Imprimindo os elementos da lista ordenada
        System.out.print("LinkedList2: ");
        lista2.mostra_lista();  // Saída: 10 20 25 30

        ListaSimplesmenteEncadeada interseccao = lista.intersecaoOrdenada(lista2);

        // Mostra a lista da interseção
        System.out.print("Interseção ordenada: ");
        interseccao.mostra_lista(); // Output esperado: 3 5

        double cosineSimilarity = CosineSimilarity.calculateCosineSimilarity(lista, lista2);
        System.out.println("Similaridade do Cosseno: " + cosineSimilarity);


    }
}