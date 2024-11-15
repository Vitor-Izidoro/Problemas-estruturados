import java.util.Scanner;
import sort.*;
import util.ArrayGenerator;
import util.CalcularTempo;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o tamanho do array ao usuário
        System.out.print("Informe o tamanho do array: ");
        int tamanho = scanner.nextInt();

        if (tamanho <= 0) {
            System.out.println("O tamanho deve ser um número positivo.");
            scanner.close();
            return;
        }

        // Gera o array base
        long[] arrayBase = ArrayGenerator.generateBaseArray(tamanho);

        // Cria os arrays derivados
        long[] arrayOrdenado = ArrayGenerator.generateSortedArray(arrayBase);
        long[] arrayDesordenado = ArrayGenerator.generateUnorderedArray(arrayBase);
        long[] arrayDecrescente = ArrayGenerator.generateDescendingArray(arrayBase);
        verificarArrays(arrayBase, arrayOrdenado, arrayDesordenado, arrayDecrescente);


        // Estrutura para armazenar os nomes dos algoritmos e os tempos formatados
        String[][] resultados = new String[6][4]; // Atualizado para 6 algoritmos

        // Preenche os nomes dos algoritmos
        resultados[0][0] = "HeapSort";
        resultados[1][0] = "MergeSort";
        resultados[2][0] = "QuickSort";
        resultados[3][0] = "InsertionSort";
        resultados[4][0] = "ShellSort";
        resultados[5][0] = "SelectionSort"; // Adiciona o SelectionSort

        // Calcula e armazena os tempos em segundos, com duas casas decimais para cada algoritmo
        resultados[0][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), HeapSort::sort) / 1000.0);

        resultados[1][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), MergeSort::mergeSort) / 1000.0);

        resultados[2][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), QuickSort::sort) / 1000.0);
        resultados[2][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), QuickSort::sort) / 1000.0);
        resultados[2][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), QuickSort::sort) / 1000.0);

        resultados[3][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), InsertionSort::sort) / 1000.0);
        resultados[3][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), InsertionSort::sort) / 1000.0);
        resultados[3][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), InsertionSort::sort) / 1000.0);

        resultados[4][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), Shellsort::sort) / 1000.0);
        resultados[4][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), Shellsort::sort) / 1000.0);
        resultados[4][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), Shellsort::sort) / 1000.0);

        // Adiciona o cálculo de tempo para SelectionSort
        resultados[5][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), SelectionSort::sort) / 1000.0);
        resultados[5][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), SelectionSort::sort) / 1000.0);
        resultados[5][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), SelectionSort::sort) / 1000.0);

        // Exibe a tabela de resultados
        tabelaAlgoritmos(tamanho, resultados);

        scanner.close();
    }
    public static void verificarArrays(long[] arrayBase, long[] arrayOrdenado, long[] arrayDesordenado, long[] arrayDecrescente) {
        // Ordena uma cópia do arrayBase para comparação
        long[] arrayBaseOrdenado = arrayBase.clone();
        Arrays.sort(arrayBaseOrdenado);

        // Verifica arrayOrdenado
        if (Arrays.equals(arrayBaseOrdenado, arrayOrdenado)) {
            System.out.println("arrayOrdenado tem os mesmos elementos que arrayBase (em ordem).");
        } else {
            System.out.println("arrayOrdenado tem elementos diferentes de arrayBase.");
        }

        // Verifica arrayDesordenado
        long[] arrayDesordenadoOrdenado = arrayDesordenado.clone();
        Arrays.sort(arrayDesordenadoOrdenado);
        if (Arrays.equals(arrayBaseOrdenado, arrayDesordenadoOrdenado)) {
            System.out.println("arrayDesordenado tem os mesmos elementos que arrayBase.");
        } else {
            System.out.println("arrayDesordenado tem elementos diferentes de arrayBase.");
        }

        // Verifica arrayDecrescente
        long[] arrayDecrescenteOrdenado = arrayDecrescente.clone();
        Arrays.sort(arrayDecrescenteOrdenado);
        if (Arrays.equals(arrayBaseOrdenado, arrayDecrescenteOrdenado)) {
            System.out.println("arrayDecrescente tem os mesmos elementos que arrayBase.");
        } else {
            System.out.println("arrayDecrescente tem elementos diferentes de arrayBase.");
        }
    }

    public static void tabelaAlgoritmos(int size, String[][] resultados) {
        // Tamanho do conjunto
        System.out.println("Tamanho do Conjunto: " + size);
        System.out.println();

        // Linha de separação superior do cabeçalho
        System.out.println("┌──────────────────────────┬──────────────────────────────────────────┐");

        // Título principal da tabela
        System.out.printf("│ %-24s │ %-38s │%n", "", "Tempos obtidos pelos algoritmos");

        // Linha de separação entre título principal e subtítulos
        System.out.println("├────────────┬─────────────┼───────────────┬──────────────────────────┤");

        // Cabeçalho da tabela
        System.out.printf("│ %-10s │ %-17s │ %-12s │ %-19s │%n", "Algoritmo", "Desordenado", "Quase ordenado", "Ordem Decrescente");

        // Linha de separação entre cabeçalho e dados
        System.out.println("├────────────┼───────────────────┼──────────────┼─────────────────────┤");

        // Imprime os dados da tabela com divisórias
        for (String[] linha : resultados) {
            System.out.printf("│ %-10s │ %-17s │ %-12s │ %-19s │%n", linha[0], linha[1], linha[2], linha[3]);
        }

        // Linha de separação inferior
        System.out.println("└────────────┴───────────────────┴──────────────┴─────────────────────┘");
    }
}
