import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário que insira o tamanho do array
        System.out.print("Digite o tamanho do array: ");
        int size = scanner.nextInt();

        // Gera e imprime um array ordenado
        long[] sortedArray = ArrayGenerator.generateSortedArray(size);
        System.out.println("Array Ordenado: " + Arrays.toString(sortedArray));

        // Gera e imprime um array em ordem decrescente
        long[] descendingArray = ArrayGenerator.generateDescendingArray(size);
        System.out.println("Array Decrescente: " + Arrays.toString(descendingArray));

        // Gera e imprime um array desordenado
        long[] unorderedArray = ArrayGenerator.generateUnorderedArray(size);
        System.out.println("Array Desordenado: " + Arrays.toString(unorderedArray));

        scanner.close(); // Fecha o Scanner
    }
}
