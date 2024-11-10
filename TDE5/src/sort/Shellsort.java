package sort;

public class Shellsort {
    public static void sort(long[] array) {
        int n = array.length;

        // Define o intervalo inicial
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                long temp = array[i];

                // Realiza a ordenação de acordo com o intervalo
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
