public class CosineSimilarity {

    public static double calculateCosineSimilarity(ListaSimplesmenteEncadeada X, ListaSimplesmenteEncadeada Y) {
        // Verifica se as listas têm o mesmo tamanho
        if (getSize(X) != getSize(Y)) {
            throw new IllegalArgumentException("As listas devem ter o mesmo tamanho");
        }

        Node nodeX = X.getHead();
        Node nodeY = Y.getHead();

        double sumProduct = 0.0;
        double sumXSquare = 0.0;
        double sumYSquare = 0.0;

        // Calcula a soma dos produtos, quadrados de X e quadrados de Y
        while (nodeX != null && nodeY != null) {
            int x = nodeX.data;
            int y = nodeY.data;

            sumProduct += x * y;
            sumXSquare += x * x;
            sumYSquare += y * y;

            nodeX = nodeX.next;
            nodeY = nodeY.next;
        }

        return sumProduct / (Math.sqrt(sumXSquare) * Math.sqrt(sumYSquare));
    }

    // Função auxiliar para obter o tamanho da lista
    private static int getSize(ListaSimplesmenteEncadeada lista) {
        int size = 0;
        Node current = lista.getHead();
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
