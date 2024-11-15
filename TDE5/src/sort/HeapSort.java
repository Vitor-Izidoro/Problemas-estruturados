package sort;

public class HeapSort {
    // Método principal que ordena um array
    public static void sort(long[] array) {
        int n = array.length;

        // Construir o heap (reorganizar o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Um por um, extrair elementos do heap
        for (int i = n - 1; i > 0; i--) {
            // Mover a raiz atual (máximo) para o final
            swap(array, 0, i);

            // Chamar heapify na árvore reduzida
            heapify(array, i, 0);
        }
    }

    // Para manter a propriedade do heap
    private static void heapify(long[] array, int n, int i) {
        int largest = i; // Inicializar o maior como raiz
        int left = 2 * i + 1; // filho à esquerda
        int right = 2 * i + 2; // filho à direita

        // Se o filho à esquerda for maior que a raiz
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // Se o filho à direita for maior que o maior até agora
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // Se o maior não for a raiz
        if (largest != i) {
            swap(array, i, largest);

            // Recursivamente heapify a subárvore afetada
            heapify(array, n, largest);
        }
    }

    // Método para trocar dois elementos no array
    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Método para imprimir o array
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
