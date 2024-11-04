package sort;

public class MergeSort {

    // Método principal para ordenar um array
    public static void mergeSort(long[] array) {
        if (array.length < 2) {
            return; // Se o array tem 0 ou 1 elemento, já está ordenado
        }

        int mid = array.length / 2; // Encontra o meio do array
        long[] left = new long[mid]; // Cria o sub-array esquerdo
        long[] right = new long[array.length - mid]; // Cria o sub-array direito

        // Preenche os sub-arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Chama recursivamente o mergeSort em cada metade
        mergeSort(left);
        mergeSort(right);

        // Mescla os sub-arrays ordenados
        merge(array, left, right);
    }

    // Método para mesclar dois sub-arrays
    private static void merge(long[] array, long[] left, long[] right) {
        int i = 0, j = 0, k = 0;

        // Mescla os elementos de left e right de volta em array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copia os elementos restantes de left, se houver
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copia os elementos restantes de right, se houver
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Método para imprimir o array
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

