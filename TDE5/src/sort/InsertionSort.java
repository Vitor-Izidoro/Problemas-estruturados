package sort;

public class InsertionSort {

    public static void sort(long[] array) {
        int n = array.length;

        // Verificação de array parcialmente ordenado
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (array[i - 1] > array[i]) {
                isSorted = false;
                break;
            }
        }

        // Se o array já estiver ordenado, evitar a ordenação completa
        if (isSorted) return;

        // InsertionSort com busca binária para determinar a posição de inserção
        for (int i = 1; i < n; i++) {
            long key = array[i];
            int position = binarySearch(array, key, 0, i - 1);

            // Mover os elementos para abrir espaço para a chave
            for (int j = i - 1; j >= position; j--) {
                array[j + 1] = array[j];
            }
            array[position] = key;
        }
    }

    // Método de busca binária para encontrar a posição de inserção ideal
    private static int binarySearch(long[] array, long key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low; // Retorna a posição ideal de inserção
    }
}
