import java.util.Scanner;

import sort.HeapSort;
import sort.MergeSort;
import util.ArrayGenerator;
import util.CalcularTempo;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tamanho do array
        System.out.print("Informe o tamanho do array: ");
        int tamanho = scanner.nextInt();

        if (tamanho <= 0) {
            System.out.println("O tamanho deve ser um número positivo.");
            scanner.close();
            return;
        }

        long[] arrayOrdenado = ArrayGenerator.generateSortedArray(tamanho);
        long[] arrayDesordenado = ArrayGenerator.generateUnorderedArray(tamanho);
        long[] arrayDecrescente = ArrayGenerator.generateDescendingArray(tamanho);

        // Estrutura para armazenar os nomes dos algoritmos e os tempos formatados
        String[][] resultados = new String[2][4];

        // Preenche os nomes dos algoritmos
        resultados[0][0] = "HeapSort";
        resultados[1][0] = "MergeSort";

        // Calcula e armazena os tempos em segundos, com duas casas decimais
        resultados[0][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), HeapSort::sort) / 1000.0);
        resultados[0][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), HeapSort::sort) / 1000.0);

        resultados[1][1] = String.format("%.2f s", CalcularTempo.quaseOrdenado(arrayOrdenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][2] = String.format("%.2f s", CalcularTempo.desordenado(arrayDesordenado.clone(), MergeSort::mergeSort) / 1000.0);
        resultados[1][3] = String.format("%.2f s", CalcularTempo.ordemDecrescente(arrayDecrescente.clone(), MergeSort::mergeSort) / 1000.0);

        // Exibir resultados
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
            // Linha de separação entre os dados
        }

        // Linha de separação inferior
        System.out.println("└────────────┴───────────────────┴──────────────┴─────────────────────┘");
    }
}