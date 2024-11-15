package sort;

public class Shellsort {

    public static void sort(long[] array) {
        int n = array.length;

        // Determina a sequência de gaps, começando com n/2 e reduzindo pela metade em cada iteração
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Aplica inserção direta para cada elemento com o espaçamento definido por 'gap'
            for (int i = gap; i < n; i++) {
                long temp = array[i];
                int j;

                // Move elementos que estão 'gap' posições para trás se forem maiores que temp
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}