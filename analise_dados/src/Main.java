import java.util.Random;
import java.util.Scanner;
imp ort java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Solicitar o tamanho do array
        System.out.print("Informe o tamanho do array: ");
        int tamanho = scanner.nextInt();

        if (tamanho <= 0) {
            System.out.println("O tamanho deve ser um número positivo.");
            scanner.close();
            return;
        }

        // Gerar um array de números aleatórios grandes
        int[] numerosAleatorios = gerarArrayAleatorio(tamanho, random);

        // Fazer cópias dos arrays para as diferentes condições de ordenação
        int[] totalmenteDesordenado = numerosAleatorios.clone();
        int[] quaseOrdenado = numerosAleatorios.clone();
        int[] ordenadoDecrescente = numerosAleatorios.clone();

        // Exemplo de como usar cada um dos arrays:
        System.out.println("Array base gerado:");
        System.out.println(Arrays.toString(numerosAleatorios));
        System.out.println("Array 1 gerado:");
        System.out.println(Arrays.toString(numerosAleatorios));
        System.out.println("Array 2 gerado:");
        System.out.println(Arrays.toString(numerosAleatorios));
        System.out.println("Array 3 gerado:");
        System.out.println(Arrays.toString(numerosAleatorios));
        scanner.close();
    }

    // Método para gerar um array com números aleatórios grandes
    public static int[] gerarArrayAleatorio(int tamanho, Random random) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(1_000_000_000); // Número grande
        }
        return array;
    }
}
