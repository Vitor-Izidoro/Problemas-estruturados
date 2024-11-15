package sort;

public class SelectionSort {
    public static void sort(long[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Troca o elemento mínimo com o primeiro elemento da sublista
            long temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}