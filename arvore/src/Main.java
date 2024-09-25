import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Node {
    int key;
    Node left, right;
    int height;

    public Node(int key) {
        this.key = key;
        this.height = 1; // Novo nó é inicialmente adicionado na folha
    }
}

class AVLTree {
    private Node root;
    public int getHeight() {
        return height(root);
    }

    // Método auxiliar para obter a altura a partir de um nó
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    // Função para obter o máximo entre dois inteiros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Rotação à direita
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realizar a rotação
        x.right = y;
        y.left = T2;

        // Atualizar as alturas
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Rotação à esquerda
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realizar a rotação
        y.left = x;
        x.right = T2;

        // Atualizar as alturas
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Obter o fator de balanceamento de um nó
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Função para inserir uma nova chave na árvore
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insertNode(node.left, key);
        else if (key > node.key)
            node.right = insertNode(node.right, key);
        else // Chaves duplicadas não são permitidas
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Se o nó ficar desbalanceado, então existem 4 casos
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Função para remover um nó
    public void remove(int key) {
        root = removeNode(root, key);
    }

    private Node removeNode(Node root, int key) {
        // Passo 1: Realizar a remoção do nó
        if (root == null) return root;

        if (key < root.key) {
            root.left = removeNode(root.left, key);
        } else if (key > root.key) {
            root.right = removeNode(root.right, key);
        } else {
            // Este é o nó a ser removido
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // Se não tem filho ou apenas um filho
                if (temp == null) {
                    return null;
                } else {
                    return temp;
                }
            } else {
                // Nó com dois filhos: obter o sucessor in-ordem (menor na subárvore da direita)
                Node temp = minValueNode(root.right);
                root.key = temp.key; // Copiar o valor do sucessor in-ordem
                root.right = removeNode(root.right, temp.key); // Remover o sucessor in-ordem
            }
        }

        // Se a árvore tiver apenas um nó, retorne
        if (root == null) return root;

        // Atualizar a altura do nó atual
        root.height = 1 + max(height(root.left), height(root.right));

        // Obter o fator de balanceamento do nó atual
        int balance = getBalance(root);

        // Se o nó ficar desbalanceado, então existem 4 casos
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Função para encontrar o nó com o menor valor maior que um determinado nó
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Métodos de impressão da árvore
    public void printPreOrder() {
        System.out.print("Pré-ordem: ");
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    public void printInOrder() {
        System.out.print("Em-ordem: ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    public void printPostOrder() {
        System.out.print("Pós-ordem: ");
        printPostOrder(root);
        System.out.println();
    }

    private void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Método para ler números de um arquivo e inseri-los na árvore
    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                insert(number);
            }
            System.out.println("Números lidos do arquivo e inseridos na árvore.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        }
    }
    public void draw() {
        drawTree(root, "", true);
    }

    private void drawTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.print(prefix + (isLeft ? "├── " : "└── ") + node.key + "\n");
            drawTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            drawTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Ler números do arquivo e inseri-los na árvore
        //tree.readFromFile("random_numbers.txt");

        // Adicionando números diretamente no código
        int[] numerosParaInserir = {100, 200, 300, 400, 500}; // Adicione os números desejados aqui
        for (int numero : numerosParaInserir) {
            tree.insert(numero);
            System.out.println("Número " + numero + " inserido na árvore.");
        }

        // Imprimir a árvore em diferentes ordens
        System.out.println("Números na árvore após inserções:");
        tree.printPreOrder();
        tree.printInOrder();
        tree.printPostOrder();

        // Remover um número e imprimir a árvore novamente
        int numeroParaRemover = 500;
        tree.remove(numeroParaRemover);
        System.out.println("Após remover o número " + numeroParaRemover + ":");
        tree.printPreOrder();
        tree.printInOrder();
        tree.printPostOrder();
        tree.draw();
        tree.remove(400);
        tree.draw();
        System.out.println("Altura da árvore: " + tree.getHeight());

    }
}
