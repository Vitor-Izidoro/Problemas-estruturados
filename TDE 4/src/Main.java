import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tamanho do conjunto de dados
        System.out.print("Informe o tamanho do conjunto de dados (número de chaves): ");
        int dataSize = scanner.nextInt();

        // Criar a árvore binária de busca e a tabela hash
        AVLTree avlTree = new AVLTree();
        HashTable hashTable = new HashTable(dataSize);
        Random random = new Random();

        // Gerar e armazenar chaves aleatórias
        int[] keys = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            keys[i] = random.nextInt(1000000000); // Gerar um número inteiro grande
        }

        // Inserir chaves na árvore AVL
        long startTimeInsertAVL = System.nanoTime();
        for (int key : keys) {
            avlTree.insert(key);
        }
        long endTimeInsertAVL = System.nanoTime();
        double insertTimeSecondsAVL = (endTimeInsertAVL - startTimeInsertAVL) / 1_000_000_000.0;

        // Inserir chaves na tabela hash
        long startTimeInsertHash = System.nanoTime();
        for (int key : keys) {
            hashTable.insert(key);
        }
        long endTimeInsertHash = System.nanoTime();
        double insertTimeSecondsHash = (endTimeInsertHash - startTimeInsertHash) / 1_000_000_000.0;

        // Buscar chaves na árvore AVL
        long startTimeSearchAVL = System.nanoTime();
        for (int key : keys) {
            avlTree.search(key);
        }
        long endTimeSearchAVL = System.nanoTime();
        double searchTimeSecondsAVL = (endTimeSearchAVL - startTimeSearchAVL) / 1_000_000_000.0;

        // Buscar chaves na tabela hash
        long startTimeSearchHash = System.nanoTime();
        for (int key : keys) {
            hashTable.search(key);
        }
        long endTimeSearchHash = System.nanoTime();
        double searchTimeSecondsHash = (endTimeSearchHash - startTimeSearchHash) / 1_000_000_000.0;

        // Apresentar os tempos de indexação e recuperação
        System.out.printf("Tempo total de indexação (inserção na AVL): %.6f segundos%n", insertTimeSecondsAVL);
        System.out.printf("Tempo total de indexação (inserção na Hash): %.6f segundos%n", insertTimeSecondsHash);
        System.out.printf("Tempo total de recuperação (busca na AVL): %.6f segundos%n", searchTimeSecondsAVL);
        System.out.printf("Tempo total de recuperação (busca na Hash): %.6f segundos%n", searchTimeSecondsHash);
        System.out.printf("Tempo total (inserção + busca na AVL): %.6f segundos%n", insertTimeSecondsAVL + searchTimeSecondsAVL);
        System.out.printf("Tempo total (inserção + busca na Hash): %.6f segundos%n", insertTimeSecondsHash + searchTimeSecondsHash);

        scanner.close();
    }
}
