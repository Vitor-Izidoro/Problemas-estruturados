package util;

import java.util.Arrays;
import java.util.Random;

public class ArrayGenerator {
    private static Random random = new Random();

    // Gera um array base aleatório
    public static long[] generateBaseArray(int size) {
        long[] baseArray = new long[size];
        for (int i = 0; i < size; i++) {
            baseArray[i] = random.nextLong(); // Valores aleatórios
        }
        return baseArray;
    }

    // Gera um array ordenado com base no array fornecido
    public static long[] generateSortedArray(long[] baseArray) {
        long[] array = Arrays.copyOf(baseArray, baseArray.length); // Cópia do array base
        Arrays.sort(array); // Ordena o array
        return array;
    }

    // Gera um array em ordem decrescente com base no array fornecido
    public static long[] generateDescendingArray(long[] baseArray) {
        long[] array = generateSortedArray(baseArray); // Usa o array ordenado
        for (int i = 0; i < array.length / 2; i++) {
            // Inverte para ordem decrescente
            long temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    // Gera um array desordenado (simplesmente faz uma cópia do array base)
    public static long[] generateUnorderedArray(long[] baseArray) {
        return Arrays.copyOf(baseArray, baseArray.length); // Retorna uma cópia do array base
    }
}
