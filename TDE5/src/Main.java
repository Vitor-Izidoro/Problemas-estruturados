import java.util.Scanner;

import sort.HeapSort;
import sort.MergeSort;
import sort.QuickSort;
import sort.InsertionSort;
import sort.Shellsort; // Importa o ShellSort
import util.ArrayGenerator;
import util.CalcularTempo;

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

        // Gera arrays de teste
        long[] arrayOrdenado = ArrayGenerator.generateSortedArray(tamanho);
        long[] arrayDesordenado = ArrayGenerator.generateUnorderedArray(tamanho);
        long[] arrayDecrescente = ArrayGenerator.generateDescendingArray(tamanho);

        // Estrutura para armazenar os nomes dos algoritmos e os tempos formatados
        String[][] resultados = new String[5][4]; // Atualizado para 5 algoritmos

        // Preenche os nomes dos algoritmos
        resultados[0][0] = "HeapSort";
        resultados[1][0] = "MergeSort";
        resultados[2][0] = "QuickSort";
        resultados[3][0] = "InsertionSort";
        resultados[4][0] = "ShellSort"; // Adiciona o ShellSort

        // Calcula e armazena os tempos em segundos, com duas casas decimais para cada algoritmo
        resultados[0][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), HeapSort::sort) / 1000.0);

        resultados[1][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), MergeSort::mergeSort) / 1000.0);

        resultados[2][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), QuickSort::sort) / 1000.0);
        resultados[2][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), QuickSort::sort) / 1000.0);
        resultados[2][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), QuickSort::sort) / 1000.0);

        resultados[3][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), InsertionSort::sort) / 1000.0);
        resultados[3][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), InsertionSort::sort) / 1000.0);
        resultados[3][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), InsertionSort::sort) / 1000.0);

        // Adiciona o cálculo de tempo para ShellSort
        resultados[4][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), Shellsort::sort) / 1000.0);
        resultados[4][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), Shellsort::sort) / 1000.0);
        resultados[4][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), Shellsort::sort) / 1000.0);

        // Exibe a tabela de resultados
        tabelaAlgoritmos(tamanho, resultados);

        scanner.close();
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
        System.out.printf("│ %-10s │ %-17s │ %-12s │ %-19s │%n", "Algoritmo", "Quase ordenado", "Desordenado", "Ordem Decrescente");

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
