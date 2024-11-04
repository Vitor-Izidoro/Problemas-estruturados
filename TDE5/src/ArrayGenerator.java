import java.util.Arrays;
import java.util.Random;

public class ArrayGenerator {
    private static Random random = new Random();

    // Gera um array ordenado com valores aleatórios do tipo long
    public static long[] generateSortedArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextLong(10000000000L); // Preenche com valores aleatórios longos
        }
        Arrays.sort(array); // Ordena o array
        return array;
    }

    // Gera um array em ordem decrescente com valores aleatórios do tipo long
    public static long[] generateDescendingArray(int size) {
        long[] array = generateSortedArray(size); // Gera um array ordenado
        for (int i = 0; i < size / 2; i++) {
            // Inverte para ordem decrescente
            long temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
        return array;
    }

    // Gera um array desordenado com valores aleatórios do tipo long
    public static long[] generateUnorderedArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextLong(10000000000L); // Valores aleatórios entre 0 e 10 bilhões
        }
        return array;
    }
}
