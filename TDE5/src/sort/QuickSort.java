package sort;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(long[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(long[] array, int low, int high) {
        while (low < high) {
            // Usar uma implementação iterativa para reduzir o consumo de pilha
            if (high - low < 10) {
                insertionSort(array, low, high);
                break;
            } else {
                int pivotIndex = randomizedPartition(array, low, high);

                // Ordena a menor partição primeiro para otimizar o uso da pilha
                if (pivotIndex - low < high - pivotIndex) {
                    quickSort(array, low, pivotIndex - 1);
                    low = pivotIndex + 1;
                } else {
                    quickSort(array, pivotIndex + 1, high);
                    high = pivotIndex - 1;
                }
            }
        }
    }

    private static int randomizedPartition(long[] array, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(array, pivotIndex, high);
        return partition(array, low, high);
    }

    private static int partition(long[] array, int low, int high) {
        long pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void insertionSort(long[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            long key = array[i];
            int j = i - 1;
            while (j >= low && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
/*
Explicação um pouco:
Pivô Aleatório: randomizedPartition escolhe um pivô aleatório, o que ajuda a evitar o pior caso em arrays já ordenados ou quase ordenados.
Iteratividade Parcial: O quickSort agora realiza a chamada recursiva apenas na menor partição, para otimizar o uso de pilha.
InsertionSort para Partições Pequenas: InsertionSort é usado para arrays de tamanho menor que 10, o que melhora a eficiência.
Essa implementação deve evitar o erro StackOverflowError e melhorar a performance em grandes arrays.

 */