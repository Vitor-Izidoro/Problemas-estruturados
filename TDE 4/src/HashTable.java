import java.util.LinkedList;

public class HashTable {
    private LinkedList<Integer>[] table;  // Cada posição da tabela será uma lista encadeada
    private int size;

    // Construtor para inicializar a tabela hash com tamanho adequado
    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();  // Inicializa cada posição com uma lista encadeada vazia
        }
    }

    // Função de hash simples utilizando módulo
    private int hashFunction(int key) {
        return key % size;
    }

    // Função para inserir uma chave na tabela hash usando encadeamento
    public void insert(int key) {
        int index = hashFunction(key);
        table[index].add(key);  // Insere a chave na lista encadeada na posição calculada
    }

    // Função para buscar uma chave na tabela hash
    public boolean search(int key) {
        int index = hashFunction(key);
        return table[index].contains(key);  // Verifica se a chave está presente na lista encadeada
    }
}
