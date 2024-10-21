import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class Node {
    int key;
    Node next;

    // Construtor para o nó
    public Node(int key) {
        this.key = key;
        this.next = null;
    }
}

class HashTable {
    private Node[] table;

    // Construtor ajustado para receber o tamanho da tabela
    public HashTable(int size) {
        table = new Node[size];
    }

    // Função hash simples (usando módulo)
    private int hashFunction(int key) {
        return key % table.length;
    }

    // Método para inserir uma chave na tabela
    public void insert(int key) {
        int hashIndex = hashFunction(key);
        Node newNode = new Node(key);

        if (table[hashIndex] == null) {
            // Se não há nenhum nó na posição, insere o novo nó
            table[hashIndex] = newNode;
        } else {
            // Se já há nós, inserir ordenadamente na lista encadeada
            Node current = table[hashIndex];
            Node previous = null;
            while (current != null && current.key < key) {
                previous = current;
                current = current.next;
            }

            if (previous == null) {
                // Inserir no início da lista
                newNode.next = table[hashIndex];
                table[hashIndex] = newNode;
            } else {
                // Inserir no meio ou no final da lista
                previous.next = newNode;
                newNode.next = current;
            }
        }
    }

    // Método para buscar uma chave na tabela
    public boolean search(int key) {
        int hashIndex = hashFunction(key);
        Node current = table[hashIndex];
        while (current != null) {
            if (current.key == key) {
                return true; // Chave encontrada
            }
            current = current.next;
        }
        return false; // Chave não encontrada
    }

    // Método para remover uma chave da tabela (opcional)
    public void remove(int key) {
        int hashIndex = hashFunction(key);
        Node current = table[hashIndex];
        Node previous = null;

        while (current != null && current.key != key) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return; // Chave não encontrada
        }

        if (previous == null) {
            // Remover o primeiro nó na lista
            table[hashIndex] = current.next;
        } else {
            // Remover o nó do meio ou do final
            previous.next = current.next;
        }
    }

    // Método para exibir a tabela hash (opcional)
    public void display() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Posição " + i + ": ");
            Node current = table[i];
            while (current != null) {
                System.out.print(current.key + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
}
